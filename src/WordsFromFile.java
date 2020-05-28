import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordsFromFile implements WordsSource {
    Random RANDOM = new Random();
    private ArrayList<String> wordsFromFile = new ArrayList<>();

    private void addWord(String word) {
        wordsFromFile.add(word);
    }

    @Override
    public String getWordToGuess() {
        String fileName = "words.txt";
        try (
                var fileReader = new FileReader(fileName);
                var bufferedReader = new BufferedReader(fileReader);
        ) {
            while (bufferedReader.readLine() != null) {
                addWord(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordsFromFile.get(RANDOM.nextInt(wordsFromFile.size())).toUpperCase();
    }
}
