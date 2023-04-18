import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;




public class ProfilerDumpsReader {
    public static void main(String[] args) {
        String[][] DumpFiles = getDumpFiles("/home/hburchell/Repos/AWFY-Profilers/ProfilesDump");
        
        for (String[] ProfilerDump : DumpFiles) {
            switch (ProfilerDump[0]) {
                case "AsyncDumps":
                    for (int i = 1; i < ProfilerDump.length -1; i++) {
                        String[][] top5 = AsyncHottestMethods(ProfilerDump[i]);
                        for (String[] strings : top5) {
                            System.out.println(strings[0] + " " + strings[1] );            
                        }
                        System.out.println("");
                    }
                    break;
                case "HonestProfiler":
                    for (int i = 1; i < ProfilerDump.length-1; i++) {
                        String[][] top5 = HonestProfilerHottestMethods(ProfilerDump[i]);
                        for (String[] strings : top5) {
                            System.out.println(strings[0] + " " + strings[1] );            
                        }
                        System.out.println("");
                    }
                    break;
                case "JavaFlightRecorder":
                    for (int i = 1; i < ProfilerDump.length-1; i++) {
                        String[][] top5 = JFRHottestMethods(ProfilerDump[i]);
                        for (String[] strings : top5) {
                            System.out.println(strings[0] + " " + strings[1] );            
                        }
                        System.out.println("");
                    }
                    break;
                case "Perf":
                    for (int i = 1; i < ProfilerDump.length-1; i++) {
                        String[][] top5 = PerfHottestMethods(ProfilerDump[i]);
                        for (String[] strings : top5) {
                            System.out.println(strings[0] + " " + strings[1] );            
                        }
                        System.out.println("");
                    }
                    break;
                default:
                    break;
            }
        }
        

    }

    private static String[][] getDumpFiles(String DumpsDirPath) {
        File[] dir = new File(DumpsDirPath).listFiles();
        String[][] DumpFiles = new String[dir.length][15];
        for (int i = 0; i < dir.length; i++) {
            if (dir[i].isFile()) {
              System.out.println(dir[i].getName());
            } else if (dir[i].isDirectory()) {
                File[] currentDir = new File(dir[i].getAbsolutePath()).listFiles();
                DumpFiles[i][0] = dir[i].getName();
                for (int j = 0; j < currentDir.length; j++) {
                    if(currentDir[j].getName().equals(".gitignore"))
                    {

                    }
                    else{
                    DumpFiles[i][j] = currentDir[j].getAbsolutePath();
                    }
                }
              
            }
          }
        return DumpFiles;
    }

    private static String[][] AsyncHottestMethods(String Filename) {
        String[][] top5 = new String[5][2];
        try {
            File myObj = new File(Filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                if (myReader.nextLine().equals("  ----------  -------  -------  ---")) {
                    for (int i = 0; i < 5; i++) {
                        String[] split = myReader.nextLine().split("\\s+");
                        if(split[0] == "")
                        {
                            top5[i][0] = split[4];
                            top5[i][1] = split[2];
                        }
                        else{
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
        String[][] top5 = new String[5][2];
        ProcessBuilder processBuilder = new ProcessBuilder();
        try {
            processBuilder.command("/home/hburchell/Repos/AWFY-Profilers/Profilers/honest-profiler/console", "-log", Filename);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();

            while (line!= null) {
                if (line.equals("Flat Profile (by method):")) {
                    for (int i = 0; i < 5; i++) {
                        String[] split = reader.readLine().split("\\s+",5);
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

    //java JfrTop /home/hburchell/Repos/AWFY-Profilers/ProfilesDump/JavaFlightRecorder/JavaFlightRecorder_CD.jfr "" 100

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

            while (line!= null) {
                if (line.startsWith("# Overhead  Command          Shared Object         Symbol ")) {
                    reader.readLine();
                    reader.readLine();
                    for (int i = 0; i < 5; i++) {
                        String[] split = reader.readLine().split("\\s+",7);
                        top5[i][0] = split[6];
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
