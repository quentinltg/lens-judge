package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The MultipleSolutionsComparer class implements the Verifier interface.
 * It provides a comparison of the output and expected values by checking if the output
 * matches any of the expected solutions.
 */
public class MultipleSolutionsComparer implements Verifier {

    /**
     * Verifies the output file against the expected file.
     *
     * @param outputFile The file containing the actual output.
     * @param expectedFile The file containing the expected output.
     * @return true if the output matches any of the expected solutions, false otherwise.
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
     * @return true if the output matches any of the expected solutions, false otherwise.
     */
    @Override
    public boolean verify(String output, String expected) {
        // Split the expected content into words and store them in a Set
        Set<String> expectedWords = new HashSet<>(List.of(expected.split("\\s+")));

        // Split the output content into words
        List<String> outputWords = List.of(output.split("\\s+"));

        // Check if the output matches any of the expected solutions
        for (String word : outputWords) {
            if (expectedWords.contains(word)) {
                return true;
            }
        }
        return false;
    }
}