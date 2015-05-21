package exercise.usefulinterface;

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
