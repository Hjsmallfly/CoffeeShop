package exercise.usefulinterface;

import java.util.ArrayList;

import exercise.product.Beverage;
import exercise.product.Ingredient;
import exercise.product.Production;

public interface HasIngredient {
	public Production addIngredient(Ingredient... ingredient);
	public Production removeIngredient(Ingredient ingredient);
	public ArrayList<Ingredient> getIngredients();
}
