package ArmyAnt.Algorithm;

public class Pair<T_First, T_Second> {
	public T_First first;
	public T_Second second;

	public Pair(T_First first, T_Second second)
	{
		this.first = first;
		this.second = second;
	}
	
	public Pair<T_Second, T_First> getSwappedPair()
	{
		return new Pair<T_Second, T_First>(second,first);
	}
	
	public boolean equalsValue(Pair<T_First, T_Second> value)
	{
		return first.equals(value.first) && second.equals(value.second);
	}
}
