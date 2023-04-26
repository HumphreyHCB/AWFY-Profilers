import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore.Entry;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;



public class ProcessOutput {
    public static ArrayList<BenchMethod> dataSet = new ArrayList<BenchMethod>();

    public static void main(String[] args) {
        processFile("JSONDumps/output.json");
        processFile("JSONDumps/output2.json");
        processFile("JSONDumps/output3.json");
        processFile("JSONDumps/output4.json");
        processFile("JSONDumps/output5.json");
        processFile("JSONDumps/output6.json");
        processFile("JSONDumps/output7.json");
        processFile("JSONDumps/output8.json");
        processFile("JSONDumps/output9.json");
        processFile("JSONDumps/output10.json");

        HashMap<String,ArrayList<BenchMethod>> Map =  mapOccurrences(dataSet);
        HashMap<String,BenchReport> statmap = statisticize(Map);
        TreeMap<String,BenchReport> orderedStatmap = orderStatisticMap(statmap);
        printOrderStatisticMap(orderedStatmap);
        System.out.println();
    }
    
    private static String[][] processFile(String Filename) {

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

    public static HashMap<String,BenchReport> statisticize(HashMap<String,ArrayList<BenchMethod>> map) {
        HashMap<String,BenchReport> statisticMap = new HashMap<String,BenchReport>();
        for (String key : map.keySet()) {
            BenchReport report = new BenchReport(key);
            for (BenchMethod benchMethod : map.get(key)) {
                report.addPercentage(benchMethod.percentage);
            }
            statisticMap.put(key, report);
        }
        return statisticMap;
    }

    public static TreeMap<String,BenchReport> orderStatisticMap(HashMap<String,BenchReport> statisticMap ) {
        return new TreeMap<String,BenchReport>(statisticMap);       
    }

    

    public static void printOrderStatisticMap(TreeMap<String,BenchReport> OSM) {
        String currentFile = "";
        BenchReport currentReport;
        ArrayList<BenchReport> highlights = new ArrayList<BenchReport>();
        Double uncertainty = 0D;
        for (String key : OSM.keySet()) {
            currentReport = OSM.get(key);
            if (!currentReport.filename.equals(currentFile)) {
                currentFile = currentReport.filename;
                System.out.println("");
                System.out.println("Uncertainty : " + uncertainty);
                uncertainty = 0D;
                System.out.println("");
                System.out.println("");
                System.out.println("----------" + currentFile + "----------");
            }
            System.out.println("");
            System.out.print("Method : " + currentReport.method);
            if (30 - currentReport.method.length()> 0) {
                for (int i = 0; i < 40 - currentReport.method.length(); i++) {
                    System.out.print(" ");
                }
            }

            System.out.print("  Entries : " + currentReport.Percentages.size());
            System.out.print("  Average : " + new DecimalFormat("0.00").format(currentReport.getAverage()));
            System.out.print("  Min : " + currentReport.min);
            System.out.print("  Max : " + currentReport.max);
            String diff = new DecimalFormat("0.00").format(currentReport.max - currentReport.min);
            uncertainty += Double.parseDouble(diff);
            if (Double.parseDouble(diff) >= 5 ) {
                highlights.add(currentReport);
            }
            System.out.print("  Diff : " + new DecimalFormat("0.00").format(currentReport.max - currentReport.min));
            
        }
        System.out.println("");
        System.out.println("Uncertainty : " + uncertainty);

        System.out.println("");
        System.out.println("");
        System.out.println("----------- Highlights -----------");
        System.out.println(" all reports that have difference over 5%");

        for (BenchReport benchReport : highlights) {
            System.out.println("");
            System.out.print(" File : " + benchReport.filename);
            System.out.println("      Method : " + benchReport.method);
            System.out.println("        Entries : " + benchReport.Percentages.size());
            System.out.println("        Average : " + new DecimalFormat("0.00").format(benchReport.getAverage()));
            System.out.println("        Min : " + benchReport.min);
            System.out.println("        Max : " + benchReport.max);
            System.out.println("        Diff : " + new DecimalFormat("0.00").format(benchReport.max - benchReport.min));
        }
    }

}
class BenchMethod {

    public String filename;
    public String method;
    public String percentage;
    public String key;

    public BenchMethod(String filename, String method, String percentage) {
        this.filename = filename;
        this.method = method;
        this.percentage = percentage;
        key = filename + " " + method;
    }

    
}

class BenchReport {

    public String filename;
    public String method;
    public String key;
    public ArrayList<Double> Percentages;
    public Double min;
    public Double max;
    //public String percentage;

    public BenchReport(String key) {
        this.key = key;
        String[] split = key.split("\\s+",2);
        filename = split[0];
        method = split[1];
        Percentages = new ArrayList<Double>();
        min = 100d;
        max = 0d;
    }

    public void addPercentage(String Percentage)
    {
                // need to do some parseing
        Percentage = Percentage.replace("%", "");
        Double percent = 0d;
        try {
            percent = Double.parseDouble(Percentage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("one of the percentages is not vaild!");
            // TODO: handle exception
        }
        
        if (percent > max) {
            max = percent;
        }
        if (percent < min) {
            min = percent;
        }
        Percentages.add(percent);

    }

    public Double getAverage() {
        double total = 0;
        for (int i = 0; i < Percentages.size(); i++) {
            total+= Percentages.get(i);
        }
        return total/Percentages.size();
    }

    
}