package exercise;

import org.jb2011.lnf.beautyeye.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import exercise.resourcepath.ResourceFilePath;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	JTabbedPane MainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
	JPanel beverageTab = new TabPanel();
	private JPanel contentPane;
	JPanel foodTab = new TabPanel();
	JLabel recommendation = new JLabel("商品图片:");
	JButton btnLog = new JButton("全部订单");
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
	 * Create the frame.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws URISyntaxException 
	 */
	public MainFrame() throws FileNotFoundException, IOException, URISyntaxException {
		super("Coffee Shop");
		setResizable(false); //不可调整大小
		setAlwaysOnTop(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779,838);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PicturePane showPIC = new PicturePane();
		showPIC.setBounds(10, 45, 705, 277);
		try{
			showPIC.addPic(this.getClass().getResource(ResourceFilePath.resourceDirectory + "/logo.jpg").toURI().getPath()); // 加上toURI 可以解决 中文路径 以及不同系统间 的问题
			showPIC.setPic(0);
			contentPane.add(showPIC);
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "找不到文件!");
		}
		
		
		MainTabbedPane.setForeground(Color.LIGHT_GRAY);
		MainTabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		MainTabbedPane.setBounds(10, 332, 716, 385);
		contentPane.add(MainTabbedPane);
		
		String[] strs = new String[]{"Mocha","Expreeso","Caffee"};
		
		
		MainTabbedPane.addTab("食物", null, foodTab, null);
		
		
		
		MainTabbedPane.addTab("咖啡", null, beverageTab, null);
		beverageTab.setLayout(null);

		
		
		recommendation.setFont(new Font("微软雅黑", Font.PLAIN, 11));
		recommendation.setForeground(RandomColor.getColor());
		recommendation.setBounds(10, 10, 160, 26);
		contentPane.add(recommendation);
		
		
		btnLog.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnLog.setBounds(554, 716, 140, 23);
		contentPane.add(btnLog);
	}
}
