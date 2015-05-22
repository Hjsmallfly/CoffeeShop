package exercise.factory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;

import exercise.customizegui.ErrorDialog;
import exercise.product.Beverage;
import exercise.product.Espresso;
import exercise.product.SmallFlyCoffee;
import exercise.resourcepath.ResourceFilePath;

/**
 * 用于产生饮料
 * @author STU_nwad
 *
 */
public class BeverageFactory {
	/**
	 * 生产相应的饮料(要求饮料已经存在于产品清单中)
	 * @param name 饮料的名字
	 * @return
	 */
	public static Beverage createBeverage(String name,int size){
		Beverage beverage = null;
		RandomAccessFile file = ResourceFilePath.openFile(ResourceFilePath.beverageDirectory + "/"  + name,"rw");
		if (file != null){
			try {
				beverage = new Beverage() {
					{ //代码块 用于初始化咯
						cost = Double.parseDouble(file.readUTF());
						setName(file.readUTF());
						description = file.readUTF();
					}
				};
			} catch (IOException e) {
				ErrorDialog.showErrorMessage(null, ResourceFilePath.beverageDirectory + "/"  + name + " 文件损坏", "文件损坏");
				beverage = null;
			}finally{
				try {
					file.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
		if (beverage != null){
			beverage.addSaleCount(beverage.getName());
		}
		
		return beverage;
	}

	/**
	 * 生成新的饮料
	 * @param name 新饮料的名字
	 * @param cost 新饮料的价格
	 * @param description 新饮料的描述
	 */
	public static Beverage createNewBeverage(String name,double cost,String description){
		Beverage beverage = new Espresso(0);
		beverage.setName(name);
		beverage.setCost(cost);
		beverage.setDescription(description);
		beverage.saveTofile();
		return beverage;
	}

	/**
	 * 返回饮料对象 
	 * @param name 饮料名
	 * @param cost 单价
	 * @param description 描述
	 * @param size 大小
	 * @return
	 */
	public static Beverage order(String name,double cost,String description,int size){
		Beverage beverage = new Espresso(size);
		beverage.setName(name);
		beverage.setCost(cost);
		beverage.setDescription(description);
		return beverage;
		
	}

}
