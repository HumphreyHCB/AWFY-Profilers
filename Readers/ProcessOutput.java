import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
        processFile("Readers/JSONDumps/report3/output.json");
        processFile("Readers/JSONDumps/report3/output2.json");
        processFile("Readers/JSONDumps/report3/output3.json");
        processFile("Readers/JSONDumps/report3/output4.json");
        processFile("Readers/JSONDumps/report3/output5.json");
        processFile("Readers/JSONDumps/report3/output6.json");
        processFile("Readers/JSONDumps/report3/output7.json");
        processFile("Readers/JSONDumps/report3/output8.json");
        processFile("Readers/JSONDumps/report3/output9.json");
        processFile("Readers/JSONDumps/report3/output10.json");
        processFile("Readers/JSONDumps/report3/output11.json");
        processFile("Readers/JSONDumps/report3/output12.json");
        processFile("Readers/JSONDumps/report3/output13.json");
        processFile("Readers/JSONDumps/report3/output14.json");
        processFile("Readers/JSONDumps/report3/output15.json");
        processFile("Readers/JSONDumps/report3/output16.json");
        processFile("Readers/JSONDumps/report3/output17.json");
        processFile("Readers/JSONDumps/report3/output18.json");
        processFile("Readers/JSONDumps/report3/output19.json");
        processFile("Readers/JSONDumps/report3/output20.json");
        processFile("Readers/JSONDumps/report3/output21.json");
        processFile("Readers/JSONDumps/report3/output22.json");
        processFile("Readers/JSONDumps/report3/output23.json");
        processFile("Readers/JSONDumps/report3/output24.json");
        processFile("Readers/JSONDumps/report3/output25.json");
        processFile("Readers/JSONDumps/report3/output26.json");
        processFile("Readers/JSONDumps/report3/output27.json");
        processFile("Readers/JSONDumps/report3/output28.json");
        processFile("Readers/JSONDumps/report3/output29.json");
        processFile("Readers/JSONDumps/report3/output30.json");


        HashMap<String,ArrayList<BenchMethod>> Map =  mapOccurrences(dataSet);
         HashMap<String,BenchReport> statmap = statisticize(Map);
         TreeMap<String,BenchReport> orderedStatmap = orderStatisticMap(statmap);
         printOrderStatisticMap(orderedStatmap);
        System.out.println();

        //mapOccurrencesOnMethodAndPrint(dataSet);
        //DebugmapOccurrences(dataSet);
    }
    
    private static String[][] processFile(String path) {

        try {
            JSONObject file = new JSONObject(new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8));
            JSONObject JavaFlightRecorder = file.getJSONObject("JavaFlightRecorder");
            JSONObject HonestProfiler = file.getJSONObject("HonestProfiler");
            JSONObject Async = file.getJSONObject("Async");
            JSONObject Runtimes = file.getJSONObject("Runtimes");
            addProfilerToDataset(Async, Runtimes, "Async", path);
            addProfilerToDataset(HonestProfiler , Runtimes, "HonestProfiler", path);
            addProfilerToDataset(JavaFlightRecorder , Runtimes, "JavaFlightRecorder", path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return new String[5][2];
    }

    private static void addProfilerToDataset(JSONObject profiler,JSONObject Runtimes, String profilerName, String path) {
        JSONArray filenames = profiler.names();
        for (int i = 0; i < filenames.length(); i++) {
            String file = filenames.get(i).toString();
            JSONObject benchmarks = (JSONObject) profiler.get(file);
            JSONArray methods = benchmarks.names();
            for (int j = 0; j < methods.length() ; j++) {
                String methodName = methods.get(j).toString();
                String percentage = benchmarks.get(methodName).toString();
                BigDecimal Runtime =  (BigDecimal) Runtimes.get(file);
                dataSet.add(new BenchMethod(file, normaliseMethodName(methodName, profilerName), percentage, Runtime.doubleValue(), path));
                                
            }
            
        }

        System.out.println();
    }

    public static String normaliseMethodName(String MethodName, String Profiler) {
        String normalisedMethodName = "";
        switch (Profiler) {
            case "Async":
                normalisedMethodName = MethodName;
                break;
            case "HonestProfiler":
            normalisedMethodName = MethodName.replace("::", ".");
                break;
            case "JavaFlightRecorder":
            normalisedMethodName = MethodName.split("\\(")[0];
                break;
        
            default:
            normalisedMethodName = MethodName;
                break;
        }

        if (normalisedMethodName.contains("$$Lambda")) {
            normalisedMethodName = normalisedMethodName.split("\\$\\$Lambda")[0];
        }

        if (normalisedMethodName.isEmpty()) {
            return MethodName;
        }
        return normalisedMethodName;
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
        
        return heatMap;
    }

    public static void mapOccurrencesOnMethodAndPrint(ArrayList<BenchMethod> collection) {
        HashMap<String,ArrayList<BenchMethod>> heatMap = new HashMap<>();
        for (BenchMethod i : collection) {
            String key = i.method;
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

        HashMap<String,BenchReportOnMethod> statisticMap = new HashMap<String,BenchReportOnMethod>();
        for (String key : heatMap.keySet()) {
            BenchReportOnMethod report = new BenchReportOnMethod(key);
            for (BenchMethod benchMethod : heatMap.get(key)) {
                report.addBenchMethod(benchMethod);
            }
            statisticMap.put(key, report);
        }
        
        for (String Method : statisticMap.keySet()) {
            if (statisticMap.get(Method).BenchMethods.size() < 10) {
                continue;
            }
            System.out.println("");
            System.out.println("----------" + Method + "----------");
            System.out.println("");
            for (BenchMethod benchMethod : statisticMap.get(Method).BenchMethods) {
                
                System.out.print(" Percentage: " +  new DecimalFormat("0.00").format(Double.parseDouble(benchMethod.percentage)));
                System.out.print("      Runtime: " + new DecimalFormat("0.00").format(benchMethod.Runtime));
                System.out.print(" File: " + benchMethod.filename);
                System.out.println("");
            }
            System.out.println("");
            System.out.println("Async Average: "+ statisticMap.get(Method).getAverage("Async"));
            System.out.println("HonestProfiler Average: "+ statisticMap.get(Method).getAverage("HonestProfiler"));
            System.out.println("JavaFlightRecorder Average: "+ statisticMap.get(Method).getAverage("JavaFlightRecorder"));
            System.out.println("");
            System.out.println("Min: "+ statisticMap.get(Method).min);
            System.out.println("Max: "+ statisticMap.get(Method).max);
            System.out.println("Diff: "+ (statisticMap.get(Method).max - statisticMap.get(Method).min));
        }
    }


    public static HashMap<String,Integer> DebugmapOccurrences(ArrayList<BenchMethod> collection) {
        HashMap<String,Integer> heatMap = new HashMap<>();
        for (BenchMethod i : collection) {
            if (heatMap.containsKey(i.method)) {
                heatMap.put(i.method, heatMap.get(i.method) + 1);

            }
            else{
                heatMap.put(i.method, 1 );
            }
        }
        int total = 0;
        int occurrences = 0;
        HashMap<String, Integer> highlights = new HashMap<String, Integer>();
        for (String method : heatMap.keySet()) {
            total+= 1;
            System.out.println(method + " " + heatMap.get(method));
            if (heatMap.get(method) <= 1) {
                highlights.put(method, heatMap.get(method));
                occurrences++;
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        for (String methoda : highlights.keySet()) {

            System.out.println(methoda + " " + heatMap.get(methoda));

        }
        System.out.println("occurrences: "+ occurrences);
        System.out.println("Total: "+ total);
        return heatMap;
    }

    public static HashMap<String,BenchReport> statisticize(HashMap<String,ArrayList<BenchMethod>> map) {
        HashMap<String,BenchReport> statisticMap = new HashMap<String,BenchReport>();
        for (String key : map.keySet()) {
            BenchReport report = new BenchReport(key);
            for (BenchMethod benchMethod : map.get(key)) {
                report.addPercentage(benchMethod.percentage, benchMethod.Runtime, benchMethod.Path);
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
                System.out.println("Total Diff : " + uncertainty);
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
            System.out.println("        Average (%) : " + new DecimalFormat("0.00").format(benchReport.getAverage()) + "    Average Runtime: " + new DecimalFormat("0.00").format(benchReport.getAverageRuntime()));
            System.out.println("        Min (%) : " + benchReport.min + "       Runtime: " + benchReport.Runtimes.get(benchReport.Percentages.indexOf(benchReport.min)));
            System.out.println("        Max (%) : " + benchReport.max + "       Runtime: " + benchReport.Runtimes.get(benchReport.Percentages.indexOf(benchReport.max)));
            System.out.println("        Diff (%) : " + new DecimalFormat("0.00").format(benchReport.max - benchReport.min)  + "     Runtime Diff: " +  (benchReport.Runtimes.get(benchReport.Percentages.indexOf(benchReport.max)) - benchReport.Runtimes.get(benchReport.Percentages.indexOf(benchReport.min))) );
            System.out.println("");
            
            System.out.println("        Runtime Greatest Diff : " + benchReport.getBigestDiffinRuntime());
            System.out.println("");
             for (int i = 0; i < benchReport.Percentages.size(); i++) {
                //System.out.println("Percentage: " + benchReport.Percentages.get(i) + " Runtime: " + benchReport.Runtimes.get(i) + " Path: " + benchReport.paths.get(i));
             System.out.print(benchReport.Percentages.get(i) + " ");
            
            }
        }
    }

}
class BenchMethod {

    public String filename;
    public String method;
    public String percentage;
    public String key;
    public Double Runtime;
    public String Path;

    public BenchMethod(String filename, String method, String percentage, Double Runtime, String path) {
        this.filename = filename;
        this.method = method;
        this.percentage = percentage;
        this.Runtime = Runtime;
        key = filename + " " + method;
        this.Path = path;
    }

    
}

class BenchReport {

    public String filename;
    public String method;
    public String key;
    public ArrayList<Double> Percentages;
    public ArrayList<Double> Runtimes;
    public ArrayList<String> paths;
    public Double min;
    public Double max;
    //public String percentage;

    public BenchReport(String key) {
        this.key = key;
        String[] split = key.split("\\s+",2);
        filename = split[0];
        method = split[1];
        Percentages = new ArrayList<Double>();
        Runtimes = new ArrayList<Double>();
        paths = new ArrayList<String>();
        min = 100d;
        max = 0d;
    }

    public void addPercentage(String Percentage, Double Runtime, String path)
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
        Runtimes.add(Runtime);
        paths.add(path);
    }

    public Double getAverage() {
        double total = 0;
        for (int i = 0; i < Percentages.size(); i++) {
            total+= Percentages.get(i);
        }
        return total/Percentages.size();
    }

    public Double getAverageRuntime() {
        double total = 0;
        for (int i = 0; i < Runtimes.size(); i++) {
            total+= Runtimes.get(i);
        }
        return total/Runtimes.size();
    }

    public Double getBigestDiffinRuntime() {
        Double Runtimemax = 0d;
        Double Runtimemin = 1000000000000d;
        for (int i = 0; i < Runtimes.size(); i++) {
            if (Runtimes.get(i) > Runtimemax) {
                Runtimemax = Runtimes.get(i);
            }
            if (Runtimes.get(i) < Runtimemin) {
                Runtimemin = Runtimes.get(i);
            }
        }
        return (Runtimemax - Runtimemin);
    }
    
}

class BenchReportOnMethod {

    public String method;
    public ArrayList<BenchMethod> BenchMethods;
    public Double min;
    public Double max;
    //public String percentage;

    public BenchReportOnMethod(String method) {
        this.method = method;
        BenchMethods = new ArrayList<BenchMethod>();
        min = 100d;
        max = 0d;
    }

    public void addBenchMethod(BenchMethod BenchMethod)
    {
                // need to do some parseing
        BenchMethod.percentage = BenchMethod.percentage.replace("%", "");
        Double percent = 0d;
        try {
            percent = Double.parseDouble(BenchMethod.percentage);
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
        BenchMethods.add(BenchMethod);
    }

    public Double getAverage(String profilerName) {
        String FileType = "";
        switch (profilerName) {
            case "Async":
            FileType = ".txt";
                break;
            case "HonestProfiler":
            FileType = ".hpl";
                break;
            case "JavaFlightRecorder":
            FileType = ".jfr";
                break;
        
            default:

                break;
        }

        double total = 0;
        for (int i = 0; i < BenchMethods.size(); i++) {
            if (BenchMethods.get(i).filename.endsWith(FileType)) {
                total+= Double.parseDouble(BenchMethods.get(i).percentage);
            }
        }
        if (total == 0) {
            return -1D;
        }
        return total/BenchMethods.size();
    }

    public Double getAverageRuntime() {
        double total = 0;
        for (int i = 0; i < BenchMethods.size(); i++) {
            total+= BenchMethods.get(i).Runtime;
        }
        return total/BenchMethods.size();
    }

/*     public Double getBigestDiffinRuntime() {
        Double Runtimemax = 0d;
        Double Runtimemin = 1000000000000d;
        for (int i = 0; i < Runtimes.size(); i++) {
            if (Runtimes.get(i) > Runtimemax) {
                Runtimemax = Runtimes.get(i);
            }
            if (Runtimes.get(i) < Runtimemin) {
                Runtimemin = Runtimes.get(i);
            }
        }
        return (Runtimemax - Runtimemin);
    } */
    
}