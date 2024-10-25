package org.knit.lab1;

/***
 * Задача 1
 * Печать чисел от 1 до 100 через запятую с переносом на новую строку каждые 10 чисел
 ***/
public class Task1 {
    public static void main(String[] args) {

        String separator = ","; // Разделитель
        int start = 1;
        int end = 100;
        int lineBreak = 10;

        for (int num = start; num <= end; num++) {
            System.out.print(num);

            if (num % lineBreak == 0) {
                System.out.println(); // Новая строка каждые 10 чисел
            } else {
                System.out.print(separator); // Запятая между числами
            }
        }
    }
}
