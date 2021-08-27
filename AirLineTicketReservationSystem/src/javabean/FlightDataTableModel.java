package javabean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class FlightDataTableModel implements TableModel,Serializable{
	String column[]={"Flight ID","Flight Name","Total Seat"}; 
	ArrayList<FlightData> list= new ArrayList<FlightData>(); 
	public void add(FlightData f) {
		list.add(f);
	}
	public FlightData get(int index) {
		return list.get(index);
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return column[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if(columnIndex==0) {
			
			return list.get(rowIndex).getFlightId();
		}
		else if(columnIndex==1) {
			
			return list.get(rowIndex).getName();
		}
		else  {
			return list.get(rowIndex).getTotalSeat();
		}	
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub	
	}
}
