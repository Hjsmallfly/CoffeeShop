package exercise.product;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import exercise.factory.BeverageFactory;

/**
 * 存放交易记录
 * @author STU_nwad
 *
 */
public class BillList {
	
	private Map<String, Production> billList = new HashMap<String, Production>(); //用于维护账单数据
	
	public String getAll(){
		StringBuilder sb = new StringBuilder();
		Set<String> keySet = billList.keySet();
		Iterator<String> it = keySet.iterator(); //产生迭代器
		while (it.hasNext()){
			String next = it.next();
			sb.append(billList.get(next).getSpecific() + " " + billList.get(next).getCost() + " "  + billList.get(next).getSaleCount(billList.get(next).getName())  + "\n");
		}
		return sb.toString();
	}
	
	public void add(Production p){
		billList.put(p.getSpecific(), p);
	}
	
	public static void main(String[] args) {
		BillList list = new BillList();
		list.add(BeverageFactory.createBeverage("Espresso", 0).addIngredient(new Sugar()));
		System.out.println(list.getAll());
	}
	
}
