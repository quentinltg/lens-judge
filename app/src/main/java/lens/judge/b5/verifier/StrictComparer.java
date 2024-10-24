package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
     * Verifies the output file against the expected file using strict equality.
     *
     * @param outputFile the output file to be verified
     * @param expectedFile the expected file to compare against
     * @return true if the output file matches the expected file exactly, false otherwise
     */
    @Override
    public boolean verify(File outputFile, File expectedFile) {
        try {
            String output = new String(Files.readAllBytes(outputFile.toPath()));
            String expected = new String(Files.readAllBytes(expectedFile.toPath()));
            return verify(output, expected);
        } catch (IOException e) {
            throw new RuntimeException("Error reading files", e);
        }
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