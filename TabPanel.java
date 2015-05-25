package exercise;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JRadioButton;
import exercise.customizegui.BillCellRenderer;
import exercise.customizegui.OrderListBox;
import exercise.customizegui.ErrorDialog;
import exercise.customizegui.NewProductionWindow;
import exercise.customizegui.ProductList;
import exercise.customizegui.SearchComboBox;
import exercise.factory.BeverageFactory;
import exercise.product.Beverage;
import exercise.product.BillList;
import exercise.product.Food;
import exercise.product.Ice;
import exercise.product.Milk;
import exercise.product.Production;
import exercise.product.Sugar;
import exercise.resourcepath.ResourceFilePath;
import exercise.usefulinterface.HasIngredient;
import exercise.usefulinterface.HasSize;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBox;

/**
 * 用于管理点单的Panel
 * @author STU_nwad
 *
 */
@SuppressWarnings("serial")
public class TabPanel extends JPanel implements ListSelectionListener,MouseListener,KeyListener,ItemListener {
	
	public static final int  BEVERAGE_TYPE = 0;
	public static final int  FOOD_TYPE = 1;
	
	private SearchComboBox searchBox;
	
	private JTextField costTextBox; //右边的价格box
	private JTextField countTextBox;	//右边的数量box
	private JTextField psTextBox;	//右边的备注box
	ProductList productListBox = new ProductList();
	private JTextField totalCostTextBox;	//右边的总价box
		
	OrderListBox billListBox = new OrderListBox();
	
	JLabel lblDiff = new JLabel("找出:"); //显示找出的label
	JLabel oweLabel = new JLabel("$0.0"); //显示要找出多少money
	
	private ButtonGroup sizeSelectBP = new ButtonGroup();
	
	
	JCheckBox chckbxIce = new JCheckBox("Ice");
	JCheckBox chckbxMilk = new JCheckBox("Milk");
	JCheckBox chckbxSugar = new JCheckBox("Sugar");
	
	JRadioButton smallJRadio = new JRadioButton("小");
	JRadioButton bigJRadio = new JRadioButton("大");
	JRadioButton middleJRadio = new JRadioButton("中");
	
	private int  type = BEVERAGE_TYPE;
	
//	private CheckboxGroup ingredientChkbox = new CheckboxGroup(); //用来存放 调料的checkbox 复选 
	//Creating a set of buttons with the same ButtonGroup object means that turning "on" one of those buttons turns off all other buttons in the group.
	JTextArea descriptionTextArea = new JTextArea();
	private JTextField incomeTextBox;
	
	private BillList billList; //用于管理
	
	JLabel totalCostLabel = new JLabel("$0.0");
	
	
	public void sortTheProductionList(){
		productListBox.sort();
	}
	
	public void setBillList(BillList bill){
		this.billList = bill;
	}
	
	public OrderListBox getOrderListBox(){
		return billListBox;
	}
	
	
	public ArrayList<Production> getProductions(){
		if ( billList.getProductions() != null)
			return  billList.getProductions();
		
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" }) //Java泛型的问题
	public void setCategory(Production p){
		ArrayList list = new ArrayList();
		if (p instanceof Beverage){ //如果是饮料类的话
			list = ResourceFilePath.readAllBeverage();
			productListBox.setList(ResourceFilePath.readAllBeverage());
			type = BEVERAGE_TYPE;

		}else if (p instanceof Food){
			list = ResourceFilePath.readAllFood();
			productListBox.setList(list);
			type = FOOD_TYPE;
		}
		searchBox.setList(list);
	}
	
	public void updateProductionList(){
		if (type == BEVERAGE_TYPE)
			productListBox.setList(ResourceFilePath.readAllBeverage());
		else if(type == FOOD_TYPE)
			productListBox.setList(ResourceFilePath.readAllFood());
	}
	
	/**
	 * 点击大小的时候 计算不同大小的价钱，然后刷新显示
	 */
	private void addListenersOfButtons(){
		ActionListener updateState = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				productListBox.updateState(); //更新list的显示状态
				
			}
		};
		smallJRadio.setBackground(Color.WHITE);
		smallJRadio.addActionListener(updateState);
		middleJRadio.setBackground(Color.WHITE);
		middleJRadio.addActionListener(updateState);
		bigJRadio.setBackground(Color.WHITE);
		bigJRadio.addActionListener(updateState);
		
