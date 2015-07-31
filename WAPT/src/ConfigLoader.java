import java.io.*;
import java.util.Properties;

import ConfigClasses.*;

public class ConfigLoader {
	
	public Config getPropValues(String fileName) throws IOException {
		
		Properties prop = new Properties();
		String propFileName = "BaseConfig.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		if(inputStream != null){
			prop.load(inputStream);
			inputStream.close();
		} else {
			System.out.println("File not found.");
		}
		
	    if(prop.getProperty("configName").equals("Basic Config")){
	    	Config config = new Config(prop.getProperty("configName"),
	    			         prop.getProperty("configDesc"),
	    			         prop.getProperty("targetPath"),
	    			         Integer.parseInt(prop.getProperty("virtualClients")),
	    			         Integer.parseInt(prop.getProperty("testDuration")));
	    	return config;
	    } else {
	    	return null;
	    }
		
	}
	
	
	
}
