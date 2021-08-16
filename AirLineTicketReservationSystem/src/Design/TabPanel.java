package Design;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import Server.MyServer;
import javabean.FarrayList;
import javabean.FlightData;
import sun.net.www.content.image.jpeg;


public class TabPanel extends Panel{
	int index=0;
	JPanel jp = new JPanel();
	JList flightList;
	public TabPanel() {
		setLayout(new BorderLayout());
	}
	public void createList() {}
	public void buttonClickEvent() {}
	
	public void addComponentToEastPanel(Component component) {
		jp.add(component);
	}
	
	public void setEastPannel() {
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
  		add(BorderLayout.EAST,jp);
  		jp.getComponent(3).addMouseListener(new MouseAdapter() {
  		    public void mouseClicked(MouseEvent evt) {
  		        JButton list = (JButton)evt.getSource();
  		        if (evt.getClickCount() == 2) {
  		        	MyServer.datas.add(new FlightData("VK",1,150));
  		        	System.out.print(MyServer.datas.getSize());
  		        	remove(flightList);
  		        	createList();
  		        	revalidate();
  		        	
  		        }  
  		    }
  		});
  		revalidate();
	}
	
	public void changeIndex(int ind) {
	}
	public void setList(FarrayList<FlightData> f) {
		
  		
  		flightList= new JList(f);
  		add(BorderLayout.CENTER,flightList);
  		
  		//Flights list double click action listner
  		flightList.addMouseListener(new MouseAdapter() {
  		    public void mouseClicked(MouseEvent evt) {
  		        JList list = (JList)evt.getSource();
  		        if (evt.getClickCount() == 2) {
  		        	int index = list.locationToIndex(evt.getPoint());
  		        	System.out.print(index);
  		        	jp.removeAll();
  		        	changeIndex(index);
  		        	
  		        
  		        }
  		        
  		    }
  		});
	}
	public void setSouthButton(String btnValue){
		//New Button
  		JButton addFlightButton = new JButton(btnValue);
  		add(BorderLayout.SOUTH,addFlightButton);

  		//Button ClickedListner
  		addFlightButton.addMouseListener(new MouseAdapter() {
  			public void mouseClicked(MouseEvent evt) {
  		        if (evt.getClickCount() == 1) {
  		        	buttonClickEvent();
  		        }
  		    }
		});
	}

}
