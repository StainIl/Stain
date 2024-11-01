package org.knit.lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task10 {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner userInput = new Scanner(System.in);

        List<String> wordPool = loadWords();

        char[] hiddenWord = wordPool.get(rand.nextInt(wordPool.size())).toCharArray();
        boolean[] revealedIndices = new boolean[hiddenWord.length];
        int remainingAttempts = 6; // Количество попыток
        boolean isWordGuessed = false;
        Set<Character> enteredLetters = new HashSet<>(); // Хранит введенные символы

        System.out.println("Добро пожаловать в игру Виселица!");
        System.out.println("Угадайте слово. У вас " + remainingAttempts + " попыток.");
        System.out.println(displayHiddenWord(hiddenWord, revealedIndices));

        while (remainingAttempts > 0 && !isWordGuessed) {
            System.out.print("Введите букву: ");
            char guess = userInput.next().charAt(0);

            // Проверка на повторный ввод
            if (enteredLetters.contains(guess)) {
                System.out.println("Эта буква уже была введена. Попробуйте другую.");
                continue;
            }

            enteredLetters.add(guess);

            // Проверка символа и обновление состояния
            if (!checkLetter(guess, hiddenWord, revealedIndices)) {
                remainingAttempts--;
                System.out.println("Неправильно! Осталось попыток: " + remainingAttempts);
            } else {
                System.out.println("Верно!");
            }

            // Отображение текущего состояния слова
            String currentView = displayHiddenWord(hiddenWord, revealedIndices);
            System.out.println(currentView);

            if (!currentView.contains("_")) {
                isWordGuessed = true;
            }
        }
        if (isWordGuessed) {
            System.out.println("Поздравляем! Вы отгадали слово: " + new String(hiddenWord));
        } else {
            System.out.println("Вы проиграли! Загаданное слово было: " + new String(hiddenWord));
        }
    }

    public static String displayHiddenWord(char[] wordArray, boolean[] revealedPositions) {
        char[] wordDisplay = new char[wordArray.length];
        for (int i = 0; i < wordArray.length; i++) {
            if (revealedPositions[i]) {
                wordDisplay[i] = wordArray[i];
            } else {
                wordDisplay[i] = '_';
            }
        }
        return new String(wordDisplay);
    }

    public static boolean checkLetter(char letter, char[] wordArray, boolean[] revealedPositions) {
        boolean isFound = false;
        for (int i = 0; i < wordArray.length; i++) {
            if (letter == wordArray[i]) {
                revealedPositions[i] = true;
                isFound = true;
            }
        }
        return isFound; // Возвращает true, если буква найдена
    }

    public static List<String> loadWords() {
        File dictionaryFile = new File("src/misc/dictionary.txt");
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(dictionaryFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Не удалось найти файл словаря. Проверьте путь к файлу.");
        }

        List<String> wordList = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String wordEntry = fileScanner.nextLine().trim();
            if (wordEntry.length() > 4) {
                wordList.add(wordEntry);
            }
        }
        fileScanner.close();
        return wordList;
    }
}
