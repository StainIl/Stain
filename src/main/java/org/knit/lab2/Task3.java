package org.knit.lab2;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите первое число (Для выхода из программы введите \"exit\"): ");
            String input = scanner.next();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Завершение программы.");
                break;
            }

            double firstNumber;
            try {
                firstNumber = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
                continue;
            }

            System.out.print("Введите оператор (+, -, *, /): ");
            String operator = scanner.next();

            System.out.print("Введите второе число: ");
            double secondNumber;
            try {
                secondNumber = scanner.nextDouble();
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");
                scanner.next(); // очищаем некорректный ввод
                continue;
            }

            Calculator calculator = new Calculator();

            try {
                double result;
                switch (operator) {
                    case "+":
                        result = calculator.add(firstNumber, secondNumber);
                        System.out.println("Результат: " + result);
                        break;
                    case "-":
                        result = calculator.subtract(firstNumber, secondNumber);
                        System.out.println("Результат: " + result);
                        break;
                    case "*":
                        result = calculator.multiply(firstNumber, secondNumber);
                        System.out.println("Результат: " + result);
                        break;
                    case "/":
                        result = calculator.divide(firstNumber, secondNumber);
                        System.out.println("Результат: " + result);
                        break;
                    default:
                        System.out.println("Введён неизвестный оператор!");
                }
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}

class Calculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Ошибка: Деление на ноль невозможно.");
        }
        return a / b;
    }
}
