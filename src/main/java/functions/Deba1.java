package functions;

public class Deba1 extends Function {
	
	//Calculating result of functoin F15(Deba1)
	//xVals - array of arguments for function, which length depends on the dimension (chromosome)
	public double calculateResult(double[] xVals) {
		
		ParametersChecker check = new ParametersChecker();
		if(check.checkBounds(xVals, 0, 1) == true) {
			double res = 0;
			
			for(int i = 0; i < xVals.length; i++) {
				res = res + Math.pow(Math.sin(5 * Math.PI * xVals[i]), 6);
			}
			
			double temp = (double) 1 / xVals.length;
			
			return temp * res;
		}
		
		System.out.println("Function argument is out of bounds");
		return Double.POSITIVE_INFINITY;
	}

	public String getName() {
		return "Deba1TestFunction";
	}

	public double getStartValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getEndValue() {
		// TODO Auto-generated method stub
		return 1;
	}

	public double[] getGlobalPeaks() {
		return new double[] {0.1, 0.3, 0.5, 0.7, 0.9};
	}

	public double[] getLocalPeaks() {
		return new double[] {};
	}

}
