package javabean;

public class FlightData {
	String Name;
	int flightId,totalSeat;
	
	public FlightData(String name, int flightId, int totalSeat) {
		Name = name;
		this.flightId = flightId;
		this.totalSeat = totalSeat;
	}
	public String getName() {
		return Name;
	}
	public int getFlightId() {
		return flightId;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Name;
	}

}
