import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import ConfigClasses.*;


public class Main {

	private static Scanner in = new Scanner(System.in);
	private static ConfigLoader cL = new ConfigLoader();
	private static Config config;
	private static double programVersion = 0.3;
	
	public static void main(String[] args) {
		programStart();
		String s1 = "BaseConfig.properties";
		loadProperties(s1);
		
		programEnd();
	}
	
	/**
	 * Program Start, print program details such as version and current date.
	 */
	public static void programStart(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		Calendar cal = Calendar.getInstance(); 
		System.out.println("== Simple Java based Web Application Performance Testing Tool ==");
		System.out.println("Version: " + programVersion);
		System.out.println("Date and Time: " + dateFormat.format(cal.getTime()));
		System.out.print("\n    ~~~~~\n~~~~~~~~~~~~~\n    ~~~~~\n\n");
	}
	
	/**
	 * Load the configuration properties, given the config file path. (Must be in Resources folder)
	 */
	public static void loadProperties(String fN){
		try {
			config = cL.getPropValues(fN);
		} catch (IOException e) {
			System.out.println("Error!");
		}
	}
	
	/**
	 * Clean up and close the program
	 */
	public static void programEnd(){
		System.out.println("Press the enter key to <END>");
		in.nextLine();
		System.exit(0);
	}
}
