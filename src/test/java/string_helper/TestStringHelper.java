package string_helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStringHelper {
    /*
     * Sample Input and Output
     * "BCDE" => "BCDE"
     * "ABCD" => "BCD"
     * "AACD" => "CD"
     * "BACD" => "BCD"
     * "AAAA" => "AA"
     * "MNAA" => "MNAA"*
     * "A" => ""
     * ""  => ""
     */
    @Test
    void testTruncateFirst2As_ANotPresent() {
        assertEquals("BCDE", StringHelper.truncateFirst2As("BCDE"));
    }

    @Test
    void testTruncateFirst2As_AIsPresent() {
        assertEquals("BCD", StringHelper.truncateFirst2As("ABCD"));
    }

    @Test
    void testTruncateFirst2As_LengthLessThen2() {
        assertEquals("", StringHelper.truncateFirst2As(""));
    }

    @Test
    void testTruncateFirst2As_SingleA() {
        assertEquals("", StringHelper.truncateFirst2As("A"));
    }

    @Test
    void testTruncateFirst2As_AIsDouble() {
        String out = StringHelper.truncateFirst2As("AACD");
        assertEquals("CD", out);
    }

    @Test
    void testTruncateFirst2As_AllAreA() {
        assertEquals("AA", StringHelper.truncateFirst2As("AAAA"));
    }

    @Test
    void testTruncateFirst2As_LastAA() {
        assertEquals("MNAA", StringHelper.truncateFirst2As("MNAA"));
    }
}
