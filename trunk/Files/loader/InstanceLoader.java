package Files.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Files.model.Catalog;
import Files.model.TestCase;

public class InstanceLoader
{
	public Catalog execute(String path)
	{
		File file = new File(path);
		Catalog catalog = new Catalog();

        try 
        {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
    			String[] tokens = line.split(";");
            
    			TestCase testCase = new TestCase(tokens.length - 2);
    			
    			int duration = Integer.parseInt(tokens[1].trim());
    			testCase.setDuration(duration);

    			for (int i = 2; i < tokens.length; i++)
    			{
					int coverage = Integer.parseInt(tokens[i].trim());
					testCase.setCoverage(i-2, coverage);
    			}
    			
    			catalog.add(testCase);
            }
            
            scanner.close();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }           

		return catalog;
	}
}