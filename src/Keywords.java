import java.util.Random;

public class Keywords implements WordsSource {
    Random RANDOM = new Random();

    public static final String[] WORDS = {
            "KSIĘŻYC", "PIASEK", "MORZE", "BAŁTYK", "WAKACJE", "WIRUS", "KWARANTANNA", "URODZINY",
            "LAMBORGINI", "MERCEDES", "PIGUŁA", "SZTUKA", "SIEKIERA", "WARAN", "PODŁOGA"
    };

    @Override
    public String getWordToGuess() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }
}
