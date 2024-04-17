import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Task2 {
    public static void main(String[] args) {
        getCoordinates(args[0], args[1]);
    }

    public static int getCoordinates(String filePath1, String filePath2) {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);

        try (FileReader fr1 = new FileReader(file1);
             FileReader fr2 = new FileReader(file2);
             BufferedReader circle = new BufferedReader(fr1);
             BufferedReader dots = new BufferedReader(fr2))
        {
            String[] circleXY = circle.readLine().split(" ");
            StringBuilder sb = new StringBuilder();
            float radius = Float.parseFloat(circle.readLine());

            for (String line = dots.readLine(); line != null; line = dots.readLine()) {
                String[] xy = line.split(" ");
                double distance = Math.sqrt(Math.pow((Float.parseFloat(xy[0]) - Float.parseFloat(circleXY[0])), 2)
                        + (Math.pow(Float.parseFloat(xy[1]) - Float.parseFloat(circleXY[1]), 2)));

                if (distance == radius) sb.append("0");
                else if (distance < radius) sb.append("1");
                else sb.append("2");
                sb.append(" ");
            }
            sb.setLength(sb.length() - 1);
            System.out.println(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}