package lens.judge.b5.problem;

import java.io.File;

/**
 * The TestCase class represents a test case with an output file and an expected output file.
 */
public class TestCase {
    private File output;
    private File expected;

    /**
     * Constructs a TestCase with the specified output and expected output files.
     *
     * @param output the file containing the actual output
     * @param expected the file containing the expected output
     */
    public TestCase(File output, File expected) {
        this.output = output;
        this.expected = expected;
    }

    /**
     * Returns the file containing the actual output.
     *
     * @return the output file
     */
    public File getOutput() {
        return output;
    }

    /**
     * Returns the file containing the expected output.
     *
     * @return the expected output file
     */
    public File getExpected() {
        return expected;
    }
}