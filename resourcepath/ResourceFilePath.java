package exercise.resourcepath;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import exercise.factory.BeverageFactory;
import exercise.product.Beverage;
import exercise.product.Production;


public class ResourceFilePath {
	//用绝对路径的形式
	public static final String resourceDirectory = "/data/pic"; //图片等资源放在data/pic文件夹里面
	public static final String productDirectory = "/data/product"; //产品的文件夹
	public static final String beverageDirectory = productDirectory + "/beverage"; //饮料类的文件夹
	public static final String ingredientDirectory = productDirectory + "/ingredient"; //调料类
	public static final String recordDirectory = productDirectory + "/record"; //存放记录的文件夹
	public static final String saleRecordDirectory = recordDirectory + "/salerecord"; //订单
	public static String rootDirectory = null; 
	static{
		try {
			rootDirectory = ResourceFilePath.class.getResource("/").toURI().getPath(); //得到根目录的绝对位置
		} catch (URISyntaxException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static final String BeverageList = "all_beverage.txt";
	
	/**
	 * 记得关闭返回的文件
	 * @return 可读写的文件或者null
	 */
	public static RandomAccessFile readBeverage(){
		try {
			RandomAccessFile beverageFile = new RandomAccessFile(beverageDirectory + "/" + BeverageList, "rw");//可读可写
			return beverageFile;
		} catch (FileNotFoundException e) {
			//JOptionPane.showMessageDialog(null, "找不到" + beverageDirectory  + "/" + BeverageList);
			return null;
		} 
	}
	
	/**
	 * 注意关闭返回的文件。
	 * @param path 文件的路径
	 * @return
	 */
	public static RandomAccessFile openFile(String path){
		try {
			RandomAccessFile file = new RandomAccessFile(rootDirectory + "/" +  path, "rw");
			return file;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	
	/**
	 * 保存信息到文件中	价格	名字	描述
	 * @param p 用于判断产品的具体类型
	 * @return
	 */
	public static boolean saveToFile(Production p){
		String path = null;
		if (p == null)
			return false;
		else if (p instanceof Beverage)
			path = beverageDirectory;
		
		RandomAccessFile f = openFile(path + "/" +  p.getName());
		
		if (f == null){
			return false;
		}
		try {
			//注意java writeUTF 真特么坑爹。。。。。是改过的UTF格式 - - 蛋疼
			f.writeUTF(p.getCost() + ""); //cost
			f.writeUTF(p.getName());	//name
			f.writeUTF(p.description());	//description
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}finally{
			try {
				f.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static ArrayList<Beverage> readAllBeverage(){
		ArrayList<Beverage> all = new ArrayList<Beverage>();
		RandomAccessFile file = openFile(productDirectory + "/" + BeverageList);
		if (file == null){
			System.err.println("找不到文件");
			return null;
		}
		String name = null;
		String beverageName = null;
		double cost = 0.0;
		String description = null;
		try {
			while( file.getFilePointer() != file.length() ){
				name = file.readUTF();
				RandomAccessFile info = openFile(beverageDirectory + "/" + name);
				cost = Double.parseDouble(info.readUTF());
				beverageName = info.readUTF();
				description = info.readUTF();
				all.add((BeverageFactory.createNewBeverage(beverageName, cost, description)));
				info.close();
			}
			file.close();
			return all;
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;

	}
	
	public static void writeAllBeverage(){
		String name = "SmallFlyCoffee";
		RandomAccessFile file = openFile(productDirectory + "/" + BeverageList);
		try {
			file.writeUTF(name);
			for(int i = 0 ; i < 100 ; ++i)
				file.writeUTF(name + i);
			file.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<Beverage> bl = readAllBeverage();
		if (bl == null){
			System.err.println("NULL");
			return ;
		}
		for(Beverage b : bl){
			System.out.println(b.getSpecific());
		}
		System.out.println(bl.get(0).getAllSaleInfo());
	}
	
}
