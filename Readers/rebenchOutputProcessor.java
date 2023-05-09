import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * rebenchOutputProcessor
 */
public class rebenchOutputProcessor {

    public static void main(String[] args) {
        //String prefix = "10";
        //HashMap<String,ArrayList<Double>> map = processFile("RebenchDump/example" + prefix + ".data");
        //"asyncTests CD"
        //"honest-profilerTests CD"
        //"JavaFlightRecorderTests Bounce"
        //printBenchmark(map, "honest-profilerTests", "Queens");
        //HashMap<String,Double> Map = getMedianExeTime(map);
        //appendRuntimesToJSON("Readers/JSONDumps/report3/output"+prefix+ ".json", Map);
        //System.out.println();

        // proccesDataDump("RebenchDump/example.data","Readers/JSONDumps/report3/output.json");
        // proccesDataDump("RebenchDump/example2.data","Readers/JSONDumps/report3/output2.json");
        // proccesDataDump("RebenchDump/example3.data","Readers/JSONDumps/report3/output3.json");
        // proccesDataDump("RebenchDump/example4.data","Readers/JSONDumps/report3/output4.json");
        // proccesDataDump("RebenchDump/example5.data","Readers/JSONDumps/report3/output5.json");
        // proccesDataDump("RebenchDump/example6.data","Readers/JSONDumps/report3/output6.json");
        // proccesDataDump("RebenchDump/example7.data","Readers/JSONDumps/report3/output7.json");
        // proccesDataDump("RebenchDump/example8.data","Readers/JSONDumps/report3/output8.json");
        // proccesDataDump("RebenchDump/example9.data","Readers/JSONDumps/report3/output9.json");
        // proccesDataDump("RebenchDump/example10.data","Readers/JSONDumps/report3/output10.json");

        // proccesDataDump("RebenchDump/example11.data","Readers/JSONDumps/report3/output11.json");
        // proccesDataDump("RebenchDump/example12.data","Readers/JSONDumps/report3/output12.json");
        // proccesDataDump("RebenchDump/example13.data","Readers/JSONDumps/report3/output13.json");
        // proccesDataDump("RebenchDump/example14.data","Readers/JSONDumps/report3/output14.json");
        // proccesDataDump("RebenchDump/example15.data","Readers/JSONDumps/report3/output15.json");
        // proccesDataDump("RebenchDump/example16.data","Readers/JSONDumps/report3/output16.json");
        // proccesDataDump("RebenchDump/example17.data","Readers/JSONDumps/report3/output17.json");
        // proccesDataDump("RebenchDump/example18.data","Readers/JSONDumps/report3/output18.json");
        // proccesDataDump("RebenchDump/example19.data","Readers/JSONDumps/report3/output19.json");
        // proccesDataDump("RebenchDump/example20.data","Readers/JSONDumps/report3/output20.json");

        // proccesDataDump("RebenchDump/example21.data","Readers/JSONDumps/report3/output21.json");
        // proccesDataDump("RebenchDump/example22.data","Readers/JSONDumps/report3/output22.json");
        // proccesDataDump("RebenchDump/example23.data","Readers/JSONDumps/report3/output23.json");
        // proccesDataDump("RebenchDump/example24.data","Readers/JSONDumps/report3/output24.json");
        // proccesDataDump("RebenchDump/example25.data","Readers/JSONDumps/report3/output25.json");
        // proccesDataDump("RebenchDump/example26.data","Readers/JSONDumps/report3/output26.json");
        // proccesDataDump("RebenchDump/example27.data","Readers/JSONDumps/report3/output27.json");
        // proccesDataDump("RebenchDump/example28.data","Readers/JSONDumps/report3/output28.json");
        // proccesDataDump("RebenchDump/example29.data","Readers/JSONDumps/report3/output29.json");
        // proccesDataDump("RebenchDump/example30.data","Readers/JSONDumps/report3/output30.json");


        //"asyncTests CD"
        //"honest-profilerTests CD"
        //"JavaFlightRecorderTests Bounce"
        //printBenchmark(map, "honest-profilerTests", "Queens");
        String profiler = "honest-profilerTests";
        String benchmark = "Queens";
        // printBenchmark(processFile("RebenchDump/example11.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example12.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example13.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example14.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example15.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example16.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example17.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example18.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example19.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example20.data"), profiler, benchmark);

        // printBenchmark(processFile("RebenchDump/example21.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example22.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example23.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example24.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example25.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example26.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example27.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example28.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example29.data"), profiler, benchmark);
        // printBenchmark(processFile("RebenchDump/example30.data"), profiler, benchmark);

        HashMap<String,ArrayList<Double>> dataset = new HashMap<String,ArrayList<Double>>();
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled1.data"), processFile("RebenchDump/example.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled1.data"), processFile("RebenchDump/example2.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled1.data"), processFile("RebenchDump/example3.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled1.data"), processFile("RebenchDump/example10.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled1.data"), processFile("RebenchDump/example11.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled1.data"), processFile("RebenchDump/example12.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled1.data"), processFile("RebenchDump/example20.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled1.data"), processFile("RebenchDump/example21.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled1.data"), processFile("RebenchDump/example22.data"), dataset);

        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled2.data"), processFile("RebenchDump/example.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled2.data"), processFile("RebenchDump/example2.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled2.data"), processFile("RebenchDump/example3.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled2.data"), processFile("RebenchDump/example10.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled2.data"), processFile("RebenchDump/example11.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled2.data"), processFile("RebenchDump/example12.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled2.data"), processFile("RebenchDump/example20.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled2.data"), processFile("RebenchDump/example21.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled2.data"), processFile("RebenchDump/example22.data"), dataset);

        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled3.data"), processFile("RebenchDump/example.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled3.data"), processFile("RebenchDump/example2.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled3.data"), processFile("RebenchDump/example3.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled3.data"), processFile("RebenchDump/example10.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled3.data"), processFile("RebenchDump/example11.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled3.data"), processFile("RebenchDump/example12.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled3.data"), processFile("RebenchDump/example20.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled3.data"), processFile("RebenchDump/example21.data"), dataset);
        dataset = compaireForOverhead(processFile("RebenchDump/nonprofiled3.data"), processFile("RebenchDump/example22.data"), dataset);
        TreeMap<String,Double> overhead = new TreeMap<String,Double>(getaverage(dataset));
        for (String key  : overhead.keySet()) {
            System.out.println(key + " " + new DecimalFormat("0.00").format(overhead.get(key)) + "%");
        }
    }

