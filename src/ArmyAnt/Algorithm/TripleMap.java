package ArmyAnt.Algorithm;

public class TripleMap<T_Key, T_Value1, T_Value2> {
	private java.util.HashMap<T_Key, Pair<T_Value1, T_Value2>> data = new java.util.HashMap<T_Key, Pair<T_Value1, T_Value2>>();
	
	public TripleMap(){}
	
	public boolean Empty()
	{
		return data.isEmpty();
	}
	
	public Pair<T_Value1, T_Value2> GetValues(T_Key key)
	{
		return data.get(key);
	}
	
	public long Size()
	{
		return data.size();
	}
	
	public boolean Insert(T_Key key, Pair<T_Value1, T_Value2> value)
	{
		if(data.containsKey(key))
			return false;
		data.put(key, value);
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
		data.remove(key);
		return true;
	}
	
	public void Clear()
	{
		data.clear();
	}
	
	public java.util.Set<T_Key> GetKeySet()
	{
		return data.keySet();
	}
}
