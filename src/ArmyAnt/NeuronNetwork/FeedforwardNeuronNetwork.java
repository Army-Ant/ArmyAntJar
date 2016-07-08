package ArmyAnt.NeuronNetwork;

import java.util.HashMap;
import java.util.function.Function;

import ArmyAnt.Algorithm.Digraph;
import ArmyAnt.Algorithm.Pair;

/**
 * Created by Jason-Zhao-Jie on 2016/5/21.
 */

/**
 * @param <T_Tag>
 * @param <T_Data>
 * @author Jason-Zhao-Jie
 */
public class FeedforwardNeuronNetwork<T_Tag, T_Data> implements INeuronNetwork<T_Tag, T_Data> {

    private Digraph<T_Tag, NeuronNetworkData<T_Data>, Double> neuronGraph = new Digraph<T_Tag, NeuronNetworkData<T_Data>, Double>();
    private HashMap<Integer, T_Tag[]> floors = new HashMap<Integer, T_Tag[]>();
    private Function<Double, Double> commonActiveFunc;
    private double lineBalancedParam = 0.1;
    private double nodeBalancedParam = 0.1;

    @SuppressWarnings("unchecked")
    public FeedforwardNeuronNetwork(java.util.Map<T_Tag, T_Data>[] neurons, Function<Double, Double> activeFunc, Pair<Double, Double> weightRange, double lineBalancedParam, double nodeBalancedParam) {
        commonActiveFunc = activeFunc;
        this.lineBalancedParam = lineBalancedParam;
        this.nodeBalancedParam = nodeBalancedParam;
        double min = java.lang.Math.min(weightRange.first, weightRange.second);
        double max = java.lang.Math.max(weightRange.first, weightRange.second);
        T_Tag[] keys = null;
        T_Tag[] lastKeys = null;
        for (int floor = neurons.length - 1; floor >= 0; --floor) {
            lastKeys = keys;
            keys = (T_Tag[]) neurons[floor].keySet().toArray();
            floors.put(floor, keys);
            for (int i = 0; i < keys.length; i++) {
                neuronGraph.AddNode(new NeuronNetworkData<T_Data>(floor, new Neuron(commonActiveFunc), neurons[floor].get(keys[i])), keys[i], java.lang.Math.random() * (max - min) / 100 + min);
                if (lastKeys != null) {
                    for (int n = 0; n < lastKeys.length; n++)
                        neuronGraph.LinkNode(keys[i], lastKeys[n], java.lang.Math.random() * (max - min) / 100 + min);
                }
            }
        }
    }

    /**
     * 输入数据，获得输出，同时记录本次输入
     *
     * @param input
     * @return
     */
    public java.util.Map<T_Tag, Double> Input(java.util.Map<T_Tag, Double> input) {
        for (int i = 0; i < floors.size(); i++) {
            input = InputOneFloor(i, input, true);
        }
        ClearInputData();
        return input;
    }

    /**
     * 输入数据，测试输出，但不记录本次输入
     *
     * @param input
     * @return
     */
    public java.util.Map<T_Tag, Double> Test(java.util.Map<T_Tag, Double> input) {
        for (int i = 0; i < floors.size(); i++) {
            input = InputOneFloor(i, input, false);
        }
        ClearInputData();
        return input;
    }

    /**
     * 有导师学习，根据指定的正确结果进行记录调整
     *
     * @param input
     * @param rightOutput
     * @return
     */
    public boolean Train(java.util.Map<T_Tag, Double> input, java.util.Map<T_Tag, Double> rightOutput) {
        // TODO: filling this algorithm
        return false;
    }

    private java.util.Map<T_Tag, Double> InputOneFloor(int floor, java.util.Map<T_Tag, Double> input, boolean record) {
        java.util.Map<T_Tag, Double> mid = new java.util.HashMap<T_Tag, Double>();
        input.forEach((T_Tag tag, Double value) -> {
            neuronGraph.GetNodeValue(tag).neuron.JoinActive(value, 1);
            mid.put(tag, neuronGraph.GetNodeValue(tag).neuron.GetOutPut());
            if (!record)
                neuronGraph.GetNodeValue(tag).neuron.LeaveActive(value, 1);
        });
        if (floor == floors.size() - 1)
            return mid;
        java.util.Map<T_Tag, Double> output = new java.util.HashMap<T_Tag, Double>();
        T_Tag[] next = floors.get(floor + 1);
        for (int i = 0; i < next.length; i++) {
            java.util.ArrayList<Double> sendingValue = new java.util.ArrayList<Double>();
            final T_Tag now = next[i];
            mid.forEach((T_Tag tag, Double value) -> {
                sendingValue.add(value * neuronGraph.GetLinkWeight(tag, now));
            });
            double total = 0.0;
            for (int n = 0; i < sendingValue.size(); i++) {
                total += sendingValue.get(n);
            }
            output.put(now, total);
        }
        return output;
    }

    private void ClearInputData(){
        for(int i=0;i<floors.size() - 1;i++){
            T_Tag[] tags = floors.get(i);
            for(int j=0; j<tags.length; j++){
                T_Tag[] linked = neuronGraph.GetNodeAllLinkedOut(tags[j]);
                for(int k=0; k<linked.length; k++){
                    neuronGraph.SetLinkWeight(tags[j], linked[k], neuronGraph.GetLinkWeight(tags[j], linked[k]) * neuronGraph.GetNodeValue(tags[j]).neuron.GetOutPut() * lineBalancedParam);
                    neuronGraph.SetNodeWeight(linked[k], neuronGraph.GetNodeWeight(linked[k]) + neuronGraph.GetNodeValue(tags[j]).neuron.GetOutPut() * nodeBalancedParam);
                }
                neuronGraph.GetNodeValue(tags[j]).neuron.ClearActive();
            }
        }
    }
}
