package exercise.customizegui;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.Font;

/**
 * 用于显示错误消息(用作消息窗口也行)
 * @author STU_nwad
 *
 */
@SuppressWarnings("serial")
public class ErrorDialog extends JDialog {
	private String errorMSG = null;
	
	private static ArrayList<String> errors = new ArrayList<String>(); //用来保存已经出现过的errormsg
	
	private JTextArea errorDisplay = new JTextArea();
	
	public ErrorDialog(JFrame parent,String msg) {
		super(parent,"ErrorMessages",false); //最后这个参数是 控制 是否是模式窗口 如果是true的话 会阻塞其他窗口
		errorMSG = msg;
		setAlwaysOnTop(true);
		setSize(460,240);
		errorDisplay.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		errorDisplay.setLineWrap(true);
		errorDisplay.setText(msg);
		getContentPane().add(new JScrollPane(errorDisplay));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		if (parent == null)
			setLocationRelativeTo(null); //居中
		setVisible(true);
	}
	
	public static void showErrorMessage(JFrame parent,String msg,String title){
//		if (errors.contains(msg))
		//	return;//不重复显示错误消息
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				errors.add(msg); //记录
				new ErrorDialog(null,msg).setTitle(title);
				
			}
		});
	}
	
	public static void main(String[] args) {
		showErrorMessage(null, "test","test");
	}
	
}