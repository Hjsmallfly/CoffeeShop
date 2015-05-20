package exercise.usefulinterface;

import exercise.product.Beverage;
import exercise.product.Ingredient;
import exercise.product.Production;

public interface HasIngredient {
	public Production addIngredient(Ingredient... ingredient);
	public Production removeIngredient(Ingredient ingredient);
}
