package hangman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHangman {
    private static Hangman hangman;
    private static Random random;

    @BeforeEach
    public void setupHangman() {
        hangman = new Hangman();
        random = new Random();
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
        int requestedLength = random.nextInt(6) + 5;
        hangman.loadsWord();
        String word = hangman.fetchWord(requestedLength);
        assertTrue(word.length() == requestedLength);
    }

    @Test
    void test_uniquenessOffFetchedWord() {
        int requestedLength = 0;
        int round = 0;
        String word = null;
        hangman.loadsWord();
        Set<String> usedWordsSet = new HashSet<>();


        while (round < 100) {
            requestedLength = random.nextInt(6) + 5;
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
}
