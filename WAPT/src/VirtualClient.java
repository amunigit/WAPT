import java.io.*;
import java.net.*;
import java.util.Random;

/**
 * The VirtualClient that is generated to send periodic requests
 *
 */
public class VirtualClient extends Thread {
	private Thread thread;
	private int vcID; 				//Virtual Client ID
	private String urlPath;
	private URL url; 				//URL to send requests
	private String urlParam;

	private int maxRunTime; 		//Maximum time to send and receive requests
	private long timeStart;			//Seconds
	private long runTime = 0;
	private long responseTime;	
	private long responseTimeAvg = 0;
	private long requestsSent = 0;
	private int sleepTime = 0;

	private String httpRequestType;   		//Http Request Type
	private HttpURLConnection connection;   //Http Connection
	private DataOutputStream oStream;       //Outbound Request Stream
	private InputStream iStream;            //Inbound Response Stream
	private Random r = new Random();

	public VirtualClient(int id, String uPath, int rTime,String requestType, String urlP) {
		vcID = id;
		urlPath = uPath;
		maxRunTime = rTime;
		httpRequestType = requestType;
		urlParam = urlP;
		/*
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
	    System.out.println("URL Parameters: " + urlParam); */
	}

	/**
	 * Start the Virtual Client
	 */
	public void start ()
	{
		if (thread == null)
		{
			thread = new Thread (this);
			thread.start();
		}
	}

	/**
	 * Runs Virtual Client by sending requests
	 */
	public void run(){

		while(runTime < maxRunTime){
			try {
				timeStart = System.currentTimeMillis();
				//System.out.println("Virtual Client #" + vcID + ": Running");

				sleepTime = r.nextInt(1000);
				this.sleep(sleepTime);

				responseTime = System.currentTimeMillis() - timeStart;
				
				runTime += responseTime;
				responseTimeAvg += responseTime;
				requestsSent++;
			} catch (InterruptedException e) {
				System.out.println("Could not sleep thread #" + vcID);
			}
		}
		responseTimeAvg /= requestsSent;
		
		System.out.println("VC #" + vcID + " - AvgResponseTime = " + responseTimeAvg);
		

		//Send Request
		/*
		try {

			//oStream = new DataOutputStream(connection.getOutputStream());
			//oStream.writeBytes(urlParam);
			//oStream.close();

			//BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			//Wait for response
			try {
				iStream = connection.getInputStream();
				iStream.read();
				responseTime = System.currentTimeMillis() - timeStart;
				System.out.println("Time taken: " + responseTime + "ms");
			} catch (IOException e1) {
				responseTime = System.currentTimeMillis() - timeStart;
				System.out.println("Time taken: " + responseTime + "ms");
				e1.printStackTrace();
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		 */
	}

}
