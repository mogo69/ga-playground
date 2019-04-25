package functions;

public class Deba2 extends Function {
	
	//Calculating result of functoin Deba2
	//xVals - array of arguments for function, which length depends on the dimension (chromosome)
	//0 <= xi <= 1
	public double calculateResult(double[] xVals) {
		
		ParametersChecker check = new ParametersChecker();
		if(check.checkBounds(xVals, 0, 1) == true) {
		
			double res = 0;
			double pow = 0;
			double deba1 = 0;
			
			for(int i = 0; i < xVals.length; i++) {
				pow = -2 * Math.log(2) * Math.pow((xVals[i] - 0.1) / 0.8, 2);
				deba1 = Math.pow(Math.sin(5 * Math.PI * xVals[i]), 6);
				res += Math.pow(Math.E, pow) * deba1;
			}
			
			return res;
		
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
		return "Deba2TestFunction";
	}


	public double[] getGlobalPeaks() {
		return new double[] {0.1};
	}

	public double[] getLocalPeaks() {
		return new double[] { 0.3, 0.5, 0.7, 0.9};
	}

}
