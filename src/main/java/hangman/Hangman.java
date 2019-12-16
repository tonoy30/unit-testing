package hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hangman {

    Set<String> usedWordsSet = new HashSet<>();
    List<String> wordsList = new ArrayList<>();

    public int countAlphabet(String word, char alphabet) {
        int result = 0;
        for (char c : word.toCharArray()) {
            if (c == alphabet) result++;
        }
        return result;

    }

    public String fetchWord(int requestedLength) {
        for (String result : wordsList) {
            if (result.length() != requestedLength) continue;
            else if (usedWordsSet.add(result)) return result;
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
}
