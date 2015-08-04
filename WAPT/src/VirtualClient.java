import java.io.*;
import java.net.*;

/**
 * The VirtualClient that is generated to send periodic requests
 *
 */
public class VirtualClient {
	private int vcID; 				//Virtual Client ID
	private String urlPath;
	private URL url; 				//URL to send requests
	private int maxRunTime; 		//Maximum time to send and receive requests
	private long requestTimeTaken;	//Seconds
	private String urlParam;
	
	private String httpRequestType;   //Http Request Type
	private HttpURLConnection connection;  //Http Connection
	private DataOutputStream oStream;      //Outbound Request Stream
	private InputStream iStream;           //Inbound Response Stream
	
	public VirtualClient(int id, String uPath, int rTime,String requestType, String urlP) {
		vcID = id;
		urlPath = uPath;
		maxRunTime = rTime;
		httpRequestType = requestType;
		urlParam = urlP;
		
		//Set URL path
		try {
			url = new URL(urlPath);
		} catch (MalformedURLException e) {
			System.out.println("Malformed Url");
		}
		
		//Try to open html connection
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(httpRequestType);
		} catch (IOException e) {
			System.out.println("Error opening Http Url Connection.");
		}
		
		connection.setRequestProperty("Content-Length", Integer.toString(urlParam.getBytes().length));
		connection.setRequestProperty("Content-Language", "en-US");  
	    connection.setUseCaches(false); //Do not want to store cache of document
	    connection.setDoOutput(true);
	    System.out.println("\n\nURL Request Type: " + httpRequestType);
	    System.out.println("URL Parameters: " + urlParam);
	}
	
	
	public void runVC(){
		//Send Request
		try {
			long timeStart = System.currentTimeMillis();
			//oStream = new DataOutputStream(connection.getOutputStream());
			//oStream.writeBytes(urlParam);
			//oStream.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
			//Wait for response
			try {
				iStream = connection.getInputStream();
				iStream.read();
				requestTimeTaken = System.currentTimeMillis() - timeStart;
				System.out.println("Time taken: " + requestTimeTaken + "ms");
			} catch (IOException e1) {
				requestTimeTaken = System.currentTimeMillis() - timeStart;
				System.out.println("Time taken: " + requestTimeTaken + "ms");
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
