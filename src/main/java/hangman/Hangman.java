package hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hangman {


    public static final int MAX_TRAILS = 10;
    public int remainingTrails;
    public int score;
    MockScoreDB mockScoreDB;
    Set<String> usedWordsSet = new HashSet<>();
    List<String> wordsList = new ArrayList<>();

    public Hangman() {
    }

    public Hangman(MockScoreDB mockScoreDB) {
        this.mockScoreDB = mockScoreDB;
    }


    public int countAlphabet(String word, char alphabet) {
        int result = 0;
        for (char c : word.toCharArray()) {
            if (c == alphabet) result++;
        }
        return result;

    }

    public String fetchWord(int requestedLength) {
        remainingTrails = MAX_TRAILS;
        for (String word : wordsList) {
            if (word.length() != requestedLength) continue;
            else if (usedWordsSet.add(word)) return word;
        }
        return null;

    }

    public void loadsWord() {
        String result = null;
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            while ((result = br.readLine()) != null) {
                wordsList.add(result);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String fetchClue(String word) {
        StringBuilder clue = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            clue.append("-");
        }

        return clue.toString();
    }

    public String fetchClue(String word, String clue, char guess) {
        StringBuilder newClue = new StringBuilder();

        if (guess >= 'A' && guess <= 'Z') guess += 32;
        if (guess < 'a' || guess > 'z')
            throw new IllegalArgumentException("Illegal Argument for Invalid guess");

        for (int i = 0; i < word.length(); i++) {
            if (guess == word.charAt(i) && guess != clue.charAt(i)) {
                newClue.append(guess);
                score += (double) MAX_TRAILS / word.length();
            } else {
                newClue.append(clue.charAt(i));
            }
        }
        remainingTrails--;
        return newClue.toString();
    }

    public boolean saveWordScoreToDB(String word, int score) {
        return mockScoreDB.writeScoreToDB(word, score);
    }
}
