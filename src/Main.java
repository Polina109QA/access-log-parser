import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    // Регулярка, которая учитывает кавычки и квадратные скобки
    private static final String LOG_ENTRY_PATTERN =
            "^(\\S+) (\\S+) (\\S+) \\[(.*?)\\] \"(\\S+) (.*?) (.*?)\" (\\d{3}) (\\d+|-) \"(.*?)\" \"(.*?)\"$";
    private static final Pattern PATTERN = Pattern.compile(LOG_ENTRY_PATTERN);


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
                int googleBotCount = 0;
                int yandexBotCount = 0;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    sum++;
                    if (length > 1024) {
                        throw new MyException(length, sum);
                    }

                    Matcher matcher = PATTERN.matcher(line);
                    if (matcher.find()) {
                        String userAgent = matcher.group(11);
                        String result = extractSpecificFragment(userAgent);
                        if ("Googlebot".equals(result)) {
                            googleBotCount++;
                        } else if ("YandexBot".equals(result)) {
                            yandexBotCount++;
                        }
                    } else {
                        System.err.println("Строка не соответствует формату");
                    }
                }
                System.out.println("общее количество строк в файле: " + sum);
                System.out.println("Доля запросов от Googlebot: " + (double) googleBotCount / sum);
                System.out.println("Доля запросов от YandexBot: " + (double) yandexBotCount / sum);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String extractSpecificFragment(String userAgent) {
        if (userAgent == null || userAgent.isEmpty()) return "";

        // 1. Выделяем часть в первых скобках с помощью RegEx
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(userAgent);

        if (matcher.find()) {
            String firstBrackets = matcher.group(1);

            // 2. Разделяем по ";"
            String[] parts = firstBrackets.split(";");

            // 3. Берем второй фрагмент (индекс 1), если он существует
            if (parts.length >= 2) {
                // 4. Очищаем от пробелов и берем часть до слэша
                String fragment = parts[1].trim();

                // Если есть слэш, отрезаем всё после него
                return fragment.contains("/")
                        ? fragment.substring(0, fragment.indexOf("/")).trim()
                        : fragment;
            }
        }

        return "Fragment not found";
    }
}