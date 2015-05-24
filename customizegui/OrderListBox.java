package exercise.customizegui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import exercise.product.BillList;
import exercise.product.Production;

/**
 * 用于维护账单的JList
 * @author STU_nwad
 *
 */
public class OrderListBox extends JList<Production> {
	private BillList orderList = new BillList(); //用于维护数据
	
	private DefaultListModel billListModel = new DefaultListModel<Production>(); //泛型擦除的原因 
	
	/**
	 * 依赖于管理数据的list
	 * @param billList
	 */
	public void relyTo(BillList billList){
		billList.addOrderListBox(this); //回调函数来着
	}
	
	
	public void setNewListModel(ListModel<Production> productionListModel){
		setModel(productionListModel); //立马就会显示
		billListModel = (DefaultListModel) productionListModel;
	}
	
	
	
	
	public int indexOf(Object p){
		if (p == null)
			return -1;
		DefaultListModel<Production> listModel = (DefaultListModel<Production>) getModel();
		return listModel.indexOf(p);
	}
	
	
	public OrderListBox(){
		
	}
	

	
	public void addElement(Production p){
		orderList.add(p);
	}
	
	/**
	 * 加入多个数据
	 * @param productions
	 */
	public void addElement(ArrayList<Production> productions){
		if (productions == null)
			return ;
		for(Production p : productions){
			orderList.add(p);
		}
	}
	

	
	/**
	 * 相当于重置账单状态
	 */
	public void removeAll(){
		orderList.removeAll();
		billListModel.clear();
	}
	
	
	/**
	 * 这里会更新数据的显示
	 * 
	 */
	public void update(){
		 int index = getSelectedIndex();
		 clearSelection();
		 setSelectedIndex(index);
	}
	
	public void remove(Production p){
		orderList.remove(p);
		billListModel.removeElement(p);
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
