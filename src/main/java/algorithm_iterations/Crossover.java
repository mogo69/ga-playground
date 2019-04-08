package algorithm_iterations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import other.PopulationItem;

public class Crossover {
	
	public ArrayList<PopulationItem> selectIndividualsForCrossover(ArrayList<PopulationItem> items) {
		ArrayList<PopulationItem> res = new ArrayList<PopulationItem>();
		
		for(PopulationItem item : items) {
			if(item.getRealNumberOfItems() != 0)
				res.add(item);
		}
		
		Collections.sort(res);
		
		return res;
	}
	
	public ArrayList<PopulationItem> doCrossover(ArrayList<PopulationItem> selectedItems) {
		ArrayList<PopulationItem> res = new ArrayList<PopulationItem>();
		int size = selectedItems.size();
		
		Random rand = new Random();
		int point = rand.nextInt();
		
		for(int i = 0, j = size / 2; j < size; i++, j++) {
			res.add(getFirstCrossoverIndividual(selectedItems.get(i), selectedItems.get(j), point));
			res.add(getSecondCrossoverIndividual(selectedItems.get(i), selectedItems.get(j), point));
		}
		
		return res;
	}
	
	private PopulationItem getFirstCrossoverIndividual(PopulationItem item1, PopulationItem item2, int point) {
		PopulationItem crossItem1 = new PopulationItem();
		crossItem1.setGenothype(getFirstChild(item1.getGenothype(), item2.getGenothype(), point));
		
		return crossItem1;
	}
	
	private PopulationItem getSecondCrossoverIndividual(PopulationItem item1, PopulationItem item2, int point) {
		PopulationItem crossItem2 = new PopulationItem();
		crossItem2.setGenothype(getSecondChild(item1.getGenothype(), item2.getGenothype(), point));
		
		return crossItem2;
	}
	
	//First child - result of the crossover
	private String[] getFirstChild(String[] gen1, String[] gen2, int point) {
		String[] res = new String[gen1.length];
		
		for(int i = 0; i < point; i++) {
			res[i] = getFirstPartStr(gen1[i], gen2[i], point);
		}
		
		return res;
	}
	
	//Second child - result of the crossover
	private String[] getSecondChild(String[] gen1, String[] gen2, int point) {
		String[] res = new String[gen1.length];
		
		for(int i = 0; i < gen1.length; i++) {
			res[i] = getSecondPartStr(gen1[i], gen2[i], point);
		}
		
		return res;
	}
	
	private String getFirstPartStr(String partGen1, String partGen2, int point) {
		String res = "";
		
		for(int i = 0; i < point; i++) {
			res += partGen1.charAt(i);
		}
		for(int i = point; i < partGen2.length(); i++) {
			res += partGen2.charAt(i);;
		}
		
		return res;
	}
	
	private String getSecondPartStr(String partGen1, String partGen2, int point) {
		String res = "";
		
		for(int i = 0; i < point; i++) {
			res += partGen2.charAt(i);
		}
		for(int i = point; i < partGen1.length(); i++) {
			res += partGen1.charAt(i);;
		}
		
		return res;
	}
	
}
