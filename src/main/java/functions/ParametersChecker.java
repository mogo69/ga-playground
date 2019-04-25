package functions;

public class ParametersChecker {
	
	//checks if function parameters is in specified bounds
	public boolean checkBounds(double[] xVals, double low, double up) {
		for(int i = 0; i < xVals.length; i++) {
			if(xVals[i] > up || xVals[i] < low)
				return false;
		}
		
		return true;
	}

}
