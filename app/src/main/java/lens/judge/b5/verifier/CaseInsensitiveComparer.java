package lens.judge.b5.verifier;

/**
 * The CaseInsensitiveComparer class extends the VerifierDecorator class.
 * It provides a case-insensitive comparison of the output and expected strings.
 */
public class CaseInsensitiveComparer extends VerifierDecorator {

    /**
     * Constructs a CaseInsensitiveComparer with the specified wrapped Verifier.
     *
     * @param wrappedVerifier the Verifier to be wrapped
     */
    public CaseInsensitiveComparer(Verifier wrappedVerifier) {
        super(wrappedVerifier);
    }

    /**
     * Verifies the output against the expected value using case-insensitive comparison.
     *
     * @param output the output string to be verified
     * @param expected the expected string to compare against
     * @return true if the normalized output matches the normalized expected string, false otherwise
     */
    @Override
    public boolean verify(String output, String expected) {
        String normalizedOutput = normalize(output);
        String normalizedExpected = normalize(expected);
        return super.verify(normalizedOutput, normalizedExpected);
    }

    /**
     * Normalizes the given string by converting it to lowercase, replacing multiple spaces with a single space, and trimming leading and trailing spaces.
     *
     * @param string the string to be normalized
     * @return the normalized string, or null if the input string is null
     */
    private String normalize(String string) {
        if (string == null) {
            return null;
        }
        // Convert to lowercase and replace multiple spaces with a single space
        return string.toLowerCase().replaceAll("\\s+", " ").trim();
    }
}