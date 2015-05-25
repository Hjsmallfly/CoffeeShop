package exercise.customizegui;

import java.awt.BorderLayout;







import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import exercise.product.BillList;
import exercise.resourcepath.ResourceFilePath;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextArea;

public class BillDialog extends JDialog {
	
	private ArrayList<String> filename = ResourceFilePath.readAllItem(ResourceFilePath.BillList);
	JTextArea infomationTextBox = new JTextArea();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BillDialog dialog = new BillDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void showWindow(){
		try {
			BillDialog dialog = new BillDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BillDialog() {
		setBounds(100, 100, 519, 355);
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 10, 196, 242);
			panel.add(scrollPane);
			
			JList billList = new JList(filename.toArray());
			billList.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			billList.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (billList.getSelectedIndex() == -1)
						return ;
					String info = BillList.readFromFile(billList.getSelectedValue().toString());
					infomationTextBox.setText(info);
				}
			});
			scrollPane.setViewportView(billList);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(216, 8, 243, 244);
			panel.add(scrollPane_1);
			
			
			scrollPane_1.setViewportView(infomationTextBox);
			infomationTextBox.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			
			
			
		}
	}
}
