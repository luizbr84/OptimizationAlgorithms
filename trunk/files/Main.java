package files;

import files.heuristics.HillClimbing;
import files.heuristics.RandomSearch;
import files.heuristics.Solution;
import files.loader.InstanceLoader;
import files.model.Catalog;

public class Main
{
	private static final int BUDGET = 10000;
	
	public static void main(String[] args)
	{
		InstanceLoader loader = new InstanceLoader();
		Catalog catalog = loader.execute("trunk\\data\\Instance.txt");
		runForLimit(catalog, 200);
		runForLimit(catalog, 300);
		runForLimit(catalog, 400);
	}

	private static void runForLimit(Catalog catalog, int limits)
	{
		HillClimbing hc1 = new HillClimbing(catalog, limits);
		Solution hcSolution1 = hc1.run(BUDGET);
		
		RandomSearch rs1 = new RandomSearch(catalog, limits);
		Solution rsSolution1 = rs1.run(BUDGET);
		
		System.out.println(limits + " - HC: " + hcSolution1.getFitness() + " RS: " + rsSolution1.getFitness());
	}
}