package functions;

public class F45 extends Function {
	
	//Calculating result of Himmelblau’s function (F45)
	//-6 <= x1 <= 6
	//-6 <= x2 <= 6
	public double calculateResult(double[] xVals) {
		
		if(xVals.length != 2) {
			System.out.println("Incorrect array length for function F45. It should be only 2!");
			return Double.POSITIVE_INFINITY;
		}
		
		double x1 = xVals[0];
		double x2 = xVals[1];
		
		if(x1 >= -6 && x1 <= 6 && x2 >= -6 && x2 <= 6) {
			double part1 = Math.pow(x1 * x1 + x2 - 11, 2);
			double part2 = Math.pow(x1 + x2 * x2 - 7, 2);
			
			return 200 - part1 - part2;
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
		return "F45TestFunction";
	}

	public double[] getGlobalPeaks() {
		return new double[0];
	}

	public double[] getLocalPeaks() {
		return new double[0];
	}
}
