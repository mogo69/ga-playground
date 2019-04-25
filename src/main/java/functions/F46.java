package functions;

public class F46 extends Function {
	
	//Calculating result of six-hump camel back function (F46)
	//-3 <= x1 <= 3
	//-2 <= x2 <= 2
	public double calculateResult(double[] xVals) {
		if(xVals.length != 2) {
			System.out.println("Incorrect array length for function F46. It should be only 2!");
			return Double.POSITIVE_INFINITY;
		}
		
		double x1 = xVals[0];
		double x2 = xVals[1];
		
		if(x1 >= -3 && x1 <=3 && x2 >= -2 && x2 <= 2) {
			double part1 = (4 - 2.1 * Math.pow(x1, 2) + Math.pow(x1, 4) / 3.0) * Math.pow(x1, 2);
			double part3 = 4 * (Math.pow(x2, 2) - 1) * Math.pow(x2, 2);
			
			return - (part1 + x1 * x2 +part3);
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
		return "F46TestFunction";
	}

	public double[] getGlobalPeaks() {
		return new double[0];
	}

	public double[] getLocalPeaks() {
		return new double[0];
	}
}
