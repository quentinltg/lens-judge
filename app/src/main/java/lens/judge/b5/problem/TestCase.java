package lens.judge.b5.problem;

public class TestCase {
    private String output;
    private String expected;

    public TestCase(String output, String expected) {
        this.output = output;
        this.expected = expected;
    }

    public String getOutput() {
        return output;
    }

    public String getExpected() {
        return expected;
    }
}