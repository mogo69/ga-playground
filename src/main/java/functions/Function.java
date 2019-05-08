package functions;

import other.PopulationItem;

import java.util.ArrayList;

public abstract class Function {
	
	//xVals - array of values for function (length depends on dimension)
	abstract public double calculateResult(double[] xVals);
	abstract public String getName();
	abstract public double getStartValue();
	abstract public double getEndValue();
	abstract public double[] getGlobalPeaks();
	abstract public double[] getLocalPeaks();

	public int getNumberOfGlobalPeaks(double dimension) {
		return (int) Math.pow((double) this.getGlobalPeaks().length, dimension);
	}

	public int getNumberOfLocalPeaks(double dimension) {
		return (int) Math.pow((double) this.getLocalPeaks().length, dimension);
	}


	public int countAllPeaksIn(ArrayList<PopulationItem> population) {
		return this.countGlobalPeaksIn(population) + this.countLocalPeaksIn(population);
	}


	public int countGlobalPeaksIn(ArrayList<PopulationItem> population) {
		int count = 0;
		for (PopulationItem item: population) {
			double [] phenotype = item.getPhenotype();
			outer: for (int i = 0; i < phenotype.length; i++) {

				for (int j = 0; j < this.getGlobalPeaks().length; j++) {
					if (Math.abs(phenotype[i] - this.getGlobalPeaks()[j]) < 0.01) {
						count++;
						break outer;
					}
				}
			}
		}

		return count;
	}

	public int countLocalPeaksIn(ArrayList<PopulationItem> population) {
		int count = 0;
		for (PopulationItem item: population) {
			double [] phenotype = item.getPhenotype();
			outer: for (int i = 0; i < phenotype.length; i++) {
				for (int j = 0; j < this.getLocalPeaks().length; j++) {
					if (Math.abs(phenotype[i] - this.getLocalPeaks()[j]) < 0.01) {
						count++;
						break outer;
					}
				}
			}
		}

		return count;
	}

}
