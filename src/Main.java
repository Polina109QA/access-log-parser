import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                int sum = 0;
                int minLength = Integer.MAX_VALUE;
                int maxLength = 0;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    sum++;
                    if (length > 1024) {
                        throw new MyException(length, sum);
                    }
                    if (length > maxLength) {
                        maxLength = length;
                    }
                    if (length < minLength) {
                        minLength = length;
                    }
                }
                System.out.println("общее количество строк в файле: " + sum);
                System.out.println("длина самой короткой строки в файле: " + minLength);
                System.out.println("длина самой длинной строки в файле: " + maxLength);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}