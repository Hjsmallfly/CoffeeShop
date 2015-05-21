package exercise;

import javax.swing.JPanel;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JRadioButton;

import exercise.customizegui.ProductList;
import exercise.product.Beverage;
import exercise.resourcepath.ResourceFilePath;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 用于管理点单的Panel
 * @author STU_nwad
 *
 */
public class TabPanel extends JPanel implements ListSelectionListener {
	private JTextField costTextBox; //右边的价格box
	private JTextField countTextBox;	//右边的数量box
	private JTextField psTextBox;	//右边的备注box
	ProductList productListBox = new ProductList();
	private static String[] strs = new String[]{"Mocha","Expresso","Coffee","Others..."};
	private static String[] billStrs = new String[]{"Mocha 1 x $9.9","Expresso 2 x $6.6","Coffee 3 x $1.0"};
	private JTextField totalCostTextBox;	//右边的总价box
	JList billListBox = new JList(billStrs);
	private ButtonGroup sizeSelectBP = new ButtonGroup();
	JTextArea descriptionTextArea = new JTextArea();
	private JTextField incomeTextBox;
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
		ArrayList<Beverage> bl = ResourceFilePath.readAllBeverage();
		

		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "商品信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.setBounds(206, 41, 143, 278);
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
		scrollPane_2.setBounds(10, 82, 129, 114);
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
		
		
		productListBox.setList(bl);
		productListBox.addListSelectionListener(this);
		scrollPane_1.setViewportView(productListBox); //添加滚动条 
		
//		productListBox.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "商品", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(99, 108, 113)));
		
		
		JButton editButton = new JButton("编辑");
		//为按钮添加 动作 监听器
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (editButton.getText() == "编辑"){
					editButton.setText("保存");
					costTextBox.setEditable(true);
					costTextBox.select(0,costTextBox.getText().length());
					costTextBox.requestFocus();
				}else {
					editButton.setText("编辑");
					costTextBox.setEditable(false);
				}

			}
		});
		editButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		editButton.setBounds(10, 212, 129, 23);
		infoPanel.add(editButton);
		
		JButton newProductButton = new JButton("新商品");
		newProductButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		newProductButton.setBounds(10, 245, 129, 23);
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
		scrollPane.setBounds(30, 22, 184, 188);
		billPanel.add(scrollPane);
		billListBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		
		scrollPane.setViewportView(billListBox);
//		billListBox.setBorder(new TitledBorder(null, "已点商品", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblCount = new JLabel("数量:");
		lblCount.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCount.setBounds(224, 20, 54, 22);
		billPanel.add(lblCount);
		
		countTextBox = new JTextField();
		countTextBox.setBounds(268, 22, 54, 21);
		billPanel.add(countTextBox);
		countTextBox.setColumns(10);
		
		JLabel lblPs = new JLabel("备注:");
		lblPs.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPs.setBounds(30, 213, 54, 22);
		billPanel.add(lblPs);
		
		psTextBox = new JTextField();
		psTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		psTextBox.setText("多加糖");
		psTextBox.setColumns(10);
		psTextBox.setBounds(69, 214, 253, 21);
		billPanel.add(psTextBox);
		
		JLabel lblCost = new JLabel("订单价格:");
		lblCost.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCost.setBounds(224, 121, 54, 15);
		billPanel.add(lblCost);
		
		JLabel label = new JLabel("$9.9");
		label.setForeground(Color.RED);
		label.setBounds(288, 121, 44, 15);
		billPanel.add(label);
		
		JLabel lblIncon = new JLabel("收款:");
		lblIncon.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblIncon.setBounds(224, 146, 33, 15);
		billPanel.add(lblIncon);
		
		JLabel lblDiff = new JLabel("找出:");
		lblDiff.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblDiff.setBounds(224, 171, 33, 15);
		billPanel.add(lblDiff);
		
		JLabel label_2 = new JLabel("$0.0");
		label_2.setForeground(Color.RED);
		label_2.setBounds(288, 171, 44, 15);
		billPanel.add(label_2);
		
		JLabel lblPrize = new JLabel("总价格:");
		lblPrize.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPrize.setBounds(224, 57, 54, 22);
		billPanel.add(lblPrize);
		
		totalCostTextBox = new JTextField();
		totalCostTextBox.setColumns(10);
		totalCostTextBox.setBounds(268, 59, 54, 21);
		billPanel.add(totalCostTextBox);
		
		JButton btnNewButton = new JButton("确认订单");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.setBounds(30, 245, 295, 23);
		billPanel.add(btnNewButton);
		
		incomeTextBox = new JTextField();
		incomeTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		incomeTextBox.setText("   $9.9");
		incomeTextBox.setForeground(Color.RED);
		incomeTextBox.setColumns(10);
		incomeTextBox.setBounds(268, 146, 54, 21);
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
	}
	
	
	 
	@Override
	public void valueChanged(ListSelectionEvent e) { //修改相应的数据显示
		ProductList p = (ProductList)e.getSource();
		int index = p.getSelectedIndex();
		if ( index != -1){
			costTextBox.setText(p.getSelectedValue().getCost() + "");
			descriptionTextArea.setText(p.getSelectedValue().description());
		}
	}
}
