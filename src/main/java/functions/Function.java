package functions;

public interface Function {
	
	//xVals - array of values for function (length depends on dimension)
	public double calculateResult(double[] xVals);
	
	public double getStartValue();
	
	public double getEndValue();
	
}
