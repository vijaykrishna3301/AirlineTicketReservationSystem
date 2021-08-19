package Administrator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Design.LabelAndTextFiled;
import Design.TabPanel;
import Server.MyServer;
import javabean.ListModelFlightData;
import javabean.ListModelScheduleData;
import javabean.ScheduleData;
import javabean.AirportData;
import javabean.FlightData;
import javabean.ListModelAirportData;

class AdminDashBoard extends JFrame{
	JPanel jPanel=new JPanel();
 	JTabbedPane tabbedPane;
 	AdminDashBoard(){
 		
 		add(jPanel);
 		jPanel.setLayout(new BorderLayout());
 		render();
 		setSize(900,650);
  		setVisible(true);
 	}
 	public void render() {
 		tabbedPane=new JTabbedPane(JTabbedPane.TOP);
    	tabbedPane.addTab("ViewFlights",new ViewFlights());
    	tabbedPane.addTab("ViewSchedules",new ViewSchedules());
    	tabbedPane.addTab("ViewAirport",new ViewAirports());
    	jPanel.add(tabbedPane);
 	}
 	public static void main(String args[]){
  		new AdminDashBoard();
 	}  
 	class ViewFlights extends TabPanel{
 		
 		ListModelFlightData<FlightData> flightDatas = new ListModelFlightData<FlightData>();
 		ViewFlights(){
 			createList();
 			setSouthButton("Add Flights"); 		
 	   	}
 		public void setflightDatas() {
 			flightDatas = MyServer.getDatas();
 		}
 		public void createList() {
 			System.out.print("createlist");
 			setflightDatas();
 			setList(flightDatas,new JLabel("FlightId      FlightName      FlightSeatCapacity"));
 		}
 		@Override
 		public void changeIndex(int index) {
 			super.changeIndex(index);
 			addComponentToEastPanel(new LabelAndTextFiled("Name", flightDatas.get(index).getName()));
 			addComponentToEastPanel(new LabelAndTextFiled("Flight Id", ""+flightDatas.get(index).getFlightId()));
 			addComponentToEastPanel(new LabelAndTextFiled("Seat Capacity", ""+flightDatas.get(index).getTotalSeat()));
 			addComponentToEastPanel(new JButton("Update"));
 			setEastPannel();
 		}
 		@Override
 		public void buttonClickEvent(){
 			System.out.print("Add Flights Button clicked");
 			JButton addFlightButton = new JButton("Add Flight");
 			jPanel.removeAll();
 			JPanel addFlightPanel = new JPanel();
 			addFlightPanel.setLayout(new BoxLayout(addFlightPanel, BoxLayout.Y_AXIS));
 			LabelAndTextFiled name = new LabelAndTextFiled("Name", "");
 			LabelAndTextFiled flightId = new LabelAndTextFiled("Flight Id", ""+"");
 			LabelAndTextFiled seatCapacity = new LabelAndTextFiled("Seat Capacity", ""+"");
 			addFlightPanel.add(flightId);
 			addFlightPanel.add(name);
 			addFlightPanel.add(seatCapacity);
 			addFlightPanel.add(addFlightButton);
 			jPanel.add(addFlightPanel);
 			
 			jPanel.repaint();
 			jPanel.revalidate();
 			addFlightButton.addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent evt) {
	    		        JButton list = (JButton)evt.getSource();
	    		        if (evt.getClickCount() == 1) {
	    		        	
	    		        	System.out.print("addFlightButton Clicked");
	    		        	jPanel.removeAll();
	    		        	render();
	    		        	jPanel.revalidate();
	    		        }  
	    		    }
			});
 		}
 		
 	}

 	class ViewSchedules extends TabPanel{
		ListModelScheduleData<ScheduleData> scheduleDatas = new ListModelScheduleData<ScheduleData>();
		ViewSchedules(){
			createList();
			setSouthButton("Make Trip"); 		
	   	}
		public void setflightDatas() {
			scheduleDatas = MyServer.getScheduleDatas();
		}
		public void createList() {
			System.out.print("createlist");
			setflightDatas();
			setList(scheduleDatas,new JLabel("TripId      FlightId      FromAirport    ToAirport             TripDate"));
		}
		@Override
		public void changeIndex(int ind) {
			super.changeIndex(ind);
			addComponentToEastPanel(new LabelAndTextFiled("Flight_Id", ""+scheduleDatas.get(ind).getFlight_Id()));
			addComponentToEastPanel(new LabelAndTextFiled("Trip_Id", ""+scheduleDatas.get(ind).getTrip_Id()));
			addComponentToEastPanel(new LabelAndTextFiled("From_Airport_Id", ""+scheduleDatas.get(ind).getFrom_Airport_Id()));
			addComponentToEastPanel(new LabelAndTextFiled("To_Airport_Id", ""+scheduleDatas.get(ind).getTo_Airport_Id()));
			addComponentToEastPanel(new LabelAndTextFiled("Date", ""+scheduleDatas.get(ind).getTrip_Date()));
			addComponentToEastPanel(new JButton("Update"));
			setEastPannel();
		}
		@Override
		public void buttonClickEvent(){
			System.out.print("Add Schedule Button clicked");
			JButton addTripButton = new JButton("Add Trip");
 			jPanel.removeAll();
 			JPanel addTripPanel = new JPanel();
 			addTripPanel.setLayout(new BoxLayout(addTripPanel, BoxLayout.Y_AXIS));
 			addTripPanel.add(new LabelAndTextFiled("Flightid", ""));
 			addTripPanel.add(new LabelAndTextFiled("From Airport Id", ""));
 			addTripPanel.add(new LabelAndTextFiled("To Airport Id", ""));
 			addTripPanel.add(new LabelAndTextFiled("date of Trip", ""));
 			addTripPanel.add(addTripButton);
 			jPanel.add(addTripPanel);
 			
 			jPanel.repaint();
 			jPanel.revalidate();
 			addTripButton.addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent evt) {
	    		        JButton list = (JButton)evt.getSource();
	    		        if (evt.getClickCount() == 1) {
	    		        	System.out.print("addTripButton Clicked");
	    		        	jPanel.removeAll();
	    		        	render();
	    		        	jPanel.revalidate();
	    		        }  
	    		    }
			});
		}
		
	}
 	class ViewAirports extends TabPanel{
		ListModelAirportData<AirportData> airportDatas = new ListModelAirportData<AirportData>();
		ViewAirports(){
			createList();
			setSouthButton("Create Airport"); 		
	   	}
		public void setflightDatas() {
			airportDatas = MyServer.getAirportDatas();
		}
		public void createList() {
			System.out.print("createlist");
			setflightDatas();
			setList(airportDatas,new JLabel("AirportId          AirportName           City                      Country"));
		}
		@Override
		public void changeIndex(int ind) {
			super.changeIndex(ind);
			addComponentToEastPanel(new LabelAndTextFiled("AirportId", airportDatas.getElementAt(ind).getAirportId()));
			addComponentToEastPanel(new LabelAndTextFiled("AirportName", airportDatas.getElementAt(ind).getName()));
			addComponentToEastPanel(new LabelAndTextFiled("City", airportDatas.getElementAt(ind).getCity()));
			addComponentToEastPanel(new LabelAndTextFiled("Country", airportDatas.getElementAt(ind).getCountry()));
			addComponentToEastPanel(new JButton("Update"));
			setEastPannel();
		}
		@Override
		public void buttonClickEvent(){
			System.out.print("Add Schedule Button clicked");
			JButton addTripButton = new JButton("Add Airport");
 			jPanel.removeAll();
 			JPanel addTripPanel = new JPanel();
 			addTripPanel.setLayout(new BoxLayout(addTripPanel, BoxLayout.Y_AXIS));
 			addTripPanel.add(new LabelAndTextFiled("AirportId", ""));
 			addTripPanel.add(new LabelAndTextFiled("AirportName", ""));
 			addTripPanel.add(new LabelAndTextFiled("City", ""));
 			addTripPanel.add(new LabelAndTextFiled("Country", ""));
 			addTripPanel.add(addTripButton);
 			jPanel.add(addTripPanel);
 			jPanel.repaint();
 			jPanel.revalidate();
 			addTripButton.addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent evt) {
	    		        JButton list = (JButton)evt.getSource();
	    		        if (evt.getClickCount() == 1) {
	    		        	System.out.print("addTripButton Clicked");
	    		        	jPanel.removeAll();
	    		        	render();
	    		        	jPanel.revalidate();
	    		        }  
	    		    }
			});
		}
		
	}

}




