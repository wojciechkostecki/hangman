import java.util.Random;

public class Keywords {
    private static final Random RANDOM = new Random();

    public static final String[] WORDS = {
            "KSIĘŻYC", "PIASEK", "MORZE", "BAŁTYK", "WAKACJE", "WIRUS", "KWARANTANNA", "URODZINY",
            "LAMBORGINI", "MERCEDES", "PIGUŁA", "SZTUKA", "SIEKIERA", "WARAN", "PODŁOGA"
    };

    public String nextWordToFind() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }
}
