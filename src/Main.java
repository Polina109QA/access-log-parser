import java.io.File;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int res = 0;
        while (true) {
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (fileExists && !isDirectory) {
                res++;
                System.out.println("Путь указан верно");
                System.out.println("Это файл номер " + res);
            } else {
                System.out.println("Указанный путь не ведет к файлу");
                continue;
            }
        }
    }
}