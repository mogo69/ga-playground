package functions;

public class F24 extends Function {
	
	//Calculating result of Generalized Shubert’s function
	//xVals - array of arguments for function, which length depends on the dimension (chromosome)
	//-10 <= xi <= 10
	public double calculateResult(double[] xVals) {
		ParametersChecker check = new ParametersChecker();
		if(check.checkBounds(xVals, -10, 10) == true) {
			double temp = 0;
			double tempRes = 0;
			double res = 1;
			
			for(int i = 0; i < xVals.length; i++) {
	
				for(int j = 1; j <= 5; j++) {
					temp = j * Math.cos((j + 1) * xVals[i] + j);
					tempRes += temp;
				}
				
				res *= tempRes;
				
				temp = 0;
				tempRes = 0;
			}
			
			return -res;
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
		return "F24TestFunction";
	}



	public double[] getGlobalPeaks() {
		return new double[0];
	}

	public double[] getLocalPeaks() {
		return new double[0];
	}
	

}
