package Server;
import java.util.ArrayList;
import java.util.Date;

import javabean.ListModelFlightData;
import javabean.ListModelScheduleData;
import javabean.ScheduleData;
import javabean.AirportData;
import javabean.FlightData;
import javabean.ListModelAirportData;

public class MyServer {
	public static  ListModelFlightData<FlightData> datas = new ListModelFlightData<>();
	public static ListModelFlightData<FlightData> getDatas() {
		datas.add(new FlightData("Deccan","F001",150));
		datas.add(new FlightData("AirIndia","F002",125));
		datas.add(new FlightData("Indiga   ","F003",130));
		return datas;
	}
	public static  ListModelScheduleData<ScheduleData> schedule_datas = new ListModelScheduleData<>();
	public static ListModelScheduleData<ScheduleData> getScheduleDatas() {
		schedule_datas.add(new ScheduleData("F001",1,"MAA","DEL",new Date()));
		schedule_datas.add(new ScheduleData("F002",2,"CBE","DEL",new Date()));
		schedule_datas.add(new ScheduleData("F003",3,"DEL","CBE",new Date()));
		return schedule_datas;
	}
	public static  ListModelAirportData<AirportData> airport_datas = new ListModelAirportData<>();
	public static ListModelAirportData<AirportData> getAirportDatas() {
		airport_datas.add(new AirportData("CBE","CBEAIRPORT"," COIMBATORE","INDIA"));
		airport_datas.add(new AirportData("DEL"," DELAIRPORT"," DELHI               ","INDIA"));
		airport_datas.add(new AirportData("MAA","MAAAIRPORT","CHENNAI         ","INDIA"));
		return airport_datas;
	}
}
