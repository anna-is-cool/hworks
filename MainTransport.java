import com.fasterxml.jackson.databind.ObjectMapper;
import Programming.Transoprt.model.TrasportDataBase;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainTransport {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TrasportDataBase dataBase =
                mapper.readValue(new File("transport1.json"), TrasportDataBase.class);

        Set<String> set = allTypes(dataBase);
        System.out.println(Arrays.toString(set.toArray()));


        System.out.println(countOfTr("8", "trolleybus", dataBase));


        Map<String, Map<String, Integer>> map = countOfTrs(dataBase);
        map.entrySet().stream().forEach((a) -> {
            System.out.print(a.getKey() + " {");
            a.getValue().entrySet().stream().forEach((b) -> {
                System.out.print("\"" + b.getKey() + "\":" + b.getValue() + "  ");
            });
            System.out.println("}");
        });

        Map<Vehicle, Integer> mapForTask3 = countOfCoord(dataBase);
    }

    public static Map<Vehicle, Integer> countOfCoord(TrasportDataBase dataBase){
        Map<Vehicle,Integer> map = new HashMap<>();
        dataBase.getData().getVehicles().forEach((a) -> {
            if (!map.containsKey(a)){
                map.put(a, 0);
            }

        });
        return map;
    }

    public static Set<String> allTypes(TrasportDataBase dataBase){
        Set<String> set = new HashSet<>();
        dataBase.getData().getVehicles().stream().forEach((a) -> set.add(a.getProperties().getVehicleMetaData().getTransport().getType()));
        return set;
    }

    public static Map<String, Map<String, Integer>> countOfTrs(TrasportDataBase dataBase){
        Set<String> allTrs = allTypes(dataBase);
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for(String str : allTrs){
            map.put(str, new HashMap<String,Integer>());
        }
        dataBase.getData().
                getVehicles().
                stream().
                forEach((a) -> {
                    Map<String,Integer> temp = map.get(a.getProperties().getVehicleMetaData().getTransport().getType());
                    String number = a.getProperties().getVehicleMetaData().getTransport().getName();
                    if (!temp.containsKey(number)){
                        temp.put(number, 1);
                    }
                    else{
                        temp.put(number, temp.get(number) + 1);
                    }
                }
        );
        return map;
    }

    public static int countOfTr(String numb, String type, TrasportDataBase dataBase){
        List<String> list = new ArrayList<>();


        dataBase.getData().getVehicles().stream().forEach((a) -> {
            if (a.getProperties().
                    getVehicleMetaData().
                    getTransport().
                    getName().
                    equals(numb))
                        list.add(a.getProperties().
                        getVehicleMetaData().
                        getTransport().
                        getType());
        });

        int count = 0;
        for (String str : list){
            if (str.equals(type)){
                count++;
            }
        }
        return count;
    }
}
