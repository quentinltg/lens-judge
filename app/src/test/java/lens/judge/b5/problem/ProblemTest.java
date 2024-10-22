package lens.judge.b5.problem;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ProblemTest {

    @Test
    public void buildProblemWithValidParameters() {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("input1", "output1"));
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(testCases)
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        assertEquals(1000, problem.getTimeLimit());
        assertEquals(1024, problem.getMemoryLimit());
        assertEquals(1, problem.getTestCases().size());
    }

    @Test
    public void buildProblemWithEmptyTestCases() {
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(new ArrayList<>())
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        assertEquals(1000, problem.getTimeLimit());
        assertEquals(1024, problem.getMemoryLimit());
        assertTrue(problem.getTestCases().isEmpty());
    }

    @Test
    public void buildProblemWithNullTestCases() {
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(null)
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        assertEquals(1000, problem.getTimeLimit());
        assertEquals(1024, problem.getMemoryLimit());
        assertNull(problem.getTestCases());
    }

    @Test
    public void addTestCaseToProblem() {
        TestCase testCase = new TestCase("input3", "output3");
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(new ArrayList<>())
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        problem.addTestCase(testCase);

        assertEquals(1, problem.getTestCases().size());
        assertEquals(testCase, problem.getTestCases().get(0));
    }

    @Test
    public void removeTestCaseFromProblem() {
        TestCase testCase1 = new TestCase("input1", "output1");
        TestCase testCase2 = new TestCase("input2", "output2");
        List<TestCase> initialTestCases = new ArrayList<>();
        initialTestCases.add(testCase1);
        initialTestCases.add(testCase2);

        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(initialTestCases)
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        problem.removeTestCase(testCase1);

        assertEquals(1, problem.getTestCases().size());
        assertEquals(testCase2, problem.getTestCases().get(0));
    }
}