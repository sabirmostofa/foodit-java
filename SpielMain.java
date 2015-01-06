import static Prog1Tools.IOTools.*;

public class SpielMain {
	
	public static void main(String [] args){
      
		//test 1
		
		
		/*
		SpielBrett a = new SpielBrett(15, 5);
      
		a.generateFirst(0,0,a.owned);
	
		System.out.print("Count: ");
		System.out.println(a.owned.size());
		System.out.println();
		
      while(true){
    	  char input = readChar();    	  
    	  a.feldErneuen(input);   	
    	  System.out.println();
    	  System.out.print("Count: ");
    	  System.out.println(a.owned.size());
    	  System.out.println();
    	  
      }
      */
		
			
	// Ende test 1
	
	
	//test 2
		/**
		Spiel2 b= new Spiel2(10, 4);
		b.takeInput();

		
		// Ende test2
	*/
	
	
		
		//Test 3
		 
		SpielComputer c = new SpielComputer(15,5);
		c.startInput();
		
	}

}