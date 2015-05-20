package exercise;

import java.util.ArrayList;

import exercise.product.Beverage;
import exercise.product.Espresso;
import exercise.product.Ice;
import exercise.product.Milk;
import exercise.product.Sugar;
import exercise.usefulinterface.HasSize;

public class TestOderSystem {
	public static void main(String[] args) {
		ArrayList< Beverage > beverages = new ArrayList< Beverage >(); 
		beverages.add(Beverage.order("Espresso", Beverage.LARGE));
		beverages.add(Beverage.order("Espresso", Beverage.LARGE));
		beverages.add(Beverage.order("Espresso", Beverage.SMALL));
		beverages.add(Beverage.order("SmallFlyCoffee", Beverage.MIDDLE));
		beverages.get(0).addIngredient(new Ice());
		beverages.get(1).addIngredient(new Milk());
		beverages.get(1).setSize(HasSize.MIDDLE);
		beverages.get(1).addIngredient(new Ice());
		beverages.get(3).addIngredient(new Sugar(),new Ice());
		
		for(Beverage beverage : beverages){
			System.out.println(beverage.getSpecific());
		}
		System.out.println( "销量情况:\n" + beverages.get(0).getAllSaleInfo());
	}
}
