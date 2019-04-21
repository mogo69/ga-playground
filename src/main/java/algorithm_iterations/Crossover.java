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
	
	public String[][] doCrossover(ArrayList<PopulationItem> selectedItems, double pm) {
		ArrayList<String[]> res = new ArrayList<String[]>();
		int size = (int) Math.round(selectedItems.size() * pm);
		
		Random rand = new Random();
		int point = rand.nextInt(size-1);
		point++;
		
		for(int i = 0, j = size / 2; j < size; i++, j++) {
			res.add(getFirstChild(selectedItems.get(i).getGenothype(), selectedItems.get(j).getGenothype(), point));
			res.add(getSecondChild(selectedItems.get(i).getGenothype(), selectedItems.get(j).getGenothype(), point));
		}
		
		//Converting ArrayList into String[][]	
		String[][] retRes = new String[res.size()][];
		for (int i = 0; i < res.size(); i++) {
		    String[] row = res.get(i);
		    retRes[i] = row;
		}
		
		return retRes;
	}
	
	
	//First child - result of the crossover
	private String[] getFirstChild(String[] gen1, String[] gen2, int point) {
		String[] res = new String[gen1.length];
		
		for(int i = 0; i < gen1.length; i++) {
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
	
	public String getFirstPartStr(String partGen1, String partGen2, int point) {
		String res = "";
		
		for(int i = 0; i < point; i++) {
			res += partGen1.charAt(i);
		}
		for(int i = point; i < partGen2.length(); i++) {
			res += partGen2.charAt(i);;
		}
		
		return res;
	}
	
	public String getSecondPartStr(String partGen1, String partGen2, int point) {
		String res = "";
		
		for(int i = 0; i < point; i++) {
			res += partGen2.charAt(i);
		}
		for(int i = point; i < partGen1.length(); i++) {
			res += partGen1.charAt(i);
		}
		
		return res;
	}
	
}
