package other;

public class ChromosomeDecoder {
	
	public double[][] decodePopulation(String[][] encodedPop, double startValue, double endValue, int chromosLength) {
		double[][] res = new double[encodedPop.length][encodedPop[0].length];
		
		for(int i = 0; i < res.length; i++) {
			res[i] = decodeChromosome(encodedPop[i], startValue, endValue, chromosLength);
		}
		
		return res;
	}
	
	//decoded function parameters
	private double[] decodeChromosome(String[] encodedArr, double startValue, double endValue, int chromosLength) {
		double[] decodedArr = new double[encodedArr.length];
		
		for(int i = 0; i < encodedArr.length; i++) {
			decodedArr[i] = decodeValue(encodedArr[i], startValue, endValue, chromosLength);
		}
		
		return decodedArr;
	}
	
	//binVal - encoded value in binary form
	//startValue - the least value of possible function args
	//endValue - the largest value of possible function args
	private double decodeValue(String binVal, double startValue, double endValue, int chromosLength) {
		int decVal = Integer.parseInt(binVal, 2);
		
		return startValue + decVal * (endValue / (Math.pow(2, chromosLength) - 1));
	}

}
