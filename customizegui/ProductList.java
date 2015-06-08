package exercise.customizegui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import exercise.product.Production;

/**
 * 用来显示产品的Jlist
 * @author STU_nwad
 *
 */
@SuppressWarnings("serial")
public class ProductList extends JList<Production> {
	@SuppressWarnings("rawtypes")
	private DefaultListModel productionListModel = new DefaultListModel(); //列表模型
	
	@SuppressWarnings("rawtypes")
	private ArrayList productionArray;
	/**
	 * 
	 * @param p 商品的列表
	 */
	@SuppressWarnings("rawtypes")
	public ProductList(ArrayList p) {
		setList(p);
	}
	
	/**
	 * 
	 * @param p 商品的列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setList(ArrayList p){
		if (p == null)
			return;
		productionListModel.removeAllElements(); //先清空
		productionArray = p;
		for(Object o : p){		//添加数据
			productionListModel.addElement(o);
		}
		setModel(productionListModel); //设置模型
		
	}
	
	public ProductList() {
		
	}
	
	/**
	 * 让Listbox选中参数所在的位置,如果列表中不存在该参数
	 * 则不会改变之前的选择状态
	 * @param p
	 */
	public void setSelectedAt(Production p){
		int newIndex = productionListModel.indexOf(p);
		if (newIndex != -1){
			setSelectedIndex(newIndex);
			ensureIndexIsVisible(newIndex);
		}
	}
	
	/**
	 * 更新列表的显示
	 */
	public void updateState(){
		int index = getSelectedIndex();
		if (index != -1){
			clearSelection();
			setSelectedIndex(index);
		}
	}
	
	/**
	 * 按照销量排序
	 */
	@SuppressWarnings("unchecked")
	public void sort(){
		Production production = getSelectedValue(); //记录之前所中的位置
		productionArray.sort(null); //因为Production 已经实现了那个接口
		setList(productionArray);
		setSelectedAt(production);//还原之前的位置
		updateState();
	}
	
}
