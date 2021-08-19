package javabean;

import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.sun.javafx.geom.AreaOp.AddOp;

public class ListModelAirportData<AirportData> implements ListModel<AirportData> {
	ArrayList<AirportData> list= new ArrayList<AirportData>();
	public void add(AirportData data) {
		list.add(data);
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return list.size();
	}
	
	@Override
	public AirportData getElementAt(int index) {
		// TODO Auto-generated method stub
		return list.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		// TODO Auto-generated method stub

	}

}
