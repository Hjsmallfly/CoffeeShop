package exercise;
import java.awt.Color;
import java.util.Random;

public class RandomColor {
	public final static Random rand = new Random(System.currentTimeMillis());
	public static Color getColor(){
		return new Color(rand.nextInt(128), rand.nextInt(128), rand.nextInt(128));
	}
}
