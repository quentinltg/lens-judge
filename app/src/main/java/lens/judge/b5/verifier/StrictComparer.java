package lens.judge.b5.verifier;

import java.util.Arrays;
import java.util.List;

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
        List<String> outputLines = readLines(output);
        List<String> expectedLines = readLines(expected);

        if (outputLines.size() != expectedLines.size()) {
            return false;
        }

        for (int i = 0; i < outputLines.size(); i++) {
            if (!outputLines.get(i).equals(expectedLines.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Reads the lines from the given string.
     *
     * @param text the string to be read
     * @return a list of lines from the string
     */
    private List<String> readLines(String text) {
        if (text == null || text.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(text.split("\n"));
    }
}