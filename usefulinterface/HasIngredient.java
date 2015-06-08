package exercise.usefulinterface;

import java.util.ArrayList;
import exercise.product.Ingredient;
import exercise.product.Production;

/**
 * 是否有调料的接口
 * @author STU_nwad
 *
 */
public interface HasIngredient {
	public Production addIngredient(Ingredient... ingredient);
	public Production removeIngredient(Ingredient ingredient);
	public Production removeAllIngredients();
	public ArrayList<Ingredient> getIngredients();
}