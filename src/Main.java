import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число: ");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число: ");
        int secNumber = new Scanner(System.in).nextInt();

        int addition = firstNumber + secNumber;
        System.out.println("Сумма: " + addition);

        int subtraction = firstNumber - secNumber;
        System.out.println("Разность: " + subtraction);

        int multiply = firstNumber * secNumber;
        System.out.println("Произведение: " + multiply);

        if (secNumber == 0) {
            System.out.println("Второе число не должно равняться нулю");
        } else {
            double division = (double) firstNumber / secNumber;
            System.out.println("Частное: " + division);
        }
    }
}