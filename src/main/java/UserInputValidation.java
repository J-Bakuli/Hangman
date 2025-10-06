package main.java;

public class UserInputValidation {
    public static boolean isInvalidInput(String str) {
        if (str == null || str.isEmpty()) {
            System.out.println("Ввод не может быть пустым");
            return true;
        }

        if (str.length() != 1) {
            System.out.printf("Пожалуйста, введите ровно одну букву, вы ввели '%s'%n", str);
            return true;
        }

        char ch = str.charAt(0);

        if (!Character.isLetter(ch)) {
            System.out.printf("Символ '%s' не является буквой%n", ch);
            return true;
        }

        if (!isRussianLetter(ch)) {
            System.out.printf("Символ '%s' не является буквой русского алфавита%n", ch);
            return true;
        }

        return false;
    }

    private static boolean isRussianLetter(char ch) {
        return Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.CYRILLIC;
    }
}
