package com.smart_home_system.devices;

public class DeviceFactory {
	 public static Device createDevice(int id, String type) {
	        switch (type) {
	            case "light":
	                return new Light(id);
	            case "thermostat":
	                return new ThermoStat(id);
	            case "door":
	                return new DoorLock(id);
	            default:
	                throw new IllegalArgumentException("Unknown device type: " + type);
	        }
	    }
}
