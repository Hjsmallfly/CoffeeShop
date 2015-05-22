package exercise.customizegui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;



import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import exercise.product.Production;

/**
 * 用于控制JList中项目的显示
 * @author STU_nwad
 *
 */
public class BillCellRenderer extends JTextArea implements ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setFont( new Font("微软雅黑", Font.PLAIN, 12) );
		setRows(3);
		setColumns(14);
		setPreferredSize(getPreferredSize());
		setLineWrap(true); //自动换行
		setWrapStyleWord(true);
		list.setFixedCellHeight(getPreferredSize().height);
		list.setFixedCellWidth(getPreferredSize().width);
		Production production = (Production) value;
		setText(production.getSpecific() + "\n" + production.getCount() + " x " + production.figureCost());
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
