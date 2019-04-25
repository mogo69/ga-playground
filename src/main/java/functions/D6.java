package functions;

public class D6 extends Function {
	
	//Calculating result of Five-Uneven-Peak Trap Function (D6)
	//0 <= x <= 30
	public double calculateResult(double[] xVals) {
		
		if(xVals.length != 1) {
			System.out.println("Incorrect array length for function D6. It should be only 1!");
			return Double.POSITIVE_INFINITY;
		}
		
		double x = xVals[0];
		
		if(x >= 0 && x <= 30) {
			if(x >= 0 && x < 2.5)
				return 80 * (2.5 - x);
			
			if(x >= 2.5 && x < 5)
				return 64 * (x - 2.5);
			
			if(x >= 5 && x < 7.5)
				return 64 * (7.5 - x);
			
			if(x >= 7.5 && x < 12.5)
				return 28 * (x - 7.5);
			
			if(x >= 12.5 && x < 17.5)
				return 28 * (17.5 - x);
			
			if(x >= 17.5 && x < 22.5)
				return 32 * (x - 17.5);
			
			if(x >= 22.5 && x < 27.5)
				return 32 * (27.5 - x);
			
			if(x >= 27.5 && x <= 30)
				return 80 * (x - 27.5);
		}
		
		System.out.println("Function argument is out of bounds");
		return Double.POSITIVE_INFINITY;
	}

	public String getName() {
		return "D6";
	}


	public double getStartValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getEndValue() {
		// TODO Auto-generated method stub
		return 30;
	}

	public double[] getGlobalPeaks() {
		return new double[0];
	}

	public double[] getLocalPeaks() {
		return new double[0];
	}

}
