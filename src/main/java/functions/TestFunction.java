package functions;

public class TestFunction extends Function {
	
	public double calculateResult(double[] xVals) {	
		return 2 * xVals[0] * xVals[0] + 1;
	}

	public double getStartValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getEndValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		return "TestFunctionTestFunction";
	}

	public double[] getGlobalPeaks() {
		return new double[0];
	}

	public double[] getLocalPeaks() {
		return new double[0];
	}

}
