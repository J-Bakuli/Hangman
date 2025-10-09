package main.java;

import java.util.Arrays;
import java.util.List;

public final class UserInputValidation {
    private UserInputValidation() {
    }

    public static boolean isValidMenuInput(String str) {
        List<String> validCommands = Arrays.asList("н", "в");

        if (!checkBasicInput(str)) {
            return false;
        }

        if (!validCommands.contains(str.toLowerCase())) {
            System.out.printf("Пожалуйста, введите Н или В, вы ввели '%s'%n", str);
            return false;
        }

        return true;
    }

    public static boolean isValidGameInput(String str) {
        return checkBasicInput(str);
    }

    private static boolean checkBasicInput(String str) {
        if (isEmptyInput(str)) {
            System.out.println("Ввод не может быть пустым");
            return false;
        }

        if (!isSingleCharacter(str)) {
            System.out.printf("Пожалуйста, введите ровно одну букву, вы ввели '%s'%n", str);
            return false;
        }

        char ch = str.charAt(0);

        if (!isCharacter(ch)) {
            System.out.printf("Символ '%s' не является буквой%n", ch);
            return false;
        }

        if (!isRussianLetter(ch)) {
            System.out.printf("Символ '%s' не является буквой русского алфавита%n", ch);
            return false;
        }

        return true;
    }

    private static boolean isRussianLetter(char ch) {
        return (ch >= 'А' && ch <= 'Я') ||
                (ch >= 'а' && ch <= 'я') ||
                ch == 'Ё' ||
                ch == 'ё';
    }

    private static boolean isEmptyInput(String str) {
        return str == null || str.isEmpty();
    }

    private static boolean isCharacter(char ch) {
        return Character.isLetter(ch);
    }

    private static boolean isSingleCharacter(String str) {
        return str.length() == 1;
    }
}
