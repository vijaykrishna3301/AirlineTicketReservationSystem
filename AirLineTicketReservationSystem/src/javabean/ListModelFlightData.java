package javabean;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ListModelFlightData<FlightData>  implements ListModel<FlightData>{
	ArrayList<FlightData> m= new ArrayList<FlightData>(); 
	public void add(FlightData f) {
		m.add(f);
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return m.size();
	}

	@Override
	public FlightData getElementAt(int index) {
		// TODO Auto-generated method stub
		
		return m.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub
		
	}
	public FlightData get(int index) {
		// TODO Auto-generated method stub
		return m.get(index);
	}

	
}
