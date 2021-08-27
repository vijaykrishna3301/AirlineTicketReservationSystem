package javabean;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class AirportDataTableModel implements TableModel,Serializable{
	String column[]={"Airport ID","Airport NAME","Airport CITY","Airport Country"}; 
	ArrayList<AirportData> list= new ArrayList<AirportData>();
	public void add(AirportData data) {
		list.add(data);
	}
	public AirportData get(int s) {
		return list.get(s);
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
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
			
			return list.get(rowIndex).getAirportId();
		}
		else if(columnIndex==1) {
			
			return list.get(rowIndex).getName();
		}
		else if(columnIndex==2) {
			
			return list.get(rowIndex).getCity();
		}
		else {
			return list.get(rowIndex).getCountry();
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
