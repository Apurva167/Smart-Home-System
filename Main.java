import java.util.Scanner;

import com.smart_home_system.core.SmartHomeSystem;

public class Main {

	public static void main(String[] args) {
		 SmartHomeSystem homeSystem = new SmartHomeSystem();
           Scanner scanner = new Scanner(System.in);
	        // Add devices to the system
	        homeSystem.addDevice(1, "light");
	        homeSystem.addDevice(2, "thermostat");
	        homeSystem.addDevice(3, "door");
            
	        // Perform actions on devices
	        homeSystem.turnOn(1);
	        homeSystem.scheduleDevice(2,"06:00","Turn On");
	        homeSystem.addAutomatedTrigger("thermostat",">", 75, "turnOff(1");
	        System.out.println("1.Status Report\n2.ScheduledTasks \n3.Automated Triggers");
	        int option=scanner.nextInt();
	        switch(option) {
	        case 1:
	        	homeSystem.statusReport();
	        	break;
	        }
	}

}