		chckbxIce.addActionListener(updateState);
		chckbxMilk.addActionListener(updateState);
		chckbxSugar.addActionListener(updateState);
		
	}
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("unchecked") // 因为Java 泛型的问题
	public TabPanel(BillList billList) {
		this.billList = billList;
		setBackground(Color.WHITE);
		setForeground(Color.LIGHT_GRAY);
		setLayout(null);
		addListenersOfButtons();
		searchBox = new SearchComboBox(billList.getProductions());
		searchBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		searchBox.setEditable(true);
		searchBox.setBounds(10, 10, 673, 21);
		searchBox.addKeyListenerForTextbox(this);
		add(searchBox);
		

		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBorder(new TitledBorder(null, "商品信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.setBounds(206, 41, 149, 291);
		add(infoPanel);
		infoPanel.setLayout(null);
		
		costTextBox = new JTextField("$100");
		costTextBox.setBackground(Color.WHITE);
		costTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		costTextBox.setEditable(false);
		costTextBox.setText("0");
		costTextBox.setBounds(10, 49, 129, 23);
		infoPanel.add(costTextBox);
		costTextBox.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 82, 129, 139);
		infoPanel.add(scrollPane_2);
		scrollPane_2.setViewportView(descriptionTextArea);
		
		
		descriptionTextArea.setForeground(Color.DARK_GRAY);
		descriptionTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		descriptionTextArea.setText("商品描述信息...");
		descriptionTextArea.setBorder(null);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 41, 186, 235);
		add(scrollPane_1);
		productListBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		
		productListBox.addListSelectionListener(this);
		productListBox.addMouseListener(this);
		productListBox.addKeyListener(this);
		scrollPane_1.setViewportView(productListBox); //添加滚动条 
		
//		productListBox.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "商品", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(99, 108, 113)));
		
		
		JButton editButton = new JButton("编辑");
		editButton.setBackground(Color.WHITE);
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
		editButton.setBounds(10, 231, 129, 23);
		infoPanel.add(editButton);
		
		JLabel costLabel = new JLabel("单价:");
		costLabel.setBounds(10, 24, 54, 15);
		infoPanel.add(costLabel);
		
		JButton newProductButton = new JButton("新商品");
		newProductButton.setBounds(10, 262, 129, 23);
		infoPanel.add(newProductButton);
		newProductButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewProductionWindow.showWindow(TabPanel.this);
			}
		});
		newProductButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		JPanel billPanel = new JPanel();
		billPanel.setBackground(Color.WHITE);
		billPanel.setBorder(new TitledBorder(null, "订单", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		billPanel.setBounds(348, 41, 335, 291);
		add(billPanel);
		billPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 22, 184, 200);
		billPanel.add(scrollPane);
		billListBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		billListBox.setBillList(billList);
		billListBox.addListSelectionListener(this);
		billListBox.addMouseListener(this);
		billListBox.setCellRenderer(new BillCellRenderer());
		
		scrollPane.setViewportView(billListBox);
		scrollPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
//		billListBox.setBorder(new TitledBorder(null, "已点商品", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblCount = new JLabel("数量:");
		lblCount.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCount.setBounds(224, 21, 44, 22);
		billPanel.add(lblCount);
		
		countTextBox = new JTextField();
		countTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		countTextBox.setBounds(268, 22, 57, 21);
		billPanel.add(countTextBox);
		countTextBox.addKeyListener(this);
		countTextBox.setColumns(10);
		
		JLabel lblPs = new JLabel("备注:");
		lblPs.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPs.setBounds(30, 232, 54, 22);
		billPanel.add(lblPs);
		
		psTextBox = new JTextField();
		psTextBox.setToolTipText("修改备注后按回车提交");
		psTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		psTextBox.setText("多加糖");
		psTextBox.setColumns(10);
		psTextBox.setBounds(69, 233, 253, 21);
		psTextBox.addKeyListener(this);
		billPanel.add(psTextBox);
		
		JLabel lblCost = new JLabel("订单价格:");
		lblCost.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblCost.setBounds(224, 113, 54, 15);
		billPanel.add(lblCost);
		
		
		totalCostLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		totalCostLabel.setForeground(Color.RED);
		totalCostLabel.setBounds(278, 113, 57, 15);
		billPanel.add(totalCostLabel);
		
		JLabel lblIncon = new JLabel("收款:");
		lblIncon.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblIncon.setBounds(224, 138, 33, 15);
		billPanel.add(lblIncon);
		
		
		lblDiff.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblDiff.setBounds(224, 168, 33, 15);
		billPanel.add(lblDiff);
		

		oweLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		oweLabel.setForeground(Color.RED);
		oweLabel.setBounds(278, 168, 44, 15);
		billPanel.add(oweLabel);
		
		JLabel lblPrize = new JLabel("总价格:");
		lblPrize.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblPrize.setBounds(224, 71, 54, 22);
		billPanel.add(lblPrize);
		
		totalCostTextBox = new JTextField();
		totalCostTextBox.setBackground(Color.WHITE);
		totalCostTextBox.setEditable(false);
		totalCostTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		totalCostTextBox.setColumns(10);
		totalCostTextBox.setBounds(268, 72, 57, 21);
		billPanel.add(totalCostTextBox);
		
		incomeTextBox = new JTextField();
		incomeTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		incomeTextBox.setText("$0.0");
		incomeTextBox.setForeground(Color.RED);
		incomeTextBox.setColumns(10);
		incomeTextBox.setBounds(276, 138, 49, 21);
		incomeTextBox.addKeyListener(this);
		incomeTextBox.addFocusListener(new FocusListener() { //用于处理焦点事件
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO 自动生成的方法存根
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (incomeTextBox.getText().startsWith("$")){
					incomeTextBox.setText(incomeTextBox.getText().substring(1));
					incomeTextBox.select(0, incomeTextBox.getText().length());
//				}else {
//					incomeTextBox.setText("");
				}
				
			}
		});
		
		billPanel.add(incomeTextBox);
		
				
				
				
		JButton submitOrder = new JButton("确认订单");
		submitOrder.setBounds(10, 264, 316, 23);
		billPanel.add(submitOrder);
		submitOrder.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		submitOrder.addActionListener(new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent e) {
						if (billList.size() == 0){
							ErrorDialog.showErrorMessage(null, "订单是空的！","订单中没有货物");
							return;
						}
						
						if ( billList.saveToFile() ){
							JOptionPane.showMessageDialog(TabPanel.this, "已经成功保存订单信息");
							Production.saveSaleInfoToFile(); //记录销量
							clearState();
						}else {
							ErrorDialog.showErrorMessage(null, "保存账单信息失败","");
						}
						
					}
				});
		
		
		smallJRadio.setBounds(10, 282, 54, 23);
		add(smallJRadio);
		smallJRadio.setSelected(true); //默认是小杯的
		sizeSelectBP.add(smallJRadio);

		middleJRadio.setBounds(66, 282, 62, 23);
		add(middleJRadio);
		sizeSelectBP.add(middleJRadio);
		

		bigJRadio.setBounds(130, 282, 54, 23);
		add(bigJRadio);
		
		//这是添加到按钮组上
		sizeSelectBP.add(bigJRadio);
		
		
		chckbxIce.setBounds(10, 303, 58, 23);
		chckbxIce.setEnabled(false);
		add(chckbxIce);
		
		
		chckbxMilk.setBounds(66, 303, 62, 23);
		chckbxMilk.setEnabled(false);
		add(chckbxMilk);
		
		chckbxSugar.setEnabled(false);
		chckbxSugar.setBounds(128, 303, 72, 23);
		add(chckbxSugar);
		
		setJRadioEnable(false); //默认不可选
		setCheckoutEnable(false); //默认不可选
	}
	
	/**
	 * 清空之前的数据
	 */
	public void clearState(){
		billList.removeAll();
		costTextBox.setText("");
		countTextBox.setText("");
		totalCostTextBox.setText("");
		psTextBox.setText("");
		totalCostLabel.setText("");
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
	
	/**
	 * 用于控制JList的事件
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) { //修改相应的数据显示
		if (e.getSource() == productListBox){ //如果是商品列表的话	
			ProductList p = (ProductList)e.getSource();
			int index = p.getSelectedIndex();
			if ( index != -1){
				Production production = p.getSelectedValue();
				if (production instanceof HasSize){ //如果有大小的选项的话
					setJRadioEnable(true); //设置大小是可以选择的
					if (smallJRadio.isSelected())
						((HasSize) production).setSize(HasSize.SMALL);
					if (middleJRadio.isSelected())
						((HasSize) production).setSize(HasSize.MIDDLE);
					if (bigJRadio.isSelected())
						((HasSize) production).setSize(HasSize.LARGE);
				}
				else {
					setJRadioEnable(false);
				}
				
				if (production instanceof HasIngredient){
					((HasIngredient) production).removeAllIngredients(); //先删除所有调料
					setCheckoutEnable(true);
					if (chckbxIce.isSelected())
						((HasIngredient) production).addIngredient(new Ice());
				
					if (chckbxMilk.isSelected())
						((HasIngredient) production).addIngredient(new Milk());
					
					if (chckbxSugar.isSelected())
						((HasIngredient) production).addIngredient(new Sugar());
					
				}
				else
					setCheckoutEnable(false);
				
				costTextBox.setText(production.figureCost() + "");
				
				descriptionTextArea.setText(production.description());

				
			}
			
		}
		else if(e.getSource() == billListBox){ //账单的话
			int index = billListBox.getSelectedIndex();
			if (index == -1)
				return;
			Production production = billListBox.getSelectedValue();
			countTextBox.setText(production.getCount() + "");
			totalCostTextBox.setText(production.figureCost() * production.getCount() + "");
			psTextBox.setText(production.getCustomerMSG());
//			//设置 JRadio 和Jcheckbox 的状态
//			if (production instanceof HasSize){
//				int size = ((HasSize) production).getSize();
//				switch (size) {
//				case HasSize.LARGE:
//					bigJRadio.setSelected(true);
//					break;
//				case HasSize.MIDDLE:
//					middleJRadio.setSelected(true);
//					break;
//				case HasSize.SMALL:
//					smallJRadio.setSelected(true);
//					break;
//				default:
//					break;
//				}
//			}
//			
//			if (production instanceof HasIngredient){
//				ArrayList<Ingredient> ingredients = ((HasIngredient) production).getIngredients();
//				if(ingredients.size() == 0)
//					return;
//				if (ingredients.contains(new Ice()))
//					chckbxIce.setSelected(true);
//				if (ingredients.contains(new Milk()))
//					chckbxMilk.setSelected(true);
//				if (ingredients.contains(new Sugar()))
//					chckbxSugar.setSelected(true);
//			}
//			
		}
	}
	
	public void addIngredients(HasIngredient p){
		p.removeAllIngredients(); //先除去
		if (chckbxIce.isSelected())
			p.addIngredient(new Ice());
		if (chckbxMilk.isSelected())
			p.addIngredient(new Milk());
		if (chckbxSugar.isSelected())
			p.addIngredient(new Sugar());
	}
	
	public void updateTotalCost(){
		double sum = billList.getBillCost();
		totalCostLabel.setText("$" + sum);
	}
	
	/**
	 * 订购商品
	 */
	public void order(){
		
		Production production = productListBox.getSelectedValue() ;
//		JOptionPane.showMessageDialog(productListBox, production.getSpecific() + "\n" + production.getCost() + "\n" + production.figureCost());
		//size和价格在都在选的时候就计算好了 所以这里要传进去的是 小杯的money
		if (production instanceof HasSize){
			int size = ((HasSize) production).getSize();
			((HasSize) production).setSize(HasSize.SMALL);
			double cost = production.getCost(); //这样得到是小杯的价格
			((HasSize) production).setSize(size); //重新设置回去
			production = BeverageFactory.order(production.getName(),cost, production.description(),size);
		}else {
			//没有大小的产品的生成代码
		}
		//添加调料
		if (production instanceof HasIngredient)
			addIngredients((HasIngredient) production);
		
		billList.add(production);
//		if (billList.size() == 1)
//			billListBox.updateCellSize(); //不然加第一个元素的时候 尺寸 会不对...
		
		updateTotalCost(); //修改显示得总价格
		
		production.addSaleCount(); //统计总的销售数量
		
		int index =	billListBox.indexOf(production); //此商品添加到的位置
											 //注意这里判断存不存在该元素 用的是 equals 方法
											 //但是如果用默认的equals方法 会比较所有字段,所以这里如果count字段不同的话 将会是不同的
											 //暂时修改了父类的equals 方法 解决该矛盾
		
//		ErrorDialog.showErrorMessage(null, " 找到的 index 是 " + index + "\n" + billList.getProductions().get(index).getSpecific(), "");
		
		//这样可以触发 ListSelection事件~借此更新画面
		
		billListBox.clearSelection();
		
		billListBox.setSelectedIndex(index);
		
		billListBox.ensureIndexIsVisible(index); //此方法保证 index 那个单元是可见的 就是会实时滚动过去~
		
	}
/* 用于实现 MouseListenr */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == productListBox && e.getClickCount() == 2){ //双击商品列表的内容  点单
			if (productListBox.getSelectedIndex() == -1)
				return ;
			order();
//			ErrorDialog.showErrorMessage(null, productListBox.getSelectedValue().getSpecific(),"");
			
		}else if(e.getSource() == billListBox && e.getClickCount() == 2){ //双击订单列表 删除这一项
			if (billListBox.getSelectedIndex() == -1)
				return ;
			int index = billListBox.getSelectedIndex();
			Production production = billListBox.getSelectedValue();
			production.setSaleCount(production.getName(), production.getSaleCount() - 1 );
			billList.remove(production);
			
			if (index > 0){
				billListBox.ensureIndexIsVisible(index - 1);
				billListBox.setSelectedIndex(index - 1);
			}
			updateTotalCost();
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

/* 用于实现 MouseListenr */
		
	
/* 用于实现 KeyListener */
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	/**
	 * 修改订单中具体商品的数量
	 */
	public void modifyCount(){
		try{
			if (billListBox.getSelectedIndex() == -1)
				return;
			billListBox.setEnabled(false);
			int count = Integer.parseInt(countTextBox.getText());
			billList.setCount(billListBox.getSelectedValue(), count);
			billListBox.update();
			billListBox.setEnabled(true);
			updateTotalCost();
		}catch(NumberFormatException error){
			ErrorDialog.showErrorMessage(null, "请输入正确的数字", "错误的数字格式");
		}
	}
	
	/**
	 * 计算要找出的钱
	 */
	public void figureOut(){
		try{
			if (billListBox.getSelectedIndex() == -1 )
				return;
			double income = Double.parseDouble(incomeTextBox.getText());
			double totalCost = billList.getBillCost();
			incomeTextBox.setText("$" + incomeTextBox.getText()); //加一个前缀
			double owe = (income - totalCost);
			if (owe < 0){
				ErrorDialog.showErrorMessage(null, "收款数不够","");
				return ;
			}
			oweLabel.setText("$" + owe);
		}catch(NumberFormatException error){
			ErrorDialog.showErrorMessage(null, "请输入正确的数字", "错误的数字格式");
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == countTextBox && e.getKeyCode() == KeyEvent.VK_ENTER){
			//修改数量
			modifyCount();

		}else if (e.getSource() == incomeTextBox && e.getKeyCode() == KeyEvent.VK_ENTER){
			//计算要找出的钱
			figureOut();
		}else if (e.getSource() == psTextBox && e.getKeyCode() == KeyEvent.VK_ENTER){
			//修改备注消息
			if (billListBox.getSelectedIndex() == -1)
				return ;
			billListBox.getSelectedValue().setCustomerMSG(psTextBox.getText());
		}else if (e.getSource() == productListBox && e.getKeyCode() == KeyEvent.VK_ENTER ){
			if (productListBox.getSelectedIndex() != -1)
				order(); //用回车键也可以订货
		}else if(e.getSource() == searchBox.getTextbox()){
			int key = e.getKeyCode();
			
			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_ENTER)
				searchBox.setSystemOperation(true); //说明是系统自定义的操作
			else
				searchBox.setSystemOperation(false);
			
			if (key == KeyEvent.VK_ALT || key == KeyEvent.VK_ENTER){ //这里用ALT键是因为 TAB ENTER 这些和系统有冲突
				Production p = searchBox.select();
				if (p != null){
					productListBox.requestFocus();//切换这个焦点
					productListBox.setSelectedAt(p);//选中这个
				}
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == searchBox.getTextbox()){
			if (searchBox.isSystemOperation() == true)
				return;
			searchBox.search();
		}
		
	}

	//处理 JRadio 和 Jcheckbox 事件
	@Override
	public void itemStateChanged(ItemEvent e) {
		//
	}


/* 用于实现 KeyListener */
}
