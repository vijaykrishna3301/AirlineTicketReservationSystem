package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

import database.DatabaseHelper;
import javabean.AirportData;
import javabean.FlightData;
import javabean.ScheduleData;
import javabean.UserData;

public class Server {
	public static void main(String args[]) throws Exception {
		ServerSocket serverSocket  = new ServerSocket(2914);
		JFrame jFrame = new JFrame();
		jFrame.add(new JLabel("Server Started"));
		jFrame.setSize(500, 500);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		while(true) {
			System.out.println("waiting");
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
		    DataInputStream dataInputStream = new DataInputStream(inputStream);
		    String inputString = dataInputStream.readUTF();
		    //login
		    if(inputString.equals("addUser")) {
		    	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    	UserData userData = (UserData) objectInputStream.readObject();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.registerUser(userData);;
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
		    }
		    else if(inputString.equals("getLoginDetails")) {
		    	DatabaseHelper helper = new DatabaseHelper();
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(helper.getEmail());
				String email = dataInputStream.readUTF();
				dos.writeUTF(helper.getPassword(email));
				dos.writeUTF(helper.getRole(email));
				
		    }
		    
		    //flight functions
		    else if(inputString.equals("addFlight")) {
		    	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    	FlightData flightData = (FlightData) objectInputStream.readObject();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.addFlight(flightData);
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
		    }
		    else if(inputString.equals("getFlight")) {
		    	DatabaseHelper helper = new DatabaseHelper();
		    	ObjectOutputStream obos=new ObjectOutputStream(outputStream);
		    	obos.writeObject(helper.getFlights());
				
		    }
		    else if(inputString.equals("updateFlight")) {
		    	String flighid  = dataInputStream.readUTF();
		    	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    	FlightData flightData = (FlightData) objectInputStream.readObject();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.updateFlight(flightData,flighid);
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
		    }
		    else if(inputString.equals("deleteFlight")) {
		    	String flightid = dataInputStream.readUTF();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.deleteFlight(flightid);
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
		    }
		    //trip functions
		    else if(inputString.equals("addTrip")) {
		    	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    	ScheduleData scheduleData = (ScheduleData) objectInputStream.readObject();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.addTrip(scheduleData);
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
		    }
		    else if(inputString.equals("getTrip")) {
		    	DatabaseHelper helper = new DatabaseHelper();
		    	ObjectOutputStream obos=new ObjectOutputStream(outputStream);
		    	obos.writeObject(helper.getTrip());
				
		    }
		    else if(inputString.equals("updateTrip")) {
		    	String tripId  = dataInputStream.readUTF();
		    	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    	ScheduleData tripData = (ScheduleData) objectInputStream.readObject();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.updateTrip(tripData,tripId);
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
		    }
		    else if(inputString.equals("deleteTrip")) {
		    	String tripId = dataInputStream.readUTF();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.deleteTrip(tripId);
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
		    }
		    //airport functions
		    else if(inputString.equals("addAirport")) {
		    	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    	AirportData airportData = (AirportData) objectInputStream.readObject();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.addAirport(airportData);
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
		    }
		    else if(inputString.equals("getAirport")) {
		    	DatabaseHelper helper = new DatabaseHelper();
		    	ObjectOutputStream obos=new ObjectOutputStream(outputStream);
		    	obos.writeObject(helper.getAirport());
				
		    }
		    else if(inputString.equals("updateAirport")) {
		    	String airportId  = dataInputStream.readUTF();
		    	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		    	AirportData airportData = (AirportData) objectInputStream.readObject();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.updateAirport(airportData,airportId);
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
				
		    }
		    else if(inputString.equals("deleteAirport")) {
		    	String airportid = dataInputStream.readUTF();
		    	DatabaseHelper helper = new DatabaseHelper();
		    	String message = helper.deleteAirport(airportid);
		    	DataOutputStream dos=new DataOutputStream(outputStream);
				dos.writeUTF(message);
		    }
		    else if(inputString.equals("getFlightandAirportList")) {
		    	DatabaseHelper helper = new DatabaseHelper();
		    	ObjectOutputStream obos=new ObjectOutputStream(outputStream);
		    	obos.writeObject(helper.getFlightList());
		    	obos.writeObject(helper.getAirportList());	
		    }
		    
		}
	}

}
