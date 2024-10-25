package lens.judge.b5.problem;

import lens.judge.b5.verifier.Verifier;
import java.util.Iterator;
import java.util.List;

/**
 * The Problem class represents a problem with a set of test cases, time limit, memory limit, and a verifier.
 * It implements the Iterable interface to allow iteration over the test cases.
 */
public class Problem implements Iterable<TestCase> {
    private List<TestCase> testCases;
    private int timeLimit = 0;
    private int memoryLimit;
    private Verifier verifier;

    /**
     * Constructs a Problem with the specified test cases, time limit, memory limit, and verifier.
     *
     * @param testCases the list of test cases for the problem
     * @param timeLimit the time limit for the problem in milliseconds
     * @param memoryLimit the memory limit for the problem in kilobytes
     * @param verifier the verifier to check the output of the test cases
     */
    public Problem(List<TestCase> testCases, int timeLimit, int memoryLimit, Verifier verifier) {
        this.testCases = testCases;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.verifier = verifier;
    }

    /**
     * Returns the list of test cases for the problem.
     *
     * @return the list of test cases
     */
    public List<TestCase> getTestCases() {
        return testCases;
    }

    /**
     * Sets the list of test cases for the problem.
     *
     * @param testCases the new list of test cases
     */
    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    /**
     * Returns the time limit for the problem.
     *
     * @return the time limit in milliseconds
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Sets the time limit for the problem.
     *
     * @param timeLimit the new time limit in milliseconds
     */
    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    /**
     * Returns the memory limit for the problem.
     *
     * @return the memory limit in kilobytes
     */
    public int getMemoryLimit() {
        return memoryLimit;
    }

    /**
     * Sets the memory limit for the problem.
     *
     * @param memoryLimit the new memory limit in kilobytes
     */
    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    /**
     * Returns an iterator over the test cases in the problem.
     *
     * @return an iterator over the test cases
     */
    public Iterator<TestCase> iterator() {
        return testCases.iterator();
    }

    /**
     * Adds a test case to the problem.
     *
     * @param testCase the test case to be added
     */
    public void addTestCase(TestCase testCase) {
        if (testCases != null) {
            testCases.add(testCase);
        }
    }

    /**
     * Removes a test case from the problem.
     *
     * @param testCase the test case to be removed
     */
    public void removeTestCase(TestCase testCase) {
        if (testCases != null) {
            testCases.remove(testCase);
        }
    }
}