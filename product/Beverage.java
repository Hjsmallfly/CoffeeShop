package exercise.product;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

import exercise.factory.BeverageFactory;
import exercise.resourcepath.ResourceFilePath;
import exercise.usefulinterface.HasIngredient;
import exercise.usefulinterface.HasSize;

/**
 * 饮料类
 * @author STU_nwad
 *
 */
public abstract class Beverage extends Production implements HasSize , HasIngredient {
	
//	private BeverageFactory factory = new BeverageFactory();
	
	protected ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(); //保存是否加了调料 
	
	protected int size;//饮料的 大 中 小 杯
		
	/*构造函数*/
	public Beverage() {
		
	}
	
	public Beverage(int size){
		this.size = size;
	}
	/*构造函数*/
	

	
//	//订饮料
//	public static Beverage order(String type,int size){
//		return 
//				BeverageFactory.createBeverage(type,size);
//	}
	
	/*get方法*/
	
	@Override
	public String getSpecific() { //获得具体信息
		StringBuilder sb = new StringBuilder();
		sb.append(name + "(" + sizeStr[size] + ") ");
		for(Ingredient i : ingredients)
			sb.append(i.getName() + " ");
		//sb.append(getCost());
		return sb.toString();
	}
	
	@Override
	public double figureCost(){
		double sum = 0.0;
		/*if (ingredients.size() == 0)
			System.out.print("it's zero");
		*/
		for(Ingredient i : ingredients){
			sum += i.getCost();
		}
		return sum + getCost(); //这里不能直接用 sum + cost 因为 getCost() 会考虑 size 的问题
	}
	
	@Override
	public int getSize() {
		return size;
	};
	
	public ArrayList<Ingredient> getIngredients(){
		return ingredients;
	}
	
	/*get方法*/
	
	
	/* set方法集 */
	@Override
	public void setSize(int size){
		this.size = size;
	}
	
	@Override
	public Beverage addIngredient(Ingredient... ingredient){
		ingredients.addAll(Arrays.asList(ingredient));
		return this;
	}
	
	/* set方法集 */
	

	public Production removeIngredient(Ingredient ingredient){
		if (ingredients.contains(ingredient))
			ingredients.remove(ingredient);
		return this;
	}
	
	public Production removeAllIngredients(){
		ingredients.clear();
		return this;
	}
	
//	public String toString(){
//		if (ingredients.size() != 0 ){
//			return name + ingredients.get(0).getName();
//		}else {
//			return name + "没有调味品";
//		}
//	}
	
	/**
	 * 因为有大小的因素，所以这里要设置价格
	 */
	@Override
	public double getCost(){
		if (size == LARGE)
			return cost * 1.5;
		if (size == MIDDLE)
			return cost * 1.25;
		return cost;
	}
	
	
	public static void main(String[] args) {
		Beverage beverage = BeverageFactory.order("sf", MIDDLE,"None", LARGE);
		beverage.addIngredient(new Ice(),new Milk());
		System.out.println(beverage.getSpecific());
		beverage.removeIngredient(new Ice());
		System.out.println(beverage.getSpecific());
		beverage.removeAllIngredients();
		System.out.println(beverage.getSpecific());
	}
	
}
