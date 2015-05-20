package exercise;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exercise.product.Beverage;
import exercise.product.Espresso;
import exercise.product.Production;
import gui.SwingConsole;

public class JListTest extends JFrame {
	private JList<Production> productionJList = new JList<Production>();
	private DefaultListModel<Production> productionListModel = new DefaultListModel<Production>();
	public JListTest() {
		productionJList.setModel(productionListModel);
		productionListModel.addElement(new Espresso(Beverage.LARGE));
		productionListModel.addElement(new Espresso(Beverage.LARGE));
		productionJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && productionJList.getSelectedIndex() != -1)
					productionListModel.remove(productionJList.getSelectedIndex());
			}
		});
		add(productionJList);
	}
	public static void main(String[] args) {
		SwingConsole.run(new JListTest());
	}
}
