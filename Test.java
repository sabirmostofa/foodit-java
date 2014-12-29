import static Prog1Tools.IOTools.readChar;
import java.util.*;




public class Test {
	public static char []farben = {'a', 'b', 'c', 'd'};
	public static ArrayList<String> combs = new ArrayList<String>();
	
	public static void main(String [] args){
		
		
		int[] a = new int[]{1,2,3,4,5};
		int[] b = a.clone();
		
		a[0] = 6;
		
		a = b.clone();
		String s = "1;2";
		String[] d = s.split(";");
		System.out.println(d[0].charAt(0));
		System.out.println(d[1].charAt(0));
		
		
		
		
		SortedSet<Map.Entry<Integer, String>> rankedMoves = new TreeSet<Map.Entry<Integer, String>>(
	            new Comparator<Map.Entry<Integer, String>>() {
	                @Override
	                public int compare(Map.Entry<Integer, String> e1,
	                        Map.Entry<Integer, String> e2) {
	                    return e1.getKey().compareTo(e2.getKey());
	                }
	            });
		

		rankedMoves.add(new AbstractMap.SimpleEntry<Integer, String>(41,"exmpleString"));
		rankedMoves.add(new AbstractMap.SimpleEntry<Integer, String>(42,"ex"));
		rankedMoves.add(new AbstractMap.SimpleEntry<Integer, String>(39,"exi"));
		rankedMoves.add(new AbstractMap.SimpleEntry<Integer, String>(44,"ex"));
		
		
		
		
		
		//combination(0, 6, farben, combs);
		combs = norepeatCombination(0,3, farben);
		/*
		
		HashMap<Integer, String> map = new HashMap<Integer, String>(); 
		
		map.put("ab",2);
		map.put("aa",2 );
		map.put("ba",2 );
		map = orderbyVal(map);
		
		//map = removeChar('b',map);
		
		*/
		

	
		
		TreeMap t = new TreeMap();
		t.put(2,"aa");
		t.put(2,"ab");
		t.put(8,"aa");
		t.put(-2,"aa");
		
	 class CustomComparator implements Comparator<Map.Entry<Integer,String>> {
		    @Override
		    public int compare(Map.Entry<Integer,String> o1, Map.Entry<Integer,String> o2) {
		        return o1.getKey().compareTo(o2.getKey());
		    }
		}
		
		ArrayList<Map.Entry<Integer, String>> moves = new ArrayList<Map.Entry<Integer, String>>();
		
		Collections.sort(moves, new CustomComparator());
		
		moves.add(new AbstractMap.SimpleEntry<Integer, String>(41,"exmpleString"));
		moves.add(new AbstractMap.SimpleEntry<Integer, String>(40,"exmpleString"));
		moves.add(new AbstractMap.SimpleEntry<Integer, String>(42,"exmp"));
		moves.add(new AbstractMap.SimpleEntry<Integer, String>(39,"exmpleString"));
		
		moves.add(new AbstractMap.SimpleEntry<Integer, String>(44,"exmpleString"));
		moves.add(new AbstractMap.SimpleEntry<Integer, String>(41,"fuck"));
		
		Collections.sort(moves, new CustomComparator());
				
		
		
		
		
	}
	
	/**

	public static SortedSet<Map.Entry<Integer, String>> newOrder( SortedSet<Map.Entry<Integer, String>> myMap ){
	SortedSet<Map.Entry<Integer, String>> sortedset = new TreeSet<Map.Entry<Integer, String>>(
            new Comparator<Map.Entry<Integer, String>>() {
                @Override
                public int compare(Map.Entry<Integer, String> e1,
                        Map.Entry<Integer, String> e2) {
                    return e1.getValue().compareTo(e2.getValue());
                }
            });

  sortedset.addAll(myMap.entrySet());
  return sortedset;
	}
	*/
	
	public static ArrayList removeChar(char c, ArrayList<Map.Entry<Integer, String>> hmap){
		
		Iterator it = hmap.iterator();
		
		//check if first character is the given character
		while(it.hasNext()){
			Map.Entry<Integer, String>  e= (Map.Entry<Integer, String> ) it.next();
			if(e.getValue().charAt(0) == c )
				it.remove();;
		}
		
		Collections.sort(hmap, new CustomComparator());
		return hmap;
		
	}
	
