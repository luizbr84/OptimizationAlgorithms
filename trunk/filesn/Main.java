package Files;

import Files.heuristics.HillClimbing;
import Files.heuristics.RandomSearch;
import Files.heuristics.Solution;
import Files.loader.InstanceLoader;
import Files.model.Catalog;

public class Main
{
	public static void main(String[] args)
	{
		InstanceLoader loader = new InstanceLoader();
		Catalog catalog = loader.execute("trunk\\data\\Instance.txt");

		HillClimbing hc1 = new HillClimbing(catalog, 200);
		Solution hcSolution1 = hc1.run(2000);
		System.out.println(hcSolution1.getLimits() + ":"+ hcSolution1.getFitness());
		
		RandomSearch rs1 = new RandomSearch(catalog, 200);
		Solution rsSolution1 = rs1.run(2000);
		System.out.println(rsSolution1.getLimits() + ":"+ rsSolution1.getFitness());
		
		HillClimbing hc2 = new HillClimbing(catalog, 300);
		Solution hcSolution2 = hc2.run(2000);
		System.out.println(hcSolution2.getLimits() + ":"+ hcSolution2.getFitness());
		
		RandomSearch rs2 = new RandomSearch(catalog, 300);
		Solution rsSolution2 = rs2.run(2000);
		System.out.println(rsSolution2.getLimits() + ":"+ rsSolution2.getFitness());
		
		HillClimbing hc3 = new HillClimbing(catalog, 400);
		Solution hcSolution3 = hc3.run(2000);
		System.out.println(hcSolution3.getLimits() + ":"+ hcSolution3.getFitness());
		
		RandomSearch rs3 = new RandomSearch(catalog, 400);
		Solution rsSolution3 = rs3.run(2000);
		System.out.println(rsSolution3.getLimits() + ":"+ rsSolution3.getFitness());
	}
}