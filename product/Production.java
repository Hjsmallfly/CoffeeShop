package exercise.product;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有商品的父类
 * @author STU_nwad
 *
 */
public abstract class Production {
	protected String name; //名称
	protected String description; //描述
	protected double cost; //价格
	protected String specificInfo; //具体信息 如 加糖 的咖啡
	protected String directory = getClass().getSimpleName(); //保存信息的文件夹
	
	private static Map<String, Integer> counter = new HashMap<String, Integer>(); //用于记录销量 
	
	protected int saleCount = 0;
	
	/*抽象方法*/
	
	/**
	 * 返回商品的具体信息，如 咖啡(大杯) ice milk 价格
	 * @return
	 */
	public abstract String getSpecific(); //返回该商品的具体信息
	
//	public abstract boolean saveTofile(); //保存信息到文件里面
	
//	public abstract boolean readFromfile(); //从文件中读取信息
	
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
	
	public  double getCost(){ //具体类应该覆盖掉
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
	
	/* get方法 */
	
	/* set方法集 */
	
	/**
	 * 为产品设置好销量计数器
	 * @param p
	 */

	public void setName(String name){
		this.name = name;
	}
	
	public void setDescription(String desc){
		description = desc;
	}
	
	public void setCost(double cost){
		this.cost = cost;
	}
	/* set方法集 */
}
