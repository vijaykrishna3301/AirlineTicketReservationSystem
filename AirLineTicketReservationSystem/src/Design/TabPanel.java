package Design;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import Server.MyServer;
import javabean.ListModelFlightData;
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
	public void changeIndex(int ind) {}
	
	public void addComponentToEastPanel(Component component) {
		jp.add(component);
	}
	public void setEastPannel() {
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
  		add(BorderLayout.EAST,jp);
  		Component[] comp = jp.getComponents();
  	    for (int i = 0;i<comp.length;i++) {
  	        if (comp[i] instanceof JButton) {
  	        	jp.getComponent(i).addMouseListener(new MouseAdapter() {
  	    		    public void mouseClicked(MouseEvent evt) {
  	    		        JButton list = (JButton)evt.getSource();
  	    		        if (evt.getClickCount() == 2) {
  	    		        	System.out.print(MyServer.datas.getSize());
  	    		        	remove(flightList);
  	    		        	createList();
  	    		        	revalidate();
  	    		        }  
  	    		    }
  	    		});
  	        }
  	    }
  		revalidate();
	}
	public void setList(ListModel f,JLabel title) {
		flightList= new JList(f);
  		JPanel center = new JPanel(new BorderLayout());
  		add(BorderLayout.CENTER,center);
  		center.add(BorderLayout.NORTH,title);
  		center.add(BorderLayout.CENTER,flightList);
  		title.setFont(new Font("arial",Font.PLAIN , 16));
  		flightList.setFont(new Font("arial",Font.PLAIN , 16));
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
