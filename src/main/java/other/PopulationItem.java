package other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PopulationItem implements Comparable<PopulationItem> {
	
	private String[] genothype; //encoded chromosome
	private double[] phenotype; //decoded chromosome
	private double fitness;
	private double probability;
	private double expectedNumberOfItems;
	private double realNumberOfItems;
	
	public PopulationItem(String[] genothype, double[] phenotype, double fitness,
			double probability, double expectedNumberOfItems,
			double realNumberOfItems) {
		
		this.genothype = genothype;
		this.phenotype = phenotype;
		this.fitness = fitness;
		this.probability = probability;
		this.expectedNumberOfItems = expectedNumberOfItems;
		this.realNumberOfItems = realNumberOfItems;
	}
	
	public PopulationItem() {
	}

	public void setGenothype(String[] genothype) {
		this.genothype = genothype;
	}

	public void setPhenotype(double[] phenotype) {
		this.phenotype = phenotype;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public void setExpectedNumberOfItems(double expectedNumberOfItems) {
		this.expectedNumberOfItems = expectedNumberOfItems;
	}

	public void setRealNumberOfItems(double realNumberOfItems) {
		this.realNumberOfItems = realNumberOfItems;
	}

	@JsonProperty("variables")
	public String[] getGenothype() {
		return genothype;
	}

	@JsonProperty("stringView")
	public String getStringView() {
		String stringView = String.join(",", genothype);
		return stringView;
	}

	@JsonProperty("decodedParams")
	public double[] getPhenotype() {
		return phenotype;
	}

	@JsonProperty("health")
	public double getFitness() {
		return fitness;
	}

	@JsonIgnore
	public double getProbability() {
		return probability;
	}

	@JsonIgnore
	public double getExpectedNumberOfItems() {
		return expectedNumberOfItems;
	}

	@JsonIgnore
	public double getRealNumberOfItems() {
		return realNumberOfItems;
	}
	
	private String genothypeToString(String[] gen) {
		String s = "";
		
		for(int i = 0; i < gen.length; i++) {
			s += gen[i]+" ";
		}
		
		return s;
	}
	
	private String phenothypeToString(double[] phen) {
		String s = "";
		
		for(int i = 0; i < phen.length; i++) {
			s += phen[i]+" ";
		}
		
		return s;
	}
	
	public String toString() {
		String s = "Genothype: ["+genothypeToString(getGenothype())+"] \n"+
				"Phenotype: ["+phenothypeToString(getPhenotype())+"] \n"+
				"Fitness: "+getFitness()+"\n"+
				"Probability: "+getProbability()+"\n"+
				"Expected number: "+getExpectedNumberOfItems()+"\n"+
				"Real number: "+getRealNumberOfItems();
		
		return s;
	}

	public int compareTo(PopulationItem that) {
		if(this.getFitness() > that.getFitness()) return 1;
		if(this.getFitness() < that.getFitness()) return -1;
		return 0;
	}

}
