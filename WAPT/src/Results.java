
public class Results {
	
	private long[] responseTimeAvgArray;
	private long[] responseTimeMaxArray;
	private long[] responseTimeMinArray;
	private int[] numberOfErrorsArray;

	public Results(long[] rtAvg, long[] rtMax, long[] rtMin, int[] errors) {
		fillResponseTimeArray(responseTimeAvgArray, rtAvg);
		fillResponseTimeArray(responseTimeMaxArray, rtMax);
		fillResponseTimeArray(responseTimeMinArray, rtMin);
		fillErrorArray(errors);
	}
	
	private int[] fillErrorArray(int[] errors){
		for(int i = 0; i < errors.length; i++){
			numberOfErrorsArray[i] = errors[i];
		}
		return numberOfErrorsArray;
	}
	
	private long[] fillResponseTimeArray(long[] rtTO, long[] rtFrom){
		for(int i = 0; i < rtFrom.length; i++){
			rtTO[i] = rtFrom[i];
		}
		return rtTO;
	}

}
