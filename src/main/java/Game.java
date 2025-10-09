package main.java;

import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private static final char HIDDEN_CHARACTER = '_';
    private final String secretWord;
    private final int maxErrCount = 6;
    private final Set<Character> wrongLetters = new HashSet<>();
    private final Set<Character> guessedLetters = new HashSet<>();
    private int errCount = 0;

    public Game() {
        this.secretWord = Dictionary.selectRandomSecretWord();
    }

    public void start(Scanner scanner) {
        printInfo();
        boolean isGameWon = false;

        while (errCount < maxErrCount) {
            HangmanRenderer.display(errCount);

            boolean isWordGuessed = processUserInput(scanner);

            if (isWordGuessed) {
                System.out.println("Поздравляем, вы выиграли!");
                isGameWon = true;
                break;
            }
        }

        if (!isGameWon) {
            System.out.println("Игра окончена. Вы допустили максимальное количество ошибок.");
            System.out.println("Загаданное слово: " + secretWord);
        }
    }

    private boolean processUserInput(Scanner scanner) {
        boolean isWordGuessed = false;
        while (errCount < maxErrCount) {
            System.out.println("Введите букву, которая есть в слове: ");
            if (errCount > 0) {
                printWrongLetters(wrongLetters);
            }

            String str = scanner.nextLine().toLowerCase(Locale.ROOT).trim();

            if (!UserInputValidation.isValidGameInput(str)) {
                continue;
            }

            char inputLetter = Character.toLowerCase(str.charAt(0));

            if (isLetterAlreadyGuessed(inputLetter)) {
                System.out.println("Вы уже вводили букву '" + inputLetter + "'!");
                continue;
            }

            boolean isContains = isContainsInWord(inputLetter);
            updateGameState(inputLetter, isContains);
            displayGameStatus(inputLetter, isContains, errCount);

            if (checkWin()) {
                isWordGuessed = true;
                break;
            }

            if (errCount == maxErrCount) {
                break;
            }
        }
        return isWordGuessed;
    }

    private void printWrongLetters(Set<Character> set) {
        System.out.printf("Вы уже неверно ввели: %s%n", set);
    }

    private void printInfo() {
        System.out.printf("Максимальное число ошибок, которое можно допустить: %d%n", maxErrCount);
        String underline = "_".repeat(secretWord.length());
        System.out.println("Загаданное слово: " + underline);
    }

    private boolean isLetterAlreadyGuessed(char ch) {
        return (guessedLetters.contains(ch) || wrongLetters.contains(ch));
    }

    private boolean isContainsInWord(char ch) {
        return secretWord.indexOf(ch) != -1;
    }

    private String printLetterOrDashes(String word) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            if (guessedLetters.contains(currentChar)) {
                result.append(currentChar);
            } else {
                result.append(HIDDEN_CHARACTER);
            }
        }
        System.out.println(result);
        return result.toString();
    }

    private void updateGameState(char ch, boolean isContains) {
        if (isContains) {
            guessedLetters.add(ch);
            printLetterOrDashes(secretWord);
        } else {
            wrongLetters.add(ch);
            errCount++;
        }
    }

    private void displayGameStatus(char ch, boolean isContains, int errorCount) {
        HangmanRenderer.display(errorCount);
        System.out.printf("Содержит букву %s? - %s%n", ch, (isContains ? "да" : "нет"));
        System.out.printf("Осталось возможных ошибок: %d%n", maxErrCount - errorCount);
        System.out.printf("Допущено ошибок: %d%n", errorCount);
    }

    private boolean checkWin() {
        return printLetterOrDashes(secretWord).equals(secretWord);
    }
}

