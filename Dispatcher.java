/*
 * Dispatcher.java
 * 
 * Copyright 2015 dancy <dancy@DANCY-HOME>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

//~ 8.2 Imagine you have a call center with three levels of employees: respondent, manager,
//~ and director. An incoming telephone call must be first allocated to a respondent
//~ who is free. If the respondent can't handle the call, he or she must escalate the call
//~ to a manager. If the manager is not free or notable to handle it, then the call should
//~ be escalated to a director. Design the classes and data structures for this problem.
//~ Implement a method dispatchCaL L () which assigns a call to the first available
//~ employee.

//NOTE: add a queue to receive call
public class Dispatcher {
	private static Dispatcher handler;
	private List<Staff> staffList = new LinkedList<Staff>();
	int maxLevel = 2;
	
	public static Dispatcher getInstance() {
		if (handler == null)
			handler = new Dispatcher();
		return handler;
	}
	
	public void setStaffList(List<Staff> staffs) {
		staffList = staffs;
	}
	
	private Dispatcher() {
	}
	
	public boolean dispatchCall(Call call, int level ) {
		Staff recept = getAvailableStaff(level);
		if (recept == null)
			return false; //or throw exception
		if (!recept.processCall(call)) {
			recept.setStatus(Status.FREE);
			adjustStaffList(recept);
			return escalate(recept, call);
		}
	}
	
	//	sort by status
	private void adjustStaffList(Staff staff) {
	}
	
	private Staff getAvailableStaff(int level) {
		int currentLevel = level;
		while (currentLevel <= maxLevel) {
			for (Staff staff : staffList) {
				if (staff.getStatus() != Status.FREE)
					break;
				if (staff.level == level ) {
					staff.setStatus(Status.BUSY);
					adjustStaffList(staff);
					return staff;
				}
			}
			currentLevel++;
		}
		
		return null;
	}
	
	public boolean escalate(Staff staff, Call call) {
		if (staff.getLevel() == MAXLEVEL)
			return false;
			
		dispatchCall(call, staff.getLevel() + 1);
	}

}

public interface Call {
}


public abstract class Staff {
	public enum Status {
		BUSY, FREE
	}

	private int level;
	private Status status;
	
	public abstract boolean processCall(Call call) {return false;}
	public Status getStatus() {return status;}
	public void setStatus(Status status) {this.status = status; }
	public int getLevel() {return level;}
}

public  Respondent implement Staff {

	public Respondent() {
			level = 0;
	}
		
	public boolean processCall() {
		return true;
	}
	
	public Status getStatus() {
		return Status.FREE;
	}
}
	
	
	public static void main (String args[]) {
		
	}
}

