package xknr.euler.util;

public interface ISeq<T> 
{
	/**
	 * Can adv be called once more?
	 * @return
	 */
	boolean has();
	
	/**
	 * Find and return the next item in the sequence. 
	 * @return
	 */
	T adv();
	
	/**
	 * Returns the last found item.
	 * adv must be called at least once before this.
	 * @return
	 */
	T curr();
}
