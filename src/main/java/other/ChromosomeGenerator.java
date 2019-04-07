package other;


//Generates encoded chromosome values
public class ChromosomeGenerator {
	
	public String[][] generatePopulation(int size, int dim, int chromLength) {
		String[][] res = new String[size][dim];
		
		for(int i = 0; i < res.length; i++) {
			res[i] = generateChromosome(dim, chromLength);
		}
		
		return res;
	}
	
	//length - length of the chromosome item (counted through log)
	private String generateChromosomeItem(int length) {
		String res = "";
		
		for(int i = 0; i < length; i++) {
			if(Math.random() < 0.5)
				res += '0';
			else
				res += '1';
		}
		
		return res;
	}
	
	//dim - dimension (number of variables necessary for function)
	//length - length of the each variable (number of bits in chromosome item)
	private String[] generateChromosome(int dim, int length) {
		String[] res = new String[dim];
		
		for(int i = 0; i < res.length; i++) {
			res[i] = generateChromosomeItem(length);
		}
		
		return res;
	}
	
	
	public static void main(String[] args) {
		/*String c = "1001";
		int decimalValue = Integer.parseInt(c, 2);
		System.out.println(decimalValue);*/
		
		/*ChromosomeGenerator chr = new ChromosomeGenerator();
		String[] res = chr.generateChromosome(7, 10);
		
		for(String s : res) {
			System.out.println(s+" - "+chr.decodeValue(s, 0, 4, 10));
		}*/
	}
	

}
