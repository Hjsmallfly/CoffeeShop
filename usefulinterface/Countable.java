package exercise.usefulinterface;

/**
 * 可以计数的 接口
 * @author STU_nwad
 *
 */
public interface Countable {
	/**
	 * 获得计数
	 * @return
	 */
	public long getCount();
	
	public void setCount(long i);
	
	public void addCount(long n);
	
	public void addCount();
	
}
