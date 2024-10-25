package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The OrderToleranceComparer class implements the Verifier interface.
 * It provides a comparison of the output and expected values by checking if the sets of lines
 * are equal, ignoring the order of the lines.
 */
public class OrderToleranceComparer implements Verifier {

    /**
     * Verifies the output file against the expected file.
     *
     * @param outputFile The file containing the actual output.
     * @param expectedFile The file containing the expected output.
     * @return true if the sets of lines in the output and expected files are equal, ignoring order.
     */
    @Override
    public boolean verify(File outputFile, File expectedFile) {
        try {
            // Read the content of the output file
            String output = new String(Files.readAllBytes(outputFile.toPath()));
            // Read the content of the expected file
            String expected = new String(Files.readAllBytes(expectedFile.toPath()));
            // Verify the output against the expected content
            return verify(output, expected);
        } catch (IOException e) {
            throw new RuntimeException("Error reading files", e);
        }
    }

    /**
     * Verifies the output string against the expected string.
     *
     * @param output The actual output as a string.
     * @param expected The expected output as a string.
     * @return true if the sets of lines in the output and expected strings are equal, ignoring order.
     */
    @Override
    public boolean verify(String output, String expected) {
        // Read lines from the output string
        List<String> outputLines = readLines(output);
        // Read lines from the expected string
        List<String> expectedLines = readLines(expected);

        // Check if the sets of lines are equal, ignoring order
        Set<String> outputSet = new HashSet<>(outputLines);
        Set<String> expectedSet = new HashSet<>(expectedLines);

        return outputSet.equals(expectedSet);
    }

    /**
     * Reads lines from the given content string.
     *
     * @param content The content string to read lines from.
     * @return A list of lines read from the content string.
     */
    private List<String> readLines(String content) {
        if (content == null || content.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(content.split("\\s+"));
    }
}