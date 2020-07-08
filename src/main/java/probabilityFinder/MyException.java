package probabilityFinder;

public class MyException extends Exception {
	
	public MyException(String s) {
		super(s);
	}
	
	public MyException(Exception e) {
		super(e);
	}
	
	public MyException(String s,Exception e) {
		super(s,e);
	}
	
}
