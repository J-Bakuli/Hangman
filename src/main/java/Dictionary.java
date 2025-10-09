package main.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private static final List<String> dictionary = initializeDictionary();
    private static final Random RANDOM = new Random();

    private static final String PATH = "src/main/resources/data/dictionary";

    private static List<String> initializeDictionary() {
        try {
            return loadDictionary();
        } catch (IOException e) {
            throwIOException();
            return Collections.emptyList();
        }
    }

    public static List<String> loadDictionary() throws IOException {
        Path path = Paths.get(PATH);
        String content = Files.readString(path);

        return Arrays.stream(content.split(","))
                .map(String::trim)
                .filter(word -> !word.isEmpty())
                .toList();
    }

    public static String selectRandomSecretWord() {
        if (dictionary == null || dictionary.isEmpty()) {
            throwEmptyDictionaryError();
        }
        int dictionarySize = dictionary.size();

        int randomIndex = RANDOM.nextInt(dictionarySize);

        String randomSecretWord = dictionary.get(randomIndex);

        return randomSecretWord;
    }

    private static void throwEmptyDictionaryError() {
        throw new RuntimeException("Dictionary in path %s is empty. Please add words for the game".formatted(PATH));
    }

    private static void throwIOException() {
        throw new RuntimeException("Error reading dictionary from path %s".formatted(PATH));
    }
}