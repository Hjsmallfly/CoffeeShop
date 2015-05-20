package exercise.product;

public abstract class Ingredient extends Production{
	@Override
	public String getSpecific(){
		return name;
	}
	
	@Override
	public double getCost(){
		return cost;
	}
}
