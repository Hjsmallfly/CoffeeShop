package exercise.product;

import java.util.ArrayList;
import java.util.Arrays;

import exercise.factory.BeverageFactory;

/**
 * 饮料类
 * @author STU_nwad
 *
 */
public abstract class Beverage extends Production {
	
//	private BeverageFactory factory = new BeverageFactory();
	
	protected ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(); //保存是否加了调料 
	
	protected int size;//饮料的 大 中 小 杯
	
	//用常量定义大小
	public static final int LARGE = 0;
	public static final int MIDDLE = 1;
	public static final int SMALL = 2;
	public static String[] sizeStr = new String[]{"大杯","中杯","小杯"};
	
	
	
	/*构造函数*/
	public Beverage() {
		
	}
	
	public Beverage(int size){
		this.size = size;
	}
	/*构造函数*/
	

	
	//订饮料
	public static Beverage order(String type,int size){
		return 
				BeverageFactory.createBeverage(type,size);
		
		
	}
	
	
	/*get方法*/
	
	@Override
	public String getSpecific() { //获得具体信息
		StringBuilder sb = new StringBuilder();
		sb.append(name + "(" + sizeStr[size] + ") ");
		for(Ingredient i : ingredients)
			sb.append(i.getName() + " ");
		sb.append(getCost());
		return sb.toString();
	}
	
	@Override
	public double getCost(){
		double sum = 0.0;
		/*if (ingredients.size() == 0)
			System.out.print("it's zero");
		*/
		for(Ingredient i : ingredients){
			sum += i.getCost();
		}
		return sum + cost;
	}
	
	/*get方法*/
	
	
	/* set方法集 */
	public void setSize(int size){
		this.size = size;
	}
	
	public void addIngredient(Ingredient... ingredient){
		ingredients.addAll(Arrays.asList(ingredient));
	}
	
	/* set方法集 */
	
}
