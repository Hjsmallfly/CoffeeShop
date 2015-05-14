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

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.put("RootPane.setupButtonVisible",false); //²»ÏÔÊ¾ÉèÖÃ°´Å¥
			BeautyEyeLNFHelper.frameBorderStyle = FrameBorderStyle.translucencyAppleLike;
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("TabbedPane.tabAreaInsets"
				    , new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0)); //tab µÄ×óËõ½ø
		} catch (Exception e) {
			//do nothing
		}
		MainFrame f = new MainFrame();
		ShowFrame.run(f,f.getWidth(),f.getHeight());
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		super("Coffee Shop");
		
		setAlwaysOnTop(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666,690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel showPIC = new PicturePane();
		showPIC.setBounds(10, 36, 604, 202);
		//showPIC.setSize(604, 202);
		showPIC.setBorder(new TitledBorder(""));
		contentPane.add(showPIC);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 248, 604, 296);
		contentPane.add(tabbedPane);
		
		JPanel tab1 = new JPanel();
		tabbedPane.addTab("Coffee", null, tab1, null);
		tab1.setLayout(null);
		
		String[] strs = new String[]{"Mocha","Expreeso","Caffee"};
		
		JList list = new JList(strs);
		list.setBorder(new TitledBorder("Items"));
		list.setBounds(10, 41, 116, 216);
		list.setForeground(RandomColor.getColor());
		tab1.add(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder("Info"));
		panel_1.setBounds(130, 41, 246, 216);
		tab1.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 17, 234, 189);
		panel_1.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 10, 214, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 37, 214, 128);
		panel.add(textArea);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder("Purchase"));
		panel_2.setBounds(375, 41, 214, 216);
		tab1.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(6, 21, 198, 185);
		panel_2.add(panel_3);
		
		JList list_1 = new JList(strs);
		list_1.setBounds(10, 10, 178, 128);
		panel_3.add(list_1);
		
		JComboBox comboBox_1 = new JComboBox(strs);
		comboBox_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 11));
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(10, 10, 579, 21);
		tab1.add(comboBox_1);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Drink", null, panel_4, null);

		
		JLabel recommendation = new JLabel("Recommendation");
		recommendation.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 11));
		recommendation.setForeground(RandomColor.getColor());
		recommendation.setBounds(10, 10, 160, 26);
		contentPane.add(recommendation);
	}
}
