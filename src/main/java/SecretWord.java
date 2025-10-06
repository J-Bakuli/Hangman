package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SecretWord {
    private static List<String> dictionary;

    static {
        loadDictionary();
    }

    public static void loadDictionary() {
        try {
            Path path = Paths.get("src/main/resources/data/dictionary");
            String content = Files.readString(path);

            dictionary = Arrays.stream(content.split(","))
                    .map(String::trim)
                    .filter(word -> !word.isEmpty())
                    .toList();

            if (dictionary.isEmpty()) {
                handleEmptyFileError();
            }
        } catch (IOException e) {
            handleIOException(e);
        } catch (IllegalArgumentException e) {
            handleIllegalArgumentError(e);
        } catch (Exception e) {
            handleUnexpectedError(e);
        }
    }

    public static Optional<String> selectRandomSecretWord() {
        if (dictionary == null || dictionary.isEmpty()) {
            handleEmptyDictionaryError();
            return Optional.empty();
        }
        return Optional.of(dictionary.get(new Random().nextInt(dictionary.size())));
    }

    private static void handleEmptyFileError() {
        System.err.println("Файл слов пуст");
    }

    private static void handleIOException(IOException e) {
        System.err.println("Ошибка при чтении файла: " + e.getMessage());
        e.printStackTrace();
    }

    private static void handleEmptyDictionaryError() {
        System.err.println("Словарь не загружен или пуст");
    }

    private static void handleIllegalArgumentError(IllegalArgumentException e) {
        System.err.println("Ошибка при обработке данных: " + e.getMessage());
        e.printStackTrace();
    }

    private static void handleUnexpectedError(Exception e) {
        System.err.println("Непредвиденная ошибка: " + e.getMessage());
        e.printStackTrace();
    }
}