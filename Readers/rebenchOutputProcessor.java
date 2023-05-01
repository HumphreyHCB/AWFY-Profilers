import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * rebenchOutputProcessor
 */
public class rebenchOutputProcessor {

    public static void main(String[] args) {
        HashMap<String,ArrayList<Double>> map = processFile("example5.data");
        HashMap<String,Double> averageMap = getAverageExeTime(map);
        appendRuntimesToJSON("JSONDumps/output5.json", averageMap);
        System.out.println();

    }

    private static void appendRuntimesToJSON(String Filename, HashMap<String,Double> averageMap) {
        JSONObject file;
        HashMap<String,Double> normalisedAverageMap = new HashMap<String,Double>();

        for (String key : averageMap.keySet()) {
            String[] split = key.split("\\s+");
            String benchmark = split[1];
            String profiler = split[0];
            if (profiler.contains("JavaFlightRecorderTests")) {
                normalisedAverageMap.put("JavaFlightRecorder_"+benchmark+".jfr", averageMap.get(key));
            }else if (profiler.contains("asyncTests")) {
                normalisedAverageMap.put("rebench_test_Async_"+benchmark + ".txt", averageMap.get(key));
            }else if (profiler.contains("honest-profilerTests")) {
                normalisedAverageMap.put(benchmark + ".hpl", averageMap.get(key));
            } else {
                System.out.println(profiler + " " + benchmark + " was removed");
            } 

        }        

        try {
            file = new JSONObject(new String(Files.readAllBytes(Paths.get(Filename)), StandardCharsets.UTF_8));
            file.put("Runtimes", new JSONObject(normalisedAverageMap));
            WriteJSONObject(file, Filename);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }

    private static void WriteJSONObject(JSONObject obj, String filename) {
        try (FileWriter file = new FileWriter(filename, false)) {
            try {
                file.write(obj.toString(4));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static HashMap<String,Double> getAverageExeTime(HashMap<String,ArrayList<Double>> map) {
        HashMap<String,Double> averageMap = new HashMap<String,Double>();
        for (String key : map.keySet()) {
            ArrayList<Double> AL = map.get(key);
            Double total = 0.0;
            for (Double double1 : AL) {
                total+= double1;
            }
            averageMap.put(key, total/AL.size());
        }
        return averageMap;
    }

    private static  HashMap<String,ArrayList<Double>> processFile(String Filename) {
        HashMap<String,ArrayList<Double>> map = new  HashMap<String,ArrayList<Double>>();

        try {
            File myObj = new File(Filename);
            Scanner myReader = new Scanner(myObj);

            int iteration = 0;
            Double exeTime = 0.0;
            String benchmark = "";
            String profiler = "";
            while (myReader.hasNextLine()) {
                if (myReader.nextLine().startsWith("# Source:")) {
                    while (myReader.hasNextLine()) {
                        String[] split = myReader.nextLine().split("\\s+");
                         iteration = Integer.parseInt(split[1]);
                         exeTime = Double.parseDouble(split[2]);
                         benchmark = split[5];
                         profiler = split[7];

                         String key = profiler + " " + benchmark;

                         if (map.containsKey(key)) {
                            map.get(key).add(exeTime);
                            //heatMap.put(key, heatMap.get(key).add(i));
                        }
                        else{
                            ArrayList<Double> array = new ArrayList<Double>();
                            array.add(exeTime);
                            map.put(key, array );
                        }
                    }
                    
                }
             
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        return map;
    }


}