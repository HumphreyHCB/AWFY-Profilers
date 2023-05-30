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
    public static HashMap<String,String> FirstHottests = new HashMap<String,String>();
    public static void main(String[] args) {
        
        processFile("Readers/JSONDumps/report4/output.json");
        for (int prefix = 2; prefix <= 30; prefix++) {
            processFile("Readers/JSONDumps/report4/output"+prefix+".json");
        }

        TreeMap<String,String> hottest = new TreeMap<String,String>(FirstHottests);
        printFristHottestsFromEachFile(hottest);


         HashMap<String,ArrayList<BenchMethod>> Map =  mapOccurrences(dataSet);
          HashMap<String,BenchReport> statmap = statisticize(Map);
          TreeMap<String,BenchReport> orderedStatmap = orderStatisticMap(statmap);
          //printOrderStatisticMapForMasterTable(orderedStatmap);
        //printOrderStatisticMap(orderedStatmap);

        mapOccurrencesOnMethodAndPrint(dataSet, "Queens.placeQueen");
        //DebugmapOccurrences(dataSet);
    }
    
    private static String[][] processFile(String path) {

        try {
            JSONObject file = new JSONObject(new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8));
            JSONObject JavaFlightRecorder = file.getJSONObject("JavaFlightRecorder");
            JSONObject HonestProfiler = file.getJSONObject("HonestProfiler");
            JSONObject Async = file.getJSONObject("Async");

            JSONObject Perf = file.getJSONObject("Perf");
            JSONObject YourKit = file.getJSONObject("YourKit");
            JSONObject JProfiler = file.getJSONObject("JProfiler");

            JSONObject Runtimes = file.getJSONObject("Runtimes");
            addProfilerToDataset(Async, Runtimes, "Async", path);
            addProfilerToDataset(HonestProfiler , Runtimes, "HonestProfiler", path);
            addProfilerToDataset(JavaFlightRecorder , Runtimes, "JavaFlightRecorder", path);

            addProfilerToDataset(Perf, Runtimes, "Perf", path);
            addProfilerToDataset(YourKit , Runtimes, "YourKit", path);
            addProfilerToDataset(JProfiler , Runtimes, "JProfiler", path);
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
            Double max = 0d;
            String tempmethod ="";
            for (int j = 0; j < methods.length() ; j++) {
                String methodName = methods.get(j).toString();
                String percentage = benchmarks.get(methodName).toString();
                BigDecimal BigRuntime =  (BigDecimal) Runtimes.get(file);
                DecimalFormat df = new DecimalFormat("#.###");
                double runtime = Double.parseDouble(df.format(BigRuntime));
                if (Double.parseDouble(percentage.replace("%", "")) > max) {
                    tempmethod = methodName;
                    max = Double.parseDouble(percentage.replace("%", ""));
                }
                dataSet.add(new BenchMethod(file, normaliseMethodName(methodName, profilerName), percentage, runtime, path));
                                
            }
            FirstHottests.put(file +" " +path, normaliseMethodName(tempmethod, profilerName));
            
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
            case "Perf":
                normalisedMethodName = MethodName.replace("[.] ", "");
                String[] thingsToRemove = {"json.JsonValue","json.JsonArray","java.lang.String","boolean","java.lang.Object","int","boolean","List$Element","void","cd.RedBlackTree$InsertResult","richards.TaskControlBlock","vtable","deltablue.AbstractConstraint","deltablue.Plan"};
                for (String str : thingsToRemove) {
                    normalisedMethodName = normalisedMethodName.replace(str, "");
                }
                normalisedMethodName = normalisedMethodName.split("\\(")[0];
                break;
            case "YourKit":
                normalisedMethodName = MethodName.split("\\(")[0];
                break;
            case "JProfiler":
                normalisedMethodName = MethodName;
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
        return normalisedMethodName.trim();
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

    public static void mapOccurrencesOnMethodAndPrint(ArrayList<BenchMethod> collection, String specificMethod) {
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
        if (specificMethod == "" || specificMethod == null){
        for (String Method : statisticMap.keySet()) {
            if (statisticMap.get(Method).BenchMethods.size() <= 30) {
                continue;
            }
            System.out.println("");
            System.out.println("----------" + Method + "----------");
            System.out.println("");

            //   for (BenchMethod benchMethod : statisticMap.get(Method).BenchMethods) {
                
            //     System.out.print(" Percentage: " +  new DecimalFormat("0.00").format(Double.parseDouble(benchMethod.percentage)));
            //     System.out.print("      Runtime: " + new DecimalFormat("0.00").format(benchMethod.Runtime));
            //     System.out.print(" File: " + benchMethod.filename);
            //     System.out.println("");
            // } 
            System.out.println("");
            System.out.println("Async Average: "+ statisticMap.get(Method).getAverage("Async"));
            System.out.println("HonestProfiler Average: "+ statisticMap.get(Method).getAverage("HonestProfiler"));
            System.out.println("JavaFlightRecorder Average: "+ statisticMap.get(Method).getAverage("JavaFlightRecorder"));

            System.out.println("Perf Average: "+ statisticMap.get(Method).getAverage("PerfTests"));
            System.out.println("YourKit Average: "+ statisticMap.get(Method).getAverage("YourKitTests"));
            System.out.println("JProfiler Average: "+ statisticMap.get(Method).getAverage("JProfilerTests"));

            System.out.println("");
            System.out.println("Min: "+ statisticMap.get(Method).min);
            System.out.println("Max: "+ statisticMap.get(Method).max);
            System.out.println("Diff: "+ (statisticMap.get(Method).max - statisticMap.get(Method).min));
        }
        }else{
            BenchReportOnMethod BROM = statisticMap.get(specificMethod);
            //BenchReportOnMethod BROM = statisticMap.get(key);

            HashMap<String,ArrayList<Double>> xdatas = new HashMap<String,ArrayList<Double>>();
            HashMap<String,ArrayList<Double>> ydatas = new HashMap<String,ArrayList<Double>>();

            for (BenchMethod BM :  BROM.BenchMethods) {
                if (xdatas.containsKey(BM.Profiler)) {
                    ydatas.get(BM.Profiler).add(Double.parseDouble(BM.percentage));
                    xdatas.get(BM.Profiler).add(BM.Runtime);
                    //heatMap.put(key, heatMap.get(key).add(i));
                }
                else{
                    ArrayList<Double> array = new ArrayList<Double>();
                    ArrayList<Double> arrayb = new ArrayList<Double>();
                    array.add(Double.parseDouble(BM.percentage));
                    arrayb.add(BM.Runtime);
                    ydatas.put(BM.Profiler, array );
                    xdatas.put(BM.Profiler, arrayb);
                }
            }
            new Grapher().multiGraph(specificMethod, xdatas, ydatas);
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

    public static void printOrderStatisticMapForMasterTable(TreeMap<String,BenchReport> OSM) {
        String currentFile = "";
        BenchReport currentReport;
        ArrayList<BenchReport> highlights = new ArrayList<BenchReport>();
        int count = 0;
        for (String key : OSM.keySet()) {
            currentReport = OSM.get(key);
            if (count == 15) {
                if (!currentReport.filename.equals(currentFile)) {
                    count = 0;
                }
                else{
                    continue;
                }
            }

            if (!currentReport.filename.equals(currentFile)) {
                count = 0;
                currentFile = currentReport.filename;
                System.out.println("");
                //System.out.println("");
                //System.out.println("");
                System.out.println(currentFile );
                //System.out.println("");
                //System.out.println("Method      Entries        Average      Min     MinRuntime      Max     MaxRuntime      Diff");
            }
            
            System.out.print(currentReport.method);
            System.out.print(" " + currentReport.Percentages.size());
            System.out.print(" " + new DecimalFormat("0.00").format(currentReport.getAverage()));
             System.out.print(" " + currentReport.min);
             System.out.print(" " + currentReport.Runtimes.get(currentReport.Percentages.indexOf(currentReport.min)));
             System.out.print(" " + currentReport.max);
             System.out.print(" " + currentReport.Runtimes.get(currentReport.Percentages.indexOf(currentReport.max)));
             String diff = new DecimalFormat("0.00").format(currentReport.max - currentReport.min);
             System.out.print(" " + new DecimalFormat("0.00").format(currentReport.max - currentReport.min));
             System.out.println("");

            if (Double.parseDouble(diff) >= 5 ) {
                highlights.add(currentReport);
            }
            count++;
        }

        // for (BenchReport benchReport : highlights) {
        //     System.out.println("");
        //     System.out.print(" File : " + benchReport.filename);
            //new Grapher().Graph(benchReport.method + " " + benchReport.filename, run, per);
        //}

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
            if (Double.parseDouble(diff) >= 5) {
                highlights.add(currentReport);
            }
            System.out.print("  Diff : " + new DecimalFormat("0.00").format(currentReport.max - currentReport.min));
            
        }

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
            ArrayList<Double> per = new ArrayList<>();
            ArrayList<Double> run = new ArrayList<>();
             for (int i = 0; i < benchReport.Percentages.size(); i++) {
                //System.out.println("Percentage: " + benchReport.Percentages.get(i) + " Runtime: " + benchReport.Runtimes.get(i) + " Path: " + benchReport.paths.get(i));
                //System.out.print(benchReport.Percentages.get(i) + " ");
                per.add(benchReport.Percentages.get(i));
                run.add(benchReport.Runtimes.get(i));
            
            }
            //new Grapher().Graph(benchReport.method + " " + benchReport.filename, run, per);
        }
    }
    private static String convertRealNametoRuntimeName(String filename , String profilerName) {
        String correctedName = "";
        if (profilerName.contains("JavaFlightRecorder")) {
            correctedName = filename.replace(".jfr", "_JFR_.jfr");
        }else if (profilerName.contains("Async")) {
            correctedName = filename.replace(".txt", "_Async_.txt");
        }else if (profilerName.contains("HonestProfiler")) {
            correctedName = filename.replace(".hpl", "_honest_.hpl");
        }else if (profilerName.contains("NonProfiledTests")) {


        }else if (profilerName.contains("Perf")) {
            correctedName = filename.replace(".data", "_Perf_.data");
        }else if (profilerName.contains("YourKit")) {
            correctedName = filename.replace("YourKit_", "");
            correctedName = correctedName.replace(".txt", "_YourKit_.txt");
        }else if (profilerName.contains("JProfiler")) {
            correctedName = filename.replace(".txt", "_JProfiler_.txt");
        } else {
            correctedName = filename;
        } 

        return correctedName;
    }


    private static void printFristHottestsFromEachFile(TreeMap<String,String> fristhottestsfromeachfile) {
        

        HashMap<String,Integer> changeInHottets = new HashMap<String,Integer>();
        int counter = 0;
        String currentMethod = "";
        ArrayList<String> seenmethods = new ArrayList<String>();
        int methodcounter = 0;
        String lastKey = "";
        for (String key : fristhottestsfromeachfile.keySet()) {
            if (counter >= 30) {
                counter = 0;
                changeInHottets.put(lastKey.split("\\s+")[0], methodcounter);
                methodcounter = 0;
                currentMethod = "";
                System.out.println(lastKey);
                for (String iterable_element : seenmethods) {
                    System.out.println(iterable_element);
                }
                seenmethods.clear();
                System.out.println();
            }
            currentMethod = fristhottestsfromeachfile.get(key);
            if(!seenmethods.contains(currentMethod)){
                seenmethods.add(currentMethod);
                methodcounter++;
            }
            counter++;
            lastKey = key;
        }
        changeInHottets.put(lastKey.split("\\s+")[0], methodcounter);
        System.out.println(lastKey);
        for (String iterable_element : seenmethods) {
            System.out.println(iterable_element);
        }
        for (String key : changeInHottets.keySet()) {
            System.out.println(key + "  " + changeInHottets.get(key));
        }
        System.out.println();

    }
}
class BenchMethod {

