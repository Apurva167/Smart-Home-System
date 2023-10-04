package com.smart_home_system.devices;

public class Light extends Device{
	   
	    public Light(int id) {
	        super(id,"light");
	        this.status = "off";
	    }

	    public void turnOn() {
	        status = "on";
	    }

	    public void turnOff() {
	        status = "off";
	    }
}
