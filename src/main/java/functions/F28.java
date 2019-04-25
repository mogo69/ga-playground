package functions;

public class F28 extends Function {
	
	//Calculating result of Vincent’s function
	//xVals - array of arguments for function, which length depends on the dimension (chromosome)
	//0.25 <= xi <= 10
	public double calculateResult(double[] xVals) {
		
		ParametersChecker check = new ParametersChecker();
		if(check.checkBounds(xVals, 0.25, 10) == true) {
			double res = 0;
			
			for(int i = 0; i < xVals.length; i++) {
				res += Math.sin(10 * Math.log(xVals[i]));
			}
			
			double temp = (double) 1 / xVals.length;
			
			return temp * res;
			
		}
		
		System.out.println("Function argument is out of bounds");
		return Double.POSITIVE_INFINITY;
		
	}

	public double getStartValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getEndValue() {
		// TODO Auto-generated method stub
		return 1;
	}

	public String getName() {
		return "F28TestFunction";
	}

	public double[] getGlobalPeaks() {
		return new double[0];
	}

	public double[] getLocalPeaks() {
		return new double[0];
	}
}
