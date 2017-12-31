/*
 * mspassov
 * 250901340
 */

public class TTTDictionary implements TTTDictionaryADT {
	
	/*
	 * Variables are instantiated. tableSize tells us the size of the array, and the hashTable is created today
	 */
	private int tableSize;
	private LinearNode [] hashTable;
	
	/*
	 * Constructor, takes in the table size. The hashTable is created here.
	 * @param size
	 * @author Martin
	 */
	public TTTDictionary(int size) {
		
		tableSize=size;
		hashTable= new LinearNode[tableSize];
		
	}
	
	/*
	 * This is an auxiliary method which computes the hash code.
	 * The method takes in the string representation of the gameboard, a constant x which I chose to be 41, and the size of the board 
	 * @param S, x, size
	 * @return integer representation of string
	 */
	private int polynomialHash(String S, int x, int size) {
		/*
		 * The string is split and each character is added to an array
		 */
		String [] wordArray=S.split("");
		/*
		 * It is then converted to a integer representation
		 */
		int value= wordArray[0].charAt(0);
		/*
		 * All the characters are summed to give an integer represntation of the string
		 */
		for(int i=1; i<wordArray.length; i++){
			value=(value*x+wordArray[i].charAt(0)) % size;
		}
		
		return value;
	}
	
	/*
	 * Method takes a record and places it in the hash table
	 * @param record, takes in a TTTRecord
	 * @return 0 or 1 depending on if the array position is empty or if a node is added to a linked list (a collision) 
	 */
	public int put(TTTRecord record) throws DuplicatedKeyException{
		
		/*
		 * Finds the cell in which the value should be stored and assigns it to a variable
		 */
		int cell=this.polynomialHash(record.getConfiguration(), 41, tableSize);
		
		/*
		 * if there is nothing in this cell a new node is created, and this will be the beginning of a potential linked list
		 */
		if(hashTable[cell]==null) {
			/*
			 * new node of type TTTRecord is created
			 */
			hashTable[cell]= new LinearNode<TTTRecord>(record);
			return 0;
		}
		
		/*
		 * If the above statement is not entered, this means that a node already exists and a collision has occurred
		 */
		else{
				LinearNode<TTTRecord> counter= hashTable[cell];
				/*
				 * First, we check to see if there is the same record in the linked list, if there is an exception is thrown
				*/
				while(counter!=null) {
					if(counter.getElement().getConfiguration().equals(record.getConfiguration())) {
						
						throw new DuplicatedKeyException();
					}
					counter=counter.getNext();
				}
				
				/*
				 * if an exception is not thrown, then we add the record to the beginning of the linked list. 
				 * A new node is created to do so. 
				 */
				
				LinearNode<TTTRecord> current= hashTable[cell];
				
				while(current.getNext()!=null) {
					current=current.getNext();
				}
				LinearNode<TTTRecord> newNode= new LinearNode<TTTRecord>(record);
				current.setNext(newNode);
				
				return 1;
			
		}
		
	}
	
	/*
	 * Method takes in a string and removes it from the hash table if it exists. If it does not exist, an exception is thrown
	 * @param config
	 * @author Martin 
	 */
	public void remove(String config) throws InexistentKeyException{
		
		/*
		 * The position of the string within the hash table is computed using the hash function
		 */
		int position=this.polynomialHash(config, 41, tableSize);
		
		LinearNode<TTTRecord> helper = new LinearNode<TTTRecord>();
		/*
		 * Check to see if that position is empty, if it is then an exception is thrown since the record cannot exist
		 */
		helper=hashTable[position];
		
		if(helper==null) {
			throw new InexistentKeyException();	
		}
		
		/*
		 * The linked list is traversed to check if the node is within the list. If it is not then an exception is thrown that the string does not exist
		 */
		int check=0;
		while(helper!=null) {
			if(helper.getElement().getConfiguration().equals(config)) {
				check++;
			}
			helper=helper.getNext();
		}
		
		if(check==0) {
			throw new InexistentKeyException();
		}
		/*
		 * Beyond this point we are sure that the item is in the linked list and needs to be removed, but we do not know the position yet.
		 * So the while loop finds the record
		*/
		helper=hashTable[position];
		
		while(true) {
			if(helper.getElement().getConfiguration().equals(config)) {
				break;
			}
			else {
				helper=helper.getNext();
			}
		}
		
		/*
		 * Checks if it is the first node to be removed, if true then it removes it
		 */
		if(helper.getElement().getConfiguration().equals(config)) {
			hashTable[position]=hashTable[position].getNext();
		}
		/*
		 * Also checks if it is the last node to be removed, and if so removes it
		 */
		else if(helper.getNext()==null) {
			
				LinearNode<TTTRecord> beforeHelper=hashTable[position];
			
				//we stop when beforeHelper is the second last node
				while(beforeHelper.getNext()!=helper) {
					beforeHelper=beforeHelper.getNext();
				}
				beforeHelper.setNext(null);
		}
		
		/*
		 * If the above statements have not been triggered, then the node must be in the middle, and needs to be removed
		 */
		else {
			LinearNode<TTTRecord> previous = new LinearNode<TTTRecord>();
			previous=hashTable[position];
			while(previous.getNext()!=helper) {
				previous=previous.getNext();
			}
			previous.setNext(helper.getNext());
		}
		
	}
	
	/*
	 * Method takes in a string configuration and returns the TTTRecord for the given configuration
	 * @param config
	 * @return TTTRecord or null
	 * @author Martin
	 */
	public TTTRecord get(String config) {
		
		/*
		 * The position is found using the polynomial hash function
		 */
		int position=this.polynomialHash(config, 41, tableSize);
		
		/*
		 * If the position contains nothing then null is returned
		 */
		if(hashTable[position]==null) {
			return null;
		}
		/*
		 * Otherwise the linked list is traversed and the record is returned
		 */
		else {
			/*
			 * Helper node is created to traverse the linked list
			 */
			LinearNode<TTTRecord> finder= hashTable[position];
			while(finder!=null) {
				if(finder.getElement().getConfiguration().equals(config)) {
					return finder.getElement();
				}
				finder=finder.getNext();
			}
			
		}
		
		return null;
		
	}
	
	/*
	 * Method returns the number of elements stored in the hash table
	 * @return counter
	 * @author Martin
	 */
	public int numElements() {
		
		int counter=0;
		/*
		 * A for loop is created which cycles through the array and if there is more than one element per cell, then the linked list is also traversed 
		 */
		for(int i=0; i<hashTable.length; i++) {
			
			LinearNode<TTTRecord> counterNode=hashTable[i];
			if(hashTable[i]!=null) {
				while(counterNode!=null) {
					counter++;
					counterNode=counterNode.getNext();
				}
			}
		}
		
		return counter;
	}

	
}
