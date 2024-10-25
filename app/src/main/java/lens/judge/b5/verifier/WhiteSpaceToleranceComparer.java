package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The WhiteSpaceToleranceComparer class is a decorator for the Verifier interface.
 * It ensures that the comparison ignores extra spaces by normalizing the strings
 * before verification.
 */
public class WhiteSpaceToleranceComparer extends VerifierDecorator {

    /**
     * Constructs a WhiteSpaceToleranceComparer with the specified wrapped Verifier.
     *
     * @param wrappedVerifier the Verifier to be wrapped
     */
    public WhiteSpaceToleranceComparer(Verifier wrappedVerifier) {
        super(wrappedVerifier);
    }

    /**
     * Verifies the output against the expected value after normalizing spaces.
     *
     * @param output the output string to be verified
     * @param expected the expected string to compare against
     * @return true if the normalized output matches the normalized expected string, false otherwise
     */
    @Override
    public boolean verify(String output, String expected) {
        String normalizedOutput = normalizeSpaces(output);
        String normalizedExpected = normalizeSpaces(expected);
        return super.verify(normalizedOutput, normalizedExpected);
    }

    /**
     * Verifies the output file against the expected file after normalizing spaces.
     *
     * @param outputFile the output file to be verified
     * @param expectedFile the expected file to compare against
     * @return true if the normalized output matches the normalized expected file, false otherwise
     */
    @Override
    public boolean verify(File outputFile, File expectedFile) {
        try {
            String output = new String(Files.readAllBytes(outputFile.toPath()));
            String expected = new String(Files.readAllBytes(expectedFile.toPath()));
            return verify(output, expected);
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Normalizes spaces in a string by trimming leading and trailing spaces
     * and replacing multiple spaces with a single space.
     *
     * @param input the input string to normalize
     * @return the normalized string
     */
    private String normalizeSpaces(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }
}