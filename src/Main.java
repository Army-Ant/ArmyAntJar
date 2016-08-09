import ArmyAnt.Algorithm.Pair;
import ArmyAnt.NeuronNetwork.FeedforwardNeuronNetwork;
import ArmyAnt.NeuronNetwork.Neuron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Jason-Zhao-Jie on 2016/7/8.
 */
public class Main {
    public static void main(String[] args) {
        neuronNetwork_Test();
    }

    private static void neuronNetwork_Test(){
        System.out.println("Welcome to the ArmyAnt.NeuronNetwork-Test!");
        System.out.println("Now we will test as an numeric inputer.");
        System.out.println("The numeric inputer has 10x10 pixes as LED numeric board");
        System.out.println("The robot neuron network will learn to recognise those numbers");
        System.out.println("Now, the neuron network is initializing ......");
        Map[] data = new Map[]{new HashMap<String, Userdata>(), new HashMap<String, Userdata>(), new HashMap<String, Userdata>()};
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                data[0].put("0" + i + j, new Userdata(i, j));
        for (int i = 0; i < 22; i++)
            data[1].put("1" + (i + 10), new Userdata(-1, i));
        for (int i = 0; i < 5; i++)
            data[2].put("20" + i, new Userdata(-2, i));

        FeedforwardNeuronNetwork<String, Userdata> network = new FeedforwardNeuronNetwork<String, Userdata>(data, Neuron.getDoubleSigmoidFunc(1), new Pair<Double, Double>(-1.0, 1.0), 0.1, 0.1);
        System.out.println("Initialized OK !");
        System.out.print("Robot learning ... times :        0 / 65536");

        Map<String, Double> inputingMap = new HashMap<String, Double>();
        ArrayList<String[]> nummap = new ArrayList<String[]>();
        nummap.add(new String[]{"004", "005", "006", "013", "016", "022", "027", "032", "037", "042", "047", "052", "057", "062", "067", "072", "077", "082", "086", "093", "094"});
        nummap.add(new String[]{"005", "015", "025", "034", "035", "044", "054", "064", "074", "083", "093"});
        nummap.add(new String[]{"005", "006", "012", "013", "015", "016", "021", "025", "036", "045", "053", "054", "062", "063", "071", "072", "081", "082", "083", "084", "085", "086", "087"});
        nummap.add(new String[]{"005", "006", "012", "013", "015", "016", "021", "025", "035", "036", "042", "043", "044", "045", "046", "057", "066", "067", "075", "076", "082", "083", "084", "085"});
        nummap.add(new String[]{"003", "006", "012", "013", "016", "022", "026", "032", "036", "041", "042", "045", "051", "052", "053", "054", "055", "056", "057", "058", "065", "075", "085", "095"});
        Function<Integer, Map<String, Double>> runFunc = (Integer num) -> {
            // inputing
            inputingMap.clear();
            int index = 0;
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    if (nummap.get(num)[index] == "0" + x + y)
                        inputingMap.put("0" + x + y, 1.0);
                    else
                        inputingMap.put("0" + x + y, 0.0);
                }
            }
            return network.input(inputingMap);
        };
        for (int i = 65536; i >= 0; --i) {
            for (int n = 0; n < 5; n++) {
                runFunc.apply(n);
            }
            if (i % 82 == 0 || i == 0) {
                for (int n = 0; n < ((65536 - i) + "").length() + 8; n++)
                    System.out.print("\b");
                System.out.print(65536 - i + " / 65536");
            }
        }
        System.out.println("  ");
        System.out.println("The network has learned 65536 times !");
        System.out.println("Now checking the result !");
        Function<Map<String, Double>, String> calcuFunc = (Map<String, Double> map) -> {
            Object[] keys = map.keySet().toArray();
            Double res = map.get(keys[0]);
            String ret = keys[0].toString();
            for (int i = 1; i < keys.length; i++) {
                if (map.get(keys[i]) > res) {
                    res = map.get(keys[i]);
                    ret = keys[i].toString();
                }
                System.out.println("Result in " + keys[i] + " has value " + map.get(keys[i]));
            }
            return ret;
        };
        String[] res = new String[]{calcuFunc.apply(runFunc.apply(0)), calcuFunc.apply(runFunc.apply(1)), calcuFunc.apply(runFunc.apply(2)), calcuFunc.apply(runFunc.apply(3)), calcuFunc.apply(runFunc.apply(4)),};
        System.out.println("Result check over !");
        System.out.println("Let's have a test:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Testing " + i + ":");
            System.out.println(res[i] == calcuFunc.apply(runFunc.apply(i)) ? ("OK, Code: " + res[i]) : "Failed");
        }
        System.out.println("All tests over !");
    }
}

class Userdata {
    int x;
    int y;

    public Userdata(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
