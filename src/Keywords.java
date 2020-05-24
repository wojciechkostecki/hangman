public class Keywords implements Word{
       public static final String[] WORDS = {
            "KSIĘŻYC", "PIASEK", "MORZE", "BAŁTYK", "WAKACJE", "WIRUS", "KWARANTANNA", "URODZINY",
            "LAMBORGINI", "MERCEDES", "PIGUŁA", "SZTUKA", "SIEKIERA", "WARAN", "PODŁOGA"
    };

    @Override
    public String nextWordToFind() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }
}
