/*
 * mspassov
 * 250901340
 * 
 * Exception checks if there are duplicated keys in the dictionary
 */

public class DuplicatedKeyException extends Exception{
	
	public DuplicatedKeyException() {
		
		super("Duplicated key");
	}

}
