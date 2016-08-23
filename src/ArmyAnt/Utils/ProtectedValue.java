package ArmyAnt.Utils;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ProtectedValue<T> {
    private T data;
    private boolean isGettingSetting = false;
    private boolean _lock = false;

    public ProtectedValue(T value){
        data = value;
    }

    public T get() {
        try {
            while (isGettingSetting || _lock)
                Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }

    public T set(T value) {
        try {
            while (isGettingSetting || _lock)
                Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isGettingSetting = true;
        data = value;
        isGettingSetting = false;
        return data;
    }

    public boolean isLocking(){
        return isGettingSetting || _lock;
    }

    public void lock(){
        _lock = true;
    }

    public boolean unlock(){
        if(isGettingSetting)
            return false;
        _lock = false;
        return true;
    }
}
