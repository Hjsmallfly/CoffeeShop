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
import javax.swing.JTextPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JRadioButton;

import exercise.customizegui.ProductList;
import exercise.product.Beverage;
import exercise.resourcepath.ResourceFilePath;

public class TabPanel extends JPanel {
	private JTextField costTextBox;
	private JTextField countTextBox;
	private JTextField psTextBox;
	private static String[] strs = new String[]{"Mocha","Expresso","Coffee","Others..."};
	private static String[] billStrs = new String[]{"Mocha 1 x $9.9","Expresso 2 x $6.6","Coffee 3 x $1.0"};
	private JTextField totalCostTextBox;
	private ButtonGroup sizeSelectBP = new ButtonGroup();
	/**
	 * Create the panel.
	 */
	public TabPanel() {
		setBackground(Color.WHITE);
		setForeground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JComboBox searchBox = new JComboBox(strs);
		searchBox.setEditable(true);
		searchBox.setBounds(10, 10, 593, 21);
		add(searchBox);
		
		JList list = new JList(strs);
		list.setBounds(20, 241, 157, -178);
		add(list);
		ArrayList<Beverage> bl = ResourceFilePath.readAllBeverage();
		

		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "商品信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.setBounds(125, 41, 181, 248);
		add(infoPanel);
		infoPanel.setLayout(null);
		
		costTextBox = new JTextField("$100");
		costTextBox.setEditable(false);
		costTextBox.setText("0");
		costTextBox.setBorder(new TitledBorder("单价"));
		costTextBox.setBounds(10, 25, 161, 47);
		infoPanel.add(costTextBox);
		costTextBox.setColumns(10);
		
		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setForeground(Color.DARK_GRAY);
		descriptionTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		descriptionTextArea.setText("商品描述信息...");
		descriptionTextArea.setBounds(10, 82, 161, 123);
		descriptionTextArea.setBorder(new TitledBorder("商品信息"));
		infoPanel.add(descriptionTextArea);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 41, 111, 248);
		add(scrollPane_1);
		ProductList productListBox = new ProductList(bl);
		productListBox.setCostTextBox(costTextBox);
		productListBox.setDescriptionTextArea(descriptionTextArea);
		scrollPane_1.setViewportView(productListBox);
		
		productListBox.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "商品", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(99, 108, 113)));
		
		
		JButton editButton = new JButton("编辑");
		editButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		editButton.setBounds(94, 215, 77, 23);
		infoPanel.add(editButton);
		
		JButton newProductButton = new JButton("新商品");
		newProductButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		newProductButton.setBounds(10, 215, 74, 23);
		infoPanel.add(newProductButton);
		
		JComboBox comboBox = new JComboBox(new Object[]{});
		comboBox.setEditable(true);
		comboBox.setBounds(-250, 0, 224, 21);
		infoPanel.add(comboBox);
		
		JPanel billPanel = new JPanel();
		billPanel.setBorder(new TitledBorder(null, "订单", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		billPanel.setBounds(316, 41, 287, 248);
		add(billPanel);
		billPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 154, 164);
		billPanel.add(scrollPane);
		
		JList billListBox = new JList(billStrs);
		scrollPane.setViewportView(billListBox);
		billListBox.setBorder(new TitledBorder(null, "已点商品", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblCount = new JLabel("数量:");
		lblCount.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCount.setBounds(164, 22, 54, 22);
		billPanel.add(lblCount);
		
		countTextBox = new JTextField();
		countTextBox.setBounds(203, 23, 74, 21);
		billPanel.add(countTextBox);
		countTextBox.setColumns(10);
		
		JLabel lblPs = new JLabel("备注:");
		lblPs.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPs.setBounds(164, 89, 54, 22);
		billPanel.add(lblPs);
		
		psTextBox = new JTextField();
		psTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		psTextBox.setText("多加糖");
		psTextBox.setColumns(10);
		psTextBox.setBounds(203, 90, 74, 21);
		billPanel.add(psTextBox);
		
		JLabel lblCost = new JLabel("订单价格:");
		lblCost.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCost.setBounds(164, 121, 89, 15);
		billPanel.add(lblCost);
		
		JLabel label = new JLabel("$9.9");
		label.setForeground(Color.RED);
		label.setBounds(233, 121, 44, 15);
		billPanel.add(label);
		
		JLabel lblIncon = new JLabel("收款:");
		lblIncon.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblIncon.setBounds(164, 146, 54, 15);
		billPanel.add(lblIncon);
		
		JLabel label_1 = new JLabel("$9.9");
		label_1.setForeground(Color.RED);
		label_1.setBounds(233, 146, 44, 15);
		billPanel.add(label_1);
		
		JLabel lblDiff = new JLabel("找出:");
		lblDiff.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblDiff.setBounds(164, 171, 54, 15);
		billPanel.add(lblDiff);
		
		JLabel label_2 = new JLabel("$0.0");
		label_2.setForeground(Color.RED);
		label_2.setBounds(233, 171, 44, 15);
		billPanel.add(label_2);
		
		JLabel lblPrize = new JLabel("总价格:");
		lblPrize.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPrize.setBounds(164, 54, 54, 22);
		billPanel.add(lblPrize);
		
		totalCostTextBox = new JTextField();
		totalCostTextBox.setColumns(10);
		totalCostTextBox.setBounds(203, 54, 74, 21);
		billPanel.add(totalCostTextBox);
		
		JButton btnNewButton = new JButton("确认订单");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 215, 267, 23);
		billPanel.add(btnNewButton);
		
		JRadioButton smallJRadio = new JRadioButton("小");
		smallJRadio.setBounds(20, 192, 57, 23);
		billPanel.add(smallJRadio);
		
		JRadioButton middleJRadio = new JRadioButton("中");
		middleJRadio.setBounds(79, 192, 54, 23);
		billPanel.add(middleJRadio);
		
		JRadioButton bigJRadio = new JRadioButton("大");
		bigJRadio.setBounds(139, 192, 73, 23);
		billPanel.add(bigJRadio);
		
		//这是添加到按钮组上
		sizeSelectBP.add(bigJRadio);
		sizeSelectBP.add(smallJRadio);
		sizeSelectBP.add(middleJRadio);
	}
}
