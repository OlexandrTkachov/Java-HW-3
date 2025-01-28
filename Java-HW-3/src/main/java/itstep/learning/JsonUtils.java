package itstep.learning;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class JsonUtils {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveToJson(List<Object> objects, String filename) {
        try (Writer writer = new FileWriter(filename)) {
            gson.toJson(objects, writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Pair<String, List<String>>> categoryConversion(List<Object> objects) {
        List<Pair<String, List<String>>> result = new ArrayList<>();
        for (Object object : objects) {
            result.add(gson.fromJson(object.toString(), new TypeToken<Pair<String, List<String>>>() {}.getType()));
        }
        return result;
    }

//    public static List<Pair<String, List<String>>> productConversion(Object object) {
//        List<Pair<String, List<String>>> result = new ArrayList<>();
//        for (Object object : objects) {
//            //Map<String, List<String>> category = gson.fromJson(object.toString(), new TypeToken<Map<String, List<String>>>() {}.getType());
//            result.add(gson.fromJson(object.toString(), new TypeToken<Pair<String, List<String>>>() {}.getType()));
//        }
//        return result;
//    }

    public static String getCategory(List<Pair<String, List<String>>> categories, Map<String, String> fields) {
        String answer = "Product";
        for (Pair<String, List<String>> p : categories) {
            boolean contains = true;
            for (String s : p.getValue()) {
                if (!fields.containsKey(s)) {
                    contains = false;
                    break;
                }
            }
            if (contains) {
                answer = p.getKey();
                break;
            }
        }
        return answer;
    }

    public static List<Object> loadFromJson(String filename) {
        try (Reader reader = new FileReader(filename)) {
            return Arrays.asList(gson.fromJson(reader, Object[].class));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}