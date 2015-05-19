package exercise.auxiliary;

import java.io.File;

/**
 * 返回合适的路径，兼容windows和linux 
 * @author STU_nwad
 *
 */
public class GetPath {
	private static boolean OnWindows,OnLinux;
	static{
		if (File.separator.equals("\\")){
			
			OnWindows = true;
			OnLinux = false;
		}
		else {
			OnLinux = true;
			OnWindows = false;
		}
	}
	
	public static boolean isWin(){
		return OnWindows;
	}
	
	public static boolean isLinux(){
		return OnLinux;
	}
	
	public static void main(String[] args) {
		if (isWin()){
			System.out.println("On windows");
		}
	}
}
