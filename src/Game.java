import java.util.*;

public class Game {
    public static final Random RANDOM = new Random();
    public static final int MAX_ERRORS = 8;
    private String wordToFind;
    private char[] wordFound;
    private int nbErrors;
    private ArrayList<String> letters = new ArrayList<>();

    private String nextWordToFind() {
        return Keywords.WORDS[RANDOM.nextInt(Keywords.WORDS.length)];
    }

    public void newGame() {
        nbErrors = 0;
        letters.clear();
        wordToFind = nextWordToFind();
        wordFound = new char[wordToFind.length()];

        Arrays.fill(wordFound, '*');
    }

    public boolean wordFound() {
        return wordToFind.contentEquals(new String(wordFound));
    }

    private String wordFoundContent() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < wordFound.length; i++) {
            sb.append(wordFound[i]);

            if (i < wordFound.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private void enter(String c){
        if(!letters.contains(c)){
            if (wordToFind.contains(c)){
                int index = wordToFind.indexOf(c);

                while (index >= 0){
                    wordFound[index] = c.charAt(0);
                    index = wordToFind.indexOf(c,index + 1);
                }
            } else {
                nbErrors++;
            }
            letters.add(c);
        }
    }

    public void play(){
        newGame();
        try(Scanner scanner = new Scanner(System.in)){
            while (nbErrors < MAX_ERRORS){
                System.out.println("Hasło do zgadnięcia: " + '\n' + wordFoundContent());
                System.out.println("Podaj literę: ");
                String letter = scanner.next().toUpperCase();

                if (letter.length()>1){
                    letter = letter.substring(0,1);
                }

                enter(letter);

                if(wordFound()){
                    System.out.println("Wygrałeś");
                    System.out.println("Hasło: " + wordToFind);
                    break;
                }else {
                    System.out.println("Pozostało prób: " + (MAX_ERRORS - nbErrors));
                }
            }
            if (nbErrors == MAX_ERRORS){
                System.out.println("Przegrałeś");
                System.out.println("Szukane słowo: " + wordToFind);
            }
        }
    }
}