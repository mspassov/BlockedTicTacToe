/*
 * mspassov
 * 250901340
 */

public class TTTRecord {
	/*
	 * variables are created here, the gameboard configuration, the associated score, and the associated level
	 */
	private String configuration;
	private int TTTscore;
	private int TTTlevel;
	
	/*
	 * Constructor takes in the configuration, the score and the level and instantiates the variables
	 * @param config, score, level
	 * @author Martin
	 */
	public TTTRecord(String config, int score, int level) {
		
		/*
		 * Variables are assigned and instantiated
		 */
		configuration=config;
		TTTscore=score;
		TTTlevel= level;
		
	}
	
	/*
	 * Method returns string configuration
	 * @return configuration
	 * @author Martin
	 */
	public String getConfiguration() {
		return configuration;
		
	}
	
	/*
	 * Method returns score
	 * @return TTTscore
	 * @author Martin
	 */
	public int getScore() {
		return TTTscore;
	}
	
	/*
	 * Method returns level
	 * @return TTTlevel
	 * @author Martin
	 */
	public int getLevel() {
		return TTTlevel;	
	}	

}
