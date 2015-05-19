//
//package exercise.product;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 记录各种商品的销量的类
// * @author STU_nwad
// *
// */
//public class Counter {
//	
//	private static String directory = "SellRecord"; //默认用这个文件夹
//	
//	private Production production; //用于记录该商品的销量
//	
//	private String name;
//	
//	private static int CounterCount = 0; //计数器的个数
//	
//	private int count;
//	
//	private static Map<String, Counter> counterMap = new HashMap<String, Counter>(); 
//	
//	public Counter(Production p) {
//		production = p; 
//		name = p.getName();
//		++CounterCount;
//		if (!counterMap.containsKey(name)){ //如果之前没有加过这种计数器的话
//			counterMap.put(p.getName(), p.getCounter());
//		}else {
//			//counterMap.get(p.getName()).add(p.getCounter().count);
//		}
//	}
//	
//	public int getCount(){
//		return counterMap.get(name).count;
//	}
//	
//	/**
//	 * 增加销量
//	 * @param x
//	 */
//	public void add(int x){
//		++count;
//	}
//	
//	public void add(){
//		add(1);
//	}
//	
//	private void readFromFile(){
//		
//	}
//	
//	private void saveToFile(){
//		
//	}
//	
//}
//*/
