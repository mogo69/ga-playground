package algorithm_iterations;

import java.util.ArrayList;
import java.util.Random;

import other.PopulationItem;

public class Mutation {
	
	public String[][] doMutation(ArrayList<PopulationItem> items, double pc) {
		//Converting ArrayList into String[][]	
		String[][] retRes = new String[items.size()][];
		for (int i = 0; i < items.size(); i++) {
		    String[] row = items.get(i).getGenothype();
		    retRes[i] = row;
		}
		
		//number of bits for mutation
		//depends on Pc
		int chromNumber = items.size();
		int chromLength = items.get(0).getGenothype()[0].length();
		int chromItemsNum = items.get(0).getGenothype().length;
		long mutBits = Math.round(chromNumber * chromLength * chromItemsNum * pc);
		if(mutBits == 0) mutBits++;
		//double mutBits = chromNumber * chromLength * chromItemsNum * pc;
		System.out.println("mutBits = "+mutBits);
		
		Random rand = new Random();
		
		for(int i = 1; i <= mutBits; i++) {
			//random chromosome to mutate
			int mutIndiv = rand.nextInt(chromNumber);
			
			//random chromosome part to mutate (depends on dimension)
			int mutChromItem = rand.nextInt(chromItemsNum);
			
			String mutChrItem = items.get(mutIndiv).getGenothype()[mutChromItem];
			retRes[mutIndiv][mutChromItem] = mutateChromosomeItem(mutChrItem);
		}
		
		return retRes;
	}
	
	//Changing one bit in encoded chromosome value
	private String mutateChromosomeItem(String chromItem) {
		char[] item = chromItem.toCharArray();
		
		Random rand = new Random();
		int charPosToRepl = rand.nextInt(item.length);
		
		if(item[charPosToRepl] == '1') {
			item[charPosToRepl] = '0';
		}
		else {
			item[charPosToRepl] = '1';
		}
		
		//Converting char[] into String
		String res = "";
		for(char c : item) {
			res += c;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		String[][] pop = new String[4][3];
		
		String[] chr0 = {"1001", "0011", "1010"};
		String[] chr1 = {"1100", "1011", "1000"};
		String[] chr2 = {"1111", "0001", "1110"};
		String[] chr3 = {"1101", "1001", "1001"};
		
		pop[0] = chr0;
		pop[1] = chr1;
		pop[2] = chr2;
		pop[3] = chr3;
		
		ArrayList<PopulationItem> mutItems = new ArrayList<PopulationItem>();
		
		PopulationItem it0 = new PopulationItem();
		it0.setGenothype(chr0);
		mutItems.add(it0);
		
		PopulationItem it1 = new PopulationItem();
		it1.setGenothype(chr1);
		mutItems.add(it1);
		
		PopulationItem it2 = new PopulationItem();
		it2.setGenothype(chr2);
		mutItems.add(it2);
		
		PopulationItem it3 = new PopulationItem();
		it3.setGenothype(chr3);
		mutItems.add(it3);
		
		Mutation mut = new Mutation();
		String[][] popRes = mut.doMutation(mutItems, 0.1);
		
		for(int i = 0; i < popRes.length; i++) {
			for(int j = 0; j < popRes[0].length; j++) {
				System.out.print(popRes[i][j]+" ");
			}
			System.out.println();
		}
	}

}
