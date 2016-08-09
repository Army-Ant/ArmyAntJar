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
	
	public boolean hasNode(T_Tag tag)
	{
		return nodes.containsKey(tag);
	}
	
	public T_Value get(T_Tag tag)
	{
		return nodes.get(tag).first;
	}
	
	public boolean set(T_Tag tag, T_Value value)
	{
		return nodes.setValue1(tag, value);
	}
	
	public T_Weight getWeight(T_Tag tag)
	{
		return nodes.get(tag).second;
	}
	
	public boolean setWeight(T_Tag tag, T_Weight weight)
	{
		return nodes.setValue2(tag, weight);
	}
	
	public boolean put(T_Value value, T_Tag tag, T_Weight weight)
	{
		if(! nodes.insert(tag, value, weight))
			return false;
		lines.put(tag,new HashMap<T_Tag,T_Weight>());
		return true;
	}
	
	public boolean remove(T_Tag tag)
	{
		lines.remove(tag);
		nodes.remove(tag);
		return true;
	}
	
	public boolean linkNode(T_Tag from, T_Tag to, T_Weight weight)
	{
		lines.get(from).put(to, weight);
		return true;
	}
	
	public boolean unlinkNode(T_Tag from, T_Tag to)
	{
		HashMap<T_Tag,T_Weight> map = lines.get(from);
		if(!map.containsKey(to))
			return false;
		map.remove(to);
		return true;
	}
	
	public boolean isLinked(T_Tag from, T_Tag to)
	{
		return lines.get(from).containsKey(to);
	}

	public T_Weight getLinkWeight(T_Tag from, T_Tag to)
	{
		return lines.get(from).get(to);
	}

	public T_Weight setLinkWeight(T_Tag from, T_Tag to, T_Weight value)
	{
		return lines.get(from).replace(to, value);
	}
	
	public boolean nodeUnlinkAllOut(T_Tag tag)
	{
		if(!lines.containsKey(tag))
			return false;
		lines.get(tag).clear();
		return true;
	}
	
	public boolean nodeUnlinkAllIn(T_Tag tag)
	{
		if(!lines.containsKey(tag))
			return false;
		Object[] keys = lines.keySet().toArray();
		for(int i=0;i<keys.length;i++)
		{
			if(lines.get(keys[i]).containsKey(tag))
				lines.get(keys[i]).remove(tag);
		}
		return true;
	}
	
	public boolean nodeUnlinkAll(T_Tag tag)
	{
		return nodeUnlinkAllOut(tag) && nodeUnlinkAllIn(tag);
	}
	
	public void unlinkAll()
	{
		Object[] keys = lines.keySet().toArray();
		for(int i=0;i<keys.length;i++)
		{
			lines.get(keys[i]).clear();
		}
		
	}
	
	public void clear()
	{
		nodes.clear();
		lines.clear();
	}
	
	@SuppressWarnings("unchecked")
	public T_Tag[] getNodeAllLinkedIn(T_Tag tag)
	{
		if(!lines.containsKey(tag))
			return null;
		java.util.ArrayList<T_Tag> ret = new java.util.ArrayList<T_Tag>();
		T_Tag[] keys = (T_Tag[]) lines.keySet().toArray();
		for(int i=0;i<keys.length;i++)
		{
			if(lines.get(keys[i]).containsKey(tag))
				ret.add(keys[i]);
		}
		return (T_Tag[]) ret.toArray();
		
	}
	
	@SuppressWarnings("unchecked")
	public T_Tag[] getNodeAllLinkedOut(T_Tag tag)
	{
		if(!lines.containsKey(tag))
			return null;
		return (T_Tag[]) lines.get(tag).keySet().toArray();
	}
}
