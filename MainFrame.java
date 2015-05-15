package exercise;

import org.jb2011.lnf.beautyeye.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

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

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
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
	 */
	public MainFrame() throws FileNotFoundException, IOException {
		super("Coffee Shop");
		setResizable(false); //不可调整大小
		setAlwaysOnTop(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674,696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PicturePane showPIC = new PicturePane();
		showPIC.setBounds(10, 36, 604, 218);
		showPIC.addPic(this.getClass().getResource("/data/recommendation.jpg").toString().substring(6));
		showPIC.setPic(0);
		contentPane.add(showPIC);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tabbedPane.setBounds(10, 264, 604, 311);
		contentPane.add(tabbedPane);
		
		JPanel tab1 = new TabPanel();
		tabbedPane.addTab("咖啡", null, tab1, null);
		tab1.setLayout(null);
		
		String[] strs = new String[]{"Mocha","Expreeso","Caffee"};
		
		JPanel panel_4 = new TabPanel();
		tabbedPane.addTab("食物", null, panel_4, null);

		
		JLabel recommendation = new JLabel("商品图片:");
		recommendation.setFont(new Font("微软雅黑", Font.PLAIN, 11));
		recommendation.setForeground(RandomColor.getColor());
		recommendation.setBounds(10, 10, 160, 26);
		contentPane.add(recommendation);
		
		JButton btnLog = new JButton("全部订单");
		btnLog.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnLog.setBounds(474, 585, 140, 23);
		contentPane.add(btnLog);
	}
}
