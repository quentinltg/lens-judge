package lens.judge.b5;
import java.util.Iterator;
import java.util.List;

public class Problem {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;

    // Constructor
    public Problem(List<TestCase> testCases, int timeLimit, int memoryLimit) {
        this.testCases = testCases;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }

    // Getters and Setters
    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Iterator<TestCase> iterator() {
        return testCases.iterator();
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    public void removeTestCase(TestCase testCase) {
        testCases.remove(testCase);
    }

}
