public class MyException extends RuntimeException {
    public MyException(Integer length, Integer line) {
        super("Длина строки не должна превышать 1024 символа, текущее количество символов " + length + " в строке " + line);
    }
}
