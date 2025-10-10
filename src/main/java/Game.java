package main.java;

import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private static final char HIDDEN_CHARACTER = '_';
    private final String secretWord;
    private String secretWordMask = null;
    private final int maxErrCount = 6;
    private boolean isLetterAlreadyGuessed = false;
    private final Set<Character> wrongLetters = new HashSet<>();
    private final Set<Character> guessedLetters = new HashSet<>();
    private int errCount = 0;
    private boolean isContainedInWord = false;

    public Game() {
        this.secretWord = Dictionary.selectRandomSecretWord();
    }

    public void start(Scanner scanner) {
        printInfo();

        while (errCount < maxErrCount) {
            displayHangmanRender(errCount);
            handleUserInput(scanner);

            if (isWin()) {
                System.out.println("Поздравляем, вы выиграли!");
                return;
            }
        }
        System.out.println("Игра окончена. Вы допустили максимальное количество ошибок.");
        System.out.println("Загаданное слово: " + secretWord);
    }

    private void handleUserInput(Scanner scanner) {
        secretWordMask = getSecretWordMask(secretWord);

        String userInput = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
        char inputLetter = userInput.charAt(0);

        if (!UserInputValidation.isValidGameInput(userInput)) {
            return;
        }

        if (isLetterAlreadyGuessed(inputLetter)) {
            handleAlreadyGuessedLetter(inputLetter);
            return;
        }

        updateGameState(inputLetter);
    }

    private void printWrongLetters(Set<Character> set) {
        if (errCount > 0) {
            System.out.printf("Вы уже неверно ввели: %s%n", set);
        }
    }

    private void printInfo() {
        System.out.printf("Максимальное число ошибок, которое можно допустить: %d%n", maxErrCount);
        String underline = "_".repeat(secretWord.length());
        System.out.println("Загаданное слово: " + underline);
        System.out.println("Введите букву, которая есть в слове: ");
    }

    private boolean isLetterAlreadyGuessed(char ch) {
        isLetterAlreadyGuessed = (guessedLetters.contains(ch) || wrongLetters.contains(ch));
        return isLetterAlreadyGuessed;
    }

    private void handleAlreadyGuessedLetter(char ch) {
        displayGameState(ch, false, errCount, secretWordMask, true);
    }

    private boolean isContainedInWord(char ch) {
        return secretWord.indexOf(ch) != -1;
    }

    private String getSecretWordMask(String word) {
        StringBuilder secretWordMask = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            if (guessedLetters.contains(currentChar)) {
                secretWordMask.append(currentChar);
            } else {
                secretWordMask.append(HIDDEN_CHARACTER);
            }
        }
        return secretWordMask.toString();
    }

    private void updateGameState(char ch) {
        isContainedInWord = isContainedInWord(ch);

        if (isContainedInWord) {
            guessedLetters.add(ch);
            getSecretWordMask(secretWord);
        } else {
            wrongLetters.add(ch);
            errCount++;
        }
        displayGameState(ch, isContainedInWord, errCount, secretWordMask, isLetterAlreadyGuessed);
    }

    private void displayHangmanRender(int errCount) {
        HangmanRenderer.display(errCount);
    }

    private void displayGameState(char ch, boolean isContains, int errorCount, String secretWordMask,
                                  boolean isAlreadyGuessed) {
        HangmanRenderer.display(errorCount);

        if (isAlreadyGuessed) {
            System.out.println(secretWordMask);
            System.out.println("Вы уже вводили букву '" + ch + "'!");
        } else {
            System.out.printf("Содержит букву %s? - %s%n", ch, (isContains ? "да" : "нет"));
            System.out.printf("Осталось возможных ошибок: %d%n", maxErrCount - errorCount);
            System.out.printf("Допущено ошибок: %d%n", errorCount);
            printWrongLetters(wrongLetters);
            System.out.println(getSecretWordMask(secretWord));
            System.out.println("Введите букву, которая есть в слове: ");
        }
    }

    private boolean isWin() {
        return getSecretWordMask(secretWord).equals(secretWord);
    }
}

