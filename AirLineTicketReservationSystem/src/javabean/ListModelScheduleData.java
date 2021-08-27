package javabean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ListModelScheduleData<ScheduleData> implements ListModel<ScheduleData>,Serializable{
	ArrayList<ScheduleData> list= new ArrayList<ScheduleData>();
	public void add(ScheduleData data) {
		list.add(data);
	}
	public ScheduleData get(int index) {
		// TODO Auto-generated method stub
		return list.get(index);
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public ScheduleData getElementAt(int index) {
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
