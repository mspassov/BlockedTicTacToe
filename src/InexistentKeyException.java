/*
 * mspassov
 * 250901340
 * 
 * Exception checks if the key does not exist in the dictionary
 */
public class InexistentKeyException extends Exception {
	public InexistentKeyException() {
		super("Key does not exist");
	}
}
