package string_helper;

public class StringHelper {
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
    public static String truncateFirst2As(final String pInput) {
        if (pInput.length() < 2) {
            return pInput.replaceAll("A", "");
        }
        String firstTwoChar = pInput.substring(0, 2);
        String remainingChar = pInput.substring(2);
        String output = firstTwoChar.replaceAll("A", "").concat(remainingChar);
        return output;

    }
}
