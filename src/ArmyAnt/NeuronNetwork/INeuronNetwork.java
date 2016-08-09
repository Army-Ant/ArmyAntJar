package ArmyAnt.NeuronNetwork;

import java.util.Map;

/**
 * Created by admin on 2016/7/8.
 */
public interface INeuronNetwork<T_Tag, T_Data> {

    /**
     * @param <T_UserData>
     * @author Jason-Zhao-Jie
     */
    class NeuronNetworkData<T_UserData> {
        public int belongedFloor;
        public Neuron neuron;

        public T_UserData data;

        public NeuronNetworkData(int belongedFloor, Neuron neuron, T_UserData data) {
            this.belongedFloor = belongedFloor;
            this.neuron = neuron;
            this.data = data;
        }
    }

    public enum Type {
        Unknown,
        Feedforward,
        Feedback,
        SelfOrganizing

    }

    public java.util.Map<T_Tag, Double> input(java.util.Map<T_Tag, Double> input);
    public java.util.Map<T_Tag, Double> test(Map<T_Tag, Double> input);
    public boolean train(java.util.Map<T_Tag, Double> input, java.util.Map<T_Tag, Double> rightOutput);

}
