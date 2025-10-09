package main.java;

import java.util.Locale;
import java.util.Scanner;

public class App {
    private static Scanner scanner;

    public App () {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        try {
            displayMenu();
            handleUserInput();
        }
        finally {
            scanner.close();
        }
    }

    private static void displayMenu() {
        System.out.println("Начать новую игру - нажмите Н. Выйти из игры - нажмите В");
    }

    private static void handleUserInput() {
        boolean isRunning = true;

        try {
            while (isRunning) {
                String input = readAndValidateInput();

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
        } finally {
            scanner.close();
        }
    }

    private static void startNewGame() {
        Game game = new Game();
        game.start(scanner);

        displayMenu();
    }

    static String readAndValidateInput() {
        String input = readInput();

        while (!UserInputValidation.isValidInput(input)) {
            input = readInput();
        }
        return input;
    }

    static String readInput() {
        return scanner.nextLine().toLowerCase(Locale.ROOT).trim();
    }
}
