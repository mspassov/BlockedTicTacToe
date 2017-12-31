/*
 * mspassov
 * 250901340
 */
public class BlockedTicTacToe {
	
	/*
	 * Instance variables are created.
	 * boardsize gives us the dimensions of the board
	 * inLine tells us how many symbols we need to win
	 * maxLvl tells us how deep the tree should be
	 * gameboard is a 2d array that stores the values
	 * hSize is a constant for how big the hashtable will be
	 */
	private int boardSize;
	private int inLine;
	private int maxLvl;
	private char[][] gameboard;
	private int hSIZE=4999;
	
	/*
	 * Constructor takes in the board size, number of symbols needed to win and depth of tree
	 * @param board_size, inLine, max_levels
	 * @author Martin
	 */
	public BlockedTicTacToe (int board_size, int inline, int max_levels) {
		
		/*
		 * Variables are assigned with inputs
		 */
		boardSize=board_size;
		inLine=inline;
		maxLvl=max_levels;
		gameboard= new char[boardSize][boardSize];
		/*
		 * 2d array is filled with blank strings " "
		 */
		for(int i=0; i<boardSize;i++) {
			for(int j=0; j<boardSize; j++) {
				gameboard[i][j]=' ';
			}
		}
	}
	
	/*
	 * Method creates a dictionary and returns it
	 * @return configurations
	 * @author Martin
	 */
	public TTTDictionary createDictionary() {
		TTTDictionary configurations= new TTTDictionary(hSIZE);
		return configurations;
	}
	
	/*
	 * Method converts the gamebaord into a string and checks to see if it is in the dictionary. If it is then its associated score is returned,
	 * otherwise -1 is returned. 
	 * @return int
	 * @author Martin
	 */
	public int repeatedConfig(TTTDictionary configurations) {
		
		String gameString="";
		/*
		 * gameboard is converted to a string
		 */
		for(int i=0; i<boardSize; i++) {
			for(int j=0; j<boardSize; j++) {
				gameString=gameString+gameboard[i][j];
			}
		}
		
		/*
		 * returns either the score or -1
		 */
		TTTRecord target= configurations.get(gameString);
		if(target!=null) {
			return target.getScore();
		}
		else {
			return -1;
		}
	}
	/*
	 * Method represents the gameboard as a string and then insterts in the dictionary
	 * @param configurations, score, level
	 * @author Martin
	 */
	public void insertConfig(TTTDictionary configurations, int score, int level) {
		
		String gameString="";
				
		for(int i=0; i<boardSize; i++) {
			for(int j=0; j<boardSize; j++) {
				gameString=gameString+ gameboard[i][j];
			}
		}
		
		/*
		 * Tries to put in the record. Throws exception if it already exists in the dictionary
		 */
		TTTRecord newRecord= new TTTRecord(gameString, score, level);
		try {	
			configurations.put(newRecord);
		}
		catch(DuplicatedKeyException e){
			e.getMessage();
		}
		
	}
	/*
	 * Method stores the symbol in the gameboard
	 * @param row, col, symbol
	 * @author Martin
	 */
	public void storePlay(int row, int col, char symbol){
		gameboard[row][col]= symbol;
	}
	
	/*
	 * Method checks to see if the square is empty
	 * @param row, col 
	 * @author Martin
	 */
	public boolean squareIsEmpty (int row, int col) {
		 if(gameboard[row][col]==' ') {
			 return true;
		 }
		 
		 else {
			 return false;
		 }
	 }
	 