	public static ArrayList<Map.Entry<Integer, String>> removeChar(ArrayList<Character> ch, ArrayList<Map.Entry<Integer, String>> hmap){
	
		   for(char c:ch){
			   removeChar(c, hmap);
			   
		   }
		   Collections.sort(hmap, new CustomComparator());
		   return hmap;
	}
	
	
	/*
	public static HashMap<Integer, String> stripFirstChar(HashMap<Integer, String> hmap){
		
		Iterator it = (hmap.entrySet()).iterator();
		HashMap<Integer, String>  stripped = new HashMap<Integer, String> ();
		
		//check if first character is the given character
		while(it.hasNext()){
			Map.Entry<Integer, String>  e= (Map.Entry<Integer, String> ) it.next();
			
			stripped.put( e.getKey().substring(1) , e.getValue());
		    
		   
		}
		
		return stripped;
		
	}
	
	*/
	/**
	 * Strip first character sorted set version
	 * @param hmap
	 * @return
	 */
	
	public static ArrayList<Map.Entry<Integer, String>> stripFirstChar(ArrayList<Map.Entry<Integer, String>> hmap){
		
		Iterator it = hmap.iterator();
		
		ArrayList<Map.Entry<Integer, String>> stripped = new ArrayList<Map.Entry<Integer, String>>();        
	  
		
		//check if first character is the given character
		while(it.hasNext()){
			Map.Entry<Integer, String>  e= (Map.Entry<Integer, String> ) it.next();
			
			stripped.add(new AbstractMap.SimpleEntry<Integer, String>(  e.getKey(),  e.getValue().substring(1)));
		    
		   
		}
		Collections.sort(stripped, new CustomComparator());
		return stripped;
		
	}
	
	
	// sort the hashmap by value Aschending order
	
	/*
	public static HashMap<Integer, String> orderbyVal(HashMap<Integer, String> hmap){		
		
		
		List<String> mapKeys = new ArrayList<String>(hmap.keySet());
		List<Integer> mapValues = new ArrayList<Integer>(hmap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		   LinkedHashMap sortedMap = new LinkedHashMap();

		   Iterator<Integer> valueIt = mapValues.iterator();
		   while (valueIt.hasNext()) {
		       Integer val = valueIt.next();
		       Iterator<String> keyIt = mapKeys.iterator();

		       while (keyIt.hasNext()) {
		           String key = keyIt.next();
		           Integer comp1 = hmap.get(key);
		           

		           if (comp1 == val){
		               hmap.remove(key);
		               mapKeys.remove(key);
		               sortedMap.put((String)key, (Integer)val);
		               break;
		           }

		       }

		   }
		   return sortedMap;
		
		
		
	}
	*/

	
	/**
	 * Make the combination of no character repeated after one another
	 * @param i
	 * @param stelle
	 * @param chars
	 * @return
	 */
	
	public static ArrayList<String> norepeatCombination(int i, int stelle, char [] chars ){

		ArrayList<String> ar = new ArrayList<String>();		
		combination(i, stelle, chars, ar);		
		
		
		Iterator<String> s = ar.iterator();
	
		while(s.hasNext()){	
			String str = s.next();
			int len = str.length();
			for( int m = 0; m<len-1 ; m++){
				if (str.charAt(m) == str.charAt(m+1)){
				s.remove();			
				break;
				}
			
			}
		
		}	
		ar.removeAll(Collections.singleton(null));
		
		Collections.sort(ar);
		return ar;
	}
	
	/**
	 * Funktion to generate combination of some characters
	 * @param i
	 * @param stelle
	 * @param chars
	 * @param ar
	 * @return
	 */
	
	public static ArrayList<String> combination(int i, int stelle, char [] chars , ArrayList<String> ar){
	
		int period;
		if(i == stelle)
			return ar;
		int len = -1;
		len = chars.length;
		
		int total = (int) Math.pow(len, stelle);
		int [] reps = new int[len];
		
		if (i == 0)
			period = total / len;
		else
			period = total/(int)Math.pow(len, i+1);
		
		System.out.println(period);
		
		
		int marker = 0;
		// first time
		if(i == 0){
			for(int k = 0; k < total ; k++){
				    if( k != 0)
		            if( k%period  == 0 )
		            	marker += 1;
		            ar.add(""+chars[marker]);
			
			}
			combination(i+1, stelle, chars,ar);
		}
		// else when for more than 1st time
		else{
			for(int k = 0; k < total ; k++){
				if( k != 0)
				if( k% period == 0 )
					marker += 1;
				
				//reset marker
				if (marker == len)
					marker = 0;
				
				// reset marker while the last loop
				if( period < len )
					marker = k%len;
				
	            ar.set( k, ar.get(k) + chars[marker]);	            
		
		}
			combination(i+1, stelle, chars,ar);
			
			
		}// end else
		
		return ar;
		
	}
	
	/**
	 * copies current to old
	 * @param old
	 * @param current
	 * @return
	 */
	
	public static char [][] arCopy(char[][] old, char[][] current){
		for(int i=0; i<old.length; i++)
			  for(int j=0; j<old[i].length; j++)
			    old[i][j]=current[i][j];
		
		return old;
	} 
	
	
	
	

}
