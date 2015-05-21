package exercise.customizegui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exercise.product.Production;

/**
 * 用来显示产品的Jlist
 * @author STU_nwad
 *
 */
public class ProductList extends JList<Production> {
	private DefaultListModel productionListModel = new DefaultListModel(); //列表模型
	
	private ArrayList productionArray;
	/**
	 * 
	 * @param p 商品的列表
	 */
	public ProductList(ArrayList p) {
		setList(p);
	}
	
	/**
	 * 
	 * @param p 商品的列表
	 */
	public void setList(ArrayList p){
		if (p == null)
			return;
		productionArray = p;
		for(Object o : p){		//添加数据
			productionListModel.addElement(o);
		}
		setModel(productionListModel); //设置模型
		
	}
	
	public ProductList() {
		
	}
	
	
}
