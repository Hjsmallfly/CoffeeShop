package exercise.factory;
import exercise.product.Food;
import exercise.product.Pizza;


public class FoodFactory {
	/**
	 * 生成新的食物 会存入文件中
	 * @param name 新食物的名字
	 * @param cost 新食物的价格
	 * @param description 新食物的描述
	 */
	public static Food createNewFood(String name,double cost,String description,int size){
		Food food = new Pizza();
		food.setSize(size);
		food.setName(name);
		food.setCost(cost);
		food.setDescription(description);
		food.saveTofile();
		return food;
	}

	/**
	 * 返回食物对象 
	 * @param name 食物名
	 * @param cost 单价
	 * @param description 描述
	 * @param size 大小
	 * @return
	 */
	public static Food order(String name,double cost,String description,int size){
		Food food = new Pizza();
		food.setSize(size);
		food.setName(name);
		food.setCost(cost);
		food.setDescription(description);
		return food;
	}
}
