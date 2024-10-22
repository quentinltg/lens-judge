package lens.judge.b5;

public class ProcessBuilder {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;

    private ProcessBuilder() {}

    public static ProcessBuilder newInstance() {
        return new ProcessBuilder();
    }

    public ProcessBuilder withTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
        return this;
    }

    public ProcessBuilder withTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public ProcessBuilder withMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        return this;
    }

    public Process build() {
        return new Process(this);
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
    public int getMemoryLimit() {
        return memoryLimit;
    }
}
