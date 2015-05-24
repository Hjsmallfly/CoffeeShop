package exercise.product;

public class Pizza extends Food {
	public Pizza() {
		name = "Pizza";
		cost = 5.0;
		description = "just a Pizza";
	}
	
	public static void main(String[] args) {
		Pizza pizza =  new Pizza();
		pizza.saveTofile();
		pizza.addSaleCount(pizza.getName());
		pizza.addSaleCount(pizza.getName());
		System.out.println( pizza.getAllSaleInfo() );
	}
}
