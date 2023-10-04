package com.smart_home_system.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.smart_home_system.devices.Device;
import com.smart_home_system.devices.DeviceFactory;
import com.smart_home_system.devices.ThermoStat;
import com.smart_home_system.tasks.AutomatedTrigger;
import com.smart_home_system.tasks.ScheduledTask;

public class SmartHomeSystem {
	private Map<Integer, Device> devices;
	private Map< Integer,String> idType;
    private List<ScheduledTask> scheduledTasks;
    private List<AutomatedTrigger> automatedTriggers;

    public SmartHomeSystem() {
        devices = new HashMap<>();
        scheduledTasks = new ArrayList<>();
        automatedTriggers = new ArrayList<>();
        idType = new HashMap<>();
    }

    public void addDevice(int id, String type) {
        Device device = DeviceFactory.createDevice(id, type);
        devices.put(id, device);
        idType.put(id,type);
    }

    public void turnOn(int id) {
        Device device = devices.get(id);
        if (device != null) {
            device.turnOn();
        }
    }
    public void turnOff(int id) {
        Device device = devices.get(id);
        if (device != null) {
            device.turnOff();
        }
    }
    
    public void scheduleDevice(int deviceId, String time, String action) {
        Device device = devices.get(deviceId);
        if (device != null) {
            ScheduledTask task = new ScheduledTask(device, time, action);
            scheduledTasks.add(task);
            scheduleTaskExecution(task);
        }
    }
    
    private void scheduleTaskExecution(ScheduledTask task) {
        // Schedule the task to execute at the specified time
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task.execute();
            }
        }, parseTime(task.getTime()));
    }

    private long parseTime(String timeString) {
 
        // Define a SimpleDateFormat with the desired time format
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long millisecondsSinceMidnight=0;
        try {
            // Parse the time string to obtain a Date object
            Date date = sdf.parse(timeString);

            // Calculate the time in milliseconds since midnight
            millisecondsSinceMidnight = date.getTime();
            
          } catch (Exception e) {
            e.printStackTrace();
        }
        return millisecondsSinceMidnight;
        
    }
    
    public void addAutomatedTrigger(String type,String relation,int value, String action) {
    	int checkDeviceId=0;
    	for(int x:idType.keySet()) {
         	if(idType.get(x).equals(type)) {
         		checkDeviceId=x;
         	}
         }
    	AutomatedTrigger trigger = new AutomatedTrigger(type,relation,value, action,devices.get(checkDeviceId));
        automatedTriggers.add(trigger);
        //startAutomatedTriggerChecking();
    }
    
    public void startAutomatedTriggerChecking() {
        // Create a timer and schedule it to check triggers every 60 seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkAutomatedTriggers();
            }
        }, 0, 60 * 1000); // Check triggers every 60 seconds
    }

    public void checkAutomatedTriggers() {
        for (AutomatedTrigger trigger : automatedTriggers) {
            if (trigger.isTriggered()) {
                // Execute the action associated with the trigger
                trigger.execute(devices);
            }
        }
    }
    
    public void statusReport() {
    	System.out.println("Status Report:");
    	for(int x:devices.keySet()) {
    	if(devices.get(x).getType().equals("thermostat")) {
    		ThermoStat thermostat= (ThermoStat)devices.get(x);
    		System.out.println("\""+devices.get(x).getType()+" is set to  "+thermostat.getTemperature()+"\"");
    		continue;
    	}
    	System.out.println("\""+devices.get(x).getType()+" "+devices.get(x).getId()+" "+devices.get(x).getStatus()+"\"");
    	}
    }
}
