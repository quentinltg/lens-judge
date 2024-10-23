package lens.judge.b5.problem;

import java.util.List;
import lens.judge.b5.verifier.Verifier;
import lens.judge.b5.verifier.StrictComparer;

public class ProblemBuilder {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;
    private Verifier verifier;

    private ProblemBuilder() {}

    public static ProblemBuilder newInstance() {
        return new ProblemBuilder();
    }

    public ProblemBuilder withTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
        return this;
    }

    public ProblemBuilder withTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public ProblemBuilder withMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        return this;
    }

    public ProblemBuilder withVerifier(Verifier verifier) {
        this.verifier = verifier;
        return this;
    }

    public Problem build() {
        if (verifier == null) {
            verifier = new StrictComparer(); // default Verifier
        }
        return new Problem(testCases, timeLimit, memoryLimit, verifier);
    }
}