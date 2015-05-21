package exercise;

import javax.swing.JPanel;
import javax.swing.JComboBox;



import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JRadioButton;

import exercise.customizegui.BillJList;
import exercise.customizegui.ErrorDialog;
import exercise.customizegui.ProductList;
import exercise.product.Beverage;
import exercise.product.BillList;
import exercise.product.Production;
import exercise.resourcepath.ResourceFilePath;
import exercise.usefulinterface.HasIngredient;
import exercise.usefulinterface.HasSize;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;

/**
 * 用于管理点单的Panel
 * @author STU_nwad
 *
 */
public class TabPanel extends JPanel implements ListSelectionListener,MouseListener {
	private JTextField costTextBox; //右边的价格box
	private JTextField countTextBox;	//右边的数量box
	private JTextField psTextBox;	//右边的备注box
	ProductList productListBox = new ProductList();
	private static String[] strs = new String[]{"Mocha","Expresso","Coffee","Others..."};
	private static String[] billStrs = new String[]{"Mocha 1 x $9.9","Expresso 2 x $6.6","Coffee 3 x $1.0"};
	private JTextField totalCostTextBox;	//右边的总价box
	BillJList billListBox = new BillJList();
	private ButtonGroup sizeSelectBP = new ButtonGroup();
	JCheckBox chckbxIce = new JCheckBox("Ice");
	JCheckBox chckbxMilk = new JCheckBox("Milk");
	JCheckBox chckbxSugar = new JCheckBox("Sugar");
//	private CheckboxGroup ingredientChkbox = new CheckboxGroup(); //用来存放 调料的checkbox 复选 
	//Creating a set of buttons with the same ButtonGroup object means that turning "on" one of those buttons turns off all other buttons in the group.
	JTextArea descriptionTextArea = new JTextArea();
	private JTextField incomeTextBox;
	private BillList billList = new BillList(); //管理订单
	
	JLabel totalCostLabel = new JLabel("$0.0");
	
	public void setCategory(Production p){
		if (p instanceof Beverage){ //如果是饮料类的话
			productListBox.setList(ResourceFilePath.readAllBeverage());
		}
	}
	
	/**
	 * Create the panel.
	 */
	public TabPanel() {
		setBackground(Color.WHITE);
		setForeground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JComboBox searchBox = new JComboBox(strs);
		searchBox.setEditable(true);
		searchBox.setBounds(10, 10, 673, 21);
		add(searchBox);
		
		JList list = new JList(strs);
		list.setBounds(20, 241, 157, -178);
		//add(list);
		//ArrayList<Beverage> bl = ResourceFilePath.readAllBeverage();
		

		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "商品信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.setBounds(206, 41, 143, 303);
		add(infoPanel);
		infoPanel.setLayout(null);
		
		costTextBox = new JTextField("$100");
		costTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		costTextBox.setEditable(false);
		costTextBox.setText("0");
		costTextBox.setBounds(10, 49, 129, 23);
		infoPanel.add(costTextBox);
		costTextBox.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 82, 129, 155);
		infoPanel.add(scrollPane_2);
		scrollPane_2.setViewportView(descriptionTextArea);
		
		
		descriptionTextArea.setForeground(Color.DARK_GRAY);
		descriptionTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		descriptionTextArea.setText("商品描述信息...");
		descriptionTextArea.setBorder(null);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 41, 186, 242);
		add(scrollPane_1);
		productListBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		
		productListBox.addListSelectionListener(this);
		productListBox.addMouseListener(this);
		scrollPane_1.setViewportView(productListBox); //添加滚动条 
		
