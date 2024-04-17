import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Task3 {
    public static void main(String[] args) {
        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];
        writeReportFile(testsFilePath, reportFilePath, parseValues(valuesFilePath));
    }

    public static Map<Integer, String> parseValues(String path) {
        Map<Integer, String> mapValues = new HashMap<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader))
        {
            AtomicReference<Integer> id = new AtomicReference<>();
            AtomicReference<String> value = new AtomicReference<>();

            bufferedReader.lines().forEach(s -> {
                if (s.contains("\"id\":")) {
                    id.set(Integer.parseInt(s.substring(s.lastIndexOf(":") + 1, s.lastIndexOf(",")).trim()));
                }
                if (s.contains("\"value\":")) {
                    value.set(s.substring(s.lastIndexOf(":") + 1, s.lastIndexOf("\"") + 1).trim());
                    mapValues.put(id.get(), value.get());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapValues;
    }

    public static int writeReportFile(String pathToTestsFile, String pathToReport, Map<Integer, String> valuesMap) {
        StringBuilder result = new StringBuilder();
        try (FileReader fileReader = new FileReader(pathToTestsFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             PrintWriter printWriter = new PrintWriter(pathToReport))
        {
            AtomicReference<Integer> id = new AtomicReference<>();

            bufferedReader.lines().forEach(s -> {
                if (s.contains("\"id\":")) {
                    id.set(Integer.parseInt(s.substring(s.lastIndexOf(":") + 1, s.lastIndexOf(",")).trim()));
                }
                if (s.contains("\"value\":")) {
                    s = s.replace("\"\"", valuesMap.get(id.get()));
                }
                result.append(s).append("\n");
            });
            printWriter.write(String.valueOf(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}