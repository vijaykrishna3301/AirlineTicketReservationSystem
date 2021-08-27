package Administrator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.sun.org.apache.bcel.internal.generic.NEW;

import Design.LabelAndTextFiled;
import Design.TabPanel;
import javabean.ListModelFlightData;
import javabean.ListModelScheduleData;
import javabean.ScheduleData;
import javabean.ScheduleDataTableModel;
import javabean.AirportData;
import javabean.AirportDataTableModel;
import javabean.FlightData;
import javabean.FlightDataTableModel;

public class AdminDashBoard extends JFrame{
	JPanel jPanel=new JPanel();
 	JTabbedPane tabbedPane;
 	public AdminDashBoard(){
 		
 		add(jPanel);
 		jPanel.setLayout(new BorderLayout());
 		render();
 		setSize(900,650);
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
 		String flightidString="";
 		FlightDataTableModel flightDatas = new FlightDataTableModel();
 		ViewFlights(){
 			createList();
 			setSouthButton("Add Flights"); 		
 	   	}
 		public void setflightDatas() {
 			flightDatas = null;
 			//server connecting
        	Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("getFlight");
		        InputStream is=s.getInputStream();
			    ObjectInputStream ois=new ObjectInputStream(is);
			    flightDatas=(FlightDataTableModel) ois.readObject();
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
 		}
 		public void createList() {
 			System.out.print("createlist");
 			setflightDatas();
 			setList(flightDatas);
 		}
 		@Override
 		public void changeIndex(int index) {
 			super.changeIndex(index);
 			flightidString = flightDatas.get(index).getFlightId();
 			addComponentToEastPanel(new LabelAndTextFiled("Flight Id", ""+flightDatas.get(index).getFlightId()));
 			addComponentToEastPanel(new LabelAndTextFiled("Name", flightDatas.get(index).getName()));
 			addComponentToEastPanel(new LabelAndTextFiled("Seat Capacity", ""+flightDatas.get(index).getTotalSeat()));
 			JPanel jPanel = new JPanel(new FlowLayout());
 			jPanel.add(new JButton("Update"));
 			jPanel.add(new JButton("Delete"));
 			addComponentToEastPanel(jPanel);
 			setEastPannel();
 		}
 		@Override
 		public void updateButtonClickEvent() {
 			LabelAndTextFiled flightId = (LabelAndTextFiled) jp.getComponent(0);
 			LabelAndTextFiled flightName = (LabelAndTextFiled) jp.getComponent(1);
 			LabelAndTextFiled seatCapacity = (LabelAndTextFiled) jp.getComponent(2);
 			FlightData flightData = new FlightData(flightName.getText(), flightId.getText(), Integer.parseInt(seatCapacity.getText()));
 			System.out.println(flightidString+" "+flightData.getFlightId()+" "+flightData.getName()+" "+flightData.getTotalSeat());
 			Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("updateFlight");
			    dataOutputStream.writeUTF(flightidString);
		        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		        objectOutputStream.writeObject(flightData);
			    InputStream is=s.getInputStream();
			    DataInputStream dis=new DataInputStream(is);
			    String message = dis.readUTF();
			    System.out.println(message);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
 		}
 		@Override
 		public void deleteButtonClickEvent() {
 			Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("deleteFlight");
			    dataOutputStream.writeUTF(flightidString);
			    InputStream is=s.getInputStream();
			    DataInputStream dis=new DataInputStream(is);
			    String message = dis.readUTF();
			    System.out.println(message);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
 		}
 		@Override
 		public void southButtonClickEvent(){
 			System.out.print("Add Flights Button clicked");
 			JButton addFlightButton = new JButton("Add Flight");
 			JButton cancelButton = new JButton("  Cancel  ");
 			jPanel.removeAll();
 			JPanel addFlightPanel = new JPanel();
 			addFlightPanel.setLayout(new BoxLayout(addFlightPanel, BoxLayout.Y_AXIS));
 			LabelAndTextFiled name = new LabelAndTextFiled("Name", "");
 			LabelAndTextFiled flightId = new LabelAndTextFiled("Flight Id", "");
 			LabelAndTextFiled seatCapacity = new LabelAndTextFiled("Seat Capacity", "");
 			addFlightPanel.add(flightId);
 			addFlightPanel.add(name);
 			addFlightPanel.add(seatCapacity);
 			
 			JPanel btngroup = new JPanel();
 			btngroup.add(addFlightButton);
 			btngroup.add(cancelButton);
 			addFlightPanel.add(btngroup);
 			
 			jPanel.add(addFlightPanel);
 			jPanel.repaint();
 			jPanel.revalidate();
 			cancelButton.addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent evt) {
	    		     if (evt.getClickCount() == 1) {
	    		    	 System.out.println("cancel Clicked");
	    		    	jPanel.removeAll();
	    		        render();
	    		        jPanel.revalidate();
	    		     }
	    		 }
 			});
 			//add flight button
 			addFlightButton.addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent evt) {
	    		        JButton list = (JButton)evt.getSource();
	    		        if (evt.getClickCount() == 1) {
	    		        	System.out.println(name.getText());
	    		        	System.out.println(flightId.getText());
	    		        	System.out.println(seatCapacity.getText());
	    		        	System.out.print("addFlightButton Clicked");
	    		        	//server connecting
	    		        	Socket s;
							try {
								s = new Socket("localhost",2914);
								OutputStream outputStream=s.getOutputStream();
								DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
							    dataOutputStream.writeUTF("addFlight");
						        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
						        objectOutputStream.writeObject(new FlightData(name.getText(), flightId.getText(), Integer.parseInt(seatCapacity.getText())));
							    InputStream is=s.getInputStream();
							    DataInputStream dis=new DataInputStream(is);
							    String message = dis.readUTF();
							    System.out.println(message);
							} catch (Exception e) {
								
								e.printStackTrace();
							} 
							
	    		        	jPanel.removeAll();
	    		        	render();
	    		        	jPanel.revalidate();
	    		        	
	    		        }  
	    		    }
			});
 		}
 		
 	}
 	//view schedule tab
 	class ViewSchedules extends TabPanel{
 		ScheduleDataTableModel scheduleDatas = new ScheduleDataTableModel();
		String tripidString="";
		ArrayList<FlightData> flightDatas = null;
		ArrayList<AirportData> airportDatas = null;
		
		ViewSchedules(){
			createList();
			setSouthButton("Make Trip"); 		
	   	}
		public void setflightDatas() {
			
			Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("getTrip");
		        InputStream is=s.getInputStream();
			    ObjectInputStream ois=new ObjectInputStream(is);
			    scheduleDatas=(ScheduleDataTableModel) ois.readObject();
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
		}
		public void createList() {
			System.out.print("createlist");
			setflightDatas();
			setList(scheduleDatas);
		}
		@Override
		public void changeIndex(int ind) {
			tripidString = ""+scheduleDatas.get(ind).getTrip_Id();
			addComponentToEastPanel(new LabelAndTextFiled("Flight_Id", ""+scheduleDatas.get(ind).getFlight_Id()));
			addComponentToEastPanel(new LabelAndTextFiled("Total Seat", ""+scheduleDatas.get(ind).getTotal_Seat()));
			addComponentToEastPanel(new LabelAndTextFiled("Base Fare", ""+scheduleDatas.get(ind).getBase_Price()));
			addComponentToEastPanel(new LabelAndTextFiled("From_Airport_Id", ""+scheduleDatas.get(ind).getFrom_Airport_Id()));
			addComponentToEastPanel(new LabelAndTextFiled("To_Airport_Id", ""+scheduleDatas.get(ind).getTo_Airport_Id()));
			
			UtilDateModel TakeOffmodel = new UtilDateModel();
 			UtilDateModel Landingmodel = new UtilDateModel();
 			Properties p = new Properties();
 			p.put("text.today", "Today");
 			p.put("text.month", "Month");
 			p.put("text.year", "Year");
 			JDatePanelImpl takeoffDatePanel = new JDatePanelImpl(TakeOffmodel, p);
 			JDatePanelImpl landingDatePanel = new JDatePanelImpl(Landingmodel, p);
 			JDatePickerImpl takeoffDate = new JDatePickerImpl(takeoffDatePanel,new Design.DateLabelFormatter());
 			JDatePickerImpl LandingDate = new JDatePickerImpl(landingDatePanel,new Design.DateLabelFormatter());
 			
 			TakeOffmodel.setValue(scheduleDatas.get(ind).getTakeoff_Date());
 			Landingmodel.setValue(scheduleDatas.get(ind).getLanding_Date());
 			
 			JSpinner takeoffTimeSpinner = new JSpinner( new SpinnerDateModel() );
 			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(takeoffTimeSpinner, "HH:mm:ss");
 			takeoffTimeSpinner.setEditor(timeEditor);
 			takeoffTimeSpinner.setValue(scheduleDatas.get(ind).getTakeoff_time());
 			JSpinner landingTimeSpinner = new JSpinner( new SpinnerDateModel() );
 			JSpinner.DateEditor landingTimeEditor = new JSpinner.DateEditor(landingTimeSpinner, "HH:mm:ss");
 			landingTimeSpinner.setEditor(landingTimeEditor);
 			landingTimeSpinner.setValue(scheduleDatas.get(ind).getLanding_time());
 			
			JPanel TakeOffDateJPanel = new JPanel(); 
 			TakeOffDateJPanel.add(new JLabel("Takeoff Date"));
 			TakeOffDateJPanel.add(takeoffDate);
 			TakeOffDateJPanel.setMaximumSize(new Dimension(500,60));
 			JPanel dateJPanel = new JPanel(); 
 			dateJPanel.add(new JLabel("Landing Date"));
 			dateJPanel.add(LandingDate);
 			dateJPanel.setMaximumSize(new Dimension(500,60));
 			
 			JPanel TakeOffTimeJPanel = new JPanel(); 
 			TakeOffTimeJPanel.add(new JLabel("TakeOff Time"));
 			TakeOffTimeJPanel.add(takeoffTimeSpinner);
 			TakeOffTimeJPanel.setMaximumSize(new Dimension(500,60));
 			JPanel timeJPanel = new JPanel(); 
 			timeJPanel.add(new JLabel("Landing Time"));
 			timeJPanel.add(landingTimeSpinner);
 			timeJPanel.setMaximumSize(new Dimension(500,60));
 			addComponentToEastPanel(TakeOffDateJPanel);
 			addComponentToEastPanel(dateJPanel);
 			addComponentToEastPanel(TakeOffTimeJPanel);
 			addComponentToEastPanel(timeJPanel);
 			
 			JPanel jPanel = new JPanel(new FlowLayout());
 			jPanel.add(new JButton("Update"));
 			jPanel.add(new JButton("Delete"));
 			addComponentToEastPanel(jPanel);
 			setEastPannel();
		}@Override
 		public void updateButtonClickEvent() {
 			LabelAndTextFiled flightId = (LabelAndTextFiled) jp.getComponent(0);
 			LabelAndTextFiled totalSeat = (LabelAndTextFiled) jp.getComponent(1);
 			LabelAndTextFiled baseFare = (LabelAndTextFiled) jp.getComponent(2);
 			LabelAndTextFiled from_Airport_Id = (LabelAndTextFiled) jp.getComponent(3);
 			LabelAndTextFiled to_Airport_Id = (LabelAndTextFiled) jp.getComponent(4);
 			
 			JDatePickerImpl takeoffDate = (JDatePickerImpl) ((JPanel) jp.getComponent(5)).getComponent(1);
 			JDatePickerImpl landingDate = (JDatePickerImpl) ((JPanel) jp.getComponent(6)).getComponent(1);
 			JSpinner takeoffTime = (JSpinner) ((JPanel) jp.getComponent(7)).getComponent(1);
 			JSpinner landingTime = (JSpinner) ((JPanel) jp.getComponent(8)).getComponent(1);
 			
 			ScheduleData scheduleData = new ScheduleData(1, flightId.getText(),Integer.parseInt(baseFare.getText()),Integer.parseInt(totalSeat.getText()), from_Airport_Id.getText(), to_Airport_Id.getText(), (Date) takeoffDate.getModel().getValue(),(Date) landingDate.getModel().getValue(), (Date) takeoffTime.getModel().getValue(),(Date) landingTime.getModel().getValue());
        	
 			Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("updateTrip");
			    dataOutputStream.writeUTF(tripidString);
		        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		        objectOutputStream.writeObject(scheduleData);
			    InputStream is=s.getInputStream();
			    DataInputStream dis=new DataInputStream(is);
			    String message = dis.readUTF();
			    System.out.println(message);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
 		}
		@Override
		public void deleteButtonClickEvent() {
 			Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("deleteTrip");
			    dataOutputStream.writeUTF(tripidString);
			    InputStream is=s.getInputStream();
			    DataInputStream dis=new DataInputStream(is);
			    String message = dis.readUTF();
			    System.out.println(message);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
 		}
		@Override
		public void southButtonClickEvent(){
			System.out.print("Add Schedule Button clicked");
			JButton addTripButton = new JButton("Add Trip");
			JButton cancelButton = new JButton("Cancel");
 			jPanel.removeAll();
 			JPanel addTripPanel = new JPanel();
 			addTripPanel.setLayout(new GridLayout(0,2));
 			
 			JPanel flightId = new JPanel();
 			JPanel flightDetails = new JPanel();
 			JPanel fairport = new JPanel();
 			JPanel tairport = new JPanel();
 			
 			LabelAndTextFiled totalseat = new LabelAndTextFiled("Total Seat", "");
 			LabelAndTextFiled basefare = new LabelAndTextFiled("Base Fare", "");
 			
 			UtilDateModel TakeOffmodel = new UtilDateModel();
 			UtilDateModel Landingmodel = new UtilDateModel();
 			Properties p = new Properties();
 			p.put("text.today", "Today");
 			p.put("text.month", "Month");
 			p.put("text.year", "Year");
 			JDatePanelImpl takeoffDatePanel = new JDatePanelImpl(TakeOffmodel, p);
 			JDatePanelImpl landingDatePanel = new JDatePanelImpl(Landingmodel, p);
 			JDatePickerImpl takeoffDate = new JDatePickerImpl(takeoffDatePanel,new Design.DateLabelFormatter());
 			JDatePickerImpl LandingDate = new JDatePickerImpl(landingDatePanel,new Design.DateLabelFormatter());
 			
 			JSpinner takeoffTimeSpinner = new JSpinner( new SpinnerDateModel() );
 			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(takeoffTimeSpinner, "HH:mm:ss");
 			takeoffTimeSpinner.setEditor(timeEditor);
 			takeoffTimeSpinner.setValue(new Date());
 			JSpinner landingTimeSpinner = new JSpinner( new SpinnerDateModel() );
 			JSpinner.DateEditor landingTimeEditor = new JSpinner.DateEditor(landingTimeSpinner, "HH:mm:ss");
 			landingTimeSpinner.setEditor(landingTimeEditor);
 			landingTimeSpinner.setValue(new Date());
 			
 			
 			
 			JComboBox<String> flightidBox = new JComboBox<>();
 			JComboBox<String> fromairportBox = new JComboBox<>();
 			JComboBox<String> toairportBox = new JComboBox<>();
 			
 			//server connecting
        	Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("getFlightandAirportList");
		        InputStream is=s.getInputStream();
			    ObjectInputStream ois=new ObjectInputStream(is);
			    flightDatas = (ArrayList<FlightData>) ois.readObject();
			    airportDatas = (ArrayList<AirportData>) ois.readObject();
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
 			for(int i=0;i<flightDatas.size();i++) {
 				flightidBox.addItem(flightDatas.get(i).getFlightId());
 			}
 			for(int i=0;i<airportDatas.size();i++) {
 				fromairportBox.addItem(airportDatas.get(i).getAirportId());
 				toairportBox.addItem(airportDatas.get(i).getAirportId());
 			}
 			
 			JLabel flightNameJLabel = new JLabel(flightDatas.get(0).getName());
 			totalseat.setText(""+ViewSchedules.this.flightDatas.get(0).getTotalSeat());
 			JLabel fromAirportNameJLabel = new JLabel(airportDatas.get(0).getName());
 			JLabel toAirportNameJLabel = new JLabel(airportDatas.get(0).getName());
 			
 			flightidBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					int index = flightidBox.getSelectedIndex();
					System.out.println(ViewSchedules.this.flightDatas.get(index).getName());
					flightNameJLabel.setText(ViewSchedules.this.flightDatas.get(index).getName());
					totalseat.setText(""+ViewSchedules.this.flightDatas.get(index).getTotalSeat());
					}
			});
 			fromairportBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					int index = fromairportBox.getSelectedIndex();
					System.out.println(ViewSchedules.this.airportDatas.get(index).getName());
					fromAirportNameJLabel.setText(ViewSchedules.this.airportDatas.get(index).getName());
					}
			});
 			toairportBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					int index = toairportBox.getSelectedIndex();
					System.out.println(ViewSchedules.this.airportDatas.get(index).getName());
					toAirportNameJLabel.setText(ViewSchedules.this.airportDatas.get(index).getName());
					}
			});
 			
 			
 			flightId.add(new JLabel("Flight Id  "));
 			flightId.add(flightidBox);
 			flightDetails.add(new JLabel("Flight Name:"));
 			flightDetails.add(flightNameJLabel);
 			fairport.add(new JLabel("From Airport"));
 			fairport.add(fromairportBox);
 			fairport.add(fromAirportNameJLabel);
 			tairport.add(new JLabel("To Airport"));
 			tairport.add(toairportBox);
 			tairport.add(toAirportNameJLabel);
 			
 			addTripPanel.add(new JLabel(""));
 			addTripPanel.add(new JLabel(""));
 			addTripPanel.add(flightId);
 			addTripPanel.add(flightDetails);
 			addTripPanel.add(fairport);
 			addTripPanel.add(tairport);
 			addTripPanel.add(totalseat);
 			addTripPanel.add(basefare);
 			
 			JPanel TakeOffDateJPanel = new JPanel(); 
 			TakeOffDateJPanel.add(new JLabel("Takeoff Date"));
 			TakeOffDateJPanel.add(takeoffDate);
 			TakeOffDateJPanel.setMaximumSize(new Dimension(500,60));
 			JPanel dateJPanel = new JPanel(); 
 			dateJPanel.add(new JLabel("Landing Date"));
 			dateJPanel.add(LandingDate);
 			dateJPanel.setMaximumSize(new Dimension(500,60));
 			
 			JPanel TakeOffTimeJPanel = new JPanel(); 
 			TakeOffTimeJPanel.add(new JLabel("TakeOff Time"));
 			TakeOffTimeJPanel.add(takeoffTimeSpinner);
 			TakeOffTimeJPanel.setMaximumSize(new Dimension(500,60));
 			JPanel timeJPanel = new JPanel(); 
 			timeJPanel.add(new JLabel("Landing Time"));
 			timeJPanel.add(landingTimeSpinner);
 			timeJPanel.setMaximumSize(new Dimension(500,60));
 			addTripPanel.add(TakeOffDateJPanel);
 			addTripPanel.add(dateJPanel);
 			addTripPanel.add(TakeOffTimeJPanel);
 			addTripPanel.add(timeJPanel);
 			addTripPanel.add(new JLabel(""));
 			addTripPanel.add(new JLabel(""));
 			JLabel welcomeLabel = new JLabel("Add a Trip");
 			welcomeLabel.setBackground(Color.blue);
 			welcomeLabel.setFont(new Font("arial",Font.PLAIN , 30));
 			JPanel southJPanel = new JPanel();
 			southJPanel.add(addTripButton);
 			southJPanel.add(cancelButton);
 					
 			jPanel.add(welcomeLabel,BorderLayout.NORTH);
 			jPanel.add(addTripPanel,BorderLayout.CENTER);
 			jPanel.add(southJPanel,BorderLayout.SOUTH);
 			jPanel.repaint();
 			jPanel.revalidate();
 			cancelButton.addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent evt) {
	    		     if (evt.getClickCount() == 1) {
	    		    	 System.out.println("cancel Clicked");
	    		    	jPanel.removeAll();
	    		        render();
	    		        jPanel.revalidate();
	    		     }
	    		 }
 			});
 			addTripButton.addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent evt) {
	    		        if (evt.getClickCount() == 1) {
	    		        	System.out.println("addTripButton Clicked");
	    		        	
	    		        	ScheduleData scheduleData = new ScheduleData(1, flightDatas.get(flightidBox.getSelectedIndex()).getFlightId(),Integer.parseInt(basefare.getText()),Integer.parseInt(totalseat.getText()), airportDatas.get(fromairportBox.getSelectedIndex()).getAirportId(),airportDatas.get(toairportBox.getSelectedIndex()).getAirportId(), (Date) takeoffDate.getModel().getValue(),(Date) LandingDate.getModel().getValue(), (Date) takeoffTimeSpinner.getModel().getValue(),(Date) landingTimeSpinner.getModel().getValue());
	    		        	 	
	    		        	Socket s;
							try {
								s = new Socket("localhost",2914);
								OutputStream outputStream=s.getOutputStream();
								DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
							    dataOutputStream.writeUTF("addTrip");
						        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
						        objectOutputStream.writeObject(scheduleData);
							    InputStream is=s.getInputStream();
							    DataInputStream dis=new DataInputStream(is);
							    String message = dis.readUTF();
							    System.out.println(message);
							} catch (Exception e) {
								
								e.printStackTrace();
							} 
							
	    		        	jPanel.removeAll();
	    		        	render();
	    		        	jPanel.revalidate();
	    		        }  
	    		    }
			});
		}
		
	}
 	//view airport tab
 	class ViewAirports extends TabPanel{
 		String airportidString="";
 		AirportDataTableModel airportDatas = new AirportDataTableModel();
		ViewAirports(){
			createList();
			setSouthButton("Create Airport"); 		
	   	}
		public void setflightDatas() {
			airportDatas = null;
 			//server connecting
        	Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("getAirport");
		        InputStream is=s.getInputStream();
			    ObjectInputStream ois=new ObjectInputStream(is);
			    airportDatas=(AirportDataTableModel) ois.readObject();
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
		}
		public void createList() {
			System.out.print("createlist");
			setflightDatas();
			setList(airportDatas);
		}
		@Override
		public void changeIndex(int ind) {
			super.changeIndex(ind);
			airportidString = airportDatas.get(ind).getAirportId();
			addComponentToEastPanel(new LabelAndTextFiled("AirportId", airportDatas.get(ind).getAirportId()));
			addComponentToEastPanel(new LabelAndTextFiled("AirportName", airportDatas.get(ind).getName()));
			addComponentToEastPanel(new LabelAndTextFiled("City", airportDatas.get(ind).getCity()));
			addComponentToEastPanel(new LabelAndTextFiled("Country", airportDatas.get(ind).getCountry()));
			JPanel jPanel = new JPanel(new FlowLayout());
 			jPanel.add(new JButton("Update"));
 			jPanel.add(new JButton("Delete"));
 			addComponentToEastPanel(jPanel);
 			setEastPannel();
		}
		@Override
 		public void updateButtonClickEvent() {
 			LabelAndTextFiled airportId = (LabelAndTextFiled) jp.getComponent(0);
 			LabelAndTextFiled airportName = (LabelAndTextFiled) jp.getComponent(1);
 			LabelAndTextFiled airportCity = (LabelAndTextFiled) jp.getComponent(2);
 			LabelAndTextFiled airportCountry = (LabelAndTextFiled) jp.getComponent(3);
 			AirportData airportData = new AirportData(airportId.getText(), airportName.getText(), airportCity.getText(), airportCountry.getText());
 			Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("updateAirport");
			    dataOutputStream.writeUTF(airportidString);
		        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		        objectOutputStream.writeObject(airportData);
			    InputStream is=s.getInputStream();
			    DataInputStream dis=new DataInputStream(is);
			    String message = dis.readUTF();
			    System.out.println(message);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
 		}
 		@Override
 		public void deleteButtonClickEvent() {
 			Socket s;
			try {
				s = new Socket("localhost",2914);
				OutputStream outputStream=s.getOutputStream();
				DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
			    dataOutputStream.writeUTF("deleteAirport");
			    dataOutputStream.writeUTF(airportidString);
			    InputStream is=s.getInputStream();
			    DataInputStream dis=new DataInputStream(is);
			    String message = dis.readUTF();
			    System.out.println(message);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
 		}
		@Override
		public void southButtonClickEvent(){
			System.out.print("Add Schedule Button clicked");
			JButton addAirportButton = new JButton("Add Airport");
			JButton cancelButton = new JButton("    Cancel    ");
 			jPanel.removeAll();
 			JPanel addTripPanel = new JPanel();
 			addTripPanel.setLayout(new BoxLayout(addTripPanel, BoxLayout.Y_AXIS));
 			LabelAndTextFiled id = new LabelAndTextFiled("AirportId", "");
 			LabelAndTextFiled name = new LabelAndTextFiled("AirportName", "");
 			LabelAndTextFiled city = new LabelAndTextFiled("City", ""+"");
 			LabelAndTextFiled country = new LabelAndTextFiled("Country", ""+"");
 			addTripPanel.add(id);
 			addTripPanel.add(name);
 			addTripPanel.add(city);
 			addTripPanel.add(country);
 			
 			JPanel btngroup = new JPanel();
 			btngroup.add(addAirportButton);
 			btngroup.add(cancelButton);
 			addTripPanel.add(btngroup);
 			
 			jPanel.add(addTripPanel);
 			jPanel.repaint();
 			jPanel.revalidate();
 			cancelButton.addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent evt) {
	    		     if (evt.getClickCount() == 1) {
	    		    	 System.out.println("cancel Clicked");
	    		    	jPanel.removeAll();
	    		        render();
	    		        jPanel.revalidate();
	    		     }
	    		 }
 			});
 			addAirportButton.addMouseListener(new MouseAdapter() {
 				public void mouseClicked(MouseEvent evt) {
	    		        JButton list = (JButton)evt.getSource();
	    		        if (evt.getClickCount() == 1) {
	    		        	System.out.println("addAirportButton Clicked");
	    		        	System.out.println(id.getText());
	    		        	System.out.println(name.getText());
	    		        	System.out.println(city.getText());
	    		        	System.out.println(country.getText());
	    		        	//server connecting
	    		        	Socket s;
							try {
								s = new Socket("localhost",2914);
								OutputStream outputStream=s.getOutputStream();
								DataOutputStream dataOutputStream=new DataOutputStream(outputStream);
							    dataOutputStream.writeUTF("addAirport");
						        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
						        objectOutputStream.writeObject(new AirportData(id.getText(),name.getText(),city.getText(),country.getText()));
							    InputStream is=s.getInputStream();
							    DataInputStream dis=new DataInputStream(is);
							    String message = dis.readUTF();
							    System.out.println(message);
							} catch (Exception e) {
								
								e.printStackTrace();
							} 
	    		        	jPanel.removeAll();
	    		        	render();
	    		        	jPanel.revalidate();
	    		        }  
	    		    }
			});
		}
		
	}

}




