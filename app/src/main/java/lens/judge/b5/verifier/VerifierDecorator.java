package lens.judge.b5.verifier;

import java.io.File;

/**
 * The VerifierDecorator class is an abstract class that implements the Verifier interface.
 * It serves as a base class for other decorators that add additional functionality
 * to the Verifier interface.
 */
public abstract class VerifierDecorator implements Verifier {
    /**
     * The wrapped Verifier instance.
     */
    protected Verifier wrappedVerifier;

    /**
     * Constructs a VerifierDecorator with the specified wrapped Verifier.
     *
     * @param wrappedVerifier the Verifier to be wrapped
     */
    protected VerifierDecorator(Verifier wrappedVerifier) {
        this.wrappedVerifier = wrappedVerifier;
    }

    /**
     * Verifies the output against the expected value by delegating to the wrapped Verifier.
     *
     * @param output the output string to be verified
     * @param expected the expected string to compare against
     * @return true if the output matches the expected string, false otherwise
     */
    @Override
    public boolean verify(String output, String expected) {
        return wrappedVerifier.verify(output, expected);
    }

    /**
     * Verifies the output file against the expected file by delegating to the wrapped Verifier.
     *
     * @param outputFile the output file to be verified
     * @param expectedFile the expected file to compare against
     * @return true if the output matches the expected file, false otherwise
     */
    @Override
    public boolean verify(File outputFile, File expectedFile) {
        return wrappedVerifier.verify(outputFile, expectedFile);
    }
}