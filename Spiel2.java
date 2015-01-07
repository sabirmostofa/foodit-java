import static Prog1Tools.IOTools.readChar;

import java.util.HashMap;


public class Spiel2 extends SpielBrett {
	
	
	protected HashMap owned2;
	protected boolean p1, p2;
	protected int steps;
	
	// variable that defines how many steps the two players can choose as same color 
	protected int sameStep;
	
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
		steps = 0;
		sameStep = 2;
		generateFirst(n-1, n-1, owned2);
		
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
	 * Exception for the first two moves, For the first two moves one can choose the color of the opponent
	 * @param c
	 * @return
	 */
	
	public boolean isValid(char c){
		
		if(steps < sameStep){
			
			if(p1){
				if(c != brett[0][0])
					return true;
				else 
					return false;
				
			}
			else {
				if(c != brett[n-1][n-1])
					return true;
				else 
					return false;
				
			}
		
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
		

		
	      while(true){	    	  
	    	  showTurn();	    	  
	    	  char c = readChar();
	    	  startGame2(c);
	    	  
	    	  

	    	  
	      }
		
	}
	
public void startGame2(Character c){
	// only runs if valid c else doesnt do anything
	  if( isValid(c) ){
	      //if player one is playing  
		  if(p1){ 
			  continueTillNew(c, owned, true); 	    	        	
	        }
	        else{	  
              continueTillNew(c, owned2, true); 	    	        	
	        }
		  steps += 1;
		  
		  //turn the players at the end
		  interchange();
		  showResult();
		  
	  }//endof isvalid
                                    }
	
	
	
	

}
