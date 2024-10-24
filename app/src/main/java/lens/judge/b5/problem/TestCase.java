package lens.judge.b5.problem;

import java.io.File;

public class TestCase {
    private File output;
    private File expected;

    public TestCase(File output, File expected) {
        this.output = output;
        this.expected = expected;
    }

    public File getOutput() {
        return output;
    }

    public File getExpected() {
        return expected;
    }
}