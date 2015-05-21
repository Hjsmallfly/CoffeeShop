package exercise.product;

import java.util.HashMap;
import java.util.Map;

import exercise.resourcepath.ResourceFilePath;
import exercise.usefulinterface.Countable;

/**
 * 所有商品的父类
 * @author STU_nwad
 *
 */
public abstract class Production implements Countable {
	protected String name; //名称
	protected String description; //描述
	protected double cost; //价格
	protected String specificInfo; //具体信息 如 加糖 的咖啡
	protected String directory = getClass().getSimpleName(); //保存信息的文件夹
	protected String customerMSG = ""; //客人的特殊要求
	
	protected long count = 0; //具体商品(不同调料算不同商品)在一笔订单中数量
	
	private static Map<String, Integer> counter = new HashMap<String, Integer>(); //用于记录销量 一开始的Integer会是zero
	
	protected int saleCount = 0;
	
	/*抽象方法*/
	
	/**
	 * 返回商品的具体信息，如 咖啡(大杯) ice milk 价格
	 * @return
	 */
	public abstract String getSpecific(); //返回该商品的具体信息
	
	public boolean saveTofile(){ //保存信息到文件里面
		return ResourceFilePath.saveToFile(this);
	}
//	public abstract boolean readFromfile(); //从文件中读取信息
	
	/**
	 * 这是没有size的order方法
	 * @return 返回实例化的对象
	 */
//	public abstract Production order();
	
	
	/*抽象方法*/
	
/*有关counter的方法*/
	/**
	 * 记录商品的销售量
	 * @param name 商品的名称
	 * @param i    销售量
	 */
	public void addSaleCount(String name,int i){
		if(counter.containsKey(name)){
			counter.put(name, counter.get(name) + i); //更新数据
		}else {
			counter.put(name, i);//新建一项
		}
	}
	
	/**
	 * 销量+1
	 * @param name 商品的名称
	 */
	public void addSaleCount(String name){
		addSaleCount(name,1);
	}
	
	public int getSaleCount(String name){
		if (counter.containsKey(name))
			return counter.get(name);
		return 0;
	}
	/**
	 * 返回所有销售数据
	 * @return
	 */
	public String getAllSaleInfo(){
		return counter.toString();
	}
	

	
	
/*有关counter的方法*/
	
	/* get方法 */
	
	/**
	 * 返回具体商品在一笔订单中的数量
	 */
	@Override
	public long getCount(){
		return count;
	}
	
	public String getCustomerMSG(){
		return customerMSG;
	}
	
	/**
	 * 返回商品的躶价
	 * @return
	 */
	public  double getCost(){ //具体类应该覆盖掉
		return cost;
	}
	
	/**
	 * 统计商品的价格，包括所加的调料
	 * @return
	 */
	public double figureCost(){
		return cost;
	}
	
	public String getName(){
		return name;
	}
	
	public String description(){
		return description;
	}
	
	public String getDirectory(){
		return directory;
	}
	
	@Override
	public String toString(){
		return name + " " +  getCost();
	}
	
	/* get方法 */
	
	/* set方法集 */
	
	/**
	 * 设置具体商品在一笔订单中的具体数量
	 */
	@Override
	public void setCount(long i){
		count = i;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setCustomerMSG(String msg){
		this.customerMSG = msg;
	}
	
	public void setDescription(String desc){
		description = desc;
	}
	
	public void setCost(double cost){
		this.cost = cost;
	}
	
	@Override
	public void addCount(long n){
		count += n;
	}
	
	@Override
	public void addCount(){
		++count;
	}
	/* set方法集 */
}
