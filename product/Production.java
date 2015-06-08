package exercise.product;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import exercise.customizegui.ErrorDialog;
import exercise.resourcepath.ResourceFilePath;
import exercise.usefulinterface.Countable;

/**
 * 所有商品的父类
 * @author STU_nwad
 *
 */
public abstract class Production implements Countable,Comparable<Production>,Comparator<Production> {
	protected String name; //名称
	protected String description; //描述
	protected double cost; //价格
	protected String specificInfo; //具体信息 如 加糖 的咖啡
	protected String directory = getClass().getSimpleName(); //保存信息的文件夹
	protected String customerMSG = ""; //客人的特殊要求
	
	protected long count = 0; //具体商品(不同调料算不同商品)在一笔订单中数量
	
	private static Map<String, Integer> counter = new HashMap<String, Integer>(); //用于记录销量 一开始的Integer会是zero
	
	protected int saleCount = 0; //某一具体商品的saleCount
	
	/*抽象方法*/
	
	/**
	 * 将所有销量记录到文件中
	 * 格式是 键值 对
	 */
	
	public static void readSaleInfoFromFile(){
		String path = ResourceFilePath.saleRecordDirectory + "/" + ResourceFilePath.SaleList;
		RandomAccessFile saleRecord = ResourceFilePath.openFile(path, "rw");
		if (saleRecord == null){
			ErrorDialog.showErrorMessage(null, "无法打开文件 " + path, "in Production");
			return ;
		}
		String name = null;
		Integer i = 0;
		try {
			for(;saleRecord.getFilePointer() != saleRecord.length();){
				name = saleRecord.readUTF();
				i = saleRecord.readInt();
				counter.put(name, i); //增加这个数据咯
			}
		} catch (IOException e) {
			ErrorDialog.showErrorMessage(null, path + " 文件损坏", "in Production");
			e.printStackTrace();
			return;
		}
	}
	
	public static void saveSaleInfoToFile(){
		if (counter.size() == 0)
			return;
		String path = ResourceFilePath.saleRecordDirectory + "/" + ResourceFilePath.SaleList;
		RandomAccessFile saleRecord = ResourceFilePath.openFile(path, "rw");
		if (saleRecord == null){
			ErrorDialog.showErrorMessage(null, "无法打开文件 " + path, "in Production");
			return ;
		}
		
		Set<String> keySet = counter.keySet();
		for(String key : keySet){ //迭代
			try{
				saleRecord.writeUTF(key);
				saleRecord.writeInt(counter.get(key));
			}catch(IOException e){
				e.printStackTrace();
				ErrorDialog.showErrorMessage(null, "储存销售记录时出错", key);
				break;
			}
		}
		try {
			saleRecord.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 返回商品的具体信息，如 咖啡(大杯) ice milk 价格
	 * @return
	 */
	public abstract String getSpecific(); //返回该商品的具体信息
	
	public boolean saveTofile(){ //保存信息到文件里面
		return ResourceFilePath.saveToFile(this);
	}
	/*抽象方法*/
	
/*有关counter的方法*/
	/**
	 * 记录商品的销售量
	 * @param name 商品的名称
	 * @param i    销售量
	 */
	public static void addSaleCount(String name,int i){
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
	public static void addSaleCount(String name){
		addSaleCount(name,1);
	}
	
	public void addSaleCount(int i){
		addSaleCount(name, i);
	}
	
	public void addSaleCount(){
		addSaleCount(name);
	}
	
	/**
	 * 设置名为name的商品的销量
	 * @param name
	 * @param i
	 */
	public void setSaleCount(String name,int i){
		if (i < 0)
			i = 0;
		if (counter.containsKey(name)){
			counter.put(name, i); //相当于覆盖之前的信息
		}
	}
	
	public static int getSaleCount(String name){
		if (counter.containsKey(name))
			return counter.get(name);
		return 0;
	}
	
	public int getSaleCount(){
		if (counter.containsKey(name))
			return counter.get(name);
		return 0;
	}
	/**
	 * 返回所有销售数据
	 * @return
	 */
	public static String getAllSaleInfo(){
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
		return name + " " + cost;
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
	
	/**
	 * 当商品的 Specific 信息相同时 才认为两者相等。
	 * 包括价格，是否有配料，等等
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == this)
			return true;
		if (obj instanceof Production){
			if (((Production) obj).getSpecific().equals(getSpecific())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Production item){
		if ( getSaleCount() < item.getSaleCount() )
			return 1;
		else if (getSaleCount() > item.getSaleCount())
			return -1;
		else
			return 0;
	}
	
	@Override //注意 升序 还是 降序 哦 哈哈
	public int compare(Production o1, Production o2) {
		if (o1.getSaleCount() > o2.getSaleCount())
			return -1;
		else if (o1.getSaleCount() < o2.getSaleCount())
			return 1;
		else 
			return 0;
	}
}
