package lens.judge.b5.problem;

import java.util.List;
import lens.judge.b5.verifier.Verifier;
import lens.judge.b5.verifier.StrictComparer;

/**
 * The ProblemBuilder class is responsible for constructing a Problem object
 * with the specified test cases, time limit, memory limit, and verifier.
 */
public class ProblemBuilder {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;
    private Verifier verifier;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private ProblemBuilder() {}

    /**
     * Creates a new instance of ProblemBuilder.
     *
     * @return a new instance of ProblemBuilder
     */
    public static ProblemBuilder newInstance() {
        return new ProblemBuilder();
    }

    /**
     * Sets the test cases for the Problem.
     *
     * @param testCases the list of test cases
     * @return the current instance of ProblemBuilder
     */
    public ProblemBuilder withTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
        return this;
    }

    /**
     * Sets the time limit for the Problem.
     *
     * @param timeLimit the time limit in milliseconds
     * @return the current instance of ProblemBuilder
     */
    public ProblemBuilder withTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    /**
     * Sets the memory limit for the Problem.
     *
     * @param memoryLimit the memory limit in kilobytes
     * @return the current instance of ProblemBuilder
     */
    public ProblemBuilder withMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        return this;
    }

    /**
     * Builds and returns a Problem object with the specified properties.
     * If no verifier is set, a default StrictComparer verifier is used.
     *
     * @return a new Problem object
     */
    public Problem build() {
        if (verifier == null) {
            verifier = new StrictComparer(); // default Verifier
        }
        return new Problem(testCases, timeLimit, memoryLimit, verifier);
    }
}