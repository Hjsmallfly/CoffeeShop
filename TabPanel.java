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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtAddMoreSugar;
	private static String[] strs = new String[]{"Mocha","Expresso","Coffee","Others..."};
	private static String[] billStrs = new String[]{"Mocha 1 x $9.9","Expresso 2 x $6.6","Coffee 3 x $1.0"};
	private JTextField textField_2;
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 41, 111, 248);
		add(scrollPane_1);
		JList productListBox = new ProductList(bl);
		scrollPane_1.setViewportView(productListBox);
		
		productListBox.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "商品", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(99, 108, 113)));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "商品信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(125, 41, 181, 248);
		add(panel);
		panel.setLayout(null);
		
		textField = new JTextField("$100");
		textField.setEditable(false);
		textField.setBorder(new TitledBorder("单价"));
		textField.setBounds(10, 25, 161, 47);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextArea txtrHereIsThe = new JTextArea();
		txtrHereIsThe.setForeground(Color.DARK_GRAY);
		txtrHereIsThe.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		txtrHereIsThe.setText("商品描述信息...");
		txtrHereIsThe.setBounds(10, 82, 161, 123);
		txtrHereIsThe.setBorder(new TitledBorder("商品信息"));
		panel.add(txtrHereIsThe);
		
		JButton editButton = new JButton("编辑");
		editButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		editButton.setBounds(94, 215, 77, 23);
		panel.add(editButton);
		
		JButton btnNewitem = new JButton("新商品");
		btnNewitem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewitem.setBounds(10, 215, 74, 23);
		panel.add(btnNewitem);
		
		JComboBox comboBox = new JComboBox(new Object[]{});
		comboBox.setEditable(true);
		comboBox.setBounds(-250, 0, 224, 21);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "订单", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(316, 41, 287, 248);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 154, 164);
		panel_1.add(scrollPane);
		
		JList billListBox = new JList(billStrs);
		scrollPane.setViewportView(billListBox);
		billListBox.setBorder(new TitledBorder(null, "已点商品", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblCount = new JLabel("数量:");
		lblCount.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCount.setBounds(164, 22, 54, 22);
		panel_1.add(lblCount);
		
		textField_1 = new JTextField();
		textField_1.setBounds(203, 23, 74, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPs = new JLabel("备注:");
		lblPs.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPs.setBounds(164, 89, 54, 22);
		panel_1.add(lblPs);
		
		txtAddMoreSugar = new JTextField();
		txtAddMoreSugar.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		txtAddMoreSugar.setText("多加糖");
		txtAddMoreSugar.setColumns(10);
		txtAddMoreSugar.setBounds(203, 90, 74, 21);
		panel_1.add(txtAddMoreSugar);
		
		JLabel lblCost = new JLabel("订单价格:");
		lblCost.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCost.setBounds(164, 121, 89, 15);
		panel_1.add(lblCost);
		
		JLabel label = new JLabel("$9.9");
		label.setForeground(Color.RED);
		label.setBounds(233, 121, 44, 15);
		panel_1.add(label);
		
		JLabel lblIncon = new JLabel("收款:");
		lblIncon.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblIncon.setBounds(164, 146, 54, 15);
		panel_1.add(lblIncon);
		
		JLabel label_1 = new JLabel("$9.9");
		label_1.setForeground(Color.RED);
		label_1.setBounds(233, 146, 44, 15);
		panel_1.add(label_1);
		
		JLabel lblDiff = new JLabel("找出:");
		lblDiff.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblDiff.setBounds(164, 171, 54, 15);
		panel_1.add(lblDiff);
		
		JLabel label_2 = new JLabel("$0.0");
		label_2.setForeground(Color.RED);
		label_2.setBounds(233, 171, 44, 15);
		panel_1.add(label_2);
		
		JLabel lblPrize = new JLabel("总价格:");
		lblPrize.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPrize.setBounds(164, 54, 54, 22);
		panel_1.add(lblPrize);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(203, 54, 74, 21);
		panel_1.add(textField_2);
		
		JButton btnNewButton = new JButton("确认订单");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 215, 267, 23);
		panel_1.add(btnNewButton);
		
		JRadioButton smallJRadio = new JRadioButton("小");
		smallJRadio.setBounds(20, 192, 57, 23);
		panel_1.add(smallJRadio);
		
		JRadioButton middleJRadio = new JRadioButton("中");
		middleJRadio.setBounds(79, 192, 54, 23);
		panel_1.add(middleJRadio);
		
		JRadioButton bigJRadio = new JRadioButton("大");
		bigJRadio.setBounds(139, 192, 73, 23);
		panel_1.add(bigJRadio);

		sizeSelectBP.add(bigJRadio);
		sizeSelectBP.add(smallJRadio);
		sizeSelectBP.add(middleJRadio);
	}
}
