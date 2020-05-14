import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Game {
    public static final Random RANDOM = new Random();
    private final static int PLAY = 1;
    private final static int EXIT = 2;
    Scanner scanner = new Scanner(System.in);
    public static final int MAX_ERRORS = 8;
    private String wordToFind;
    private char[] wordFound;
    private int nbErrors;
    private ArrayList<String> letters = new ArrayList<>();
    private ArrayList<String> lettersFromFile = new ArrayList<>();

    public void hangman(){
    boolean isException = true;
        while (isException) {
        try {
            int option = -1;
            while (option != EXIT) {

                System.out.println("Wybierz opcję:");
                System.out.println("1 - graj");
                System.out.println("2 - wyjście z programu.");
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case EXIT:
                        System.out.println("Bye bye!");
                        scanner.close();
                        break;
                    case PLAY:
                        play();
                        break;
                    default:
                        System.out.println("Wybrałeś nieprawidłową opcję");
                }
            }
            isException = false;
        } catch (InputMismatchException e) {
            System.out.println("Podałeś niepoprawny znak.");
            scanner.nextLine();
        }
    }
}

    private String nextWordToFind() {
        return Keywords.WORDS[RANDOM.nextInt(Keywords.WORDS.length)];
    }

    public void addWord(String word) {
        lettersFromFile.add(word);
    }

    private String wordToFindFromFile() {
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
        return lettersFromFile.get(RANDOM.nextInt(lettersFromFile.size())).toUpperCase();
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
        try {
            while (nbErrors < MAX_ERRORS) {
                System.out.println("Hasło do zgadnięcia: " + '\n' + wordFoundContent());
                System.out.println("Podaj literę: ");
                String letter = scanner.next().toUpperCase();

                if (letter.length() > 1) {
                    letter = letter.substring(0, 1);
                }

                enter(letter);

                if (wordFound()) {
                    System.out.println("Wygrałeś");
                    System.out.println("Hasło: " + wordToFind);
                    break;
                } else {
                    System.out.println("Pozostało prób: " + (MAX_ERRORS - nbErrors));
                }
            }
            if (nbErrors == MAX_ERRORS) {
                System.out.println("Przegrałeś");
                System.out.println("Szukane słowo: " + wordToFind);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}