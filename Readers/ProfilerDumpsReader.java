import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
import java.util.Comparator;

public class ProfilerDumpsReader {
    public static void main(String[] args) {
        proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump2", "output500.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump", "Readers/JSONDumps/report3/output.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump2", "Readers/JSONDumps/report3/output2.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump3", "Readers/JSONDumps/report3/output3.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump4", "Readers/JSONDumps/report3/output4.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump5", "Readers/JSONDumps/report3/output5.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump6", "Readers/JSONDumps/report3/output6.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump7", "Readers/JSONDumps/report3/output7.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump8", "Readers/JSONDumps/report3/output8.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump9", "Readers/JSONDumps/report3/output9.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/A/ProfilesDump10", "Readers/JSONDumps/report3/output10.json");

        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump", "Readers/JSONDumps/report3/output11.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump2", "Readers/JSONDumps/report3/output12.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump3", "Readers/JSONDumps/report3/output13.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump4", "Readers/JSONDumps/report3/output14.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump5", "Readers/JSONDumps/report3/output15.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump6", "Readers/JSONDumps/report3/output16.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump7", "Readers/JSONDumps/report3/output17.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump8", "Readers/JSONDumps/report3/output18.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump9", "Readers/JSONDumps/report3/output19.json");
        // proccesDumpDir("/home/hburchell/Repos/DeepStorage/Report3/B/ProfilesDump10", "Readers/JSONDumps/report3/output20.json");
        

        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump", "Readers/JSONDumps/report3/output21.json");
        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump2", "Readers/JSONDumps/report3/output22.json");
        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump3", "Readers/JSONDumps/report3/output23.json");
        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump4", "Readers/JSONDumps/report3/output24.json");
        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump5", "Readers/JSONDumps/report3/output25.json");
        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump6", "Readers/JSONDumps/report3/output26.json");
        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump7", "Readers/JSONDumps/report3/output27.json");
        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump8", "Readers/JSONDumps/report3/output28.json");
        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump9", "Readers/JSONDumps/report3/output29.json");
        // proccesDumpDir("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump10", "Readers/JSONDumps/report3/output30.json");

    }

    private static void proccesDumpDir(String DumpFilesDir, String outputDir) {
        //String prefix = "10";
        String[][] DumpFiles = getDumpFiles(DumpFilesDir);
        String filename = outputDir;
        JSONObject AllProfilerDumps = new JSONObject();
        for (String[] ProfilerDump : DumpFiles) {
            if (ProfilerDump[0].contains("AsyncDumps")) {
                System.out.println("---------------AsyncDumps---------------");
                JSONObject AsyncDumps = new JSONObject();
                for (int i = 0; i < ProfilerDump.length; i++) {
                    JSONObject benchmark = new JSONObject();
                    String[][] top10 = AsyncHottestMethods(ProfilerDump[i]);
                    for (String[] strings : top10) {
                        System.out.println(strings[0] + " " + strings[1]);
                        benchmark.put(strings[0], strings[1]);
                    }
                    AsyncDumps.put(new File(ProfilerDump[i]).getName(), benchmark);
                    System.out.println("");

                }
                AllProfilerDumps.put("Async", AsyncDumps);

            } else if (ProfilerDump[0].contains("HonestProfiler")) {
                System.out.println("---------------HonestProfiler---------------");
                JSONObject HonestProfiler = new JSONObject();
                for (int i = 0; i < ProfilerDump.length; i++) {
                    JSONObject benchmark = new JSONObject();
                    String[][] top10 = HonestProfilerHottestMethods(ProfilerDump[i]);
                    for (String[] strings : top10) {
                        System.out.println(strings[0] + " " + strings[1]);
                        benchmark.put(strings[0], strings[1]);
                    }
                    HonestProfiler.put(new File(ProfilerDump[i]).getName(), benchmark);
                    System.out.println("");
                }
                AllProfilerDumps.put("HonestProfiler", HonestProfiler);

            } else if (ProfilerDump[0].contains("JavaFlightRecorder")) {
                System.out.println("---------------JavaFlightRecorder---------------");
                JSONObject JavaFlightRecorder = new JSONObject();
                for (int i = 0; i < ProfilerDump.length; i++) {
                    JSONObject benchmark = new JSONObject();
                    String[][] top10 = JFRHottestMethods(ProfilerDump[i]);
                    for (String[] strings : top10) {
                        System.out.println(strings[0] + " " + strings[1]);
                        if(strings[0] == null)
                        {
                            benchmark.put(" ", "0");
                        }
                        else{
                        benchmark.put(strings[0], strings[1]);
                        }
                    }
                    JavaFlightRecorder.put(new File(ProfilerDump[i]).getName(), benchmark);
                    System.out.println("");
                }
                AllProfilerDumps.put("JavaFlightRecorder", JavaFlightRecorder);
            } else if (ProfilerDump[0].contains("Perf")) {
                System.out.println("---------------Perf---------------");
                JSONObject Perf = new JSONObject();
                for (int i = 0; i < ProfilerDump.length; i++) {
                    JSONObject benchmark = new JSONObject();
                    String[][] top10 = PerfHottestMethods(ProfilerDump[i]);
                    for (String[] strings : top10) {
                        System.out.println(strings[0] + " " + strings[1]);
                        benchmark.put(strings[0], strings[1]);
                    }
                    Perf.put(new File(ProfilerDump[i]).getName(), benchmark);
                    System.out.println("");
                }
                AllProfilerDumps.put("Perf", Perf);
            } else if (ProfilerDump[0].contains("JProfiler")) {
                System.out.println("---------------JProfiler---------------");
                JSONObject JProfiler = new JSONObject();
                for (int i = 0; i < ProfilerDump.length; i++) {
                    JSONObject benchmark = new JSONObject();
                    String[][] top10 = JProfilerHottestMethods(ProfilerDump[i]);
                    for (String[] strings : top10) {
                        System.out.println(strings[0] + " " + strings[1]);
                        if(strings[0] == null)
                        {
                            benchmark.put(" ", "0");
                        }
                        else{
                        benchmark.put(strings[0], strings[1]);
                        }
                    }
                    JProfiler.put(new File(ProfilerDump[i]).getName(), benchmark);
                    System.out.println("");
                }
                AllProfilerDumps.put("JProfiler", JProfiler);
            }

        }
        WriteJSONObject(AllProfilerDumps, filename);

    }

