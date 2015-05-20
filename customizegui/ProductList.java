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
	
	private JTextField costTextBox; //用于显示价格的TextBox
	
	private JTextArea descriptionTextArea; //用于显示商品信息的TextArea
	
	public void setCostTextBox(JTextField t){
		costTextBox = t;
	}
	
	public void setDescriptionTextArea(JTextArea t){
		descriptionTextArea = t;
	}
	
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
		
		addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (getSelectedIndex() != -1){
					costTextBox.setText((( Production)productionArray.get(getSelectedIndex())).getCost() + " ");
					descriptionTextArea.setText((( Production)productionArray.get(getSelectedIndex())).description());
				}
			}
		});
		
	}
	
	public ProductList() {
		
	}
	
	
}
