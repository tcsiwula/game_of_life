public class ArgumentParser
{	
	
	public ArgumentParser()
	{	
	}
	
	public void getData(String[] args, String fileNameOfInitialConditions, String fileNameToOutput, int numberOfGenerations)
	{
		String arg1 = args[0]; // filename for input
		String arg2 = args[1]; //  filename for output
		String arg3 = args[2]; // number of generations

		fileNameOfInitialConditions = arg1;
		fileNameToOutput = arg2;
		numberOfGenerations = Integer.parseInt(arg3);
	}
}
