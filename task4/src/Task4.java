import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        minDistance(args[0]);
    }

    public static int minDistance(String filename) {
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader))
        {
            List<Integer> arr = new ArrayList<>();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                arr.add(Integer.parseInt(line));
            }

            arr.sort(Comparator.naturalOrder());

            int median = arr.get(arr.size()/2);
            int result = 0;
            for (Integer num : arr) {
                result += Math.abs(median - num);
            }

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}