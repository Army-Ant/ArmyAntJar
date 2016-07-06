package ArmyAnt.Algorithm;

import java.util.HashMap;

/**
 * Created by Jason-Zhao-Jie on 2016/5/21.
 */
public class Digraph<T_Tag, T_Value, T_Weight> {
	private TripleMap<T_Tag,T_Value,T_Weight> nodes = new TripleMap<T_Tag,T_Value,T_Weight>();
	private HashMap<T_Tag,HashMap<T_Tag,T_Weight>> lines = new HashMap<T_Tag,HashMap<T_Tag,T_Weight>>();
	
	public Digraph()
	{
	}
	
	public boolean AddNode(T_Value value, T_Tag tag, T_Weight weight)
	{
		return nodes.Insert(tag, value, weight);
	}
}
