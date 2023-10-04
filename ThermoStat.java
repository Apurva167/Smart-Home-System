package com.smart_home_system.devices;

public class ThermoStat extends Device {
	    private int temperature;

	    public ThermoStat(int id) {
	        super(id,"thermostat");
	        this.temperature = 70;
	    }
        
	    public int getTemperature() {
	    	return temperature;
	    }

	    public void setTemperature(int temperature) {
	        this.temperature = temperature;
	    } 
}
