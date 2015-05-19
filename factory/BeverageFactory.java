package exercise.factory;

import exercise.product.Beverage;
import exercise.product.Expresso;
import exercise.product.SmallFlyCoffee;

/**
 * 用于产生饮料
 * @author STU_nwad
 *
 */
public class BeverageFactory {
	/**
	 * 生产相应的饮料
	 * @param name 饮料的名字
	 * @return
	 */
	public static Beverage createBeverage(String name,int size){
		Beverage beverage = null;
		if (name.equals("Expresso")){
			beverage = new Expresso(size);
		}else if(name.equals("SmallFlyCoffee")){
			beverage = new SmallFlyCoffee(size);
		}
		
		if (beverage != null){
			beverage.addSaleCount(beverage.getName());
		}
		
		return beverage;
	}
}
