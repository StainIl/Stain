package org.knit.lab2;

import java.util.ArrayList;
import java.util.List;

public class Task5 {
    public static void main(String[] args) {
        FileSystemComponent file1 = new File("file1.txt", 100);
        FileSystemComponent file2 = new File("file2.txt", 200);
        FileSystemComponent file3 = new File("file3.txt", 300);

        FileSystemComponent folder1 = new Folder("Folder1");
        FileSystemComponent folder2 = new Folder("Folder2");
        
        List<Integer> numbers = new ArrayList<>();

        // Добавляем файлы в папки
        folder1.add(file1);
        folder1.add(file2);

        folder2.add(file3);
        folder2.add(folder1); // Вложенная папка

        // Выводим структуру файловой системы
        folder2.display("");

        // Выводим общий размер папки 2
        System.out.println("Total size of Folder2: " + folder2.getSize() + " bytes");
    }
}

// Абстрактный класс, представляющий компонент файловой системы
abstract class FileSystemComponent {
    public abstract String getName();

    public abstract double getSize();

    // Добавление компонента (для файлов будет вызвано исключение)
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Нельзя добавить компонент к файлу!");
    }

    // Удаление компонента (для файлов будет вызвано исключение)
    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Нельзя удалить компонент из файла!");
    }

    // Метод для отображения структуры файловой системы
    public void display(String indent) {
        System.out.println(indent + getName());
    }
}

// Класс File для файловой системы, представляющий файл
class File extends FileSystemComponent {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    // Отображает файл с информацией о размере
    public void display(String indent) {
        System.out.println(indent + getName() + " (" + getSize() + " bytes)");
    }
}

// Класс Folder для файловой системы, представляющий папку
class Folder extends FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Метод, который рассчитывает общий размер папки
    public double getSize() {
        double totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }

    // Добавление компонента в папку
    public void add(FileSystemComponent component) {
        components.add(component);
    }

    // Удаление компонента из папки
    public void remove(FileSystemComponent component) {
        components.remove(component);
    }

    // Отображает папку и её содержимое с соответствующими отступами
    public void display(String indent) {
        System.out.println(indent + getName());
        for (FileSystemComponent component : components) {
            component.display(indent + "\t");
        }
    }
}
