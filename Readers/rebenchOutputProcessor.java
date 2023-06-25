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
    static Double golbalmin = 1000000000D;
    static Double golbalmax = 0D;
    static Double globaltotal = 0D;
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

        // proccesDataDump("datapoint1.data","Readers/FlagReport.json");
        // proccesDataDump("datapoint2.data","Readers/FlagReport2.json");
        //proccesDataDump("First1ms.data","Readers/JSONDumps/report5/output.json");
        // proccesBulkDataDump("BatchRun1ms.data", "Readers/JSONDumps/report5/output2.json", 2);
        // proccesBulkDataDump("BatchRun1ms.data", "Readers/JSONDumps/report5/output3.json", 3);

        // proccesBulkDataDump("BatchRun1ms.data", "Readers/JSONDumps/report5/output4.json", 4);
        // proccesBulkDataDump("BatchRun1ms.data", "Readers/JSONDumps/report5/output5.json", 5);

        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output6.json",6);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output7.json",7);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output8.json",8);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output9.json",9);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output10.json",10);

        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output11.json",11);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output12.json",12);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output13.json",13);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output14.json",14);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output15.json",15);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output16.json",16);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output17.json",17);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output18.json",18);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output19.json",19);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output20.json",20);

        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output21.json",21);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output22.json",22);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output23.json",23);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output24.json",24);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output25.json",25);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output26.json",26);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output27.json",27);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output28.json",28);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output29.json",29);
        // proccesBulkDataDump("BatchRun1ms.data","Readers/JSONDumps/report5/output30.json",30);

        // proccesDataDump("RebenchDump/data.data","Readers/JSONDumps/report4/output.json");
        // proccesDataDump("RebenchDump/data2.data","Readers/JSONDumps/report4/output2.json");
        // proccesDataDump("RebenchDump/data3.data","Readers/JSONDumps/report4/output3.json");
        // proccesDataDump("RebenchDump/data4.data","Readers/JSONDumps/report4/output4.json");
        // proccesDataDump("RebenchDump/data5.data","Readers/JSONDumps/report4/output5.json");
        // proccesDataDump("RebenchDump/data6.data","Readers/JSONDumps/report4/output6.json");
        // proccesDataDump("RebenchDump/data7.data","Readers/JSONDumps/report4/output7.json");
        // proccesDataDump("RebenchDump/data8.data","Readers/JSONDumps/report4/output8.json");
        // proccesDataDump("RebenchDump/data9.data","Readers/JSONDumps/report4/output9.json");
        // proccesDataDump("RebenchDump/data10.data","Readers/JSONDumps/report4/output10.json");

        // proccesDataDump("RebenchDump/data11.data","Readers/JSONDumps/report4/output11.json");
        // proccesDataDump("RebenchDump/data12.data","Readers/JSONDumps/report4/output12.json");
        // proccesDataDump("RebenchDump/data13.data","Readers/JSONDumps/report4/output13.json");
        // proccesDataDump("RebenchDump/data14.data","Readers/JSONDumps/report4/output14.json");
        // proccesDataDump("RebenchDump/data15.data","Readers/JSONDumps/report4/output15.json");
        // proccesDataDump("RebenchDump/data16.data","Readers/JSONDumps/report4/output16.json");
        // proccesDataDump("RebenchDump/data17.data","Readers/JSONDumps/report4/output17.json");
        // proccesDataDump("RebenchDump/data18.data","Readers/JSONDumps/report4/output18.json");
        // proccesDataDump("RebenchDump/data19.data","Readers/JSONDumps/report4/output19.json");
        // proccesDataDump("RebenchDump/data20.data","Readers/JSONDumps/report4/output20.json");

        // proccesDataDump("RebenchDump/data21.data","Readers/JSONDumps/report4/output21.json");
        // proccesDataDump("RebenchDump/data22.data","Readers/JSONDumps/report4/output22.json");
        // proccesDataDump("RebenchDump/data23.data","Readers/JSONDumps/report4/output23.json");
        // proccesDataDump("RebenchDump/data24.data","Readers/JSONDumps/report4/output24.json");
        // proccesDataDump("RebenchDump/data25.data","Readers/JSONDumps/report4/output25.json");
        // proccesDataDump("RebenchDump/data26.data","Readers/JSONDumps/report4/output26.json");
        // proccesDataDump("RebenchDump/data27.data","Readers/JSONDumps/report4/output27.json");
        // proccesDataDump("RebenchDump/data28.data","Readers/JSONDumps/report4/output28.json");
        // proccesDataDump("RebenchDump/data29.data","Readers/JSONDumps/report4/output29.json");
        // proccesDataDump("RebenchDump/data30.data","Readers/JSONDumps/report4/output30.json");


        //"asyncTests CD"
        //"honest-profilerTests CD"
        //"JavaFlightRecorderTests Bounce"
        //printBenchmark(map, "honest-profilerTests", "Queens");
        String profiler = "NonProfiledTests";
        String benchmark = "Towers";


        // getMinAvgMax(processFile("RebenchDump/data.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data2.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data3.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data4.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data5.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data6.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data7.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data8.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data9.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data10.data"), profiler, benchmark);


        // getMinAvgMax(processFile("RebenchDump/data11.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data12.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data13.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data14.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data15.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data16.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data17.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data18.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data19.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data20.data"), profiler, benchmark);

        // getMinAvgMax(processFile("RebenchDump/data21.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data22.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data23.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data24.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data25.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data26.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data27.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data28.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data29.data"), profiler, benchmark);
        // getMinAvgMax(processFile("RebenchDump/data30.data"), profiler, benchmark);

        // System.out.println("" + golbalmin + " " + globaltotal/30  + " " + golbalmax);

         HashMap<String,ArrayList<Double>> dataset = new HashMap<String,ArrayList<Double>>();

         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 2), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 3), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 4), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 5), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 6), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 7), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 8), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 9), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 10), dataset);

         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 11), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 12), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 13), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 14), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 15), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 16), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 17), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 18), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 19), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 20), dataset);

         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 21), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 22), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 23), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 24), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 25), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 26), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 27), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 28), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 29), dataset);
         dataset = compaireForOverhead(processSpecificBulkFile("Combind.data", 30), dataset);





        //dataset = compaireForOverhead(processFile("RebenchDump/data.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data2.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data3.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data4.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data5.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data6.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data7.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data8.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data9.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data10.data"), dataset);

        // dataset = compaireForOverhead(processFile("RebenchDump/data11.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data12.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data13.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data14.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data15.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data16.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data17.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data18.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data19.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data20.data"), dataset);

        // dataset = compaireForOverhead(processFile("RebenchDump/data21.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data22.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data23.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data24.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data25.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data26.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data27.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data28.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data29.data"), dataset);
        // dataset = compaireForOverhead(processFile("RebenchDump/data30.data"), dataset);


        // dataset = (processFile("RebenchDump/data.data"));
        // dataset = (processFile("RebenchDump/data2.data"));
        // dataset = (processFile("RebenchDump/data3.data"));
        // dataset = (processFile("RebenchDump/data4.data"));
        // dataset = (processFile("RebenchDump/data5.data"));
        // dataset = (processFile("RebenchDump/data6.data"));
        // dataset = (processFile("RebenchDump/data7.data"));
        // dataset = (processFile("RebenchDump/data8.data"));
        // dataset = (processFile("RebenchDump/data9.data"));
        // dataset = (processFile("RebenchDump/data10.data"));

        // dataset = (processFile("RebenchDump/data11.data"));
        // dataset = (processFile("RebenchDump/data12.data"));
        // dataset = (processFile("RebenchDump/data13.data"));
        // dataset = (processFile("RebenchDump/data14.data"));
        // dataset = (processFile("RebenchDump/data15.data"));
        // dataset = (processFile("RebenchDump/data16.data"));
        // dataset = (processFile("RebenchDump/data17.data"));
        // dataset = (processFile("RebenchDump/data18.data"));
        // dataset = (processFile("RebenchDump/data19.data"));
        // dataset = (processFile("RebenchDump/data20.data"));

        // dataset = (processFile("RebenchDump/data21.data"));
        // dataset = (processFile("RebenchDump/data22.data"));
        // dataset = (processFile("RebenchDump/data23.data"));
        // dataset = (processFile("RebenchDump/data24.data"));
        // dataset = (processFile("RebenchDump/data25.data"));
        // dataset = (processFile("RebenchDump/data26.data"));
        // dataset = (processFile("RebenchDump/data27.data"));
        // dataset = (processFile("RebenchDump/data28.data"));
        // dataset = (processFile("RebenchDump/data29.data"));
        // dataset = (processFile("RebenchDump/data30.data"));

        TreeMap<String,Double> overhead = new TreeMap<String,Double>(getaverage(dataset));
        for (String key  : overhead.keySet()) {
            System.out.println(key + " " + new DecimalFormat("0.00").format(overhead.get(key)) + "%");
        }
    }

    public static HashMap<String,ArrayList<Double>> compaireForOverhead( HashMap<String,ArrayList<Double>> profiled, HashMap<String,ArrayList<Double>> dataset) {
        HashMap<String,Double> profileddata = getMedianExeTime(profiled);
        HashMap<String,Double> result = new HashMap<String,Double>();
        for (String key : profileddata.keySet()) {
            result.put(key, (profileddata.get(key) - profileddata.get("NonProfiledTests " + key.split("\\s+")[1]))  / profileddata.get("NonProfiledTests " + key.split("\\s+")[1]) * 100);
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

    public static void proccesBulkDataDump(String rebenchData, String Outputfiledir, int fileIte) {

        HashMap<String,ArrayList<Double>> map = processBulkFile(rebenchData);
        HashMap<String,Double> Map = getMedianExeTime(map);
        appendBulkRuntimesToJSON(Outputfiledir, Map, fileIte);

    }

    private static void printBenchmark(HashMap<String,ArrayList<Double>> map, String profiler, String benchmark) {
        System.out.println(profiler + " " + benchmark);
        for (Double runtime : map.get(profiler + " " + benchmark)) {
            System.out.print(runtime + " ");
        }
    }

    private static void getMinAvgMax(HashMap<String,ArrayList<Double>> map, String profiler, String benchmark) {
        Double min = 1000000000D;
        Double max = 0D;
        Double total = 0D;
        for (Double runtime : map.get(profiler + " " + benchmark)) {
            if (runtime > max) {
                max = runtime;
            }
            if (runtime < min) {
                min = runtime;
            }
            total = runtime + total;

        }
        if (max > golbalmax) {
            golbalmax = max;
        }
        if (golbalmin > min) {
            golbalmin = min;
        }

        globaltotal+=total/300;
        //System.out.println("Min : " + min + " Avg : " + total/300  + " Max: " + max);
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
            }else if (profiler.contains("NonProfiledTests")) {
                normalisedMap.put(benchmark + "_not_profiled_", Map.get(key));
            }else if (profiler.contains("PerfTests")) {
                normalisedMap.put(benchmark + ".data", Map.get(key));
            }else if (profiler.contains("YourKitTests")) {
                normalisedMap.put("YourKit_"+benchmark + ".txt", Map.get(key));
            }else if (profiler.contains("JProfilerTests")) {
                normalisedMap.put("JProfiler_" + benchmark + ".txt", Map.get(key));
            } else {
                System.out.println(profiler + " " + benchmark + " was not added to the json");
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

    private static void appendBulkRuntimesToJSON(String Filename, HashMap<String,Double> Map, int File_ite) {
        JSONObject file;
        HashMap<String,Double> normalisedMap = new HashMap<String,Double>();
        for (String key : Map.keySet()) {
            String[] split = key.split("\\s+");
            String benchmark = split[2];
            String profiler = split[1];
            int var_ite = Integer.parseInt(split[0]);

            if (File_ite != var_ite) {
                continue;
            }

            if (profiler.contains("JavaFlightRecorderTests")) {
                normalisedMap.put("JavaFlightRecorder_"+benchmark+".jfr", Map.get(key));
            }else if (profiler.contains("asyncTests")) {
                normalisedMap.put("rebench_test_Async_"+benchmark + ".txt", Map.get(key));
            }else if (profiler.contains("honest-profilerTests")) {
                normalisedMap.put(benchmark + ".hpl", Map.get(key));
            }else if (profiler.contains("NonProfiledTests")) {
                normalisedMap.put(benchmark + "_not_profiled_", Map.get(key));
            }else if (profiler.contains("PerfTests")) {
                normalisedMap.put(benchmark + ".data", Map.get(key));
            }else if (profiler.contains("YourKitTests")) {
                normalisedMap.put("YourKit_"+benchmark + ".txt", Map.get(key));
            }else if (profiler.contains("JProfilerTests")) {
                normalisedMap.put("JProfiler_" + benchmark + ".txt", Map.get(key));
            } else {
                System.out.println(profiler + " " + benchmark + " was not added to the json");
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

    private static HashMap<String,Double> getmin(HashMap<String,ArrayList<Double>> map) {
        HashMap<String,Double> Map = new HashMap<String,Double>();
        for (String key : map.keySet()) {
            ArrayList<Double> AL = map.get(key);
            Double lowest = 1000000000D;
            for (Double double1 : AL) {
                if (double1 < lowest) {
                    lowest = double1;
                }
            }
            Map.put(key, lowest);
        }
        return Map;
    }

    private static HashMap<String,Double> getMax(HashMap<String,ArrayList<Double>> map) {
        HashMap<String,Double> Map = new HashMap<String,Double>();
        for (String key : map.keySet()) {
            ArrayList<Double> AL = map.get(key);
            Double max = 0d;
            for (Double double1 : AL) {
                if (double1 > max) {
                    max = double1;
                }
            }
            Map.put(key, max);
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
            int var_iteration = 0;
            while (myReader.hasNextLine()) {
                if (myReader.nextLine().startsWith("# Source:")) {
                    while (myReader.hasNextLine()) {
                        String[] split = myReader.nextLine().split("\\s+");
                         iteration = Integer.parseInt(split[1]);
                         exeTime = Double.parseDouble(split[2]);
                         benchmark = split[5];
                         profiler = split[7];
                         //var_iteration =  Integer.parseInt(split[10]);

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

    private static  HashMap<String,ArrayList<Double>> processBulkFile(String Filename) {
        HashMap<String,ArrayList<Double>> map = new  HashMap<String,ArrayList<Double>>();

        try {
            File myObj = new File(Filename);
            Scanner myReader = new Scanner(myObj);

            int iteration = 0;
            Double exeTime = 0.0;
            String benchmark = "";
            String profiler = "";
            int var_iteration = 0;
            while (myReader.hasNextLine()) {
                if (myReader.nextLine().startsWith("# Source:")) {
                    while (myReader.hasNextLine()) {
                        String[] split = myReader.nextLine().split("\\s+");
                         iteration = Integer.parseInt(split[1]);
                         exeTime = Double.parseDouble(split[2]);
                         benchmark = split[5];
                         profiler = split[7];
                         var_iteration =  Integer.parseInt(split[10]);

                         String key = var_iteration + " " +profiler + " " + benchmark;

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

    private static  HashMap<String,ArrayList<Double>> processSpecificBulkFile(String Filename, int Specificiteration) {
        HashMap<String,ArrayList<Double>> map = new  HashMap<String,ArrayList<Double>>();

        try {
            File myObj = new File(Filename);
            Scanner myReader = new Scanner(myObj);

            int iteration = 0;
            Double exeTime = 0.0;
            String benchmark = "";
            String profiler = "";
            int var_iteration = 0;
            while (myReader.hasNextLine()) {
                if (myReader.nextLine().startsWith("# Source:")) {
                    while (myReader.hasNextLine()) {
                        String[] split = myReader.nextLine().split("\\s+");
                         iteration = Integer.parseInt(split[1]);
                         exeTime = Double.parseDouble(split[2]);
                         benchmark = split[5];
                         profiler = split[7];
                         var_iteration =  Integer.parseInt(split[10]);
                        if(var_iteration == Specificiteration){

                        }
                        else{
                            continue;
                        }
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