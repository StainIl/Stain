package org.knit.lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Task9 {

    public static void main(String[] args) {
        List<String> wordList = loadWords();

        Random rand = new Random();
        Scanner inputScanner = new Scanner(System.in);

        int totalWords = wordList.size();

        // Получаем текущее время и время завершения (через минуту)
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 60000;

        int wordCount = 0;
        int correctWordCount = 0;
        int charCount = 0;

        // Запуск теста на 1 минуту
        while (System.currentTimeMillis() < endTime) {
            int randomIndex = rand.nextInt(totalWords);
            String generatedWord = wordList.get(randomIndex);
            wordCount++;
            System.out.println("Введите слово: " + generatedWord);
            String userWord = inputScanner.next();

            System.out.println("Прошло " + ((System.currentTimeMillis() - startTime) / 1000) + " секунд.");
            charCount += userWord.length();
            if ((generatedWord.equalsIgnoreCase(userWord)) && (System.currentTimeMillis() <= endTime)) {
                correctWordCount++;
            }
        }

        // Вывод результатов
        System.out.println("Всего слов: " + wordCount);
        System.out.println("Верных слов: " + correctWordCount);
        System.out.println("Общее количество символов: " + charCount);
        System.out.printf("Символов в секунду: %.5f", (double) charCount / 60);
    }

    // Читает слова из файла и возвращает список
    public static List<String> loadWords() {
        File dictionaryFile = new File("src/misc/dictionary.txt");
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(dictionaryFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл словаря не найден: " + e);
        }

        List<String> wordList = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String word = fileScanner.nextLine();
            wordList.add(word);
        }
        fileScanner.close();
        return wordList;
    }
}
