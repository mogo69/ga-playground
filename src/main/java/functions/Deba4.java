package functions;

public class Deba4 extends Function {
	
	//0 <= xi <= 1
	public double calculateResult(double[] xVals) {
		ParametersChecker check = new ParametersChecker();
		if(check.checkBounds(xVals, 0, 1) == true) {
			double res = 0;
			double pow = 0;
			double underSin = 0;
			
			for(int i = 0; i < xVals.length; i++) {
				pow = -2 * Math.log(2) * Math.pow((xVals[i] - 0.08) / 0.854, 2);
				underSin = Math.pow(xVals[i], 0.75) - 0.05;
				
				res = res + Math.pow(Math.E, pow) * Math.pow(Math.sin(5 * Math.PI * underSin), 6);
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
		return "Deba4TestFunction";
	}




	public double[] getGlobalPeaks() {
		return new double[]{0.08};
	}

	public double[] getLocalPeaks() {
		return new double[]{0.247, 0.451, 0.681, 0.934};
	}

}
