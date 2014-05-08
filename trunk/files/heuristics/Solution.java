package files.heuristics;

import java.util.Random;

import files.model.Catalog;
import files.model.TestCase;

/**
 * Class that represents a solution for the test case selection problem
 */
public class Solution
{
	private Catalog catalog;
	private int limits;
	private boolean[] testCases;
	
	/**
	 * Creates a new solution
	 */
	public Solution(Catalog catalog, int limits)
	{
		this.catalog = catalog;
		this.limits = limits;
		this.testCases = new boolean[catalog.size()];
	}

	/**
	 * Creates a random, potentially invalid solution
	 */
	public void randomize()
	{
		Random r = new Random();
		
		for (int i = 0; i < testCases.length; i++)
			testCases[i] = r.nextDouble() >= 0.5;
	}
	
	/**
	 * Creates a random, though valid, solution
	 */
	public void randomizeValid()
	{
		Random r = new Random();
		int catalogSize = catalog.size();
		
		int[] testCaseIndex = new int[catalogSize];
		
		for (int i = 0; i < catalogSize; i++)
		{
			testCaseIndex[i] = i;
			testCases[i] = false;
		}
		
		int totalExecutionTime = 0;
		
		for (int i = 0; i < catalogSize; i++)
		{
			int sample = r.nextInt(catalogSize - i);
			int index = testCaseIndex[sample];
			
			TestCase testCase = catalog.get(index);
			totalExecutionTime += testCase.getDuration();
			
			if (totalExecutionTime > limits)
				return;
			
			testCases[index] = true;
			
			for (int j = sample; j < catalogSize-i-1; j++)
				testCaseIndex[j] = testCaseIndex[j+1];
		}
	}
	
	/**
	 * Sets a test case to a specific state
	 */
	public void setTestCase(int index, boolean state)
	{
		testCases[index] = state;
	}
	
	/**
	 * Returns the stateof a given test case
	 */
	public boolean getTestCase(int index)
	{
		return testCases[index];
	}
	
	/**
	 * Calculates the execution time of the solution
	 */
	public int getExecutionTime()
	{
		int duration = 0;
		
		for (int i = 0; i < testCases.length; i++)
			if (testCases[i])
				duration += catalog.get(i).getDuration();
		
		return duration;
	}

	/**
	 * Calculates the coverage of the solution
	 */
	public int getCoverage()
	{
		int duration = 0;
		
		for (int i = 0; i < testCases.length; i++)
			if (testCases[i])
				duration += catalog.get(i).getTotalCoverage();
		
		return duration;
	}
	
	/**
	 * Calculates the fitness of the solution
	 */
	public int getFitness()
	{
		if (getExecutionTime() > limits)
			return 0;
		
		return getCoverage();
	}
	
	/**
	 * Creates a clone of the current solution
	 */
	public Solution clone()
	{
		Solution solution_ = new Solution(catalog, limits);
		
		for (int i = 0; i < testCases.length; i++)
			solution_.setTestCase(i, testCases[i]);

		return solution_;
	}
}