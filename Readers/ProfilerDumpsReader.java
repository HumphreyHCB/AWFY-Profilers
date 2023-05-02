import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;
import java.util.Comparator;

public class ProfilerDumpsReader {
    public static void main(String[] args) {
        String prefix = "10";
        String[][] DumpFiles = getDumpFiles("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump"+ prefix);
        String filename = "Readers/JSONDumps/output"+ prefix +".json";
        JSONObject AllProfilerDumps = new JSONObject();
        for (String[] ProfilerDump : DumpFiles) {
            if (ProfilerDump[0].contains("AsyncDumps")) {
                System.out.println("---------------AsyncDumps---------------");
                JSONObject AsyncDumps = new JSONObject();
                for (int i = 0; i < ProfilerDump.length - 1; i++) {
                    JSONObject benchmark = new JSONObject();
                    String[][] top5 = AsyncHottestMethods(ProfilerDump[i]);
                    for (String[] strings : top5) {
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
                for (int i = 1; i < ProfilerDump.length - 1; i++) {
                    JSONObject benchmark = new JSONObject();
                    String[][] top5 = HonestProfilerHottestMethods(ProfilerDump[i]);
                    for (String[] strings : top5) {
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
                for (int i = 1; i < ProfilerDump.length - 1; i++) {
                    JSONObject benchmark = new JSONObject();
                    String[][] top5 = JFRHottestMethods(ProfilerDump[i]);
                    for (String[] strings : top5) {
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
                for (int i = 1; i < ProfilerDump.length - 1; i++) {
                    JSONObject benchmark = new JSONObject();
                    String[][] top5 = PerfHottestMethods(ProfilerDump[i]);
                    for (String[] strings : top5) {
                        System.out.println(strings[0] + " " + strings[1]);
                        benchmark.put(strings[0], strings[1]);
                    }
                    Perf.put(new File(ProfilerDump[i]).getName(), benchmark);
                    System.out.println("");
                }
                AllProfilerDumps.put("Perf", Perf);
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
                Arrays.sort(DumpFiles[i]);

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
        String[][] top5 = new String[10][2];
        try {
            File myObj = new File(Filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                if (myReader.nextLine().equals("  ----------  -------  -------  ---")) {
                    for (int i = 0; i < 10; i++) {
                        String[] split = myReader.nextLine().split("\\s+");
                        if (split[0] == "") {
                            top5[i][0] = split[4];
                            top5[i][1] = split[2];
                        } else {
                            top5[i][0] = split[3];
                            top5[i][1] = split[1];
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
        return top5;
    }

    private static String[][] HonestProfilerHottestMethods(String Filename) {
        String[][] top5 = new String[10][2];
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
                        top5[i][0] = split[4];
                        top5[i][1] = split[3].substring(0, split[3].length() - 1);
                    }
                    break;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return top5;
    }

    // java JfrTop
    // /home/hburchell/Repos/AWFY-Profilers/ProfilesDump/JavaFlightRecorder/JavaFlightRecorder_CD.jfr
    // "" 100

    private static String[][] JFRHottestMethods(String Filename) {
        String[][] top5 = new JfrTop().getHottestMethods(Filename);
        return top5;
    }

    private static String[][] PerfHottestMethods(String Filename) {
        String[][] top5 = new String[5][2];
        ProcessBuilder processBuilder = new ProcessBuilder();
        try {
            processBuilder.command("sudo", "perf", "report", "--stdio", "-i", Filename);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();

            while (line != null) {
                if (line.startsWith("# Overhead  Command          Shared Object         Symbol ")) {
                    reader.readLine();
                    reader.readLine();
                    for (int i = 0; i < 5; i++) {
                        String[] split = reader.readLine().split("\\s+", 7);
                        top5[i][0] = split[split.length - 1];
                        top5[i][1] = split[1];
                    }
                    break;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return top5;
    }

}
