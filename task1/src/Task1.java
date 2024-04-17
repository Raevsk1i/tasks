import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        circularArrayPath(n, m);
    }

    public static int circularArrayPath(int n, int m) {
        int[] arr = new int[n];
        int[] marr = Arrays.copyOf(arr, m);
        int lastIndex = m - 1;
        List<Integer> result = new ArrayList<>();

        Arrays.setAll(arr, i -> ++i);
        result.add(arr[0]);

        do {
            for (int i = 0; i < m; i++) {
                marr[i] = arr[(lastIndex + i) % n];
            }
            lastIndex = (lastIndex + m - 1) % n;
            result.add(marr[0]);
        } while (arr[0] != marr[m - 1]);
        StringBuilder stringBuilder = new StringBuilder();
        result.forEach(stringBuilder::append);
        System.out.println(stringBuilder);
        return 0;
    }
}