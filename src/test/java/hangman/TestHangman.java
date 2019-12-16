package hangman;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestHangman {
    private static Hangman hangman;
    private static Random random;
    private static int requestedLength;

    @BeforeAll
    public static void setupClass() {
        hangman = new Hangman();
        random = new Random();
        hangman.loadsWord();
    }

    @BeforeEach
    public void setupHangman() {
        requestedLength = random.nextInt(6) + 5;
        hangman.score = 0;
    }

    @Test
    void test_alphabetCountInWord() {
        String word = "pizza";
        char alphabet = 'a';
        int count = hangman.countAlphabet(word, alphabet);
        assertEquals(1, count);
    }

    @Test
    void test_lengthOfFetchedWordRandom() {
        String word = hangman.fetchWord(requestedLength);
        assertTrue(word.length() == requestedLength);
    }

    @Test
    void test_uniquenessOffFetchedWord() {
        int round = 0;
        String word = null;
        Set<String> usedWordsSet = new HashSet<>();
        while (round < 100) {
            word = hangman.fetchWord(requestedLength);
            round++;
            assertTrue(usedWordsSet.add(word));
        }

    }

    @Test
    void test_fetchClueBeforeAnyGuess() {
        String clue = hangman.fetchClue("pizza");
        assertEquals("-----", clue);
    }

    @Test
    void test_fetchClueAfterCorrectGuess() {
        String clue = hangman.fetchClue("pizza");
        String newClue = hangman.fetchClue("pizza", clue, 'p');
        String clue1 = hangman.fetchClue("pizza", newClue, 'a');
        String clue2 = hangman.fetchClue("pizza", clue1, 'z');
        assertEquals("p-zza", clue2);
    }

    @Test
    void test_fetchClueAfterIncorrectGuess() {
        String clue = hangman.fetchClue("pizza");
        String newClue = hangman.fetchClue("pizza", clue, 'y');
        assertEquals("-----", clue);
    }

    @Test
    void test_fetchClueAfterIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> hangman.fetchClue("pizza", "-----", '1'));
    }

    @Test
    void test_fetchClueAfterIllegalArgumentExceptionWithMessage() {
        Exception e = assertThrows(IllegalArgumentException.class,
                () -> hangman.fetchClue("pizza", "-----", '1'));
        assertEquals("Illegal Argument for Invalid guess", e.getMessage());
    }

    @Test
    void test_remainingTrailBeforeAnyGuesses() {
        hangman.fetchWord(requestedLength);
        assertEquals(Hangman.MAX_TRAILS, hangman.remainingTrails);
    }

    @Test
    void test_remainingTrailAfterOneGuess() {
        hangman.fetchWord(requestedLength);
        hangman.fetchClue("pizza", "-----", 'p');
        assertEquals(Hangman.MAX_TRAILS - 1, hangman.remainingTrails);
    }

    @Test
    void test_scoreBeforeAnyGuess() {
        hangman.fetchWord(requestedLength);
        assertEquals(0, hangman.score);
    }

    @Test
    void test_scoreAfterCorrectGuess() {
        String word = "pizza";
        String clue = "-----";
        char guess = 'a';

        hangman.fetchClue(word, clue, guess);
        assertEquals((double) Hangman.MAX_TRAILS / word.length(), hangman.score);
    }

    @Test
    void test_scoreAfterIncorrectGuess() {
        String word = "pizza";
        String clue = "-----";
        char guess = 'x';

        hangman.fetchClue(word, clue, guess);
        assertEquals(0, hangman.score);
    }

}
