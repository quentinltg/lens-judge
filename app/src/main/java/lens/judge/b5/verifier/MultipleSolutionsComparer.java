package lens.judge.b5.verifier;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The MultipleSolutionsComparer class implements the Verifier interface.
 * It provides a comparison of the output and expected values by checking if all expected lines
 * are present in the output lines, considering multiple solutions.
 */
public class MultipleSolutionsComparer implements Verifier {

    /**
     * Verifies the output against the expected value by checking if all expected lines
     * are present in the output lines.
     *
     * @param output the output string to be verified
     * @param expected the expected string to compare against
     * @return true if all expected lines are present in the output lines, false otherwise
     */
    @Override
    public boolean verify(String output, String expected) {
        List<String> outputLines = readOutputLines(output);
        List<String> expectedLines = readOutputLines(expected);

        if (outputLines.size() != expectedLines.size()) {
            return false;
        }

        Set<String> seenLines = new HashSet<>();
        for (String expectedLine : expectedLines) {
            if (!outputLines.contains(expectedLine) || !seenLines.add(expectedLine)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reads the output lines from the given string.
     *
     * @param output the output string to be read
     * @return a list of lines from the output string
     */
    private List<String> readOutputLines(String output) {
        if (output == null || output.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(output.split("\n"));
    }

    /**
     * Checks if a single solution is present in the given collection.
     *
     * @param solution the solution string to be checked
     * @param collection the collection of strings to check against
     * @return true if the solution is present in the collection, false otherwise
     */
    private boolean isSingleSolutionInCollection(String solution, List<String> collection) {
        for (String item : collection) {
            if (item.trim().equals(solution.trim())) {
                return true;
            }
        }
        return false;
    }
}