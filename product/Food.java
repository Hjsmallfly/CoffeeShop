package exercise.product;

import exercise.usefulinterface.HasSize;

public class Food extends Production implements HasSize {
	
	private int size; //存储大小
	
	@Override
	public double getCost() {
		if (size == LARGE)
			return cost * 1.5;
		if (size == MIDDLE)
			return cost * 1.2;
		return cost;
	};
	
	@Override
	public double figureCost() {
		return getCost();
	};
	
	
	@Override
	public void setSize(int size) {
		this.size = size;
		
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public String getSpecific() {
		return name + "(" + sizeStr[size] + ") ";
	}

}
