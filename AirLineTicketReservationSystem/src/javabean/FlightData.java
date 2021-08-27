package javabean;

import java.io.Serializable;

public class FlightData implements Serializable {
	private String Name,flightId;
	private int totalSeat;
	
	public FlightData(String name, String flightId, int totalSeat) {
		Name = name;
		this.flightId = flightId;
		this.totalSeat = totalSeat;
	}
	public String getName() {
		return Name;
	}
	public String getFlightId() {
		return flightId;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return flightId+"           "+Name+"             "+totalSeat;
	}

}
