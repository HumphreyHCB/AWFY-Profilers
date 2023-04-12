import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * ProcessAsyncFile
 */
public class ProcessHonestProfilerFile {

    public static void main(String[] args) {
        String[][] top5 = getTop5HottestMethods(args[0]);

        for (String[] strings : top5) {
            System.out.println(strings[0]);            
        }
        for (String[] strings : top5) {
            System.out.println(strings[1]);            
        }
    }
    
    private static String[][] getTop5HottestMethods(String Filename) {
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




}