    public String filename;
    public String method;
    public String percentage;
    public String key;
    public Double Runtime;
    public String Path;
    public String Profiler;

    public BenchMethod(String filename, String method, String percentage, Double Runtime, String path) {
        this.filename = filename;
        this.method = method;
        this.percentage = percentage;
        this.Runtime = Runtime;
        key = filename + " " + method;
        this.Path = path;
        decidedProfiler(filename);
    }

    public void decidedProfiler(String filename) {
        if (filename.contains("JavaFlightRecorder_")) {
            Profiler = "JavaFlightRecorder";
        }else if (filename.contains("rebench_test_Async_")) {
            Profiler = "Async";
        }else if (filename.contains(".hpl")) {
            Profiler = "honest-profiler";
        }else if (filename.contains("_not_profiled_")) {
            Profiler = "_not_profiled_";
        }else if (filename.contains(".data")) {
            Profiler = "Perf";
        }else if (filename.contains("YourKit_")) {
            Profiler = "YourKit";
        }else if (filename.contains("JProfiler_")) {
            Profiler = "JProfiler";
        } else {

        } 
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
        String LookUp = "";
        switch (profilerName) {
            case "Async":
            LookUp = "Async";
                break;
            case "HonestProfiler":
            LookUp = ".hpl";
                break;
            case "JavaFlightRecorder":
            LookUp = ".jfr";
                break;
            case "PerfTests":
                LookUp = ".data";
                    break;
            case "YourKitTests":
                    LookUp = "YourKit";
                        break;
            case "JProfilerTests":
                        LookUp = "JProfiler";
                            break;        
            default:

                break;
        }

        double total = 0;
        for (int i = 0; i < BenchMethods.size(); i++) {
            if (BenchMethods.get(i).filename.contains(LookUp)) {
                total+= Double.parseDouble(BenchMethods.get(i).percentage);
            }
        }
        if (total == 0) {
            return -1D;
        }
        return total/BenchMethods.size();
    }

    private static String convertRealNametoRuntimeName(String filename , String profilerName) {
        String correctedName = "";
        if (profilerName.contains("JavaFlightRecorder")) {
            correctedName = filename.replace(".jfr", "_JFR_.jfr");
        }else if (profilerName.contains("Async")) {
            correctedName = filename.replace(".txt", "_Async_.txt");
        }else if (profilerName.contains("HonestProfiler")) {
            correctedName = filename.replace(".hpl", "_honest_.hpl");
        }else if (profilerName.contains("NonProfiledTests")) {


        }else if (profilerName.contains("Perf")) {
            correctedName = filename.replace(".data", "_Perf_.data");
        }else if (profilerName.contains("YourKit")) {
            correctedName = filename.replace("YourKit_", "");
            correctedName = correctedName.replace(".txt", "_YourKit_.txt");
        }else if (profilerName.contains("JProfiler")) {
            correctedName = filename.replace(".txt", "_JProfiler_.txt");
        } else {
            correctedName = filename;
        } 

        return correctedName;
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