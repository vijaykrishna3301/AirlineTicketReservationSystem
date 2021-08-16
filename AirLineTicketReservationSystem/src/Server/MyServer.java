package Server;
import java.util.ArrayList;

import javabean.FarrayList;
import javabean.FlightData;

public class MyServer {
	public static  FarrayList<FlightData> datas = new FarrayList<>();
	public static FarrayList<FlightData> getDatas() {
		datas.add(new FlightData("DeccanAir",1,150));
		datas.add(new FlightData("AirIndia",2,125));
		datas.add(new FlightData("Indiga",3,130));
		return datas;
	}
	
	
}
