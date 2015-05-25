package exercise.customizegui;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import exercise.ShowFrame;
import exercise.product.Espresso;
import exercise.product.Pizza;
import exercise.product.Production;


/**
 * 支持搜索功能的ComboBox
 * @author STU_nwad
 *
 */
@SuppressWarnings("serial")
public class SearchComboBox extends JComboBox<Production> { //有搜索功能的的ComboBox
//	private DefaultComboBoxModel<Production> productionListModel = new DefaultComboBoxModel<Production>(); //管理显示出来的列表 
	//这里不适合用这个，因为往列表模型里面加东西的话 会改掉 editor 里面的内容
	
	
	private ArrayList<Production> productionList = new ArrayList<Production>(); //用于保存数据
	private JTextField editor; //用于管理JComboBox的输入框
	
	public JTextField getTextbox(){
		return editor;
	}
	
	boolean SYSTEM_OPERATION = false; //记录是否是系统操作
	
	public boolean isSystemOperation(){
		return SYSTEM_OPERATION;
	}
	
	public void setSystemOperation(boolean flag){
		SYSTEM_OPERATION = flag;
	}
	
	/**
	 * 清空TEXTBOX中的字符串
	 */
	public void clear(){
		editor.setText("");
	}
	
	/**
	 * 为TEXTBOX添加KeyListener
	 * @param keyListener
	 */
	public void addKeyListenerForTextbox(KeyListener keyListener){
		editor.addKeyListener(keyListener);
	}
	
	public void setList(ArrayList<Production> list){
		if (list == null)
			return;
		if (list.size() == 0)
			return;
//		JOptionPane.showMessageDialog(editor,"It's alright");
		productionList.addAll(list);
	}
	
	
	/**
	 * 修改TEXTBOX中的内容为 选中的下拉菜单的内容
	 * @return 返回该元素 如果没有选中项则返回null
	 */
	public Production select(){
		int index = SearchComboBox.this.getSelectedIndex();
		if (index != -1){
			editor.setText(SearchComboBox.this.getSelectedItem().toString());
			SearchComboBox.this.hidePopup();
			return getItemAt(index);
		}
		return null;
		
	}
	
	/**
	 * 开始搜索,并会有相应的显示
	 */
	public void search(){
		String name = getTextbox().getText();
		
		if (name.trim().equals("")) //不处理空的情况
			return;
		SearchComboBox.this.hidePopup();
		if (productionList.size() == 0 )
			return;
		SearchComboBox.this.removeAllItems();
		
		String lowCase = name.toLowerCase(); //小写
		for(Production p : productionList){
			if (p.toString().toLowerCase().contains(lowCase)){ //不区分大小写
				SearchComboBox.this.addItem(p);
			}
		}
		if (SearchComboBox.this.getItemCount() != 0)
			SearchComboBox.this.showPopup();
		else {
			SearchComboBox.this.hidePopup();
		}
		getTextbox().setText(name);
			
	}
	
	public SearchComboBox(ArrayList<Production> list) {
		setList(list);
//		setModel(productionListModel);//加入这个模型
		setEditable(true); //可以编辑
		editor = (JTextField) getEditor().getEditorComponent();
		editor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 3)
					editor.setText(""); //清空
			}
		});
//		editor.addKeyListener(new ListenTyping());
	}
	
/*
	class ListenTyping implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_ENTER)
				setSystemOperation(true); //说明是系统自定义的操作
			else
				setSystemOperation(false);
			
			if (key == KeyEvent.VK_ALT || key == KeyEvent.VK_ENTER){ //这里用ALT键是因为 TAB ENTER 这些和系统有冲突
				select();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (isSystemOperation() == true)
				return;
			search();
		}
		
	}
	
	@Override
		public void showPopup() {
			if (SYSTEM_OPERATION != true)
				super.showPopup();
		}
*/	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		ArrayList<Production> list = new ArrayList<Production>();
		list.add(new Espresso(0));
		list.add(new Espresso(0));
		list.add(new Espresso(0));
		list.add(new Espresso(0));
		list.add(new Pizza());
		f.add(new SearchComboBox(list));
		ShowFrame.run(f);
	}
	
}

