package functions;

public class Deba3 extends Function {
	
	//Calculating result of functoin Deba2
	//xVals - array of arguments for function, which length depends on the dimension (chromosome)
	//0 <= xi <= 1
	public double calculateResult(double[] xVals) {
		
		ParametersChecker check = new ParametersChecker();
		if(check.checkBounds(xVals, 0, 1) == true) {
			double res = 0;
			double underSin = 0;
			
			for(int i = 0; i < xVals.length; i++) {
				underSin = Math.pow(xVals[i], 0.75) - 0.05;
				res = res + Math.pow(Math.sin(5 * Math.PI * underSin), 6);
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
		return "Deba3TestFunction";
	}


	public double[] getGlobalPeaks() {
		return new double[]{0.08, 0.247, 0.451, 0.681, 0.934};
	}

	public double[] getLocalPeaks() {
		return new double[]{};
	}

}
