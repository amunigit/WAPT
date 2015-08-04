package ConfigClasses;

public class HTMLConfig extends Config {

	private String requestType;
	private String urlParam;
	
	public HTMLConfig(String cN, String cD, String tP, int vC, int tD, String rT, String uParam) {
		super(cN, cD, tP, vC, tD);
		this.requestType = rT;
		this.urlParam = uParam;
	}
	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}
}