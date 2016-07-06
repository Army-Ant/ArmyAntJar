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
	
	public boolean HasNode(T_Tag tag)
	{
		return nodes.HasKey(tag);
	}
	
	public T_Value GetNodeValue(T_Tag tag)
	{
		return nodes.GetValues(tag).first;
	}
	
	public boolean SetNodeValue(T_Tag tag, T_Value value)
	{
		return nodes.SetValue1(tag, value);
	}
	
	public T_Weight GetNodeWeight(T_Tag tag)
	{
		return nodes.GetValues(tag).second;
	}
	
	public boolean SetNodeWeight(T_Tag tag, T_Weight weight)
	{
		return nodes.SetValue2(tag, weight);
	}
	
	public boolean AddNode(T_Value value, T_Tag tag, T_Weight weight)
	{
		if(! nodes.Insert(tag, value, weight))
			return false;
		lines.put(tag,new HashMap<T_Tag,T_Weight>());
		return true;
	}
	
	public boolean RemoveNode(T_Tag tag)
	{
		lines.remove(tag);
		return nodes.Remove(tag);
	}
	
	public boolean LinkNode(T_Tag from, T_Tag to, T_Weight weight)
	{
		lines.get(from).put(to, weight);
		return true;
	}
	
	public boolean DelinkNode(T_Tag from, T_Tag to)
	{
		HashMap<T_Tag,T_Weight> map = lines.get(from);
		if(!map.containsKey(to))
			return false;
		map.remove(to);
		return true;
	}
	
	public boolean IsLinked(T_Tag from, T_Tag to)
	{
		return lines.get(from).containsKey(to);
	}
	
	public T_Weight GetLinkWeight(T_Tag from, T_Tag to)
	{
		return lines.get(from).get(to);
	}
	
	public boolean NodeDelinkAllOut(T_Tag tag)
	{
		if(!lines.containsKey(tag))
			return false;
		lines.get(tag).clear();
		return true;
	}
	
	public boolean NodeDelinkAllIn(T_Tag tag)
	{
		if(!lines.containsKey(tag))
			return false;
		T_Tag[] keys = null;
		keys = lines.keySet().toArray(keys);
		for(int i=0;i<keys.length;i++)
		{
			if(lines.get(keys[i]).containsKey(tag))
				lines.get(keys[i]).remove(tag);
		}
		return true;
	}
	
	public boolean NodeDelinkAll(T_Tag tag)
	{
		return NodeDelinkAllOut(tag) && NodeDelinkAllIn(tag);
	}
	
	public void DelinkAll()
	{
		T_Tag[] keys = null;
		keys = lines.keySet().toArray(keys);
		for(int i=0;i<keys.length;i++)
		{
			lines.get(keys[i]).clear();
		}
		
	}
	
	public void Clear()
	{
		nodes.Clear();
		lines.clear();
	}
	
	public T_Tag[] GetNodeAllLinkedOut(T_Tag tag)
	{
		if(!lines.containsKey(tag))
			return null;
		java.util.ArrayList<T_Tag> ret = new java.util.ArrayList<T_Tag>();
		T_Tag[] keys = null;
		keys = lines.keySet().toArray(keys);
		for(int i=0;i<keys.length;i++)
		{
			if(lines.get(keys[i]).containsKey(tag))
				ret.add(keys[i]);
		}
		return ret.toArray(keys);
		
	}
	
	public T_Tag[] GetNodeAllLinkedIn(T_Tag tag)
	{
		if(!lines.containsKey(tag))
			return null;
		T_Tag[] ret = null;
		return lines.get(tag).keySet().toArray(ret);
	}
}
