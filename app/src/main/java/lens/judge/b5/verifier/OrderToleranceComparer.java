package lens.judge.b5.verifier;

import java.util.Arrays;
import java.util.List;

/**
 * The OrderToleranceComparer class implements the Verifier interface.
 * It provides a comparison of the output and expected values by checking if all expected lines
 * are present in the output lines, considering the order tolerance.
 */
public class OrderToleranceComparer implements Verifier {

    /**
     * Verifies the output against the expected value by checking if all expected lines
     * are present in the output lines, considering the order tolerance.
     *
     * @param output the output string to be verified
     * @param expected the expected string to compare against
     * @return true if all expected lines are present in the output lines in the correct order, false otherwise
     */
    @Override
    public boolean verify(String output, String expected) {
        List<String> outputLines = readExpectedLines(output);
        List<String> expectedLines = readExpectedLines(expected);

        int expectedIndex = 0;
        for (String outputLine : outputLines) {
            if (expectedIndex < expectedLines.size() && outputLine.equals(expectedLines.get(expectedIndex))) {
                expectedIndex++;
            }
        }
        return expectedIndex == expectedLines.size();
    }

    /**
     * Reads the expected lines from the given string.
     *
     * @param expected the expected string to be read
     * @return a list of lines from the expected string
     */
    private List<String> readExpectedLines(String expected) {
        if (expected == null || expected.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(expected.split("\n"));
    }

    /**
     * Checks if a line is present in the given collection.
     *
     * @param line the line to be checked
     * @param collection the collection of strings to check against
     * @return true if the line is present in the collection, false otherwise
     */
    private boolean isLineInCollection(String line, List<String> collection) {
        return collection.contains(line);
    }
}