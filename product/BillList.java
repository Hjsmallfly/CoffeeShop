package exercise.product;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import exercise.customizegui.ErrorDialog;
import exercise.factory.BeverageFactory;
import exercise.resourcepath.ResourceFilePath;

/**
 * 存放交易记录
 * @author STU_nwad
 *
 */
public class BillList {
	
	private Map<String, Production> billList = new HashMap<String, Production>(); //用于维护账单数据
//	private Map<String,Integer> itemCount = new HashMap<String,Integer>(); //用于统计个数 
	
	/**
	 * 将账单里面的所有商品信息转换成字符串
	 * @return
	 */
	public String getAllToString(){
		StringBuilder sb = new StringBuilder();
		Set<String> keySet = billList.keySet();
		Iterator<String> it = keySet.iterator(); //产生迭代器
		while (it.hasNext()){
			String next = it.next();
			sb.append(billList.get(next).getSpecific() + " "  + billList.get(next).getCount() + " x $" + billList.get(next).figureCost() + "\n");
		}
		return sb.toString();
	}
	
	/**
	 * 为特定商品设置数量
	 * @param count
	 */
	public void setCount(Production p ,int count){
		if (billList.containsKey(p.getSpecific())){
			billList.get(p.getSpecific()).setCount(count);
		}
	}
	
	/**
	 * 会设置相应的count
	 * @param p
	 */
	public void add(Production p){
		if (p == null)
			return ;
		String feature = p.getSpecific();
		if (billList.containsKey(feature)){ //如果已经存在这个商品的话
			billList.get(feature).addCount(); //递增1
		}else {
			p.setCount(1);
			billList.put(feature, p); //这样确保可以十分细致的统计
		}
		
	}
	
	public boolean Exist(Production p){
		return billList.containsKey(p.getSpecific());
	}
	
	public ArrayList<Production> getProductions(){
		if (billList.isEmpty())
			return null;
		ArrayList<Production> all = new ArrayList<Production>();
		Set<String> keySet = billList.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()){
			String index = it.next();
			all.add(billList.get(index));
		}
		return all;
	}
	
	public boolean isEmpty(){
		return billList.isEmpty();
	}
	
	public int size(){
		return  billList.size();

	}
	
	/**
	 * 得到账单的总价格
	 * @return
	 */
	public double getBillCost(){
		if (billList.size() == 0)
			return 0.0;
		double sum = 0.0;
		for(Production p : getProductions()){
			sum += p.figureCost() * p.getCount(); //这里会莫名多出一些小数位 记得处理一下
		}
		return sum;
	}
	
	
	/**
	 * 保存形式 具体信息 
	 * @return
	 */
	public boolean saveToFile(){
		long time = System.currentTimeMillis();
		RandomAccessFile record = ResourceFilePath.openFile(ResourceFilePath.recordDirectory + "/order-" + time , "rw"); //打开文件
		if (record == null)
			return false;
		try{
			record.writeInt(getProductions().size()); //记录一共有多少数据
			for(Production production : getProductions()){
//				record.writeUTF(production.getName());
				record.writeUTF(production.getSpecific());
				record.writeDouble(production.figureCost());
				record.writeUTF(production.getCustomerMSG());
				//记录下单时间。
			}
		}catch(IOException e){
			ErrorDialog.showErrorMessage(null, "保存订单信息时出错","");
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		BillList list = new BillList();
		list.add(BeverageFactory.createBeverage("Espresso", 0).addIngredient(new Sugar()));
		list.add(BeverageFactory.createBeverage("Espresso", 0).addIngredient(new Sugar()));
		list.add(BeverageFactory.createBeverage("Espresso", 0).addIngredient(new Sugar()));
		list.add(BeverageFactory.createBeverage("SmallFlyCoffee10", 0).addIngredient(new Ice()));
		System.out.println(list.getAllToString());
		System.out.println(list.getProductions().toString());
	}
	
}