//		productListBox.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "商品", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(99, 108, 113)));
		
		
		JButton editButton = new JButton("编辑");
		//为按钮添加 动作 监听器
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (productListBox.getSelectedIndex() == -1) //修复一个bug
					return;
				if (editButton.getText() == "编辑"){
					editButton.setText("保存");
					costTextBox.setEditable(true);
					costTextBox.select(0,costTextBox.getText().length());
					costTextBox.requestFocus();
					productListBox.setEnabled(false); //防止出错
				}else {
					try{
						double cost = Double.parseDouble( costTextBox.getText());
						productListBox.getSelectedValue().setCost(cost);
						productListBox.getSelectedValue().setDescription(descriptionTextArea.getText());
						productListBox.getSelectedValue().saveTofile(); //保存修改后的结果
						editButton.setText("编辑");
						costTextBox.setEditable(false);
						productListBox.setEnabled(true);
					}catch(NumberFormatException NFE){
						ErrorDialog.showErrorMessage(null, "请输入数字", "请在价格处输入数字！");
						costTextBox.select(0,costTextBox.getText().length());
						costTextBox.requestFocus();
					}
				}

			}
		});
		editButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		editButton.setBounds(10, 237, 129, 23);
		infoPanel.add(editButton);
		
		JButton newProductButton = new JButton("新商品");
		newProductButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		newProductButton.setBounds(10, 270, 129, 23);
		infoPanel.add(newProductButton);
		
		JComboBox comboBox = new JComboBox(new Object[]{});
		comboBox.setEditable(true);
		comboBox.setBounds(-250, 0, 224, 21);
		infoPanel.add(comboBox);
		
		JLabel costLabel = new JLabel("单价:");
		costLabel.setBounds(10, 24, 54, 15);
		infoPanel.add(costLabel);
		
		JPanel billPanel = new JPanel();
		billPanel.setBorder(new TitledBorder(null, "订单", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		billPanel.setBounds(348, 41, 335, 278);
		add(billPanel);
		billPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 22, 184, 214);
		billPanel.add(scrollPane);
		billListBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		billListBox.setBillList(billList);
		billListBox.addListSelectionListener(this);
		
		scrollPane.setViewportView(billListBox);
//		billListBox.setBorder(new TitledBorder(null, "已点商品", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblCount = new JLabel("数量:");
		lblCount.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCount.setBounds(224, 20, 54, 22);
		billPanel.add(lblCount);
		
		countTextBox = new JTextField();
		countTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		countTextBox.setBounds(268, 22, 54, 21);
		billPanel.add(countTextBox);
		countTextBox.setColumns(10);
		
		JLabel lblPs = new JLabel("备注:");
		lblPs.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPs.setBounds(30, 246, 54, 22);
		billPanel.add(lblPs);
		
		psTextBox = new JTextField();
		psTextBox.setToolTipText("修改备注后按回车提交");
		psTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		psTextBox.setText("多加糖");
		psTextBox.setColumns(10);
		psTextBox.setBounds(69, 247, 253, 21);
		billPanel.add(psTextBox);
		
		JLabel lblCost = new JLabel("订单价格:");
		lblCost.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCost.setBounds(224, 134, 54, 15);
		billPanel.add(lblCost);
		
		
		totalCostLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		totalCostLabel.setForeground(Color.RED);
		totalCostLabel.setBounds(288, 134, 44, 15);
		billPanel.add(totalCostLabel);
		
		JLabel lblIncon = new JLabel("收款:");
		lblIncon.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblIncon.setBounds(224, 180, 33, 15);
		billPanel.add(lblIncon);
		
		JLabel lblDiff = new JLabel("找出:");
		lblDiff.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblDiff.setBounds(224, 221, 33, 15);
		billPanel.add(lblDiff);
		
		JLabel oweLabel = new JLabel("$0.0");
		oweLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		oweLabel.setForeground(Color.RED);
		oweLabel.setBounds(288, 221, 44, 15);
		billPanel.add(oweLabel);
		
		JLabel lblPrize = new JLabel("总价格:");
		lblPrize.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPrize.setBounds(224, 71, 54, 22);
		billPanel.add(lblPrize);
		
		totalCostTextBox = new JTextField();
		totalCostTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		totalCostTextBox.setColumns(10);
		totalCostTextBox.setBounds(268, 72, 54, 21);
		billPanel.add(totalCostTextBox);
		
		incomeTextBox = new JTextField();
		incomeTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		incomeTextBox.setText("   $9.9");
		incomeTextBox.setForeground(Color.RED);
		incomeTextBox.setColumns(10);
		incomeTextBox.setBounds(268, 177, 54, 21);
		billPanel.add(incomeTextBox);
		
		JRadioButton smallJRadio = new JRadioButton("小");
		smallJRadio.setBounds(10, 296, 54, 23);
		add(smallJRadio);
		sizeSelectBP.add(smallJRadio);
		
		JRadioButton middleJRadio = new JRadioButton("中");
		middleJRadio.setBounds(66, 296, 62, 23);
		add(middleJRadio);
		sizeSelectBP.add(middleJRadio);
		
		JRadioButton bigJRadio = new JRadioButton("大");
		bigJRadio.setBounds(130, 296, 54, 23);
		add(bigJRadio);
		
		//这是添加到按钮组上
		sizeSelectBP.add(bigJRadio);
		
		
		chckbxIce.setBounds(10, 321, 58, 23);
		chckbxIce.setEnabled(false);
		add(chckbxIce);
		
		
		chckbxMilk.setBounds(66, 321, 72, 23);
		chckbxMilk.setEnabled(false);
		add(chckbxMilk);
		
		chckbxSugar.setEnabled(false);
		chckbxSugar.setBounds(130, 321, 85, 23);
		add(chckbxSugar);

		
		
		
		JButton btnNewButton = new JButton("确认订单");
		btnNewButton.setBounds(380, 321, 295, 23);
		add(btnNewButton);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		setJRadioEnable(false); //默认不可选
		setCheckoutEnable(false); //默认不可选
	}
	
	/**
	 * 设置大小按钮是否可以选
	 */
	public void setJRadioEnable(boolean flag){
		Enumeration<AbstractButton> allJRadiobuttons =  sizeSelectBP.getElements();
		while(allJRadiobuttons.hasMoreElements()){ //还有元素的时候
			allJRadiobuttons.nextElement().setEnabled(flag);
		}
	}
	
	public void setCheckoutEnable(boolean flag){
		chckbxIce.setEnabled(flag);
		chckbxMilk.setEnabled(flag);
		chckbxSugar.setEnabled(flag);
	}
	
	 
	@Override
	public void valueChanged(ListSelectionEvent e) { //修改相应的数据显示
		if (e.getSource() == productListBox){ //如果是商品列表的话	
			ProductList p = (ProductList)e.getSource();
			int index = p.getSelectedIndex();
			if ( index != -1){
				costTextBox.setText(p.getSelectedValue().getCost() + "");
				descriptionTextArea.setText(p.getSelectedValue().description());
				
				if (p.getSelectedValue() instanceof HasSize) //如果有大小的选项的话
					setJRadioEnable(true); //设置大小是可以选择的
				else {
					setJRadioEnable(false);
				}
				
				if (p.getSelectedValue() instanceof HasIngredient)
					setCheckoutEnable(true);
				else
					setCheckoutEnable(false);
				
			}
		}else if(e.getSource() == billListBox){ //账单的话
			int index = billListBox.getSelectedIndex();
			if (index == -1)
				return;
			countTextBox.setText(billListBox.getSelectedValue().getCount() + "");
			totalCostTextBox.setText(billListBox.getSelectedValue().figureCost() * billListBox.getSelectedValue().getCount() + "");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == productListBox && e.getClickCount() == 2){ //双击商品列表的内容  点单
			if (productListBox.getSelectedIndex() == -1)
				return ;
//			ErrorDialog.showErrorMessage(null, productListBox.getSelectedValue().getSpecific(),"");
			billListBox.addElement(productListBox.getSelectedValue());
			billList.add(productListBox.getSelectedValue());
			double sum = 0.0;
			for(Production p : billList.getProductions()){
				sum += p.figureCost() * p.getCount(); //这里会莫名多出一些小数位 记得处理一下
			}
			
			totalCostLabel.setText("$" + sum);
			
			//这样可以触发 ListSelection事件~借此更新画面
			billListBox.clearSelection();
			
			billListBox.setSelectedIndex(billList.size() - 1);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
}
