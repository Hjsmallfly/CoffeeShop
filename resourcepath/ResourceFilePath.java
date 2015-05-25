package exercise.resourcepath;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.text.BreakIterator;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import exercise.customizegui.ErrorDialog;
import exercise.factory.BeverageFactory;
import exercise.factory.FoodFactory;
import exercise.product.Beverage;
import exercise.product.Food;
import exercise.product.Production;
import exercise.usefulinterface.HasSize;


public class ResourceFilePath {
	//用绝对路径的形式
	public static final String resourceDirectory = "/data/pic"; //图片等资源放在data/pic文件夹里面
	public static final String productDirectory = "/data/product"; //产品的文件夹
	public static final String beverageDirectory = productDirectory + "/beverage"; //饮料类的文件夹
	public static final String foodDirectory = productDirectory + "/food"; //食物类的文件夹
	public static final String ingredientDirectory = productDirectory + "/ingredient"; //调料类
	public static final String recordDirectory = productDirectory + "/record"; //存放记录的文件夹
	public static final String saleRecordDirectory = recordDirectory + "/salerecord"; //商品的交易量
	public static final String billRecordDirectory = recordDirectory + "/billrecord"; //订单信息
	public static String rootDirectory = null; 
	static{ //得到根目录的绝对位置
		try {
			rootDirectory = ResourceFilePath.class.getResource("/").toURI().getPath();
		} catch (URISyntaxException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static final String BeverageList = "all_beverage.txt";
	public static final String BillList = "billlist.txt"; //存放所有交易记录
	public static final String FoodList = "all_food.txt";
	public static final String SaleList = "all_sale.txt"; //存放各种商品的全部交易记录
	
	
	
	/**
	 * 如果是rw模式的话 文件不存在的话 便会创建。若是r模式文件不存在的话返回null
	 * @param path	文件路径
	 * @param model r 或者 rw 
	 * @return
	 */
	public static RandomAccessFile openFile(String path,String model){
		try {
			RandomAccessFile file = new RandomAccessFile(rootDirectory + "/" +  path, model);
			return file;
		} catch (FileNotFoundException e) {
			//ErrorDialog.showErrorMessage(null, "找不到文件:" + rootDirectory + "/" +  path );
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
		if (p instanceof Beverage)
			path = beverageDirectory;
		else if (p instanceof Food)
			path = foodDirectory;
		RandomAccessFile f = openFile(path + "/" +  p.getName(),"rw");
		
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
	
	
	public static Production readProductionInfo(String path){
		RandomAccessFile file = openFile( path, "r");
		if (file == null){
//			ErrorDialog.showErrorMessage(null, "找不到:" + path, "找不到文件");
			return null;
		}
		double cost = 0.0;
		String name = null;
		String description = null;
		try{	
			cost = Double.parseDouble(file.readUTF());
			name = file.readUTF();
			description = file.readUTF();
			Production production = BeverageFactory.order(name, cost, description, HasSize.SMALL);
			file.close();
			return production;
		}catch(IOException e){
			ErrorDialog.showErrorMessage(null, ResourceFilePath.beverageDirectory + "/"  + name + " 文件损坏", "文件损坏");
			return null;
		}
		
		
	}
	
	public static ArrayList<Food> readAllFood(){
		ArrayList<String> name = readAllItem(FoodList);
		if ( name.size() == 0 ){
			ErrorDialog.showErrorMessage(null, "新建立的列表文件", "");
			return null;
		}
		StringBuilder sb = new StringBuilder("找不到下列文件:\n");
		ArrayList<Food> allFood = new ArrayList<Food>();
		
		for(String path : name){
			Production production = readProductionInfo(  foodDirectory + "/" + path);
			if (production != null){
				allFood.add(FoodFactory.order(production.getName(), production.getCost(), production.description(), HasSize.SMALL));
			}else{
				sb.append(foodDirectory + "/" + path + "\n");
			}
		}
		if (!sb.toString().equals("找不到下列文件:\n"))
			ErrorDialog.showErrorMessage(null, sb.toString(), "找不到下列文件");
		return allFood;
	}
	
	
	/**
	 * 读取清单中所有的饮料
	 * @return null 如果找不到清单文件
	 */
	public static ArrayList<Beverage> readAllBeverage(){
		ArrayList<Beverage> all = new ArrayList<Beverage>();
		RandomAccessFile file = openFile(productDirectory + "/" + BeverageList,"r");
		if (file == null){
			ErrorDialog.showErrorMessage(null, "找不到 " + productDirectory + "/" + BeverageList, "找不到文件");
			return null;
		}
		StringBuilder errMsg = new StringBuilder(); //用于统计找不到的文件
		boolean missFiles = false;
		String name = null;
		String beverageName = null;
		double cost = 0.0;
		String description = null;
		try {
			for(; file.getFilePointer() != file.length() ; ){
				name = file.readUTF();	
				RandomAccessFile info = openFile(beverageDirectory + "/" + name,"r");
				if (info != null){
					try{	
						cost = Double.parseDouble(info.readUTF());
						beverageName = info.readUTF();
						description = info.readUTF();
					}catch(IOException e){
						ErrorDialog.showErrorMessage(null, ResourceFilePath.beverageDirectory + "/"  + name + " 文件损坏", "文件损坏");
						continue; //跳过添加的动作 帅气~
					}
					all.add((BeverageFactory.createNewBeverage(beverageName, cost, description,HasSize.SMALL))); //默认是小杯
				}else {
					missFiles = true;
					errMsg.append(beverageDirectory + "/" + name + "\n");
				}
				if (info != null)
					info.close();
			}
			if (missFiles)
				ErrorDialog.showErrorMessage(null, "找不到下列文件:\n" +  errMsg.toString(),"找不到文件");
			file.close();
			return all;
		} catch (IOException e) {
			ErrorDialog.showErrorMessage(null, e.getMessage(), e.toString());
			e.printStackTrace();
		}
		return all;
	}
	
	/**
	 * 修改饮料的清单文件
	 */
//	public static void writeAllBeverage(){
//		String name = "Coffee";
//		RandomAccessFile file = openFile(productDirectory + "/" + BeverageList,"rw");
//		try {
//			file.writeUTF(name);
//			for(int i = 0 ; i < 100 ; ++i)
//				file.writeUTF(name + i);
//			file.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) throws IOException {
//		writeAllBeverage();
//		BeverageFactory.createNewBeverage("Coffee", 1.0, "Test Coffee",HasSize.SMALL); //注意与存的时候保持一致
//		for(int i = 0 ; i < 100 ; ++i){
//			Production p = BeverageFactory.createNewBeverage("Coffee" + i, 1.0, "Test Coffee",HasSize.SMALL);
//		}
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0 ; i < 100 ; ++i){
			names.add("Pizza" + i);
			FoodFactory.createNewFood("Pizza" + i, 5.0,"just pizza", HasSize.SMALL);
		}
		updateItemList(names, FoodList);
		readAllFood();
	}
	
	/**
	 * 取得 某种类型 事物的清单
	 * 目前有 饮料清单 账单的清单
	 * @param path 相对路径即可 即 绝对路径的前缀会自动得出
	 * @return 如果返回的list的size是0的话 说明这个文件是新创建的
	 */
	public static ArrayList<String> readAllItem(String path){
		if (path.equals(BeverageList))
			path = productDirectory + "/"  + BeverageList;
		else if(path.equals(BillList))
			path = recordDirectory + "/" + BillList;
		else if (path.equals(FoodList))
			path = productDirectory + "/" + FoodList;
		
		RandomAccessFile file = openFile(path, "rw");

		ArrayList<String> names = new ArrayList<String>();
		try{
			while(file.getFilePointer() != file.length()){
				names.add(file.readUTF());
			}
			file.close();
			return names;
		}catch(IOException e){
			e.printStackTrace();
			return names;
		}
	}
	
	public static void updateItemList(ArrayList<String> names,String path){
		if (names.size() == 0)
			return;
		if (path.equals(BeverageList))
			path = productDirectory + "/"  + BeverageList;
		else if (path.equals(BillList))
			path = recordDirectory + "/" + BillList;
		else if (path.equals(FoodList))
			path = productDirectory + "/" + FoodList;
		RandomAccessFile file = openFile(path,"rw");
		try{
			for(String s : names){
				file.writeUTF(s);
			}
			file.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
