package main.java;

public class Main {
    public static void main(String[] args) {
        try {
            App app = new App();
            app.run();
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}
