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
	private static HTMLConfig htmlConfig;
	//private static xConfig xConfig; //Add your own
	
	private static double programVersion = 1.0;
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
	private static Calendar cal = Calendar.getInstance(); 
	private static File resourceFolder = new File("Resources");
	private static String[] resourceFolderFileArray;
	
	private static VirtualClientGenerator vCG;

	
	public static void main(String[] args) {
		getConfigList();
		
		programStart();

		loadMenu();

		programEnd();
	}
	
	/**
	 * Takes all files in Resources folder and points resourceFolderFileArray at the resulting Array of File names (Strings).
	 */
	public static void getConfigList(){
		resourceFolderFileArray = resourceFolder.list();	
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
		clearConsole();
		System.out.println("Program Menu");
		System.out.println("1. Load config file");
		System.out.println("2. Enter config file details");
		System.out.println("3. Exit");
		System.out.print(">"); 
		menuChoice = inputCheckIsInt();
		switch(menuChoice){
		case 1:  configFileName();break;
		case 2:  System.out.println("input 2");break;
		case 3:  System.out.println("input 3");break;
		default: System.out.println("Please input a number from 1 to 4");
		}
	}

	/**
	 * Menu input check
	 * @return a = Corresponding menu option, 1 through 9
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
					System.out.println("Input error, please type in the number for the desired option. (1-9)");
					System.out.print(">");
				}
			} else {
				System.out.println("Input error, please type in the number for the desired option. (1-9)");
				System.out.print(">");
			}
		}
		return a;
	}
	
	/**
	 * Configuration menu
	 */
	public static void configRunMenu(){
		clearConsole();
		int configMenuChoice = 0;
		System.out.println("\nConfig Run Menu");
		System.out.println("1. Run " + config.getConfigClass());
		System.out.println("2. Choose different Config");
		System.out.println("3. Main Menu");
		configMenuChoice = inputCheckIsInt();
		switch(configMenuChoice){
		case 1:  runConfig();break;
		case 2:  configFileName();break;
		case 3:  loadMenu();break;
		default: System.out.println("Please input a number from 1 to 3");
		}
	}
	
	/**
	 * Run chosen configuration
	 * Add the methods for your own config files
	 */
	public static void runConfig(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Running: " + config.getConfigName());
		System.out.println("Config Class: " + config.getConfigClass());
		System.out.println("Clients: " + config.getVirtualClients());
		System.out.println("Path: " + config.getTargetPath());
		System.out.println("Duration: " + config.getTestDuration() + "ms");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		vCG = null;
		
		//For HTMLConfig files, that conform to HTMLConfig class file
		if(config.getConfigClass().equals("HTMLConfig")){
			htmlConfig = (HTMLConfig) config;
			vCG = new VirtualClientGenerator(htmlConfig.getVirtualClients(), htmlConfig);
			vCG.runGenerator();
		} 
		//Basic Config file 
		else if(config.getConfigClass().equals("BasicConfig")){
			System.out.println("Basic Config. Must use this class as superconstructor for custom config files");
		} 
		//Add your own
		else {
			System.out.println();
		}
	}

	/**
	 * Choose Config File from Resource Folder and load into global "config" variable
	 */
	public static void configFileName(){
		clearConsole();
		System.out.println("Choose config File:");
		int i = 1;
		for(String s : resourceFolderFileArray){
			System.out.println(i + ": " + s);
			i++;
		}
		System.out.print(">");
		while(true){
			i = inputCheckIsInt();
			if(i>0 && i<=resourceFolderFileArray.length){
				System.out.println("Loading option " + i + ": " + resourceFolderFileArray[i-1]);
				break;
			} else {
				System.out.println("Incorrect Input, Please the number of the config files");
				i = 0;
			}
		}
		loadProperties(resourceFolderFileArray[i-1]);
		System.out.println("Load Complete");
		configRunMenu();
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
		{}
	}

	/**
	 * Load the configuration properties, given the config file path. (Must be in Resources folder)
	 */
	public static void loadProperties(String fN){
		try {
			config = cL.getPropValues(fN);
		} catch (IOException e) {
			System.out.println("Error loading properties! Perhaps the file name was incorrect.");
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
