package lens.judge.b5.verifier;

public interface Verifier {
    boolean verify(String output, String expected);
}