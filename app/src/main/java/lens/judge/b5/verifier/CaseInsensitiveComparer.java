package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The CaseInsensitiveComparer class implements the Verifier interface.
 * It provides a case-insensitive comparison of the output and expected values.
 */
public class CaseInsensitiveComparer extends VerifierDecorator {
    /**
     * Constructor for CaseInsensitiveComparer.
     *
     * @param wrappedVerifier The Verifier instance to be wrapped.
     */
    public CaseInsensitiveComparer(Verifier wrappedVerifier) {
        super(wrappedVerifier);
    }

    /**
     * Verifies the output file against the expected file in a case-insensitive manner.
     *
     * @param outputFile The file containing the actual output.
     * @param expectedFile The file containing the expected output.
     * @return true if the output matches the expected output, false otherwise.
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
     * Verifies the output string against the expected string in a case-insensitive manner.
     *
     * @param output The actual output as a string.
     * @param expected The expected output as a string.
     * @return true if the output matches the expected output, false otherwise.
     */
    @Override
    public boolean verify(String output, String expected) {
        String normalizedOutput = normalize(output);
        String normalizedExpected = normalize(expected);
        return super.verify(normalizedOutput, normalizedExpected);
    }

    /**
     * Normalizes the input string by converting it to lowercase, replacing multiple
     * whitespace characters with a single space, and trimming leading and trailing whitespace.
     *
     * @param string The input string to be normalized.
     * @return The normalized string.
     */
    private String normalize(String string) {
        if (string == null) {
            return null;
        }
        return string.toLowerCase().replaceAll("\\s+", " ").trim();
    }
}