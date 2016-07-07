package ArmyAnt.Algorithm;

/**
 * Created by Jason-Zhao-Jie on 2016/5/21.
 */
public class Tree<T_Tag, T_Value>{
	private TripleMap<T_Tag,T_Value,Pair<T_Tag,java.util.ArrayList<T_Tag>>> data;
	
	public Tree(T_Tag root, T_Value rootValue)
	{
		data = new TripleMap<T_Tag,T_Value,Pair<T_Tag,java.util.ArrayList<T_Tag>>>();
		data.Insert(root, rootValue, new Pair<T_Tag,java.util.ArrayList<T_Tag>>(null, new java.util.ArrayList<T_Tag>()));
	}
	
	public java.util.Set<T_Tag> KeySet()
	{
		return data.GetKeySet();
	}

	public void Clear() {
		data.clear();
	}

	public boolean HasKey(T_Tag key) {
		return data.containsKey(key);
	}

	public T_Value GetValue(T_Tag key) {
		return data.get(key).first;
	}

	public boolean IsEmpty() {
		return data.isEmpty();
	}

	public int DataLength() {
		return data.size();
	}
	
}
