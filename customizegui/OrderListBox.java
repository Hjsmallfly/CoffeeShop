package exercise.customizegui;
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
@SuppressWarnings("serial")
public class OrderListBox extends JList<Production> {
	
	private DefaultListModel<Production> billListModel = null; //这个由外部传入 这个用于管理数据
	
	/**
	 * 依赖于管理数据的list
	 * @param billList
	 */
	public void relyTo(BillList billList){
		billList.addOrderListBox(this); //回调函数来着
	}
	
	
	public void setNewListModel(ListModel<Production> productionListModel){
		setModel(productionListModel); //立马就会显示
		billListModel = (DefaultListModel<Production>) productionListModel;
	}
	
	
	
	
	public int indexOf(Object p){
		if (p == null)
			return -1;
		DefaultListModel<Production> listModel = (DefaultListModel<Production>) getModel();
		return listModel.indexOf(p);
	}
	
	
	public OrderListBox(){
		
	}
		
	/**
	 * 更新列表中数据的显示
	 * 
	 */
	public void update(){
		 int index = getSelectedIndex();
		 clearSelection();
		 setSelectedIndex(index);
	}
	
	
	/**
	 * 设置合适的cellRenderer的size
	 */
	public void updateCellSize(){
//		If height is -1, cell heights are computed in the ListUI by applying getPreferredSize to the cell renderer component for each list element. 

		setFixedCellHeight(-1);
		setFixedCellWidth(-1);
	}
	
}
