package exercise;

import org.jb2011.lnf.beautyeye.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import exercise.customizegui.BillDialog;
import exercise.customizegui.ErrorDialog;
import exercise.product.BillList;
import exercise.product.Espresso;
import exercise.product.Pizza;
import exercise.product.Production;
import exercise.resourcepath.ResourceFilePath;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ChangeListener {
	BillList billList = new BillList(); //用于管理全部账单
	JTabbedPane MainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
	TabPanel beverageTab = new TabPanel(billList);
	private JPanel contentPane;
	TabPanel foodTab = new TabPanel(billList);
	JLabel itemPicture = new JLabel("商品图片:");
	JButton showBills = new JButton("全部订单");
	JButton sortButton = new JButton("按销量排序");

	
	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {
		try {
			UIManager.put("RootPane.setupButtonVisible",false); //不显示设置按钮
			BeautyEyeLNFHelper.frameBorderStyle = FrameBorderStyle.translucencyAppleLike;
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("TabbedPane.tabAreaInsets"
				    , new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0)); //tab 的左缩进
		} catch (Exception e) {
			//do nothing
		}
		MainFrame f = new MainFrame();
		ShowFrame.run(f,f.getWidth(),f.getHeight());
	}
	
	/**
	 * 为组件添加各种事件
	 */
	public void addListeners(){
		MainTabbedPane.addChangeListener(new ChangeListener() { //这里用匿名内部类，是因为要用到MainFrame中的其他参数
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane t = (JTabbedPane) e.getSource();
				int index = t.getSelectedIndex();
				if (index != -1){
					//do something
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws URISyntaxException 
	 */
	public MainFrame() throws FileNotFoundException, IOException, URISyntaxException {
		super("Coffee Shop");
		setResizable(false); //不可调整大小
		//setAlwaysOnTop(true);
		Production.readSaleInfoFromFile(); //获得销售数据
		addListeners();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779,781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PicturePane showPIC = new PicturePane();
		showPIC.setBounds(10, 25, 705, 268);
		try{
			showPIC.addPic(this.getClass().getResource(ResourceFilePath.resourceDirectory + "/logo.jpg").toURI().getPath()); // 加上toURI 可以解决 中文路径 以及不同系统间 的问题
			showPIC.setPic(0);
			contentPane.add(showPIC);
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, ResourceFilePath.resourceDirectory + "/logo.jpg");
		}
		
		MainTabbedPane.setForeground(Color.LIGHT_GRAY);
		MainTabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		MainTabbedPane.setBounds(10, 296, 716, 373);
		MainTabbedPane.addChangeListener(this);
		contentPane.add(MainTabbedPane);
		

		
		MainTabbedPane.addTab("饮料", null, beverageTab, null);
		beverageTab.setLayout(null);
		beverageTab.setCategory(new Espresso(0));
		
		
		MainTabbedPane.addTab("食物", null, foodTab, null);
		foodTab.setCategory(new Pizza());


//		foodTab.setBillList(billList); //同一份账单
//		beverageTab.setBillList(billList);
		
		foodTab.getOrderListBox().relyTo(billList); //这样感觉更灵活些
		beverageTab.getOrderListBox().relyTo(billList);
//		billList.addListDataListener(foodTab,beverageTab); //监听List内容的变化
		
		
		
		itemPicture.setFont(new Font("微软雅黑", Font.PLAIN, 11));
		itemPicture.setForeground(RandomColor.getColor());
		itemPicture.setBounds(10, 0, 160, 26);
		contentPane.add(itemPicture);
		showBills.setBounds(551, 672, 135, 23);
		contentPane.add(showBills);
		showBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> names = ResourceFilePath.readAllItem(ResourceFilePath.BillList);
				if (names.size() == 0){
					ErrorDialog.showErrorMessage(null, "还没有账单记录", "空的账单记录");
				}
				else{
					BillDialog.showWindow();
					ErrorDialog.showErrorMessage(null, Production.getAllSaleInfo(), "全部商品的销量统计");
				}
			}
		});
		
		
		showBills.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		sortButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		sortButton.setBounds(10, 672, 93, 23);
		sortButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				foodTab.sortTheProductionList();
				beverageTab.sortTheProductionList();
				
			}
		});
		contentPane.add(sortButton);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == MainTabbedPane){
			if (MainTabbedPane.getSelectedIndex() == 0)
				beverageTab.updateTotalCost();
			else {
				foodTab.updateTotalCost();
			}
		}
	}
}
