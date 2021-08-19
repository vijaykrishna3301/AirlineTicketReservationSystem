package javabean;

import java.util.Date;

public class ScheduleData {
	private int Trip_Id;
	private String Flight_Id,From_Airport_Id,To_Airport_Id;
	private Date Trip_Date;
	public ScheduleData(String flight_Id, int trip_Id, String from_Airport_Id, String to_Airport_Id, Date trip_Date) {
		super();
		Flight_Id = flight_Id;
		Trip_Id = trip_Id;
		From_Airport_Id = from_Airport_Id;
		To_Airport_Id = to_Airport_Id;
		Trip_Date = trip_Date;
	}
	public String getFlight_Id() {
		return Flight_Id;
	}
	public int getTrip_Id() {
		return Trip_Id;
	}
	public String getFrom_Airport_Id() {
		return From_Airport_Id;
	}
	public String getTo_Airport_Id() {
		return To_Airport_Id;
	}
	public Date getTrip_Date() {
		return Trip_Date;
	}
	@Override
	public String toString() {
		return "  "+Trip_Id+"            "+Flight_Id+"             "+From_Airport_Id+"                  "+To_Airport_Id+"          "+Trip_Date;
	}

}
