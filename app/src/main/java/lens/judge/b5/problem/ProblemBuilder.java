package lens.judge.b5.problem;

import java.util.List;

public class ProblemBuilder {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;
    
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

    public Problem build() {
        return new Problem(testCases, timeLimit, memoryLimit);
    }
}
