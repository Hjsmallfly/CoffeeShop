package exercise.customizegui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import exercise.product.BillList;
import exercise.product.Production;

/**
 * 用于维护账单的JList
 * @author STU_nwad
 *
 */
public class BillJList extends JList<Production> {
	private BillList orderList;// = new BillList(); //用于维护数据
	
	private DefaultListModel billListModel = new DefaultListModel<Production>(); //泛型擦除的原因 
	
	
	public int indexOf(Object p){
		if (p == null)
			return -1;
		return billListModel.indexOf(p);
	}
	
	public BillJList(BillList bill) {
		setBillList(bill);
	}
	
	public BillJList(){
		//empty
	}
	
	public void setBillList(BillList bill){
		if (bill == null)
			return;
		
		ArrayList<Production> productions = bill.getProductions();
		if (!bill.isEmpty())
			for(Production p: productions){
				billListModel.addElement(p); //添加
			}
		setModel(billListModel);
		this.orderList = bill;
		
	}
	
	public void addElement(Production p){
		if (!orderList.Exist(p))
			billListModel.addElement(p);
	}
	
	public void setCount(int count){
		 Production p = (Production) billListModel.getElementAt(getSelectedIndex());
		 p.setCount(count);
		 int index = getSelectedIndex();
		 //刷新一下数据
		 clearSelection();
		 setSelectedIndex(index);
	}
	
	/**
	 * 设置合适的cellRenderer的size
	 */
	public void updateCellSize(){
		setFixedCellHeight(-1);
		setFixedCellWidth(-1);
	}
	
	public BillList getBillList(){
		return orderList;
	}
}
