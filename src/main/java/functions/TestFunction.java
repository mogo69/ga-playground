package functions;

public class TestFunction implements Function {
	
	public double calculateResult(double[] xVals) {	
		return 2 * xVals[0] * xVals[0] + 1;
	}

}
