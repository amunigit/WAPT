import java.io.*;
import java.net.*;
import java.util.Random;

/**
 * The VirtualClient that is generated to send periodic requests
 *
 */
public class VirtualClient implements Runnable {
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
	private long maxResponseTime = 0;
	private long minResponseTime = 9999999;
	private int sleepTime = 0;
	private int errors = 0;

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
	}
	
	private void resetTimers(){
		runTime = 0;
		responseTimeAvg = 0;
		requestsSent = 0;
	}
	
	/**
	 * Load connection settings
	 * Must be run if not simulating requests via simulateSendRequest(); i.e. Calling sendRequest();
	 */
	private void loadConnection(){
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
			connection.setRequestProperty("Content-Length", Integer.toString(urlParam.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");  
			connection.setUseCaches(false);
			connection.setDoOutput(true); //We want to send requests
			//System.out.println("\n\nURL Request Type: " + httpRequestType);
			//System.out.println("URL Parameters: " + urlParam);
		} catch (IOException e) {
			System.out.println("Error opening Http Url Connection.");
		}
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
	public synchronized void run(){
		resetTimers();
		while(runTime < maxRunTime){
			timeStart = System.currentTimeMillis();
			sendRequest();
			//simulateSendRequest();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
			}
		}
		responseTimeAvg /= requestsSent;
		//System.out.println("VC #" + vcID + " - AvgResponseTime = " + responseTimeAvg + " - Errors: " + errors);
	}
	

	@SuppressWarnings("unused")
	//Simulate the request by setting response time to a random int from 0 to 1000 (ms)
	private void simulateSendRequest(){
		try {
			timeStart = System.currentTimeMillis();
			//System.out.println("Virtual Client #" + vcID + ": Running");

			sleepTime = r.nextInt(1000);
			Thread.sleep(sleepTime);
			updateTimeTaken();

		} catch (InterruptedException e) {
			System.out.println("Could not sleep thread #" + vcID);
		}
	}

	/**
	 * Evaluate responseTime, runTime, and increase requestsSent by 1
	 */
	private void updateTimeTaken(){
		responseTime = System.currentTimeMillis() - timeStart;
		if(responseTime > maxResponseTime){
			maxResponseTime = responseTime;
		} else if(responseTime < minResponseTime) {
			minResponseTime = responseTime;
		}
		runTime += responseTime;
		responseTimeAvg += responseTime;
		requestsSent++;
	}

	/**
	 * Send Request to the loaded connection
	 */
	private synchronized void sendRequest(){
		try {

			//Send Request
			loadConnection();
			oStream = new DataOutputStream(connection.getOutputStream());
			oStream.writeBytes(urlParam);
			oStream.close();

			//Wait for response
			try {
				iStream = connection.getInputStream();
				iStream.read();

				updateTimeTaken();
				//System.out.println(this.thread.getName() + " - " + urlPath + " -> " + httpRequestType + " -> " + urlParam + " |||| " +"Time taken: " + responseTime + "ms");
			} catch (IOException e1) {
				updateTimeTaken();
				errors++;
				System.out.println("Error - Time taken: " + responseTime + "ms");
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public long getMaxResponseTime(){
		return maxResponseTime;
	}
	
	public long getMinResponseTime(){
		return minResponseTime;
	}

	public long getAvgResponseTime(){
		return responseTimeAvg;
	}
	
	public int getErrors(){
		return errors;
	}

}
