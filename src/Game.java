import java.util.*;

public class Game {
    private final static int PLAY = 1;
    private final static int EXIT = 2;
    private static final int MAX_ERRORS = 8;

    private Scanner scanner = new Scanner(System.in);

    private String wordToGuess;
    private char[] guessedWord;
    private int mistakesMade;
    private ArrayList<String> words = new ArrayList<>();

    private Keywords keywords = new Keywords();
    private WordsFromFile wordsFromFile = new WordsFromFile();

    public void hangman() {
        int option = -1;
        while (option != EXIT) {

            printOptions();

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
    }

    private void printOptions(){
        System.out.println("Wybierz opcję:");
        System.out.println("1 - graj");
        System.out.println("2 - koniec programu");
    }

    private void newGame() {
        mistakesMade = 0;
        words.clear();
        wordToGuess = wordsFromFile.getWordToGuess();
        guessedWord = new char[wordToGuess.length()];

        Arrays.fill(guessedWord, '*');
    }

    private boolean isWordFound() {
        return wordToGuess.contentEquals(new String(guessedWord));
    }

    private String wordFoundContent() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < guessedWord.length; i++) {
            sb.append(guessedWord[i]);

            if (i < guessedWord.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private void checkLetter(String c) {
        if (!words.contains(c)) {
            if (wordToGuess.contains(c)) {
                int index = wordToGuess.indexOf(c);

                while (index >= 0) {
                    guessedWord[index] = c.charAt(0);
                    index = wordToGuess.indexOf(c, index + 1);
                }
            } else {
                mistakesMade++;
            }
            words.add(c);
        }
    }

    private void play() {
        newGame();
        while (mistakesMade < MAX_ERRORS) {
            System.out.println("Hasło do zgadnięcia: " + '\n' + wordFoundContent());
            System.out.println("Podaj literę: ");
            String letter = scanner.next().toUpperCase();

            if (letter.length() > 1) {
                letter = letter.substring(0, 1);
            }

            checkLetter(letter);

            if (isWordFound()) {
                System.out.println("Wygrałeś");
                System.out.println("Hasło: " + wordToGuess);
                break;
            } else {
                System.out.println("Pozostało prób: " + (MAX_ERRORS - mistakesMade));
            }
        }
        if (mistakesMade == MAX_ERRORS) {
            System.out.println("Przegrałeś");
            System.out.println("Szukane słowo: " + wordToGuess);
        }
    }
}