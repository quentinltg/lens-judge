package lens.judge.b5.verifier;

/**
 * The StrictComparer class implements the Verifier interface.
 * It provides a strict comparison of the output and expected strings.
 */
public class StrictComparer implements Verifier {

    /**
     * Verifies the output against the expected value using strict equality.
     *
     * @param output the output string to be verified
     * @param expected the expected string to compare against
     * @return true if the output matches the expected string exactly, false otherwise
     */
    @Override
    public boolean verify(String output, String expected) {
        return output.equals(expected);
    }

    /**
     * Counts the number of lines in the given output string.
     *
     * @param output the output string whose lines are to be counted
     * @return the number of lines in the output string
     */
    private int countLines(String output) {
        int count = 0;
        for (int i = 0; i < output.length(); i++) {
            if (output.charAt(i) == '\n') {
                count++;
            }
        }
        return count;
    }
}