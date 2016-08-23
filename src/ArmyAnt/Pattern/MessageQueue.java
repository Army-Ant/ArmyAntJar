package ArmyAnt.Pattern;

import java.util.function.Function;

import ArmyAnt.Algorithm.Pair;
import ArmyAnt.Algorithm.TripleMap;
import ArmyAnt.Utils.ProtectedValue;


/**
 * Created by Administrator on 2016/8/24.
 */
public class MessageQueue<T_Data> {
    private TripleMap<Long, Function<T_Data, Void>, T_Data> queue;
    private long first = 0;
    private long last = -1;
    private Thread runningThread = new Thread(() -> {
        threadMain();
    });
    private ProtectedValue<Boolean> isRunning = new ProtectedValue<Boolean>(false);
    private boolean isEnd = false;

    private void threadMain() {
        try {
            while (!isEnd) {
                if (!isRunning.get() || first > last)
                    Thread.sleep(1);
                else {
                    Pair<Function<T_Data, Void>, T_Data> data = queue.get(first);
                    data.first.apply(data.second);
                    queue.remove(first++);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MessageQueue() {
        runningThread.run();
    }

    @Override
    protected void finalize() throws Throwable {
        isEnd = true;
        runningThread.join();
        super.finalize();
    }

    public void push( Function<T_Data, Void> callback, T_Data data){
        queue.insert(++last,callback,data);
    }

    public boolean start(){
        if(isRunning.get())
            return false;
        isRunning.set(true);
        return true;
    }

    public boolean stop(){
        isRunning.set(false);
        return true;
    }

    public void clear(){
        queue.clear();
    }
}