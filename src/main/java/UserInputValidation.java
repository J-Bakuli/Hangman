package main.java;

public class UserInputValidation {
    public static boolean isValidInput(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("Ввод не может быть пустым");
            return false;
        }

        if (str.length() != 1) {
            System.out.printf("Пожалуйста, введите ровно одну букву, вы ввели '%s'%n", str);
            return false;
        }

        char ch = str.charAt(0);

        if (!Character.isLetter(ch)) {
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
}
