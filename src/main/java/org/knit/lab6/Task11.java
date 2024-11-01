package org.knit.lab6;

import java.util.Scanner;
import java.util.TreeSet;

public class Task11 {
    private static TreeSet<String> students = new TreeSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавить студента");
            System.out.println("2. Удалить студента");
            System.out.println("3. Показать всех студентов");
            System.out.println("4. Найти студента");
            System.out.println("5. Выйти");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // очистка ввода после выбора опции

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    removeStudent(scanner);
                    break;
                case 3:
                    showAllStudents();
                    break;
                case 4:
                    findStudent(scanner);
                    break;
                case 5:
                    System.out.println("Выход.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный действие. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Введите имя студента: ");
        String studentName = scanner.nextLine();
        if (students.contains(studentName)) {
            System.out.println("Студент с таким именем уже существует.");
        } else {
            students.add(studentName);
            System.out.println("Студент добавлен.");
        }
    }

    private static void removeStudent(Scanner scanner) {
        System.out.print("Введите имя студента для удаления: ");
        String studentName = scanner.nextLine();
        if (students.remove(studentName)) {
            System.out.println("Студент удален.");
        } else {
            System.out.println("Студент не найден.");
        }
    }

    private static void showAllStudents() {
        System.out.println("Список студентов:");
        if (students.isEmpty()) {
            System.out.println("Список пуст.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void findStudent(Scanner scanner) {
        System.out.print("Введите имя студента для поиска: ");
        String studentName = scanner.nextLine();
        if (students.contains(studentName)) {
            System.out.println("Студент найден.");
        } else {
            System.out.println("Студент не найден.");
        }
    }
}
