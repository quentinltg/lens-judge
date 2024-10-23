package lens.judge.b5.verifier;

public abstract class VerifierDecorator implements Verifier {
    protected Verifier wrappedVerifier;

    public VerifierDecorator(Verifier wrappedVerifier) {
        this.wrappedVerifier = wrappedVerifier;
    }

    @Override
    public boolean verify(String output, String expected) {
        // Implementation goes here
        return false;
    }
}