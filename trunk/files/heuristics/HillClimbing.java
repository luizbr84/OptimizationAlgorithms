package files.heuristics;

import files.model.Catalog;


public class HillClimbing 
{
	private Catalog catalog;
	private int limits;
	
	public HillClimbing(Catalog catalog, int limits)
	{
		this.catalog = catalog;
		this.limits = limits;
	}
	
	public Solution run(int fitnessEvaluationBudget)
	{
		int neighbor = 0;
		int changedNeighbor = 0;
		boolean restartRequired = false;
		
		Solution bestSolution = new Solution(catalog, limits);
		//bestSolution.randomize();
		int bestFitness = bestSolution.getFitness();
				
		Solution solution = new Solution(catalog, limits);
		solution = bestSolution.clone();
		
		int neighborCount = catalog.size();
		
		for (int i = 0; i < fitnessEvaluationBudget; i++)
		{
			if (neighbor>=neighborCount) break;
			
			if (restartRequired) 
			{
				restartRequired = false;
				neighbor = 0;
			}

			if (changedNeighbor == neighbor) neighbor++; 
			solution.setTestCase(neighbor, !solution.getTestCase(neighbor));
			
			int currentFitness = solution.getFitness();
			
			if (currentFitness > bestFitness) 
			{
				bestFitness = currentFitness;
				bestSolution = solution.clone();
				restartRequired = true;
				changedNeighbor = neighbor;
			} else solution.setTestCase(neighbor, !solution.getTestCase(neighbor));
			neighbor++;
		}
		
		return bestSolution;
	}
}
