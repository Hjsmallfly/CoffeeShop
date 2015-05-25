package exercise;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PicturePane extends JPanel {
	private ArrayList<Image>  srcPICs = new ArrayList<Image>()  ; //保存原图片的List
	private Image fitPic; //压缩之后的图片
	public PicturePane() {
		//addMouseListener(new ChoosePic());//双击弹出文件选择窗口
	}
	
	/**
	 * 设置panel显示的图片
	 * @param filename 图片路径
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void addPic(String filename) throws FileNotFoundException, IOException {
		srcPICs.add( ImageIO.read(new FileInputStream(filename)) ); //添加图片
	}
	
	/**
	 * 
	 * @param index 需要显示的图片的下标
	 */
	public void setPic(int index){
		if (index < 0 || index >= srcPICs.size())
			return;
		double rate  = (double) PicturePane.this.getHeight() / PicturePane.this.getWidth();
		fitPic = srcPICs.get(index).getScaledInstance(PicturePane.this.getWidth(), (int) (PicturePane.this.getWidth() * rate ), Image.SCALE_DEFAULT); //压缩图片
		PicturePane.this.repaint();//调用重绘方法
	}
	
	@Override //进行绘图 覆盖该方法会使用 双缓冲 技术
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
			if (e.getClickCount() == 2){ //双击
				JFileChooser fileChooser = new JFileChooser();
				int val = fileChooser.showOpenDialog(PicturePane.this);
				if (val == JFileChooser.APPROVE_OPTION){
					try {
						addPic(fileChooser.getSelectedFile().toString());
					} catch (IOException e1) {
						//图片打开错误
					}  
				}
					
			}
		}
	
	}
}
