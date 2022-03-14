import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);

        System.out.println("input age");
        File file = new File("weather_report.txt");
        Scanner sc = new Scanner(file);

        System.out.println(sc.next());
        //scan.hasNext();

        //System.out.println("your age is " + scan);

    }
}
