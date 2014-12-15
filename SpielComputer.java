import static Prog1Tools.IOTools.readChar;
import java.util.*;


/**
 * Variant for the game against computer
 * @author cacophonix
 *
 */
public class SpielComputer extends Spiel2 {
	
	SpielComputer(int n, int f){
		super(n, f);		
	}
	
	/**
	 * Finds the best option to choose a color
	 * @param c
	 */
	public char findMost(){
		
		char current = brett[n-1][n-1];
		char opponent = brett[0][0];
		ArrayList<Character> colors = new ArrayList<Character>();
		ArrayList<Integer> sizeArray  = new ArrayList<Integer>();
		char [][] brettCopy = new char[n][n];
		HashMap owned2Copy;
		
		// get available colors
		for(char ac: farben){
			if(ac != current && ac != opponent)
				colors.add(ac);
		}
		
		int curSize = owned2.size();
		
		// copying because the values will be changed
		brettCopy = brett.clone();
		owned2Copy = new HashMap(owned2);
		
		
		
		for(char acc: colors){		
			continueTillNew(acc, owned2,false);			
			sizeArray.add(owned2.size() - curSize);
			
			// backs to its original values
			brett = brettCopy.clone();
			owned2 = new HashMap(owned2Copy);

		}
		
		int max = sizeArray.get(0);
		for(int i:sizeArray){
			if(i > max)
				max = i;		
		}
		
		int maxIndex = sizeArray.indexOf(max);
		
		return  colors.get(maxIndex);
		
		
	}

	
	public void startInput(){
		// find the owned boxes at start
		generateFirst(0, 0, owned);
		generateFirst(n-1, n-1, owned2);
		showResult();
		
	      while(true){	    	  
	    	  	    	  
	    	  char c = readChar();
	    	  
	    	  // only runs if valid c else doesnt do anything
	    	  if( isValid(c) ){
	    	      //if player one is playing  
	    		  
	    			  continueTillNew(c, owned,false); 	    	        	
	    	        
	    		  // computer is playing
	    			  
	    			  char ac = findMost();
	    			  
	    			  continueTillNew(ac, owned2,true);
	    			  

	    		  
	    	
	    		
	    		  showResult();
	    	  }//endof isvalid
	    	  
	      }
		
	}
}
