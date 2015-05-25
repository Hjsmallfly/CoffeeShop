package exercise.factory;
import exercise.product.Beverage;
import exercise.product.Espresso;


/**
 * 用于产生饮料
 * @author STU_nwad
 *
 */
public class BeverageFactory {
	/**
	 * 生成新的饮料 会存入文件中
	 * @param name 新饮料的名字
	 * @param cost 新饮料的价格
	 * @param description 新饮料的描述
	 */
	public static Beverage createNewBeverage(String name,double cost,String description,int size){
		Beverage beverage = new Espresso(size);
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
