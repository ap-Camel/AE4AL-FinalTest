import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        long startTime = System.nanoTime();

        File file = new File("words.txt");
        Scanner scanner = new Scanner(file);

        Hash_table table = new Hash_table();
        
        while(scanner.hasNext()) {

            table.add(scanner.next());
        }

        //table.print();
        table.statistics();

        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);

        scanner.close();    }
}
