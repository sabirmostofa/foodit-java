import static Prog1Tools.IOTools.*;

public class SpielMain {
	
	public static void main(String [] args){
      SpielBrett b = new SpielBrett(10, 4);
      
      while(true){
    	  char input = readChar();    	  
    	  b.feldErneuen(input);   	  
    	  
      }
		
	}
	

}
