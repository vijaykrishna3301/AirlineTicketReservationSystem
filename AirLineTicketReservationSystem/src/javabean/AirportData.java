package javabean;

import java.io.Serializable;

public class AirportData implements Serializable {
	private String AirportId,name,city,country;

	public AirportData(String airportId, String name, String city, String country)  {
		super();
		AirportId = airportId;
		this.name = name;
		this.city = city;
		this.country = country;
	}

	public String getAirportId() {
		return AirportId;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
	public String toString() {
		
		return "  "+AirportId+"           "+name+"       "+city+"      "+country;
	}

}
