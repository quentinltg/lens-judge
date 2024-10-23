package lens.judge.b5.problem;

import lens.judge.b5.problem.TestCase;
import lens.judge.b5.verifier.Verifier;
import java.util.Iterator;
import java.util.List;

public class Problem {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;
    private Verifier verifier;

    public Problem(List<TestCase> testCases, int timeLimit, int memoryLimit, Verifier verifier) {
        this.testCases = testCases;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.verifier = verifier;
    }

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
        if (testCases != null) {
            testCases.add(testCase);
        }
    }

    public void removeTestCase(TestCase testCase) {
        if (testCases != null) {
            testCases.remove(testCase);
        }
    }

    public boolean verifyTestCase(TestCase testCase) {
        return verifier.verify(testCase.getOutput(), testCase.getExpected());
    }
}