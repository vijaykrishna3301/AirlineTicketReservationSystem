package javabean;

import java.io.Serializable;
import java.util.Date;

public class ScheduleData implements Serializable {
	private int trip_Id,base_Price,total_Seat;
	private String flight_Id,from_Airport_Id,to_Airport_Id;
	private Date takeoff_Date,landing_Date,takeoff_time,landing_time;
	
	
	public ScheduleData(int trip_Id,  String flight_Id,int base_Price,Integer total_Seat, String from_Airport_Id, String to_Airport_Id,
			Date takeoff_Date, Date landing_Date, Date takeoff_time, Date landing_time) {
		super();
		this.trip_Id = trip_Id;
		this.base_Price = base_Price;
		this.total_Seat = total_Seat;
		this.flight_Id = flight_Id;
		this.from_Airport_Id = from_Airport_Id;
		this.to_Airport_Id = to_Airport_Id;
		this.takeoff_Date = takeoff_Date;
		this.landing_Date = landing_Date;
		this.takeoff_time = takeoff_time;
		this.landing_time = landing_time;
	}
	
	public int getTrip_Id() {
		return trip_Id;
	}

	public int getBase_Price() {
		return base_Price;
	}
	
	public int getTotal_Seat() {
		return total_Seat;
	}
	
	public String getFlight_Id() {
		return flight_Id;
	}

	public String getFrom_Airport_Id() {
		return from_Airport_Id;
	}

	public String getTo_Airport_Id() {
		return to_Airport_Id;
	}

	public Date getTakeoff_Date() {
		return takeoff_Date;
	}

	public Date getLanding_Date() {
		return landing_Date;
	}

	public Date getTakeoff_time() {
		return takeoff_time;
	}

	public Date getLanding_time() {
		return landing_time;
	}

	@Override
	public String toString() {
		return "  "+trip_Id+"     "+flight_Id+"  "+base_Price +"   "+total_Seat+"   "+from_Airport_Id+"        "+to_Airport_Id+"       "+takeoff_Date+"   "+landing_Date+" "+takeoff_time+"   "+landing_time;
	}

}
