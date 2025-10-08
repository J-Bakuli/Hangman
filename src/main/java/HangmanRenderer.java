package main.java;

public final class HangmanRenderer {
    private static final String[] HANGMAN_STAGES = {
            " =========" + System.lineSeparator() +
                    " |       |" + System.lineSeparator() +
                    " |       " + System.lineSeparator() +
                    " |       " + System.lineSeparator() +
                    " |       " + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |______",

            " =========" + System.lineSeparator() +
                    " |       |" + System.lineSeparator() +
                    " |       O" + System.lineSeparator() +
                    " |       " + System.lineSeparator() +
                    " |       " + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |______",

            " =========" + System.lineSeparator() +
                    " |       |" + System.lineSeparator() +
                    " |       O" + System.lineSeparator() +
                    " |       |" + System.lineSeparator() +
                    " |       " + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |______",

            " =========" + System.lineSeparator() +
                    " |       |" + System.lineSeparator() +
                    " |       O" + System.lineSeparator() +
                    " |      /|" + System.lineSeparator() +
                    " |       " + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |______",

            " =========" + System.lineSeparator() +
                    " |       |" + System.lineSeparator() +
                    " |       O" + System.lineSeparator() +
                    " |      /|\\" + System.lineSeparator() +
                    " |       " + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |______",

            " =========" + System.lineSeparator() +
                    " |       |" + System.lineSeparator() +
                    " |       O" + System.lineSeparator() +
                    " |      /|\\" + System.lineSeparator() +
                    " |      /" + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |______",

            " =========" + System.lineSeparator() +
                    " |       |" + System.lineSeparator() +
                    " |       O" + System.lineSeparator() +
                    " |      /|\\" + System.lineSeparator() +
                    " |      / \\" + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |" + System.lineSeparator() +
                    " |______"
    };

    private static final int MAX_ERRORS = HANGMAN_STAGES.length - 1;

    private HangmanRenderer() {
    }

    public static String getStage(int errorCount) {
        if (errorCount < 0) {
            throw new IllegalArgumentException("Количество ошибок не может быть отрицательным");
        }
        return HANGMAN_STAGES[Math.min(errorCount, MAX_ERRORS)];
    }

    public static void display(int errorCount) {
        render(errorCount);
    }

    public static void render(int count) {
        System.out.println(getStage(count));
    }
}
