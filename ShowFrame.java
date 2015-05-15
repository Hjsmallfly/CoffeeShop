package exercise;
  
import javax.swing.*;
public class ShowFrame {
	public static void run(final JFrame f,final int width,final int height){

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//f.setTitle(f.getClass().getName());
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width,height);
				f.setLocationRelativeTo(null); //����
				f.setVisible(true);
				
			}
		});
	}
	
	public static void run(final JFrame f){
		run(f,640,320);
	}
	
}
  
