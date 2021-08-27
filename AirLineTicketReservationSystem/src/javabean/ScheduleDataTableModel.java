package javabean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ScheduleDataTableModel implements TableModel,Serializable{
	
	String column[]={"flight ID","Base Fare","Total Seat","From Airport","To Airport","Takeoff Date","Takeoff Time","Landing Date","Landing Time"}; 
	ArrayList<ScheduleData> list= new ArrayList<ScheduleData>();
	public void add(ScheduleData data) {
		list.add(data);
	}
	public ScheduleData get(int s) {
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
		return 9;
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
			
			return list.get(rowIndex).getFlight_Id();
		}
		else if(columnIndex==1) {
			
			return list.get(rowIndex).getBase_Price();
		}
		else if(columnIndex==2) {
			
			return list.get(rowIndex).getTotal_Seat();
		}
		else if(columnIndex==3) {
			
			return list.get(rowIndex).getFrom_Airport_Id();
		}
		else if(columnIndex==4) {
			
			return list.get(rowIndex).getTo_Airport_Id();
		}
		else if(columnIndex==5) {
			
			return list.get(rowIndex).getTakeoff_Date();
		}
		else if(columnIndex==6) {
			
			return list.get(rowIndex).getTakeoff_time();
		}
		else if(columnIndex==7) {
			
			return list.get(rowIndex).getLanding_Date();
		}
		else  {
			return list.get(rowIndex).getLanding_time();
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
