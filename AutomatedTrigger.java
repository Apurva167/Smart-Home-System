package com.smart_home_system.tasks;

import java.util.Map;

import com.smart_home_system.devices.Device;
import com.smart_home_system.devices.ThermoStat;

public class AutomatedTrigger {
	private final String condition;
    private final String action;
    private  Device checkDevice;
    public AutomatedTrigger(String type,String relation,int value, String action,Device checkDevice) {
        this.condition = type+" "+relation+" "+value;
        this.action = action;
        this.checkDevice=checkDevice;
    }

    public boolean isTriggered() {
    	 String[] parts = condition.split(" ");
         ThermoStat thermostat=(ThermoStat)checkDevice;
         if (parts.length != 3) {
             return false;
         }

         String operator = parts[1];    
         int value = Integer.parseInt(parts[2]); 
         switch(operator) {
         case "=":
        	 return thermostat.getTemperature() == value;
         case ">":
        	 return thermostat.getTemperature() > value;
         case "<":
        	 return thermostat.getTemperature() < value;
         case ">=":
        	 return thermostat.getTemperature() >= value;
         case "<=":
        	 return thermostat.getTemperature() <= value;
         default:
        	 return false;
         }
  
    }
    
    // Execute the action on the specified device
    public void execute(Map<Integer, Device> devices) {
    	StringBuilder actionNameSb=new StringBuilder();
    	int i=0;
    	while(action.charAt(i)!='(') {
    		actionNameSb.append(action.charAt(i++));
    	}
    	String actionName=actionNameSb.toString();
    	int targetDeviceId=action.charAt(++i)-48;
        if (actionName.equals("TurnOn")) {
            devices.get(targetDeviceId).turnOn();
        } else if (actionName.equals("TurnOff")) {
            devices.get(targetDeviceId).turnOff();
        }
    }     
}
