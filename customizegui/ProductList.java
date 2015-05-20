package exercise.customizegui;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import exercise.product.Production;

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
	
	public void setList(ArrayList p){
		productionArray = p;
		for(Object o : p){		//添加数据
			productionListModel.addElement(o);
		}
		setModel(productionListModel); //设置模型
	}
	
	public ProductList() {
		
	}
	
}
