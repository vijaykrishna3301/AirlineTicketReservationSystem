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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import javabean.ListModelFlightData;
import javabean.FlightData;
import sun.net.www.content.image.jpeg;


public class TabPanel extends Panel{
	int index=0;
	public JPanel jp = new JPanel();
	JTable flightList;
	JScrollPane center;
	public TabPanel() {
		setLayout(new BorderLayout());
	}
	public void createList() {}
	public void southButtonClickEvent() {}
	public void updateButtonClickEvent() {}
	public void deleteButtonClickEvent() {}
	public void changeIndex(int ind) {}
	
	public void addComponentToEastPanel(Component component) {
		jp.add(component);
	}
	//set east panel
	public void setEastPannel() {
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
  		add(BorderLayout.EAST,jp);
  		int componentCount = jp.getComponentCount();
  		JPanel jPanel = (JPanel) jp.getComponent(componentCount-1);
  		JButton updateButton=(JButton) jPanel.getComponent(0);
  		JButton deleteButton=(JButton) jPanel.getComponent(1);
  	    updateButton.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 1) {
		        	System.out.println("update button ");
		        	updateButtonClickEvent();
		        	remove(center);
		        	createList();
		        	revalidate();
		        }  
		    }
		});
  	  deleteButton.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 1) {
		        	System.out.println("delete button ");
		        	deleteButtonClickEvent();
		        	remove(center);
		        	createList();
		        	jp.removeAll();
		        	revalidate();
		        }  
		    }
		});
  		revalidate();
	}
	//set list in center
	public void setList(TableModel f) {
		
		flightList= new JTable(f);
		center = new JScrollPane(flightList);
		
  		add(BorderLayout.CENTER,center);
  		
  		flightList.setFont(new Font("arial",Font.PLAIN , 16));
  		//Flights list double click action listner
  		flightList.addMouseListener(new MouseAdapter() {
  		    public void mouseClicked(MouseEvent evt) {
  		    	JTable list = (JTable)evt.getSource();
  		        if (evt.getClickCount() == 2) {
  		        	int index = list.getSelectedRow();
  		        	System.out.print(index);
  		        	jp.removeAll();
  		        	changeIndex(index);
  		        }
  		    }
  		});
	}
	//south button
	public void setSouthButton(String btnValue){
		//New Button
  		JButton addFlightButton = new JButton(btnValue);
  		add(BorderLayout.SOUTH,addFlightButton);

  		//Button ClickedListner
  		addFlightButton.addMouseListener(new MouseAdapter() {
  			public void mouseClicked(MouseEvent evt) {
  		        if (evt.getClickCount() == 1) {
  		        	southButtonClickEvent();
  		        }
  		    }
		});
	}

}
