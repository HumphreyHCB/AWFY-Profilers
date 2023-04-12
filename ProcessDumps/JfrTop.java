import jdk.jfr.consumer.RecordingFile;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

//thank you to https://stackoverflow.com/questions/61324185/java-flight-recorder-profiling-summary-extraction


public class JfrTop {

    

    public String[][] getHottestMethods(String FileName) {
        var fileName = FileName;
        var packageName = "";
        var top = 5;

        var hotMethods = new HashMap<String, Long>();
        long total = 0;

        try (var recording = new RecordingFile(Paths.get(fileName))) {
            while (recording.hasMoreEvents()) {
                var event = recording.readEvent();
                if (event.getEventType().getName().equals("jdk.ExecutionSample")) {
                    var stackTrace = event.getStackTrace();
                    if (stackTrace != null && stackTrace.getFrames().size() > 0) {
                        var method = stackTrace.getFrames().get(0).getMethod();
                        var className = method.getType().getName();
                        if (className.startsWith(packageName)) {
                            var fullName = className + '.' + method.getName() + method.getDescriptor();
                            hotMethods.compute(fullName, (key, val) -> val == null ? 1L : (val + 1));
                        }
                    }
                    total++;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String[][] top5 = new String[5][2];
        double percent = 100.0 / total;
        ArrayList<String> method = new ArrayList<String>();

        hotMethods.entrySet().stream()
                 .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                 .limit(top)
                 .forEachOrdered(e -> method.add(Double.toString(e.getValue() * percent) + " "+ e.getKey()));
                
        int count = 0;
        for (String key : method) {
            String[] splitedkey = key.split("\\s+", 2);
            top5[count][0] = splitedkey[1];
            top5[count][1] = splitedkey[0];
            count++;
        }

        return top5;
        
    }
}