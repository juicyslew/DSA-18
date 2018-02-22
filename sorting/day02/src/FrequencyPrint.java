import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        HashMap<String, Integer> frequencymap = new HashMap<>();
        HashMap<Integer, ArrayList<String>> invertedmap = new HashMap<>();
        String[] strs = s.split(" ");
        int maxcount = 0;

        //develop forward frequency map
        for (int i = 0; i < strs.length; i++){
            int tmp = frequencymap.getOrDefault(strs[i], 0)+1;
            if (tmp > maxcount){
                maxcount = tmp;
            }
            frequencymap.put(strs[i], tmp);
        }
        System.out.println(maxcount);

        //develop inverted map
        for (Map.Entry<String, Integer> entry : frequencymap.entrySet()){
            ArrayList tmp = invertedmap.getOrDefault(entry.getValue(),new ArrayList<String>());
            tmp.add(entry.getKey());
            invertedmap.put(entry.getValue(), tmp);
        }

        //create new string
        StringBuilder finstring = new StringBuilder();

        for (int i = 1; i <= maxcount; i++){
            ArrayList<String> tmp = invertedmap.get(i);
            if (tmp != null) {
                for (String piece : tmp){
                    for (int repeater = 0; repeater < i; repeater++){
                        finstring.append(piece);
                        finstring.append(" ");
                    }
                }
            }
        }
        finstring.deleteCharAt(finstring.length()-1);

        return finstring.toString();
    }

}
