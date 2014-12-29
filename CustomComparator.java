import java.util.Comparator;
import java.util.Map;


public 	 class CustomComparator implements Comparator<Map.Entry<Integer,String>> {
    @Override
    public int compare(Map.Entry<Integer,String> o1, Map.Entry<Integer,String> o2) {
        return o1.getKey().compareTo(o2.getKey());
    }
}
