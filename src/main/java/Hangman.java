package main.java;

public class Hangman {
    private Hangman() {
    }

    public static void display(int errorCount) {
        render(errorCount);
    }

    private static void render(int errorCount) {
        System.out.println(" =========");
        System.out.println(" |       |");
        System.out.println(" |       " + (errorCount > 0 ? "O" : ""));
        System.out.println(" |      " +
                (errorCount > 1 ? "/" : "") +
                (errorCount > 2 ? "|" : "") +
                (errorCount > 3 ? "\\" : ""));
        System.out.println(" |      " +
                (errorCount > 4 ? "/" : "") +
                (errorCount > 5 ? " \\" : ""));
        System.out.println(" |");
        System.out.println(" |");
        System.out.println(" |______");
    }
}
