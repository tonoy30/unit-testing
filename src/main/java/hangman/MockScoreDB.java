package hangman;

public interface MockScoreDB {
    boolean writeScoreToDB(String word, double score);
}
