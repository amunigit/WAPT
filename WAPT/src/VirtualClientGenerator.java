import ConfigClasses.*;

public class VirtualClientGenerator {

	private VirtualClient[] virtualClientThreads;
	private int[] responseTimeArray;
	private HTMLConfig config;
	
	public VirtualClientGenerator(int noOfClients, HTMLConfig con) {
		virtualClientThreads = new VirtualClient[noOfClients];
		responseTimeArray = new int[virtualClientThreads.length];
		config = con;
	}
	
	public void runGenerator(){
		for(int i = 0; i < virtualClientThreads.length; i++){
			virtualClientThreads[i] = new VirtualClient(i, config.getTargetPath(), config.getTestDuration(), config.getRequestType(), config.getUrlParam());
			virtualClientThreads[i].start();
		}
		try {
			Thread.sleep(config.getTestDuration() + 1000);
			System.out.println("Run completed...");
		} catch (InterruptedException e) {
		}
	}

}