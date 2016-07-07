package ArmyAnt.NeuronNetwork;

import java.util.function.Function;

import ArmyAnt.Algorithm.Digraph;

/**
 * Created by Jason-Zhao-Jie on 2016/5/21.
 */
public class NeuronNetwork<T_Tag,T_Data> {
	public enum Type
	{
		Unknown,
		SimpleNetwork,
		Feedforward,
		Feedback,
		SelfOrganizing
		
	}
	
    private Digraph<T_Tag,T_Data,Double> neuronGraph = new Digraph<T_Tag,T_Data,Double>();

    public NeuronNetwork(Type type, java.util.Map<T_Tag, T_Data[]> neurons, Function<Double, Double> activeFunc)
    {
    	
    }
}

class NeuronNetworkData<T_UserData>{
	public Neuron neuron;
}
