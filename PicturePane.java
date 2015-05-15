package exercise;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PicturePane extends JPanel {
	private Image src; //ͼƬ
	private Image fitPic; //ѹ���ߴ���ͼƬ
	public PicturePane() {
		addMouseListener(new ChoosePic()); //˫��ѡ��ͼƬ
	}
	
	/**
	 * 设置panel显示的图片
	 * @param filename 图片路径
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void setPic(String filename) throws FileNotFoundException, IOException {
		src = ImageIO.read(new FileInputStream(filename));
		double rate  = (double) PicturePane.this.getHeight() / PicturePane.this.getWidth();
		fitPic = src.getScaledInstance(PicturePane.this.getWidth(), (int) (PicturePane.this.getWidth() * rate ), Image.SCALE_DEFAULT); //压缩图片
		PicturePane.this.repaint();//调用重绘方法
	}
	
	@Override //进行绘图ͼ����
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (fitPic != null){
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(fitPic, 0, 0, this);
		}
		g.dispose();
	}
	
	
	public class ChoosePic extends MouseAdapter{
		public void mouseClicked(MouseEvent e) { 
			if (e.getClickCount() == 2){ //˫��
				JFileChooser fileChooser = new JFileChooser();
				int val = fileChooser.showOpenDialog(PicturePane.this);
				if (val == JFileChooser.APPROVE_OPTION){
					try {
						setPic(fileChooser.getSelectedFile().toString());
					} catch (IOException e1) {
						//ͼƬ�򿪴���
					}  
				}
					
			}
		}
	
	}
}