    private static String[][] getDumpFiles(String DumpsDirPath) {
        File[] dir = new File(DumpsDirPath).listFiles();
        if (dir == null) {
            String[][] DumpFiles = new String[1][1];
            DumpFiles[0][0] = DumpsDirPath;
            return DumpFiles;
        }
        Arrays.sort(dir);
        String[][] DumpFiles = new String[dir.length][14]; // 14 for 14 benchmarks
        for (int i = 0; i < dir.length; i++) {
            if (dir[i].isFile()) {
                System.out.println(dir[i].getName());
            } else if (dir[i].isDirectory()) {
                File[] currentDir = new File(dir[i].getAbsolutePath()).listFiles();
                DumpFiles[i][0] = dir[i].getName();
                for (int j = 0; j < currentDir.length; j++) {
                    if (currentDir[j].getName().equals(".gitignore")) {

                    } else {
                        DumpFiles[i][j] = currentDir[j].getAbsolutePath();
                    }
                }
                if (DumpFiles[i][13] == null) {
                    
                }
                else{
                    Arrays.sort(DumpFiles[i]);
                }
              
            }
        }
        Arrays.sort(DumpFiles, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[0];
                final String time2 = entry2[0];
                return time1.compareTo(time2);
            }
        });
        return DumpFiles;
    }

    private static void WriteJSONObject(JSONObject obj, String filename) {
        try (FileWriter file = new FileWriter(filename, true)) {
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

    private static String[][] AsyncHottestMethods(String Filename) {
        String[][] top10 = new String[10][2];
        try {
            File myObj = new File(Filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                if (myReader.nextLine().equals("  ----------  -------  -------  ---")) {
                    for (int i = 0; i < 10; i++) {
                        String[] split = myReader.nextLine().split("\\s+");
                        if (split[0] == "") {
                            top10[i][0] = split[4];
                            top10[i][1] = split[2];
                        } else {
                            top10[i][0] = split[3];
                            top10[i][1] = split[1];
                        }
                    }
                    break;
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return top10;
    }

    private static String[][] HonestProfilerHottestMethods(String Filename) {
        String[][] top10 = new String[10][2];
        ProcessBuilder processBuilder = new ProcessBuilder();
        try {
            processBuilder.command("/home/hburchell/Repos/AWFY-Profilers/Profilers/honest-profiler/console", "-log",
                    Filename);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();

            while (line != null) {
                if (line.equals("Flat Profile (by method):")) {
                    for (int i = 0; i < 10; i++) {
                        String[] split = reader.readLine().split("\\s+", 5);
                        top10[i][0] = split[4];
                        top10[i][1] = split[3].substring(0, split[3].length() - 1);
                    }
                    break;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return top10;
    }

    // java JfrTop
    // /home/hburchell/Repos/AWFY-Profilers/ProfilesDump/JavaFlightRecorder/JavaFlightRecorder_CD.jfr
    // "" 100

    private static String[][] JFRHottestMethods(String Filename) {
        String[][] top10 = new JfrTop().getHottestMethods(Filename);
        return top10;
    }

    private static String[][] PerfHottestMethods(String Filename) {
        String[][] top10 = new String[10][2];
        ProcessBuilder processBuilder = new ProcessBuilder();
        try {
            processBuilder.command("perf", "report", "--stdio", "-i", Filename);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();

            while (line != null) {
                if (line.startsWith("# Overhead  Command          Shared Object         Symbol ")) {
                    reader.readLine();
                    reader.readLine();
                    for (int i = 0; i < 5; i++) {
                        String[] split = reader.readLine().split("\\s+", 7);
                        top10[i][0] = split[split.length - 1];
                        top10[i][1] = split[1];
                    }
                    break;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return top10;
    }

    private static String[][] JProfilerHottestMethods(String Filename) {
        String[][] top10 = new String[10][2];
        // most of this is riped from stack overflow
        try {
            File fXmlFile = new File(Filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("hotspot");
            NodeList tree = doc.getElementsByTagName("tree");
            int lengtha = nList.getLength();
        
            for (int temp = 0; temp < 10 && temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    top10[temp][0]=eElement.getAttribute("class");
                    top10[temp][1]=eElement.getAttribute("percent");
                }
            }
            } catch (Exception e) {
            e.printStackTrace();
            }
        return top10;
    }

    private static String[][] YourKitHottestMethods(String Filename) {
        String[][] top10 = new String[10][2];
        ProcessBuilder processBuilder = new ProcessBuilder();
        try {
            processBuilder.command("perf", "report", "--stdio", "-i", Filename);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();

            while (line != null) {
                if (line.startsWith("# Overhead  Command          Shared Object         Symbol ")) {
                    reader.readLine();
                    reader.readLine();
                    for (int i = 0; i < 5; i++) {
                        String[] split = reader.readLine().split("\\s+", 7);
                        top10[i][0] = split[split.length - 1];
                        top10[i][1] = split[1];
                    }
                    break;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return top10;
    }

}
