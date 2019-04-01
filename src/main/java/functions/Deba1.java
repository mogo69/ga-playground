package functions;

public class Deba1 implements Function {
	
	//Calculating result of functoin F15(Deba1)
	//xVals - array of arguments for function, which length depends on the dimension
	public double calculateResult(double[] xVals) {
		double res = 0;
		
		for(int i = 0; i < xVals.length; i++) {
			res = res + Math.pow(Math.sin(5 * Math.PI + xVals[i]), 6);
		}
		
		return (double) (1 / xVals.length) * res;
	}

}
