import static Prog1Tools.IOTools.readChar;

import java.util.HashMap;


public class Spiel2 extends SpielBrett {
	
	
	protected HashMap owned2;
	protected boolean p1, p2;
	
	/**
	 * Constructor
	 * 
	 * @param n
	 * @param f
	 */
	Spiel2(int n, int f){
		super(n,f);
		owned2 = new HashMap();		
		p1 = true;
		p2 = false;
		
	}
	
	
	/**
	 * interchange p1 p2 to make the turn of the players
	 */
	
	public void interchange(){
		
		if(p1 == true){
			p1 = false;
			p2 = true;
		}
		else{
			p1 = true;
			p2 = false;
		}
		
	}
	/**
	 * Decides if the input is valid. Must the same color as the opponent  and must not the same color as he already has.
	 * @param c
	 * @return
	 */
	
	public boolean isValid(char c){
		
		if(p1){
			if(c != brett[n-1][n-1] && c != brett[0][0])
				return true;
			else
				return false;
		}
		else{
			if(c != brett[0][0] && c != brett[n-1][n-1])
				return true;
			else
				return false;
			
		}
	
		
	}
	
	/**
	 * Prints the result
	 */
	public void showResult(){
		
		System.out.print("Player1: ");
		System.out.println(owned.size());
		System.out.print("Player2: ");
		System.out.println( owned2.size());
	}
	
	public void showTurn(){
		if(p1){
			
			 System.out.println("\nPlayer 1:\n");
		}
		else
			 System.out.println("\nPlayer 2:\n");
	}
	
	/**
	 * Takes the input and starts the loop
	 */
	
	public void takeInput(){
		// find the owned boxes at start
		generateFirst(0, 0, owned);
		generateFirst(n-1, n-1, owned2);
		
	      while(true){	    	  
	    	  showTurn();	    	  
	    	  char c = readChar();
	    	  
	    	  // only runs if valid c else doesnt do anything
	    	  if( isValid(c) ){
	    	      //if player one is playing  
	    		  if(p1){ 
	    			  continueTillNew(c, owned); 	    	        	
	    	        }
	    	        else{	  
                      continueTillNew(c, owned2); 	    	        	
	    	        }
	    		  
	    		  //turn the players at the end
	    		  interchange();
	    		  showResult();
	    	  }//endof isvalid
	    	  
	      }
		
	}
	
	
	
	

}
