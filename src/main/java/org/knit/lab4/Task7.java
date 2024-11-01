package org.knit.lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task7 {
    public static void main(String[] args) {

        File dictFile = new File("src/misc/dictionary.txt");
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(dictFile);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
            System.exit(1);
        }

        StringBuilder allWordsBuffer = new StringBuilder();
        int palindromesCount = 0;

        while (fileScanner.hasNextLine()) {
            String lineWord = fileScanner.nextLine();
            allWordsBuffer.append(lineWord).append(" ");
            if (isSymmetric(lineWord)) {
                palindromesCount++;
            }
        }
        fileScanner.close();

        String[] wordArray = allWordsBuffer.toString().trim().split("\\s+");
        WordStats stats = new WordStats(wordArray, palindromesCount);

        stats.computeData();
        System.out.println(stats.displayData());

        System.out.println("Случайное слово: " + stats.fetchRandomWord());

        findWords("Мама", wordArray);
    }

    public static boolean isSymmetric(String str) {
        return new StringBuilder(str).reverse().toString().equalsIgnoreCase(str);
    }

    public static void findWords(String baseWord, String[] wordSet) {
        Map<Character, Integer> baseCountMap = new HashMap<>();
        for (char ch : baseWord.toLowerCase().toCharArray()) {
            baseCountMap.put(ch, baseCountMap.getOrDefault(ch, 0) + 1);
        }

        System.out.println("Слова, которые можно составить из \"" + baseWord + "\":");
        for (String candidate : wordSet) {
            if (canConstruct(candidate, baseCountMap)) {
                System.out.println(candidate);
            }
        }
    }

    private static boolean canConstruct(String candidate, Map<Character, Integer> baseCountMap) {
        Map<Character, Integer> tempMap = new HashMap<>();
        for (char ch : candidate.toLowerCase().toCharArray()) {
            tempMap.put(ch, tempMap.getOrDefault(ch, 0) + 1);
            if (tempMap.get(ch) > baseCountMap.getOrDefault(ch, 0)) {
                return false;
            }
        }
        return true;
    }
}

class WordStats {
    private String[] dictWords;
    private int totalWords;
    private int palindromeTotal;
    private int maxLen;
    private int minLen;
    private Random randomizer;
    private Map<Character, Integer> charFrequency;

    public WordStats(String[] words, int palindromeCount) {
        this.dictWords = words;
        this.palindromeTotal = palindromeCount;
        this.totalWords = words.length;
        this.randomizer = new Random();
        this.charFrequency = new TreeMap<>();
        this.maxLen = Integer.MIN_VALUE;
        this.minLen = Integer.MAX_VALUE;
    }

    public void computeData() {
        for (String word : dictWords) {
            int currentLen = word.length();
            maxLen = Math.max(maxLen, currentLen);
            minLen = Math.min(minLen, currentLen);

            for (char ch : word.toLowerCase().toCharArray()) {
                if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.CYRILLIC) {
                    charFrequency.put(ch, charFrequency.getOrDefault(ch, 0) + 1);
                }
            }
        }
    }

    public String displayData() {
        return String.format(
                "Всего слов: %d%nПалиндромов: %d%nМаксимальная длина слова: %d%nМинимальная длина слова: %d%nЧастота букв: %s",
                totalWords, palindromeTotal, maxLen, minLen, charFrequency
        );
    }

    public String fetchRandomWord() {
        return dictWords[randomizer.nextInt(dictWords.length)];
    }
}
