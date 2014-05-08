package Files.heuristics;

import java.util.Random;

import Files.model.Catalog;

public class Solution
{
	private Catalog catalog;
	private int limits;
	private boolean[] testCases;
	
	public Solution(Catalog catalog, int limits)
	{
		this.catalog = catalog;
		this.limits = limits;
		this.testCases = new boolean[catalog.size()];
	}

	public void randomize()
	{
		Random r = new Random();
		
		for (int i = 0; i < testCases.length; i++)
			testCases[i] = r.nextDouble() >= 0.5;
	}
	
	public void setTestCase(int index, boolean state)
	{
		testCases[index] = state;
	}
	
	public boolean getTestCase(int index)
	{
		return testCases[index];
	}
	
	public int getExecutionTime()
	{
		int duration = 0;
		
		for (int i = 0; i < testCases.length; i++)
			if (testCases[i])
				duration += catalog.get(i).getDuration();
		
		return duration;
	}
	
	public int getCoverage()
	{
		int duration = 0;
		
		for (int i = 0; i < testCases.length; i++)
			if (testCases[i])
				duration += catalog.get(i).getTotalCoverage();
		
		return duration;
	}
	
	public int getFitness()
	{
		if (getExecutionTime() > limits)
			return 0;
		
		return getCoverage();
	}
}