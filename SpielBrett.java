import java.util.HashMap;
import java.util.Random;
import java.io.*;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;


public class SpielBrett {
	public char[][] brett;
	protected char[] farben;
	protected int n,f;
	private String[] indices;
	protected HashMap owned;
	protected boolean newEntry;
	protected char eingabe;
	
	
	SpielBrett(int n,int f){
		this.n = n;
		this.f = f;
		brett =  new char[n][n];
		farben = new char[f];
		indices = new String[n*n];
		owned = new HashMap();
		
		generateFarben(f);
		feldGenerate(n,f);
		generateFirst(0, 0, owned);
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
	   
	   /*
	   
	   ObjectOutputStream oos = new ObjectOutputStream(new FileWriter("NUMBERS.DATA"));
	   int[][] NUMBERS;  // Populate it.
	   oos.writeObject(NUMBERS);
	   */
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
	
	public void ownedChange(char c, HashMap h){
		 for(Object key: h.keySet()){
	            String s = key.toString();
	            //System.out.println(s);
	            String[] d = s.split(";");
	            int i = Integer.parseInt(d[0]);	            
	            int j = Integer.parseInt(d[1]);
	            
	            brett[i][j] = c;

	        }
		
	}
	

	
	public boolean ifOwned(int i, int j, HashMap h){
		String s= Integer.toString(i).concat(";"+Integer.toString(j));
		if(h.containsKey(s))
			return true;
		return false;
		
	}
	
	public void putFeld(int i, int j, HashMap h){
		String s= Integer.toString(i).concat(";"+Integer.toString(j));
		//System.out.println(s);
		h.put(s, 1);
	}
	
	
	public void loopThrough(char c, HashMap h){
		//for iterating, becauase owned can be modified while iterating over it
		HashMap ownedCopy = new HashMap(h);
		newEntry=false;
		 for(Object key: ownedCopy.keySet()){
	            String s = key.toString();	      
	            String[] d = s.split(";");
	            int i = Integer.parseInt(d[0]);	            
	            int j = Integer.parseInt(d[1]);
	            
	            findeMatch(i,j,c, h);   

	        }
     }
	
	/**
	 * finde ab k oder l ten stelle reihenlang und spaltenlang operation rekursiv
	 * @param k
	 * @param l
	 * @param c
	 */
	public void findeMatch(int i, int j, char c, HashMap h){		
         int k;
		
         //nach rechts
		for(k=j+1;k<n;k++){
		    if(brett[i][k]==c){
			    if(!ifOwned(i,k, h)){
	                putFeld(i, k, h);
	                newEntry=true;
			    }
			
		 }
	     else
	         break; 	 
		}

		//nach links
		for(k=j-1;k>=0;k--){
		    if(brett[i][k]==c){
			    if(!ifOwned(i,k, h)){
	                putFeld(i, k, h);
	                newEntry=true;
			    }
			
		 }
	         else
	            break; 
		}


		//nach oben
		for(k=i-1;k>=0;k--){
		    if(brett[k][j]==c){
			    if(!ifOwned(k,j, h)){
	                putFeld(k, j, h);
	                newEntry=true;
			    }
			
		    }
	         else
	            break; 
		}
		 

		//nach unten
		for(k=i+1;k<n;k++){
		    if(brett[k][j] == c){
			    if(!ifOwned(k,j, h)){
	                putFeld(k, j, h);
	                newEntry=true;
			    }
			
		 }
	         else
	            break; 
		}	   
		   
		
	}
	
	
	public int getSize(HashMap a){
		return a.size();
	}
	
	
	public void generateFirst(int i, int j, HashMap h){
		char c = brett [i][j];
		   if(!ifOwned(i,j, h)){
			   putFeld(i,j, h);
		       continueTillNew(c, h, false);
		   }
	}
	
	
	public void continueTillNew(char c, HashMap h, boolean show){
		
		   newEntry=true;
		   
		   while(newEntry){
			   loopThrough(c, h);
		   }
		   
		   ownedChange(c, h);
		   

		   
		   //testprint
		   
		  // printOwned();
		   
		   //ende der Erneuen zeig nochmal das brett,
		   if(show)
		   feldAusgabe();
	}
	
	/**
	 * erneuet das feld nach der Farbe
	 * @param c
	 */
	public void feldErneuen(char c){		  
		   
		   
		   //für erstes mal finde nach der Farbe des ersten Platzes
			//generateFirst(0,0,owned);
		
		   //wechself alle Farben zu der gegeben Farbe in 
		    ownedChange(c, owned);
		    
		    continueTillNew(c, owned, true);
		  
		
	}
	
	
	

}
