package probabilityFinder;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class MainClassProbability {
	
	public static void main(String[] args) {
	     // init the prob generator
		try {
	     ProbabilityGenerator probabilityGenerator = new ProbabilityGeneratorImpl("probabilities.csv");
	     //numberOfTimes : Number of times that method is called. We can have any value for the same
	     Scanner myInput = new Scanner( System.in );
	     System.out.print( "Enter numberOfTimes: " );
	     int numberOfTimes = myInput.nextInt();
	     String outputFilePath="output.csv";
	     
	     File outputFile = new File(outputFilePath);
	     FileWriter outputfile = new FileWriter(outputFile);
	     CSVWriter writer = new CSVWriter(outputfile, CSVWriter.DEFAULT_SEPARATOR,
					CSVWriter.NO_QUOTE_CHARACTER,
				    CSVWriter.NO_ESCAPE_CHARACTER,
				    CSVWriter.DEFAULT_LINE_END);
	     for(int i=0; i<numberOfTimes; i++) {
	          String nextStr = probabilityGenerator.getNextString();
//	          System.out.println(nextStr);
	          writer.writeNext(new String[] {nextStr});
	          writer.flush();
	     }
	     writer.close();
	     myInput.close();
		}
		catch (Exception e) {
//			new MyException("Error in main",e);
			e.printStackTrace();
		}
	     

	}

}
