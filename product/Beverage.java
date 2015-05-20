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
	public double figureCost(){
		double sum = 0.0;
		/*if (ingredients.size() == 0)
			System.out.print("it's zero");
		*/
		for(Ingredient i : ingredients){
			sum += i.getCost();
		}
		return sum + cost;
	}
	
	@Override
	public int getSize() {
		return size;
	};
	
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
	
	//暂时未实现
	public Production removeIngredient(Ingredient ingredient){
		return this;
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		Beverage beverage = null;
//		for(int i = 0 ; i < 100 ; ++i){
//			beverage = BeverageFactory.createBeverage("Espresso" + rand.nextInt(100), LARGE);
//		}
//		System.out.println(beverage.getAllSaleInfo());
		
//		for(int i = 0 ; i < 100 ; ++i){
//			BeverageFactory.createNewBeverage("SmallFlyCoffee" + i, rand.nextDouble(), "this is a virtual coffee");
//		}
		
	}
	
}
