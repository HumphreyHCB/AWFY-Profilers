import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;



public class ProcessOutput {
    public static ArrayList<BenchMethod> dataSet = new ArrayList<BenchMethod>();

    public static void main(String[] args) {
        process("output.json");
        process("output2.json");
        HashMap<String,ArrayList<BenchMethod>> Map =  mapOccurrences(dataSet);
        System.out.println();
    }
    
    private static String[][] process(String Filename) {

        try {
            JSONObject file = new JSONObject(new String(Files.readAllBytes(Paths.get(Filename)), StandardCharsets.UTF_8));
            JSONObject JavaFlightRecorder = file.getJSONObject("JavaFlightRecorder");
            JSONObject HonestProfiler = file.getJSONObject("HonestProfiler");
            JSONObject Async = file.getJSONObject("Async");
            addProfilerToDataset(Async);
            addProfilerToDataset(HonestProfiler);
            addProfilerToDataset(JavaFlightRecorder);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return new String[5][2];
    }

    private static void addProfilerToDataset(JSONObject profiler) {
        JSONArray filenames = profiler.names();
        for (int i = 0; i < filenames.length(); i++) {
            String file = filenames.get(i).toString();
            JSONObject benchmarks = (JSONObject) profiler.get(file);
            JSONArray methods = benchmarks.names();
            for (int j = 0; j < methods.length() ; j++) {
                String methodName = methods.get(j).toString();
                String percentage = benchmarks.get(methodName).toString();
                dataSet.add(new BenchMethod(file, methodName, percentage));
                                
            }
            
        }

        System.out.println();
    }


    public static HashMap<String,ArrayList<BenchMethod>> mapOccurrences(ArrayList<BenchMethod> collection) {
        HashMap<String,ArrayList<BenchMethod>> heatMap = new HashMap<>();
        for (BenchMethod i : collection) {
            String key = i.filename + " " + i.method;
            if (heatMap.containsKey(key)) {
                heatMap.get(key).add(i);
                //heatMap.put(key, heatMap.get(key).add(i));
            }
            else{
                ArrayList<BenchMethod> array = new ArrayList<BenchMethod>();
                array.add(i);
                heatMap.put(key, array );
            }
        }
        collection.clear(); // test of the collecetions speed of clearing
        return heatMap;
    }

}
class BenchMethod {

    public String filename;
    public String method;
    public String percentage;

    public BenchMethod(String filename, String method, String percentage) {
        this.filename = filename;
        this.method = method;
        this.percentage = percentage;
    }

    
}