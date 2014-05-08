package files.heuristics;

import files.model.Catalog;
import files.heuristics.Solution;

public class RandomSearch
{
	private Catalog catalog;
	private int limits;
	
	public RandomSearch(Catalog catalog, int limits)
	{
		this.catalog = catalog;
		this.limits = limits;
	}

	public Solution run(int fitnessEvaluationBudget)
	{
		Solution bestSolution = new Solution(catalog, limits);
		bestSolution.randomize();
		int bestFitness = bestSolution.getFitness();
		
		for (int i = 0; i < fitnessEvaluationBudget; i++)
		{
			Solution solution = new Solution(catalog, limits);
			solution.randomize();
			int currentFitness = solution.getFitness();
			
			if (currentFitness > bestFitness)
			{
				bestFitness = currentFitness;
				bestSolution = solution.clone();
			}
		}
		
		return bestSolution;
	}
}