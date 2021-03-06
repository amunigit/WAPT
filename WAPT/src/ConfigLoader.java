import java.io.*;
import java.util.Properties;

import ConfigClasses.*;

public class ConfigLoader {
	
	public Config getPropValues(String fileName) throws IOException {
		
		Properties prop = new Properties();
		String propFileName = fileName;
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		if(inputStream != null){
			prop.load(inputStream);
			inputStream.close();
		} else {
			System.out.println("File not found.");
		}
		
		//BasicConfig class type
		if(prop.getProperty("configClass").equals("BasicConfig")){
				Config config = new Config(prop.getProperty("configName"),
						prop.getProperty("configClass"),
						prop.getProperty("configDesc"),
						prop.getProperty("targetPath"),
						Integer.parseInt(prop.getProperty("virtualClients")),
						Integer.parseInt(prop.getProperty("testDuration")));
				return config;
		}
		
		//HTMLconfig class type
	    if(prop.getProperty("configClass").equals("HTMLConfig")){
	    	HTMLConfig config = new HTMLConfig(prop.getProperty("configName"),
	    			prop.getProperty("configClass"),
					prop.getProperty("configDesc"),
					prop.getProperty("targetPath"),
					Integer.parseInt(prop.getProperty("virtualClients")),
					Integer.parseInt(prop.getProperty("testDuration")),
					prop.getProperty("requestType"),
					prop.getProperty("urlParameters"));
	    	return config;
	    }
	    
	    /*
	    //Add desired config, use above for guidance
	    if(prop.getProperty("").equals("")){
	    	
	    }
	    */
		return null;
	}
	
	
	
}
