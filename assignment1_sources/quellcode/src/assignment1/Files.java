package assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/***
 * @author Annika Baldi, Milan Proell
 */

public class Files {
    public static void main(String[] args) throws IOException {
        //Sorgt dafür, dass der Code auf Code Ocean ausgeführt wird.
        System.out.println(sumFiles("file1.txt"));
        //System.out.println(sumFiles2(""));
    }

    public static int sumFiles(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        int sum = 0;
        String line;
        while ((line = br.readLine()) != null) {
            sum = sum + Integer.parseInt(line);
        }
        return 0;
    }

    public static int sumFiles2(String fileName) throws IOException {
        return 0;
    }
}