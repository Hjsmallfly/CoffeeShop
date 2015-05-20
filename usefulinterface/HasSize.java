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
	public static final String[] sizeStr = new String[]{"大","中","小"};
	
	public void setSize(int size);
	
	public int getSize();
	
}
