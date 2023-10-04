package com.smart_home_system.devices;

public class DoorLock extends Device{

	    public DoorLock(int id) {
	        super(id,"door");
	        this.status = "locked";
	    }
	    public void turnOn() {
	    	status = "locked";
	    }

	    public void turnOff() {
	    	status = "unlocked";
	    }
}
