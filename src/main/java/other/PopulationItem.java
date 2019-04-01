package other;

public class PopulationItem {
	
	double genothype; //encoded chromosome
	double phenotype; //decoded chromosome
	double fitness;
	double probability;
	double expectedNumberOfItems;
	double realNumberOfItems;
	
	public PopulationItem(double genothype, double phenotype, double fitness,
			double probability, double expectedNumberOfItems,
			double realNumberOfItems) {
		
		this.genothype = genothype;
		this.phenotype = phenotype;
		this.fitness = fitness;
		this.probability = probability;
		this.expectedNumberOfItems = expectedNumberOfItems;
		this.realNumberOfItems = realNumberOfItems;
	}

	public double getGenothype() {
		return genothype;
	}

	public double getPhenotype() {
		return phenotype;
	}

	public double getFitness() {
		return fitness;
	}

	public double getProbability() {
		return probability;
	}

	public double getExpectedNumberOfItems() {
		return expectedNumberOfItems;
	}

	public double getRealNumberOfItems() {
		return realNumberOfItems;
	}
	
	public String toString() {
		String s = getGenothype()+" "+getPhenotype()+" "+getFitness()+" "+getProbability()+" "+
	" "+getExpectedNumberOfItems()+" "+getRealNumberOfItems();
		
		return s;
	}

}
