import java.util.HashMap;
import java.util.Random;


public class SpielBrett {
	private char[][] brett;
	private char[] farben;
	private int n,f;
	private String[] indices;
	private HashMap owned;
	private boolean newEntry;
	
	
	SpielBrett(int n,int f){
		this.n = n;
		this.f = f;
		brett =  new char[n][n];
		farben = new char[f];
		indices = new String[n*n];
		owned = new HashMap();
		
		generateFarben(f);
		feldGenerate(n,f);
		feldAusgabe();
		
	}
	
	/**
	 * Nicht Farben sondern Buchstaben
	 */
	public void generateFarben(int f){		
		int cBase; 
		cBase = 65;
		for(int i=0; i<f ; i++){
			farben[i] =  Character.toChars(cBase + i)[0];
		}
	}
	
	/**
	 * Gibt eine zufallsZahl zwischen 0 und paramater
	 */
	public int zufallsZahl(int z){
		Random generator = new Random(); 
		int i = generator.nextInt(z);
		return i;
	}
	
	
	/**
	 * erzeugt das Brett erstes mal
	 * @param n
	 * @param f
	 */
	
	public void feldGenerate(int n, int f){
		
	   for(int i=0; i < n; i++ ){		   
		   for(int j=0; j<n; j++){			
			   char nt = farben[zufallsZahl(f)];
			   brett[i][j] = nt;		   
		   }
	
	   }
	}
	
	/**
	 * Druckt das Brett auf der Konsole
	 * @param n
	 */
	
	public void feldAusgabe(){
		
		   for(int i=0; i < n; i++ ){
			   // linien
			   System.out.print("  ");
			   for(int z=0; z < 5*n ; z++)			   
			       System.out.print("-");
			   
			   System.out.println();
			   
			   for(int j=0; j<n; j++){
				   System.out.print("  | ");	
				   System.out.print(brett[i][j]);			   
			   }
			   
			   System.out.print(" |");
			   System.out.println();
		   }
		
	}
	
	/**
	 * change all owned color to c
	 * @param c
	 */
	
	public void ownedChange(char c){
		 for(Object key: owned.keySet()){
	            String s = key.toString();
	            //System.out.println(s);
	            int i = Character.getNumericValue(s.charAt(0));	            
	            int j = Character.getNumericValue(s.charAt(1));
	            
	            brett[i][j] = c;

	        }
		
	}
	
	/**
	 * Test methiod zum owned ausdrucken
	 * 
	 */
	
	public void printOwned(){
		 int total = 0;
		 for(Object key: owned.keySet()){
			    total +=1;
	            String s = key.toString();
	            System.out.println(s);
	            int i = Character.getNumericValue(s.charAt(0));	            
	            int j = Character.getNumericValue(s.charAt(1));
	            System.out.println();
	     

	        }
		 
		 System.out.println(total);
		
	}
	
	public boolean ifOwned(int i, int j){
		String s= Integer.toString(i).concat(Integer.toString(j));
		if(owned.containsKey(s))
			return true;
		return false;
		
	}
	
	public void putFeld(int i, int j){
		String s= Integer.toString(i).concat(Integer.toString(j));
		//System.out.println(s);
		owned.put(s, 1);
	}
	
	
	public void loopThrough(char c){
		//for iterating, becauase owned can be modified while iterating over it
		HashMap ownedCopy = new HashMap(owned);
		 for(Object key: ownedCopy.keySet()){
	            String s = key.toString();	      
	            int i = Character.getNumericValue(s.charAt(0));	            
	            int j = Character.getNumericValue(s.charAt(1));
	            findeMatch(i,j,c);   

	        }
     }
	
	/**
	 * finde ab k oder l ten stelle reihenlang und spaltenlang operation rekursiv
	 * @param k
	 * @param l
	 * @param c
	 */
	public void findeMatch(int i, int j, char c){		
         int k;
		newEntry=false;
         //nach rechts
		for(k=j+1;k<n;k++){
		    if(brett[i][k]==c){
			    if(!ifOwned(i,k)){
	                putFeld(i, k);
	                newEntry=true;
			    }
			
		 }
	     else
	         break; 	 
		}

		//nach links
		for(k=j-1;k>=0;k--){
		    if(brett[i][k]==c){
			    if(!ifOwned(i,k)){
	                putFeld(i, k);
	                newEntry=true;
			    }
			
		 }
	         else
	            break; 
		}


		//nach oben
		for(k=i-1;k>=0;k--){
		    if(brett[k][j]==c){
			    if(!ifOwned(k,j)){
	                putFeld(k, j);
	                newEntry=true;
			    }
			
		    }
	         else
	            break; 
		}
		 

		//nach unten
		for(k=i+1;k<n;k++){
		    if(brett[k][j]==c){
			    if(!ifOwned(k,j)){
	                putFeld(k, j);
	                newEntry=true;
			    }
			
		 }
	         else
	            break; 
		}	   
		   
		
	}
	
	/**
	 * erneuet das feld nach der Farbe
	 * @param c
	 */
	public void feldErneuen(char c){		  
		   
		   
		   //für erstes mal finde nach der Farbe des ersten Platzes
		   if(!ifOwned(0,0)){
			   putFeld(0,0);
		       findeMatch(0,0,brett[0][0]);
		   }
		   
		   //wechself alle Farben zu der gegeben Farbe in 
		    ownedChange(c);
		   
		   newEntry=true;
		   
		   while(newEntry){
			   loopThrough(c);
		   }
		   
		   ownedChange(c);
		   

		   
		   //testprint
		   
		   printOwned();
		   
		   //ende der Erneuen zeig nochmal das brett
		   feldAusgabe();
		
	}
	
	
	

}
