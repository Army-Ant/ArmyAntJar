package ArmyAnt.Algorithm;

public class Triad<T_First, T_Second, T_Third>{
	public T_First first;
	public T_Second second;
	public T_Third third;
	
	public Triad(T_First first, T_Second second, T_Third third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public boolean equalsValue(Triad<T_First, T_Second, T_Third> value)
	{
		return first.equals(value.first) && second.equals(value.second) && third.equals(value.third);
	}

	//交换内容12
	public Triad<T_Second, T_First, T_Third> swap12()
	{
		return new Triad<T_Second, T_First, T_Third>(second,first,third);
	}
	//交换内容13
	public Triad<T_Third, T_Second, T_First> swap13()
	{
		return new Triad<T_Third, T_Second, T_First>(third,second,first);		
	}
	//交换内容23
	public Triad<T_First, T_Third, T_Second> swap23()
	{
		return new Triad<T_First, T_Third, T_Second>(first,third,second);		
	}
	//获取二元组12
	public Pair<T_First, T_Second> getValue12()
	{
		return new Pair<T_First, T_Second>(first,second);
	}
	//获取二元组13
	public Pair<T_First, T_Third> getValue13()
	{
		return new Pair<T_First, T_Third>(first,third);		
	}
	//获取二元组23
	public Pair<T_Second, T_Third> getValue23()
	{
		return new Pair<T_Second, T_Third>(second,third);		
	}
	
}