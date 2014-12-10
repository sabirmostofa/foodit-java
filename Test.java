import static Prog1Tools.IOTools.readChar;
import java.util.Arrays;
import java.util.*;


class Pair{
	public int first,second;
	public Pair(int x,int y){
		first=x;
		second=y;
	}
}





public class Test {
	
	
	public static Pair getPair (int a, int b){
		return new Pair(a,b);
	}
	
	public static void main(String [] args){
		String[] myStringArray = new String[3];
		String[] myStringArray2 = {"a","b","c"};
		String[] myStringArray3 = new String[]{"a","b","c"};
		
		int[] myIntArray = new int[3];
		int[] myIntArray2 = {1,2,3};
		int[] myIntArray3 = new int[]{1,2,3};
		//System.out.println(Arrays.toString(myStringArray2));
		//System.out.println(Arrays.asList(myIntArray2).indexOf(2));
		
		HashMap first_player = new HashMap();
		HashMap second_player = new HashMap();
		
		Pair p1= new Pair(0,10);
		Pair p2= new Pair(10,10);

		
	
		first_player.put(new HashMap(); ,1);
		second_player.put(p2,1);
		first_player.put(p1,1);
		
		Pair s = getPair(0,10);
		
		Pair[] pairArray = new Pair[10];
		
		pairArray[0]= new Pair(0,10);
		

		System.out.println(first_player.containsKey(Integer.toString(0).concat(Integer.toString(1))));
		System.out.println(first_player.containsKey(p2));
		System.out.println(second_player.containsKey(p1));
		System.out.println(second_player.containsKey(p2));
		
		System.out.println(second_player.containsKey(s));
		
		
		
	
			
		}

}
