import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("data.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] array = line.split("\\|");
            System.out.printf("new HalfReaction(\"%s\", %s, %s),\n", array[0], array[1], array[2]);
        }
    }
}
