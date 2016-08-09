package ArmyAnt.Algorithm;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jason-Zhao-Jie on 2016/5/21.
 */
public class Tree<T_Tag, T_Value> implements java.util.Map<T_Tag, T_Value>{
	private TripleMap<T_Tag,T_Value,Pair<T_Tag,java.util.ArrayList<T_Tag>>> data;
	
	public Tree(T_Tag root, T_Value rootValue)
	{
		data = new TripleMap<T_Tag,T_Value,Pair<T_Tag,java.util.ArrayList<T_Tag>>>();
		data.insert(root, rootValue, new Pair<T_Tag,java.util.ArrayList<T_Tag>>(null, new java.util.ArrayList<T_Tag>()));
	}
	
	public java.util.Set<T_Tag> keySet()
	{
		return data.keySet();
	}

	@Override
	public Collection<T_Value> values() {
		return null;
	}

	@Override
	public Set<Entry<T_Tag, T_Value>> entrySet() {
		return null;
	}

	public void clear() {
		data.clear();
	}

	public boolean containsKey(Object key) {
		return data.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return false;
	}

	public T_Value get(Object key) {
		return data.get(key).first;
	}

	@Override
	public T_Value put(T_Tag key, T_Value value) {
		return null;
	}

	@Override
	public T_Value remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends T_Tag, ? extends T_Value> m) {

	}

	public boolean set(T_Tag key, T_Value value){return data.setValue1(key,value);}

	@Override
	public int size() {
		return 0;
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public int dataLength() {
		return data.size();
	}
	
}
