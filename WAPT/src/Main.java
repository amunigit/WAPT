import java.io.File;
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
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
	private static Calendar cal = Calendar.getInstance(); 
	private static File resourceFolder = new File("Resources");
	private static String[] resourceFolderFileArray;

	public static void main(String[] args) {
		getConfigList();
		
		programStart();

		loadMenu();

		programEnd();
	}
	
	public static void getConfigList(){
		System.out.println(resourceFolder.list().length);
		resourceFolderFileArray = resourceFolder.list();
		System.out.println(resourceFolderFileArray[0]);
		
	}

	/**
	 * Program Start, print program details such as version and current date.
	 */
	public static void programStart(){
		System.out.println("== Simple Java based Web Application Performance Testing Tool ==");
		System.out.println("Version: " + programVersion);
		System.out.println("Starting Date and Time: " + dateFormat.format(cal.getTime()));
		System.out.print("\n    ~~~~~\n~~~~~~~~~~~~~\n    ~~~~~\n\n");
	}

	/**
	 * Main menu
	 */
	public static void loadMenu(){
		int menuChoice = 0;
		System.out.println("Program Menu");
		System.out.println("1. Load config file");
		System.out.println("2. Enter config file details");
		System.out.println("3. Run");
		System.out.println("4. Exit");
		//System.out.println("");
		System.out.print(">"); 
		menuChoice = inputCheckIsInt();
		switch(menuChoice){
		case 1:  configFileName();break;
		case 2:  System.out.println("input 2");break;
		case 3:  System.out.println("input 3");break;
		case 4:  System.out.println("input 4");break;
		default: System.out.println("Please input a number from 1 to 4");
		}
	}

	/**
	 * Menu input check
	 * @return a = Corresponding menu option
	 */
	public static int inputCheckIsInt(){
		int a = 0;
		String input = "";
		char aa = '1';
		char bb = '9';
		boolean correctInput = false;
		while(!correctInput){
			input = in.nextLine();
			if(input.length() == 1){
				if(input.charAt(0) >= aa && input.charAt(0) <= bb){
					a = Integer.parseInt(input);
					System.out.println(a);
					correctInput = true;
					break;
				} else {
					System.out.println("Input error, please type in the number for the desired option.");
					System.out.print(">");
				}
			} else {
				System.out.println("Input error, please type in the number for the desired option.");
				System.out.print(">");
			}
		}
		return a;
	}

	/**
	 * Choose Config File from Resource Folder and load into global "config" variable
	 */
	public static void configFileName(){
		clearConsole();
		int i = 1;
		for(String s : resourceFolderFileArray){
			System.out.println(i + ": " + s);
			i++;
		}
		i = inputCheckIsInt();
		if(i>0 && i<resourceFolderFileArray.length){
			System.out.println("Loading " + i + " " + resourceFolderFileArray[i-1]);
		}
		loadProperties(resourceFolderFileArray[i-1]);
	}

	public final static void clearConsole()
	{
		try
		{
			final String os = System.getProperty("os.name");

			if (os.contains("Windows"))
			{
				Runtime.getRuntime().exec("cls");
			}
			else
			{
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (final Exception e)
		{
			//  Handle any exceptions.
		}
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
