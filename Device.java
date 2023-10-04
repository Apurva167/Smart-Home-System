package com.smart_home_system.devices;

 public class Device {
	 
	    protected final int id;
	    protected final String type;
	    protected String status;
	    
	    public Device(int id,String type) {
	        this.id = id;
	        this.type=type;
	    }
	    public String getStatus() {
	        return status;
	    }
	    public void turnOn() {
	        status = "on";
	    }
	    public void turnOff() {
	        status = "off";
	    }
	    public int getId() {
	        return id;
	    }
	    public String getType() {
	        return type;
	    }
	    
}

