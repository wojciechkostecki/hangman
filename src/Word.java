import java.util.Random;

public interface Word {
    Random RANDOM = new Random();

    String nextWordToFind();
}
