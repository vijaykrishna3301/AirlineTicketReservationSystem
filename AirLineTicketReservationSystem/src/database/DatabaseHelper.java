package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import javabean.AirportData;
import javabean.AirportDataTableModel;
import javabean.FlightData;
import javabean.FlightDataTableModel;
import javabean.ListModelFlightData;
import javabean.ListModelScheduleData;
import javabean.ScheduleData;
import javabean.ScheduleDataTableModel;
import javabean.UserData;



public class DatabaseHelper {
	Connection connection = DBConnection.getConnection();
	//login
	/*CREATE TABLE  "USERDATA" 
   (	"USERID" NUMBER NOT NULL ENABLE, 
	"USER_NAME" VARCHAR2(30) NOT NULL ENABLE, 
	"USER_MAILID" VARCHAR2(30) NOT NULL ENABLE, 
	"USER_MOBNO" NUMBER(10,0) NOT NULL ENABLE, 
	"USER_PASSWORD" VARCHAR2(30) NOT NULL ENABLE, 
	"USER_ROLE" VARCHAR2(10) NOT NULL ENABLE, 
	 CONSTRAINT "USERDATA_PK" PRIMARY KEY ("USERID") ENABLE
   ) ;

	CREATE OR REPLACE TRIGGER  "BI_USERDATA" 
	  before insert on "USERDATA"               
	  for each row  
	begin   
	  if :NEW."USERID" is null then 
	    select "USERDATA_SEQ".nextval into :NEW."USERID" from dual; 
	  end if; 
	end; 
	
	/
	ALTER TRIGGER  "BI_USERDATA" ENABLE;*/
	public String registerUser(UserData userData){       
        try {
            String Quary="insert into userdata(user_name,user_mailid,user_mobno,user_password,user_role) values(?,?,?,?,?)";
            PreparedStatement ps=connection.prepareStatement(Quary);
            ps.setString(1,userData.getName());
            ps.setString(2,userData.getEmail());
            ps.setString(3, userData.getMobno());
            ps.setString(4, userData.getPassword());
            ps.setString(5, userData.getRole());
            ps.executeQuery();
        }
        catch(Exception e){
        	return e.toString();   
        }
        return "Success";
    }
	public String getEmail(){
        String email="";
        try {
            Statement stmt =connection.createStatement();
            String Quary="select user_mailid from userdata";
            ResultSet rs=stmt.executeQuery(Quary);
            
            while((rs.next())){        
                email+=(rs.getString(1))+" ";
            }         
            return email;
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return email;
    }
	public String getPassword(String email){
        String password="";
        try {
            Statement stmt =connection.createStatement();
            String Quary="select user_password from userdata where user_mailid='"+email+"'";
            
            ResultSet rs=stmt.executeQuery(Quary);
            
            while((rs.next())){ 
                password+=(rs.getString(1));
            }
           
            return password;


        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
           

    }
	public String getRole(String email){
        String role="";
        try {
            Statement stmt =connection.createStatement();
            String Quary="select user_role from userdata where user_mailid='"+email+"'";
            
            ResultSet rs=stmt.executeQuery(Quary);
            
            while((rs.next())){ 
                role+=(rs.getString(1));
            }
            System.out.print(role+" vk");
            return role;


        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
	//flight table functions
	public String addFlight(FlightData flightData) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="insert into flight values('"+flightData.getFlightId()+"','"+flightData.getName()
			+"','"+flightData.getTotalSeat()+"')";
			System.out.println("addflight");
	        stmt.executeUpdate(query);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			return e.toString();
		}
		return "Success";
	}
	
	public FlightDataTableModel getFlights(){
		FlightDataTableModel flightlist = new FlightDataTableModel();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="select * from flight";
		    ResultSet rst=stmt.executeQuery(query);
		    
		    while(rst.next()){
	            FlightData f = new FlightData(rst.getString("flight_name"), rst.getString("flight_id"), rst.getInt("total_seat"));
	            flightlist.add(f);
	        }
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return flightlist;
		
	}
	public ArrayList<FlightData> getFlightList(){
		ArrayList<FlightData> flightlist = new ArrayList<FlightData>();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="select * from flight";
		    ResultSet rst=stmt.executeQuery(query);
		    
		    while(rst.next()){
	            FlightData f = new FlightData(rst.getString("flight_name"), rst.getString("flight_id"), rst.getInt("total_seat"));
	            flightlist.add(f);
	        }
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return flightlist;
		
	}
	public String updateFlight(FlightData flightData,String flightid) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="update flight set flight_id='"+flightData.getFlightId()+"',flight_name='"+flightData.getName()
			+"',total_seat='"+flightData.getTotalSeat()+"' where flight_id='"+flightid+"'";
	        System.out.println("updateflight");
	        stmt.executeUpdate(query);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			return e.toString();
		}
		return "Success";
	}
	public String deleteFlight(String flightId) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="delete from flight where flight_id='"+flightId+"'";
	        stmt.executeUpdate(query);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			return e.toString();
		}
		return "Success";
	}
	
	//trip table functions
	/*CREATE table "TRIP" (
    "TRIPID"        NUMBER NOT NULL,
    "FLIGHTID"      VARCHAR2(10) NOT NULL,
    "TOTALSEAT"     NUMBER NOT NULL,
    "BASEFARE"      NUMBER NOT NULL,
    "FROMAIRPORTID" VARCHAR2(10) NOT NULL,
    "TOAIRPORTID"   VARCHAR2(10) NOT NULL,
    "TAKEOFFDATE"   DATE NOT NULL,
    "LANDINGDATE"   DATE NOT NULL,
    "TAKEOFFTIME"   DATE NOT NULL,
    "LANDINGTIME"   DATE NOT NULL,
	    constraint  "TRIP_PK" primary key ("TRIPID")
	)
	/
	
	CREATE sequence "TRIP_SEQ" 
	/
	
	CREATE trigger "BI_TRIP"  
	  before insert on "TRIP"              
	  for each row 
	begin  
	  if :NEW."TRIPID" is null then
	    select "TRIP_SEQ".nextval into :NEW."TRIPID" from dual;
	  end if;
	end;
	/   
	
	*/
	public String addTrip(ScheduleData scheduleData) {
		Statement stmt;
		try {
			String query = "INSERT INTO trip(FLIGHTID,TOTALSEAT,BASEFARE,FROMAIRPORTID,TOAIRPORTID,TAKEOFFDATE,LANDINGDATE,TAKEOFFTIME,LANDINGTIME) VALUES ( ?,?,?,?,?,?,?,?,?)";
		     PreparedStatement pstmt = connection.prepareStatement(query);
		     pstmt.setString(1, scheduleData.getFlight_Id());
		     pstmt.setInt(2,scheduleData.getTotal_Seat());
		     pstmt.setInt(3,scheduleData.getBase_Price());
		     pstmt.setString(4, scheduleData.getFrom_Airport_Id());
		     pstmt.setString(5, scheduleData.getTo_Airport_Id());
		     pstmt.setDate(6, new Date(scheduleData.getTakeoff_Date().getTime()));
		     pstmt.setDate(7, new Date(scheduleData.getLanding_Date().getTime()));
		     pstmt.setTime(8, new Time(scheduleData.getTakeoff_time().getTime()));
		     pstmt.setTime(9, new Time(scheduleData.getLanding_time().getTime()));
		     pstmt.execute();
		} catch (SQLException e) {
			return e.toString();
		}
		return "Success";
		
	}
	public String updateTrip(ScheduleData scheduleData,String tripid) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="UPDATE trip SET FLIGHTID = ?,TOTALSEAT = ?,BASEFARE = ?,FROMAIRPORTID = ?,TOAIRPORTID = ?,TAKEOFFDATE = ?,LANDINGDATE = ?,TAKEOFFTIME = ?,LANDINGTIME = ? WHERE TRIPID = ?";
			System.out.println("updatetrip");
			PreparedStatement pstmt = connection.prepareStatement(query);
		     pstmt.setString(1, scheduleData.getFlight_Id());
		     pstmt.setInt(2,scheduleData.getTotal_Seat());
		     pstmt.setInt(3,scheduleData.getBase_Price());
		     pstmt.setString(4, scheduleData.getFrom_Airport_Id());
		     pstmt.setString(5, scheduleData.getTo_Airport_Id());
		     pstmt .setDate(6, new Date(scheduleData.getTakeoff_Date().getTime()));
		     pstmt .setDate(7, new Date(scheduleData.getLanding_Date().getTime()));
		     pstmt.setTime(8, new Time(scheduleData.getTakeoff_time().getTime()));
		     pstmt.setTime(9, new Time(scheduleData.getLanding_time().getTime()));
		     pstmt.setString(10, tripid);
		     pstmt.execute();
		} catch (SQLException e) {
			return e.toString();
		}
		return "Success";
	}
	public ScheduleDataTableModel getTrip(){
		ScheduleDataTableModel schedulelist = new ScheduleDataTableModel();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="select * from trip order by TAKEOFFDATE,TAKEOFFTIME";
		    ResultSet rst=stmt.executeQuery(query);
		    
		    while(rst.next()){
		    	ScheduleData trip = new ScheduleData(rst.getInt("TRIPID"), rst.getString("FLIGHTID"), rst.getInt("BASEFARE"), rst.getInt("TOTALSEAT"), rst.getString("FROMAIRPORTID"), rst.getString("TOAIRPORTID"), rst.getDate("TAKEOFFDATE"), rst.getDate("LANDINGDATE"), rst.getTime("TAKEOFFTIME"), rst.getTime("LANDINGTIME"));
	            schedulelist.add(trip);
	        }
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return schedulelist;	
	}
	public ArrayList<ScheduleData> getTripList(){
		ArrayList<ScheduleData> schedulelist = new ArrayList<ScheduleData>();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="select * from trip";
		    ResultSet rst=stmt.executeQuery(query);
		    
		    while(rst.next()){
		    	ScheduleData trip = new ScheduleData(rst.getInt("TRIPID"), rst.getString("FLIGHTID"), rst.getInt("BASEFARE"), rst.getInt("TOTALSEAT"), rst.getString("FROMAIRPORTID"), rst.getString("TOAIRPORTID"), rst.getDate("TAKEOFFDATE"), rst.getDate("LANDINGDATE"), rst.getTime("TAKEOFFTIME"), rst.getTime("LANDINGTIME"));
	            schedulelist.add(trip);
	        }
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return schedulelist;
		
	}
	public String deleteTrip(String tripId) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="delete from trip where tripid='"+tripId+"'";
	        stmt.executeUpdate(query);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			return e.toString();
		}
		return "Success";
	}
	//airport table functions
	public String addAirport(AirportData airportData) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="insert into airport values('"+airportData.getAirportId()+"','"+airportData.getName()
			+"','"+airportData.getCity()+"','"+airportData.getCountry()+"')";
	        stmt.executeUpdate(query);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			return e.toString();
		}
		return "Success";
	}
	public AirportDataTableModel getAirport(){
		AirportDataTableModel airportlist = new AirportDataTableModel();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="select * from airport order by airport_city,airport_city";
		    ResultSet rst=stmt.executeQuery(query);
		    
		    while(rst.next()){
		    	AirportData f = new AirportData(rst.getString("airport_id"), rst.getString("airport_city"), rst.getString("airport_city"),rst.getString("airport_country"));
	            airportlist.add(f);
	        }
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return airportlist;
		
	}
	public ArrayList<AirportData> getAirportList(){
		ArrayList<AirportData> airportlist = new ArrayList<AirportData>();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="select * from airport";
		    ResultSet rst=stmt.executeQuery(query);
		    
		    while(rst.next()){
		    	AirportData f = new AirportData(rst.getString("airport_id"), rst.getString("airport_name"), rst.getString("airport_city"),rst.getString("airport_country"));
	            airportlist.add(f);
	        }
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return airportlist;
		
	}
	public String updateAirport(AirportData airportData, String airportId) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="update airport set airport_id='"+airportData.getAirportId()+"',airport_name='"+airportData.getName()+"',airport_city='"+airportData.getCity()
			+"',airport_country='"+airportData.getCountry()+"' where airport_id='"+airportId+"'";
	        stmt.executeUpdate(query);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			return e.toString();
		}
		return "Success";
	}
	public String deleteAirport(String airportId) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			String query="delete from airport where airport_id='"+airportId+"'";
	        stmt.executeUpdate(query);
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			return e.toString();
		}
		return "Success";
	}
}
