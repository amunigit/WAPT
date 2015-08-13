import ConfigClasses.*;

public class VirtualClientGenerator {

	private VirtualClient[] virtualClientThreads;
	private long[] responseTimeAvgArray;
	private long[] responseTimeMaxArray;
	private long[] responseTimeMinArray;
	private int[] numberOfErrorsArray;
	private HTMLConfig config;
	private boolean runComplete = false;
	
	public VirtualClientGenerator(int noOfClients, HTMLConfig con) {
		virtualClientThreads = new VirtualClient[noOfClients];
		responseTimeAvgArray = new long[virtualClientThreads.length];
		responseTimeMaxArray = new long[virtualClientThreads.length];
		responseTimeMinArray = new long[virtualClientThreads.length];
		numberOfErrorsArray = new int[virtualClientThreads.length];
		config = con;
	}
	
	public void runGenerator(){
		for(int i = 0; i < virtualClientThreads.length; i++){
			virtualClientThreads[i] = new VirtualClient(i, config.getTargetPath(), config.getTestDuration(), config.getRequestType(), config.getUrlParam());
			virtualClientThreads[i].start();
			responseTimeAvgArray[i] = 0;
		}
		try {
			Thread.sleep(config.getTestDuration() + 5000);
			for(int i = 0; i < virtualClientThreads.length; i++){
				responseTimeAvgArray[i] = virtualClientThreads[i].getAvgResponseTime();
				responseTimeMaxArray[i] = virtualClientThreads[i].getMaxResponseTime();
				responseTimeMinArray[i] = virtualClientThreads[i].getMinResponseTime();
				numberOfErrorsArray[i] = virtualClientThreads[i].getErrors();
			}
			System.out.println("Run completed...");
			System.out.println("~~~~ Results ~~~~");
			for(int i = 0; i < virtualClientThreads.length; i++){
				System.out.println("VC #" + i + " | Response Time | Max:" + responseTimeMaxArray[i] + "ms - Min:" + responseTimeMinArray[i] + "ms - Avg:" + responseTimeAvgArray[i] + "ms | Errors:" + numberOfErrorsArray[i]);
			}
			runComplete = true;
		} catch (InterruptedException e) {
		}
	}
	
	public boolean isRunComplete(){
		return runComplete;
	}


}
