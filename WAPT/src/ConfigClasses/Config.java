package ConfigClasses;

public class Config {

	private String configName; //Config Name
	private String configDesc; //Config Description
	private String targetPath; //Request Target
	private int virtualClients; //Number of virtual clients to generate
	private int testDuration; //Length of test
	
	public Config (String cN, String cD, String tP, int vC, int tD){
		this.configName = cN;
		this.configDesc = cD;
		this.targetPath = tP;
		this.virtualClients = vC;
		this.testDuration = tD;
	}
	
	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigDesc() {
		return configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public int getVirtualClients() {
		return virtualClients;
	}

	public void setVirtualClients(int virtualClients) {
		this.virtualClients = virtualClients;
	}

	public int getTestDuration() {
		return testDuration;
	}

	public void setTestDuration(int testDuration) {
		this.testDuration = testDuration;
	}
	
}
