package lens.judge.b5.verifier;

import java.io.File;

/**
 * The Verifier interface defines a method for verifying the output against the expected value.
 */
public interface Verifier {
    /**
     * Verifies the output against the expected value.
     *
     * @param output the output string to be verified
     * @param expected the expected string to compare against
     * @return true if the output matches the expected string, false otherwise
     */
    boolean verify(File output, File expected);
    boolean verify(String output, String expected);
}