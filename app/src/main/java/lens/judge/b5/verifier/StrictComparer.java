package lens.judge.b5.verifier;

public class StrictComparer implements Verifier {

    @Override
    public boolean verify(String output, String expected) {
        return output.equals(expected);
    }

    private int countLines(String output) {
        int count = 0;
        for (int i = 0; i < output.length(); i++) {
            if (output.charAt(i) == '\n') {
                count++;
            }
        }
        return count;
    }
}