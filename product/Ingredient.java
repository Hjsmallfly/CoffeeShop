package exercise.product;

import java.io.IOException;
import java.io.RandomAccessFile;

import exercise.resourcepath.ResourceFilePath;

public abstract class Ingredient extends Production{
	@Override
	public String getSpecific(){
		return name;
	}
	
	@Override
	public double getCost(){
		return cost;
	}
	
	//写入文件
	@Override
	public boolean saveTofile(){
		RandomAccessFile f = ResourceFilePath.openFile(ResourceFilePath.beverageDirectory + "/" +  name);
		if (f == null)
			return false;
		try {
			f.writeDouble(cost);
			f.writeUTF(name);
			f.writeUTF(description);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		try {
			f.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return true;
	}
	
}
