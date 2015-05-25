package exercise.product;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataListener;

import exercise.TimeData;
import exercise.customizegui.ErrorDialog;
import exercise.customizegui.OrderListBox;
import exercise.factory.BeverageFactory;
import exercise.resourcepath.ResourceFilePath;

/**
 * 存放交易记录
 * @author STU_nwad
 *
 */
public class BillList {
	
	private Map<String, Production> billList = new HashMap<String, Production>(); //用于维护账单数据
	
	private ArrayList<OrderListBox> orderboxes = new ArrayList<OrderListBox>(); //用于管理加入到这里的 列表框
	
	
	private DefaultListModel<Production> productionListModel = new DefaultListModel<Production>(); //BillList 维护这个 
	
	/**
	 * 为listdatamodel提供一个接口
	 * @param listeners
	 */
	public void addListDataListener(ListDataListener... listeners){
		if (listeners.length != 0){
			for(ListDataListener listener : listeners)
				productionListModel.addListDataListener(listener);
		}
	}
	
	
	public void addOrderListBox(OrderListBox ... boxes){
		if (boxes.length != 0){
			for(int i = 0 ; i < boxes.length ; ++i){
				boxes[i].setNewListModel(productionListModel); //关联到这个数据模型上
				orderboxes.add(boxes[i]);
			}
		}
	}
	
	public void removeOrderListBox(OrderListBox ...boxes){
		if (boxes.length != 0){
			for(int i = 0 ; i < boxes.length ; ++i){
				orderboxes.remove(boxes[i]);
				boxes[i].setNewListModel(new DefaultListModel()); //不再跟随这个数据模型了
			}
		}
	}
	
	public void removeAllOrderListBox(){
		if (orderboxes.size() != 0){
			for(OrderListBox listBox : orderboxes)
				listBox.setNewListModel(new DefaultListModel());
		}
		orderboxes.clear();
	}
	
	
	
	
	/**
	 * 为特定商品设置数量
	 * @param count
	 */
	public void setCount(Production p ,int count){
		if (billList.containsKey(p.getSpecific())){{
			billList.get(p.getSpecific()).setCount(count);
			p.setSaleCount(p.getName(),count); //全部销量那里也要统计
		}
		}
	}
	
	/**
	 * 添加相应的产品到 账单上 自动维护数量
	 * @param p
	 */
	public void add(Production p){
		if (p == null)
			return ;
		String feature = p.getSpecific();
		if (billList.containsKey(feature)){ //如果已经存在这个商品的话
			billList.get(feature).addCount(); //递增1 
												//这里增加了1同时model那里的数据也会得到更新 
			if (orderboxes.size() != 0){
				for(OrderListBox listBox : orderboxes)
					listBox.update();
			}
			
		}else {
				p.setCount(1);
				billList.put(feature, p); //这样确保可以十分细致的统计
				productionListModel.addElement(p); //这个列表也增加这一项 注意这里添加的是引用
													//所以在 Map 上东这个东西,实际上 productionListModel 里面的内容也被修改了 因为指向同一个对象！
				if (billList.size() == 1){
					//加入第一个元素的时候,需要调整ListBox的单元格的大小
					if (orderboxes.size() != 0)
						for(OrderListBox listBox : orderboxes){
							listBox.updateCellSize();
						}
				}
		}
		
	}
	
	public void add(ArrayList<Production> productions){
		if (productions != null)
			for(Production p : productions)
				add(p);
	}
	
	public void remove(Production p){
		billList.remove(p.getSpecific(), p); //注意 removeValue 和这个方法有区别
		productionListModel.removeElement(p);
	}
	
	public void removeAll(){
		billList.clear();
		productionListModel.clear();
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
		ArrayList<Production> productions = getProductions();
		if (productions != null)
			for(Production p : productions){
				sum += p.figureCost() * p.getCount(); //这里会莫名多出一些小数位 记得处理一下
			}
		return sum;
	}
	
	
	/**
	 * 保存形式 具体信息 数量 售价 备注信息 
	 * @return
	 */
	public boolean saveToFile(){
		long time = System.currentTimeMillis();
		String currentTime = TimeData.getTimeString(new Date(time));
		RandomAccessFile record = ResourceFilePath.openFile(ResourceFilePath.billRecordDirectory + "/order-" + currentTime , "rw"); //打开文件
		if (record == null)
			return false;
		try{
			record.writeInt(getProductions().size()); //记录一共有多少数据
			record.writeDouble(getBillCost()); //记录这笔账单的总价格 这个记在前面是因为这个数据常常需要用到
			for(Production production : getProductions()){
//				record.writeUTF(production.getName());
				production.addSaleCount((int) production.getCount()); //修改整体销量
				record.writeUTF(production.getSpecific());
				record.writeInt((int) production.getCount());
				record.writeDouble(production.figureCost());
				record.writeUTF(production.getCustomerMSG());
			}

			//记录下单时间。
			record.writeUTF(currentTime);
			record.close();
			ArrayList<String> names = ResourceFilePath.readAllItem(ResourceFilePath.BillList); //维护数据
			names.add("order-" + currentTime); //加入这个文件名
			ResourceFilePath.updateItemList(names, ResourceFilePath.BillList);
		}catch(IOException e){
			ErrorDialog.showErrorMessage(null, "保存订单信息时出错","");
			return false;
		}
		return true;
	}
	
	public static String readFromFile(String filename){
		RandomAccessFile record = ResourceFilePath.openFile(ResourceFilePath.billRecordDirectory + "/" + filename, "r");
		StringBuilder sb = new StringBuilder();
		try {
			int size = record.readInt();
			sb.append("一共有 " + size + "件商品:");
			double totalCost = record.readDouble();
			for(int i = 0 ; i < size ; ++i){ //一共有 size 个商品
				String specific = record.readUTF();
				int count = record.readInt();
				double cost = record.readDouble();
				String msg = record.readUTF();
				sb.append( "\n" + specific + " " + count + " x $" + cost + "\n备注:[" + msg + "]");
			}

			sb.append("\n总价是 : $" + totalCost);
			String orderTime = record.readUTF();
			sb.append("\n交易时间 :" + orderTime);
//			System.out.println(sb.toString());
			record.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return sb.toString();
		}
	}
	

	

	
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
	
	public static void main(String[] args) {
//		BillList list = new BillList();
//		list.add(BeverageFactory.createBeverage("Espresso", 0).addIngredient(new Sugar()));
//		list.add(BeverageFactory.createBeverage("Espresso", 0).addIngredient(new Sugar()));
//		list.add(BeverageFactory.createBeverage("Espresso", 0).addIngredient(new Sugar()));
//		list.add(BeverageFactory.createBeverage("SmallFlyCoffee10", 0).addIngredient(new Ice()));
////		System.out.println(list.getAllToString());
////		System.out.println(list.getProductions().toString());
//		list.saveToFile();
//		list.readFromFile("order-2015.05.23-13.01.01");
	}
	
}
