package functions;

public class Deba1 implements Function {
	
	//Calculating result of functoin F15(Deba1)
	//xVals - array of arguments for function, which length depends on the dimension (chromosome)
	public double calculateResult(double[] xVals) {
		double res = 0;
		
		for(int i = 0; i < xVals.length; i++) {
			//System.out.println(xVals[i]);
			res = res + Math.pow(Math.sin(5 * Math.PI * xVals[i]), 6);
		}
		
		double temp = (double) 1 / xVals.length;
		
		return temp * res;
	}

	public double getStartValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getEndValue() {
		// TODO Auto-generated method stub
		return 1;
	}

}
