package probabilityFinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ProbabilityGeneratorImpl implements ProbabilityGenerator {
	private final String path;
	private List<String> list = null;
	private final int sizeOfTotalString;
	private int requestNumber;
	public ProbabilityGeneratorImpl(String path) throws IOException, MyException {
		this.path = path;
		this.sizeOfTotalString = 100;
		this.requestNumber=0;
		readFile();
	}

	private void readFile() throws IOException, MyException {
		list = new ArrayList<String>();
		String line = "";
		String splitBy = ",";
		
		//Load file from the resource
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(path);
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(streamReader);

		int r=0;
		while ((line = br.readLine()) != null) {
			try {
				r++;
				line = line.trim();
				int indexOfSplit = line.lastIndexOf(splitBy);
				String text = line.substring(0, indexOfSplit);
				int probabilityOfString = (int) (sizeOfTotalString
						* Float.parseFloat(line.substring(indexOfSplit+1, line.length())));
				for (int i = 0; i < probabilityOfString; i++) {
					list.add(text);
				}
			} catch (Exception e) {
//				e.printStackTrace();
				throw new MyException("In input file, row number:"+r+" has some error.",e);
			}
		}
		if (list.size() != sizeOfTotalString)
			throw new MyException("The Sum of All the Probabilities is not equal to 1");
		//For shuffling the position of strings
		Collections.shuffle(list); 
	}

//	@Override
	public String getNextString() {
		requestNumber++;
		Random rand = new Random();
		int rand_int = rand.nextInt(sizeOfTotalString);
		return list.get(rand_int);
	}
	public int getRequestNumber() {
		return requestNumber;
	}
}
