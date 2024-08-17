package xknr.euler.util;

import java.util.List;
import java.util.NoSuchElementException;

public class SeqList<T> implements ISeq<T> 
{
	public SeqList(List<T> li) 
	{
		this.li = li;
		this.ind = -1;
	}

	@Override
	public boolean has() 
	{
		return ind < li.size() - 1;
	}

	@Override
	public T adv() 
	{
		if (ind >= li.size() - 1)
		{
			throw new NoSuchElementException();
		}
		
		return li.get(++ind);
	}

	@Override
	public T curr() 
	{
		if (ind == -1)
		{
			throw new NoSuchElementException();
		}
		
		return li.get(ind);
	}

	private List<T> li;
	private int ind;
}
