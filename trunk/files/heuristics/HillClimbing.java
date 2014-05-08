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
		Solution bestSolution = new Solution(catalog, limits);
		bestSolution.randomize();
		int bestFitness = bestSolution.getFitness();
				
		Solution solution = new Solution(catalog, limits);
		solution = bestSolution.clone();
		
		int evaluationCount = 0;
		int neighborCount = catalog.size();
		
		while (evaluationCount < fitnessEvaluationBudget)
		{
			boolean restartRequired = true;
			
			for (int i = 0; i < neighborCount; i++)
			{
				boolean currentState = solution.getTestCase(i);
				solution.setTestCase(i, !currentState);				

				int currentFitness = solution.getFitness();
				evaluationCount++;
				
				if (currentFitness > bestFitness) 
				{
					bestFitness = currentFitness;
					bestSolution = solution.clone();
					restartRequired = false;
					break;
				} 
				else 
					solution.setTestCase(i, currentState);
			}
			
			if (restartRequired)
				solution.randomize();
		}
		
		return bestSolution;
	}
}
