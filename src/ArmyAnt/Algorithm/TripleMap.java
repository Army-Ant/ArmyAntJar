package ArmyAnt.Algorithm;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TripleMap<T_Key, T_Value1, T_Value2> implements java.util.Map<T_Key, Pair<T_Value1,T_Value2>>{
	private java.util.HashMap<T_Key, Pair<T_Value1, T_Value2>> data = new java.util.HashMap<T_Key, Pair<T_Value1, T_Value2>>();
	
	public TripleMap(){}
	
	public boolean Empty()
	{
		return isEmpty();
	}
	
	public boolean HasKey(T_Key key)
	{
		return containsKey(key);
	}
	
	public Pair<T_Value1, T_Value2> GetValues(T_Key key)
	{
		return get(key);
	}
	
	public boolean SetValues(T_Key key, T_Value1 value1, T_Value2 value2)
	{
		if(!data.containsKey(key))
			return false;
		data.replace(key,  new Pair<T_Value1, T_Value2>(value1,value2));
		return true;
	}
	
	public boolean SetValue1(T_Key key, T_Value1 value)
	{
		return SetValues(key,value,data.get(key).second);
	}
	
	public boolean SetValue2(T_Key key, T_Value2 value)
	{
		return SetValues(key,data.get(key).first,value);
	}
	
	public long Size()
	{
		return size();
	}
	
	public boolean Insert(T_Key key, Pair<T_Value1, T_Value2> value)
	{
		if(data.containsKey(key))
			return false;
		put(key, value);
		return true;
	}
	
	public boolean Insert(T_Key key, T_Value1 value1, T_Value2 value2)
	{
		return Insert(key, new Pair<T_Value1, T_Value2>(value1,value2));
	}
	
	public boolean Remove(T_Key key)
	{
		if(!data.containsKey(key))
			return false;
		remove(key);
		return true;
	}
	
	public void Clear()
	{
		clear();
	}
	
	public java.util.Set<T_Key> GetKeySet()
	{
		return keySet();
	}
	
	public java.util.HashMap<T_Key,T_Value1> GetNewMapOfValue1()
	{
		java.util.HashMap<T_Key,T_Value1> ret = new java.util.HashMap<T_Key,T_Value1>();
		T_Key[] keys = null;
		keys = data.keySet().toArray(keys);
		for(int i=0;i<keys.length;i++)
		{
			ret.put(keys[i], data.get(keys[i]).first);
		}
		return ret;
	}
	
	public java.util.HashMap<T_Key,T_Value2> GetNewMapOfValue2()
	{
		java.util.HashMap<T_Key,T_Value2> ret = new java.util.HashMap<T_Key,T_Value2>();
		T_Key[] keys = null;
		keys = data.keySet().toArray(keys);
		for(int i=0;i<keys.length;i++)
		{
			ret.put(keys[i], data.get(keys[i]).second);
		}
		return ret;
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return data.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return data.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<T_Key, Pair<T_Value1, T_Value2>>> entrySet() {
		return data.entrySet();
	}

	@Override
	public Pair<T_Value1, T_Value2> get(Object key) {
		return data.get(key);
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public Set<T_Key> keySet() {
		return data.keySet();
	}

	@Override
	public Pair<T_Value1, T_Value2> put(T_Key key, Pair<T_Value1, T_Value2> values) {
		return data.put(key, values);
	}

	@Override
	public void putAll(Map<? extends T_Key, ? extends Pair<T_Value1, T_Value2>> valueMap) {
		data.putAll(valueMap);
	}

	@Override
	public Pair<T_Value1, T_Value2> remove(Object key) {
		return data.remove(key);
	}

	@Override
	public int size() {
		return data.size();
	}

	@Override
	public Collection<Pair<T_Value1, T_Value2>> values() {
		return data.values();
	}
}
