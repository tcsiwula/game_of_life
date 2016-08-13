
public class Life
{
	
	public Life()
	{
	}
	
	public static void main(String[] args)
	{
		
		int numberOfGenerations;
		MySparseArray currentGeneration = new MySparseArray();
		MySparseArray nextGeneration = new MySparseArray();
		MySparseArray numberOfNeighbors = new MySparseArray();

		boolean alive;
		String fileNameOfInitialConditions = "data.txt";
		String fileNameToOutput = "results.txt";
		
		// 1 get arguments
		ArgumentParser getInput = new ArgumentParser();
			
		// 2 read data
		ReadInitialConditionsFile getNumbers = new ReadInitialConditionsFile();
		getNumbers.openFile(fileNameOfInitialConditions);
		getNumbers.copyFile(currentGeneration );
//		System.out.println("File Read Error");

		
		// 3 compute generations
		Simulate compute = new Simulate();
		//compute.start(currGen, nextGen, numberOfGenerations);
		
		// 4 write results
		WriteResultsToFile export = new WriteResultsToFile();
		export.open(fileNameToOutput);
		
	}
	
}