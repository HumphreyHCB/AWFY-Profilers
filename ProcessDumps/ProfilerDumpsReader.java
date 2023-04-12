import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;




public class ProfilerDumpsReader {
    public static void main(String[] args) {
        String[][] top5 = JFRHottestMethods(args[0]);

        for (String[] strings : top5) {
            System.out.println(strings[0]);            
        }
        for (String[] strings : top5) {
            System.out.println(strings[1]);            
        }
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
                        top5[i][0] = split[4];
                        top5[i][1] = split[2];
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

}

