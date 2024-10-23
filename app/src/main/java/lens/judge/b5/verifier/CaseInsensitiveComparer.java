package lens.judge.b5.verifier;

public class CaseInsensitiveComparer extends VerifierDecorator {
    public CaseInsensitiveComparer(Verifier wrappedVerifier) {
        super(wrappedVerifier);
    }

    @Override
    public boolean verify(String output, String expected) {
        // Implementation goes here
        return false;
    }

    private String normalize(String string) {
        // Implementation goes here
        return null;
    }
}