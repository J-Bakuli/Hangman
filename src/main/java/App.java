package main.java;

import java.util.Locale;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public void run() {
        try {
            displayMenu();
            handleUserInput();
        } finally {
            scanner.close();
        }
    }

    private static void displayMenu() {
        System.out.println("Начать новую игру - нажмите Н. Выйти из игры - нажмите В");
    }

    private static void handleUserInput() {
        boolean isRunning = true;

        while (isRunning) {
            String input = readInput();
            validateInput(input);

            switch (input) {
                case "н":
                    startNewGame();
                    break;
                case "в":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Сделайте выбор снова.");
            }
        }
    }

    private static void startNewGame() {
        Game game = new Game();
        game.start(scanner);

        displayMenu();
    }

    private static String readInput() {
        return scanner.nextLine().toLowerCase(Locale.ROOT).trim();
    }

    private static void validateInput(String input) {
        while (!UserInputValidation.isValidMenuInput(input)) {
            input = readInput();
        }
    }
}