	/*
	 * Method checks to see if someone has won
	 * @param symbol 
	 * @return Martin
	 */
	 public boolean wins (char symbol) {
		 
		 /*
		  * First method checks horizontally
		  * 
		  */
		 int Hcheck=0;
		 
		 for (int i=0; i<boardSize; i++) {
			 for(int j=0; j<boardSize; j++) {
				 if(gameboard[i][j]==symbol) {
					 Hcheck++; 
				 }
				 /*
				  * This condition resets the counter if adjacent squares do not contain the same symbol
				  */
				 if(gameboard[i][j]!=symbol && Hcheck<inLine) {
					 Hcheck=0;
				 }
			 }
			
			 if(Hcheck==inLine) {
				 return true;
			 }
			 else {
				 Hcheck=0;
			 }
		 }
		 
		 /*
		  * Next method will check vertically for a win. 
		  */
		 int Vcheck=0;
		 
		 for (int i=0; i<boardSize; i++) {
			 for(int j=0; j<boardSize; j++) {
				 if(gameboard[j][i]==symbol) {
					 Vcheck++;
				 }
				 /*
				  * This condition resets the counter if adjacent squares do not contain the same symbol
				  */
				 if(gameboard[j][i]!=symbol && Vcheck<inLine) {
					 Vcheck=0;
				 }
			 }
			 
			 if(Vcheck==inLine) {
				 return true;
			 }
			 else {
				 Vcheck=0;
			 }
		 }
		 
		 /*
		  * Finally, method checks the diagonal, first the diagonals in this direction \ (bottom half of the array)
		  * A counter is placed to keep a running count of how many symbols are adjacent
		  */
		 
		 int Dcheck1=0;
		 int i=0;
		 int max=gameboard.length;
		 int iter=0;
		 
		 /*
		  * The diagonals begin to be traversed 
		  */
		 while(i<gameboard.length) {
			 for(int j=0; j<max; j++) {
				 if(gameboard[i][j]==symbol) {
					 Dcheck1++;
				 }
				 /*
				  * This condition resets the counter if adjacent squares do not contain the same symbol
				  */
				 if(gameboard[i][j]!=symbol && Dcheck1<inLine) {
					 Dcheck1=0;
				 }
				 i++;
			 }
			 
			 if(Dcheck1==inLine) {
				 return true;
			 }
			 /*
			  * The counter is reset is no win is found
			  */
			 else {
				 iter++;
				 i=iter;
				 max--;
				 Dcheck1=0;
			 }
			 
		 }
		 
		 /*
		  * Diagonals (\) top half of array
		  */
		 
		 int Dcheck2=0;
		 int start=1;
		 int i2=0;
		 
		 while(start<gameboard.length) {
			 for(int j=start; j<gameboard.length; j++) {
				 if(gameboard[i2][j]==symbol) {
					 Dcheck2++;
					 
				 }
				 /*
				  * This condition resets the counter if adjacent squares do not contain the same symbol
				  */
				 if(gameboard[i2][j]!=symbol && Dcheck2<inLine) {
					 Dcheck2=0;
				 }
				 i2++;
			 }
			 
			 if(Dcheck2==inLine) {
				 return true;
			 }
			 
			 else {
				 i2=0;
				 start++;
				 Dcheck2=0;
			 }
			 
		 }
		 
		 /*
		  * Check Diagonals top half in this direction /
		  */
		 
		 int Dcheck3=0;
		 int i3=gameboard.length-1;
		 int iter3=gameboard.length-1;
		 int max2=gameboard.length;
		 
		 while(max2>0) {
			 for(int j=0; j<max2; j++) {
				 if(gameboard[i3][j]==symbol) {
					 Dcheck3++;
				 }
				 /*
				  * This condition resets the counter if adjacent squares do not contain the same symbol
				  */
				 if(gameboard[i3][j]!=symbol && Dcheck3<inLine) {
					 Dcheck3=0;
				 }
				 i3--;
			 }
			 
			 if(Dcheck3==inLine) {
				 return true;
			 }
			 else {
				 iter3--;
				 i3=iter3;
				 Dcheck3=0;
				 max2--;
			 }
		 }
		 
		 /*
		  * Check Diagonals bottom half in this direction /
		  */
		 
		 int Dcheck4=0;
		 int i4=gameboard.length-1;
		 int start4=1;
		 
		 while(start4<gameboard.length) {
			 for(int j=start4; j<gameboard.length;j++) {
				 if(gameboard[i4][j]==symbol) {
					 Dcheck4++;
					 
					 
				 }
				 /*
				  * This condition resets the counter if adjacent squares do not contain the same symbol
				  */
				 if(gameboard[i4][j]!=symbol && Dcheck4<inLine) {
					 Dcheck4=0;
				 }
				 i4--;
			 }
			 
			 if(Dcheck4==inLine) {
				 return true;
			 }
			 else {
				 i4=gameboard.length-1;
				 start4++;
				 Dcheck4=0;
			 }
			 
		 }
		 
		 
		 return false;
	 }
	/*
	 * Method checks if there is a draw
	 * @return True or False
	 * @author Martin
	 */
	 public boolean isDraw() {
		 
		 boolean availablePos=false;
		 /*
		  * Checks to see if the gamebaord is full
		  */
		 for(int i=0;i<boardSize;i++) {
				for(int j=0; j<boardSize; j++) {
					if(gameboard[i][j]==' ') {
						availablePos=true;
						break;
					}
				}
			}
		 /*
		  * Also makes sure that no one has won
		  */
		 if(availablePos==false && (this.wins('x')==false && this.wins('o')==false)) {
			 return true;
			 
		 }
		 else {
			 return false;
		 } 
		
	 }
	 /*
	  * Method returns the score associated with a win or a draw
	  * @return int
	  * @author Martin
	  */
	 public int evalBoard() {
		 
		 if(this.wins('o')==true) {
			 return 3;
		 }
		 
		 else if(this.wins('x')) {
			 return 0;
		 }
		 else if(this.isDraw()) {
			 return 1;
		 }
		 else {
			 return 2;
		 }
	 }
	 
}
