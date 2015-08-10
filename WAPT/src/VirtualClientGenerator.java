import ConfigClasses.*;

public class VirtualClientGenerator {

	private VirtualClient[] virtualClientThreads;
	private long[] responseTimeArray;
	private HTMLConfig config;
	private boolean runComplete = false;
	
	public VirtualClientGenerator(int noOfClients, HTMLConfig con) {
		virtualClientThreads = new VirtualClient[noOfClients];
		responseTimeArray = new long[virtualClientThreads.length];
		config = con;
	}
	
	public void runGenerator(){
		for(int i = 0; i < virtualClientThreads.length; i++){
			virtualClientThreads[i] = new VirtualClient(i, config.getTargetPath(), config.getTestDuration(), config.getRequestType(), config.getUrlParam());
			virtualClientThreads[i].start();
			responseTimeArray[i] = 0;
		}
		try {
			Thread.sleep(config.getTestDuration() + 5000);
			for(int i = 0; i < virtualClientThreads.length; i++){
				responseTimeArray[i] = virtualClientThreads[i].getResponseTime();
			}
			System.out.println("Run completed...");
			runComplete = true;
		} catch (InterruptedException e) {
		}
	}
	
	public boolean isRunComplete(){
		return runComplete;
	}

}
