package Files.heuristics;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import Files.model.Catalog;

public class Solution
{
//	private Catalog catalog;
//	private int limits;
//	private boolean[] testCases;
	
	public Solution(Catalog catalog, int limits)
	{
		this.catalog = catalog;
		this.limits = limits;
		this.testCases = new boolean[catalog.size()];
	}
	
	/**
	 * total of duration
	 */
	private @Getter @Setter Catalog catalog;
	private @Getter @Setter int limits;
	private @Getter @Setter boolean[] testCases;

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
	
	public Solution clone()
	{
		Solution solution_ = new Solution(catalog, limits);
		solution_.setTestCases(testCases);
		solution_.setCatalog(catalog);
		solution_.setLimits(limits);
		
		return solution_;
	}
}