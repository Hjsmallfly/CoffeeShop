package exercise.usefulinterface;

/**
 * 如果商品实现了这个接口，则说明该商品是有大小这个属性的。
 * @author STU_nwad
 *
 */
public interface HasSize {
	//用常量定义大小
	public static final int LARGE = 0;
	public static final int MIDDLE = 1;
	public static final int SMALL = 2;
	public static final String[] sizeStr = new String[]{"L","M","S"};
	
	public void setSize(int size);
	
	/**
	 * 有size的产品的order方法
	 * @param size
	 * @return
	 */
//	public Production order(int size);
	
	public int getSize();
	
}
