import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordsFromFile implements Word{
    private ArrayList<String> wordsFromFile = new ArrayList<>();

    private void addWord(String word) {
        wordsFromFile.add(word);
    }

    @Override
    public String nextWordToFind() {
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
