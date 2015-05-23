package exercise;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeData {
		
	public static String getTimeString(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");//可以方便地修改日期格式
		return dateFormat.format(date);
	}
}
