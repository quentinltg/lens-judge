package lens.judge.b5.verifier;

public class WhiteSpaceToleranceComparer extends VerifierDecorator {
    public WhiteSpaceToleranceComparer(Verifier wrappedVerifier) {
        super(wrappedVerifier);
    }

    @Override
    public boolean verify(String output, String expected) {
        // Implementation goes here
        return false;
    }

    private String normalizeSpaces(String string) {
        // Implementation goes here
        return null;
    }
}
