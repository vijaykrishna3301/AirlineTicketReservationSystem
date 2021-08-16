package Administrator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import Design.LabelAndTextFiled;
import Design.TabPanel;
import Server.MyServer;
import javabean.FarrayList;
import javabean.FlightData;

class AdminDashBoard extends JFrame{
 	JTabbedPane t;
 	AdminDashBoard(){
  		t=new JTabbedPane(JTabbedPane.TOP);
  		t.addTab("ViewFlights",new ViewFlights());
  		t.addTab("ViewSchedules",new ViewSchedules());
  		add(t,BorderLayout.CENTER);
  		setSize(600,600);
  		setVisible(true);
 	}
 	public static void main(String args[]){
  		new AdminDashBoard();
 	}  
}

class ViewFlights extends TabPanel{
	
	FarrayList<FlightData> flightDatas = new FarrayList<FlightData>();
	ViewFlights(){
		
		String[] f = {"1","2","3"};
		createList();
		setSouthButton("Add Flights"); 		
   	}
	public void setflightDatas() {
		flightDatas = MyServer.getDatas();
	}
	public void createList() {
		System.out.print("createlist");
		setflightDatas();
		setList(flightDatas );
	}
	@Override
	public void changeIndex(int ind) {
		super.changeIndex(ind);
		addComponentToEastPanel(new LabelAndTextFiled("Name", flightDatas.get(ind).getName()));
		addComponentToEastPanel(new LabelAndTextFiled("Flight Id", ""+flightDatas.get(ind).getFlightId()));
		addComponentToEastPanel(new LabelAndTextFiled("Seat Capacity", ""+flightDatas.get(ind).getTotalSeat()));
		addComponentToEastPanel(new JButton("Update"));
		setEastPannel();
	}
	@Override
	public void buttonClickEvent(){
		System.out.print("Add Flights Button clicked");
	}
	
}

class ViewSchedules extends TabPanel{
	ViewSchedules(){
		String f[] = {"vijay","krishna"};
		//setList(f);
		setSouthButton("Add Schedule");
		
   	}
	@Override
	public void buttonClickEvent(){
		System.out.print("Add Schedule Button clicked");
	}
	
}


