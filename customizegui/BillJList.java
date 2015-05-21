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
public class BillJList extends JList<BillList> {
	private BillList orderList;// = new BillList(); //用于维护数据
	
	private DefaultListModel<Production> billListModel = new DefaultListModel<Production>(); 
	
	public BillJList(BillList bill) {
		this.orderList = bill;
	}
}
