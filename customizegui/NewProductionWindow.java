package exercise.customizegui;
import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextArea;
import exercise.TabPanel;
import exercise.factory.BeverageFactory;
import exercise.factory.FoodFactory;
import exercise.resourcepath.ResourceFilePath;
import exercise.usefulinterface.HasSize;

@SuppressWarnings("serial")
public class NewProductionWindow extends JDialog {
	private JTextField nameTextBox;
	private JTextField prizeTextBox;
	JPanel contentPane = new JPanel();
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton beverageJRadio = new JRadioButton("饮料");
	JRadioButton foodJRadio = new JRadioButton("食物");
	JTextArea textArea = new JTextArea();
	
	private TabPanel mainPanel;
	
	ArrayList<String> beverageNames = new ArrayList<String>();
	
	ArrayList<String> foodNames = new ArrayList<String>();
	
	public static void showWindow(TabPanel t){
		try {
			
			NewProductionWindow dialog = new NewProductionWindow();
			dialog.setTabPanel(t);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTabPanel(TabPanel t){
		this.mainPanel = t;
	}
	
	/**
	 * Create the dialog.
	 */
	public NewProductionWindow() {
		setBounds(100, 100, 237, 366);
		getContentPane().setLayout(new BorderLayout());
		{

			getContentPane().add(contentPane, BorderLayout.CENTER);
			

			beverageJRadio.setBounds(0, 6, 69, 23);
			

			foodJRadio.setBounds(111, 6, 66, 23);
			
			JLabel label = new JLabel("名称:");
			label.setBounds(10, 38, 30, 15);
			
			nameTextBox = new JTextField();
			nameTextBox.setBounds(50, 35, 127, 21);
			nameTextBox.setColumns(10);
			
			JLabel label_1 = new JLabel("单价:");
			label_1.setBounds(10, 66, 30, 15);
			
			prizeTextBox = new JTextField();
			prizeTextBox.setBounds(50, 66, 127, 21);
			prizeTextBox.setColumns(10);
			contentPane.setLayout(null);
			contentPane.add(beverageJRadio);
			contentPane.add(foodJRadio);
			contentPane.add(label);
			contentPane.add(nameTextBox);
			contentPane.add(label_1);
			contentPane.add(prizeTextBox);
			

			textArea.setFont(new Font("微软雅黑", Font.PLAIN, 13));
			textArea.setBounds(10, 97, 167, 146);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setBorder(new TitledBorder("描述信息"));
			contentPane.add(textArea);
			
			buttonGroup.add(beverageJRadio);
			buttonGroup.add(foodJRadio);
			beverageJRadio.setSelected(true);
			
			JButton confirmButton = new JButton("添加");
			confirmButton.setBounds(10, 250, 167, 23);
			contentPane.add(confirmButton);
			
			confirmButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<String> names = null;
					String newItem = nameTextBox.getText();
					String path = null;
					if (newItem.trim().equals("")){
						ErrorDialog.showErrorMessage(null, "请输入文件名", "错误");
						return ;
					}
					if (beverageJRadio.isSelected()){
						names = beverageNames;
						path = ResourceFilePath.BeverageList;
						if (names.contains(newItem)){
							ErrorDialog.showErrorMessage(null, "已经存在该饮料!", "错误");
							return ;
						}
					}else if (foodJRadio.isSelected()){
						names = foodNames;
						path = ResourceFilePath.FoodList;
						if (names.contains(newItem)){
							ErrorDialog.showErrorMessage(null, "已经存在该食物!", "错误");
							return ;
						}
					}
					
			
					try {
							
							double cost = Double.parseDouble(prizeTextBox.getText());
							String description = textArea.getText();
							if (beverageJRadio.isSelected())
								BeverageFactory.createNewBeverage(newItem, cost, description,HasSize.SMALL);
							else
								FoodFactory.createNewFood(newItem, cost, description, HasSize.SMALL);
							
							names.add(newItem); //维护商品列表
							ResourceFilePath.updateItemList(names, path);
							JOptionPane.showMessageDialog(NewProductionWindow.this, "成功加入商品");
							if (mainPanel != null)
								mainPanel.updateProductionList();
					} catch (NumberFormatException e2) {
							ErrorDialog.showErrorMessage(null, "请输入正确的数字", "错误");
							return ;
					}
					
				}
			});
			
			setTitle("添加新商品");
			
			beverageNames = ResourceFilePath.readAllItem(ResourceFilePath.BeverageList); //读饮料
			
			foodNames = ResourceFilePath.readAllItem(ResourceFilePath.FoodList); //食物的清单
			
			setLocationRelativeTo(null); //居中显示
		}
	}
}