    public static HashMap<String,ArrayList<Double>> compaireForOverhead(HashMap<String,ArrayList<Double>> nonProfiled , HashMap<String,ArrayList<Double>> profiled, HashMap<String,ArrayList<Double>> dataset) {
        HashMap<String,Double> nonProfileddata = getMedianExeTime(nonProfiled);
        HashMap<String,Double> profileddata = getMedianExeTime(profiled);
        HashMap<String,Double> result = new HashMap<String,Double>();
        for (String key : profileddata.keySet()) {
            result.put(key, (profileddata.get(key) - nonProfileddata.get("Tests " + key.split("\\s+")[1]))  / nonProfileddata.get("Tests " + key.split("\\s+")[1]) * 100);
        }
        for (String key  : result.keySet()) {
            if (dataset.containsKey(key)) {
                dataset.get(key).add(result.get(key));
            }
            else{
                ArrayList<Double> array = new ArrayList<Double>();
                array.add(result.get(key));
                dataset.put(key, array );
            }
        }
        return dataset;
    }

    public static void proccesDataDump(String rebenchData, String Outputfile) {

        HashMap<String,ArrayList<Double>> map = processFile(rebenchData);
        HashMap<String,Double> Map = getMedianExeTime(map);
        appendRuntimesToJSON(Outputfile, Map);

    }

    private static void printBenchmark(HashMap<String,ArrayList<Double>> map, String profiler, String benchmark) {
        System.out.println(profiler + " " + benchmark);
        for (Double runtime : map.get(profiler + " " + benchmark)) {
            System.out.print(runtime + " ");
        }
    }

    private static void bulkPrint(ArrayList<HashMap<String,ArrayList<Double>>> mapofMaps, String profiler, String benchmark) {
        for (int i = 0; i < mapofMaps.size(); i++) {

        }
    }

    private static void appendRuntimesToJSON(String Filename, HashMap<String,Double> Map) {
        JSONObject file;
        HashMap<String,Double> normalisedMap = new HashMap<String,Double>();

        for (String key : Map.keySet()) {
            String[] split = key.split("\\s+");
            String benchmark = split[1];
            String profiler = split[0];
            if (profiler.contains("JavaFlightRecorderTests")) {
                normalisedMap.put("JavaFlightRecorder_"+benchmark+".jfr", Map.get(key));
            }else if (profiler.contains("asyncTests")) {
                normalisedMap.put("rebench_test_Async_"+benchmark + ".txt", Map.get(key));
            }else if (profiler.contains("honest-profilerTests")) {
                normalisedMap.put(benchmark + ".hpl", Map.get(key));
            } else {
                System.out.println(profiler + " " + benchmark + " was removed");
            } 

        }        

        try {
            file = new JSONObject(new String(Files.readAllBytes(Paths.get(Filename)), StandardCharsets.UTF_8));
            file.put("Runtimes", new JSONObject(normalisedMap));
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

    private static HashMap<String,Double> getMedianExeTime(HashMap<String,ArrayList<Double>> map) {
        HashMap<String,Double> Map = new HashMap<String,Double>();
        for (String key : map.keySet()) {
            ArrayList<Double> AL = map.get(key);
            Collections.sort(AL);
            Double Median = (AL.get(AL.size()/2) +AL.get((AL.size()/2)-1) )/2;
            Map.put(key, Median);
        }
        return Map;
    }

    private static HashMap<String,Double> getaverage(HashMap<String,ArrayList<Double>> map) {
        HashMap<String,Double> Map = new HashMap<String,Double>();
        for (String key : map.keySet()) {
            ArrayList<Double> AL = map.get(key);
            Double total = 0.0;
            for (Double double1 : AL) {
                total+= double1;
            }
            Map.put(key, total/AL.size());
        }
        return Map;
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