package exercise.customizegui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;
import exercise.product.Production;

/**
 * 用于控制JList中项目的显示
 * @author STU_nwad
 *
 */
@SuppressWarnings("serial")
public class BillCellRenderer extends JTextArea implements ListCellRenderer<Production> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Production> list, Production value, int index,
			boolean isSelected, boolean cellHasFocus) {
		setFont( new Font("微软雅黑", Font.PLAIN, 12) );
		setRows(2);
		setColumns(14);
		setPreferredSize(getPreferredSize());//把控件大小设置为合适的大小
		setLineWrap(true); //自动换行
		setWrapStyleWord(true);
		TitledBorder titlebBorder = new TitledBorder("");
		titlebBorder.setTitleColor(Color.blue);
		setBorder(titlebBorder);
		list.setFixedCellHeight(getPreferredSize().height);
		list.setFixedCellWidth(getPreferredSize().width);
		Production production = (Production) value;
		setText(production.getSpecific() + "\n\t" + production.getCount() + " x " + production.figureCost());
		 if (isSelected) {
	            setBackground(Color.GRAY);
	            setForeground(Color.WHITE);


	        } else {
	        	setBackground(Color.WHITE);
	            setForeground(Color.BLACK);
	        };
		return this;
	}

}
