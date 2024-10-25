package lens.judge.b5.problem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ProblemTest {

    @Test
    void buildProblemWithValidParameters() {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(new File("output1"), new File("expected1")));
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(testCases)
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        Assertions.assertEquals(1000, problem.getTimeLimit());
        Assertions.assertEquals(1024, problem.getMemoryLimit());
        Assertions.assertEquals(1, problem.getTestCases().size());
    }

    @Test
    void buildProblemWithEmptyTestCases() {
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(new ArrayList<>())
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        Assertions.assertEquals(1000, problem.getTimeLimit());
        Assertions.assertEquals(1024, problem.getMemoryLimit());
        Assertions.assertTrue(problem.getTestCases().isEmpty());
    }

    @Test
    void buildProblemWithNullTestCases() {
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(null)
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        Assertions.assertEquals(1000, problem.getTimeLimit());
        Assertions.assertEquals(1024, problem.getMemoryLimit());
        Assertions.assertNull(problem.getTestCases());
    }

    @Test
    void addTestCaseToProblem() {
        TestCase testCase = new TestCase(new File("output3"), new File("expected3"));
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(new ArrayList<>())
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        problem.addTestCase(testCase);

        Assertions.assertEquals(1, problem.getTestCases().size());
        Assertions.assertEquals(testCase, problem.getTestCases().get(0));
    }

    @Test
    void removeTestCaseFromProblem() {
        TestCase testCase1 = new TestCase(new File("output1"), new File("expected1"));
        TestCase testCase2 = new TestCase(new File("output2"), new File("expected2"));
        List<TestCase> initialTestCases = new ArrayList<>();
        initialTestCases.add(testCase1);
        initialTestCases.add(testCase2);

        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(initialTestCases)
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        problem.removeTestCase(testCase1);

        Assertions.assertEquals(1, problem.getTestCases().size());
        Assertions.assertEquals(testCase2, problem.getTestCases().get(0));
    }

    @Test
    void testIterator() {
        TestCase testCase1 = new TestCase(new File("output1"), new File("expected1"));
        TestCase testCase2 = new TestCase(new File("output2"), new File("expected2"));
        List<TestCase> initialTestCases = new ArrayList<>();
        initialTestCases.add(testCase1);
        initialTestCases.add(testCase2);

        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(initialTestCases)
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        Iterator<TestCase> iterator = problem.iterator();
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(testCase1, iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals(testCase2, iterator.next());
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void testSetTestCases() {
        TestCase testCase1 = new TestCase(new File("output1"), new File("expected1"));
        TestCase testCase2 = new TestCase(new File("output2"), new File("expected2"));
        List<TestCase> initialTestCases = new ArrayList<>();
        initialTestCases.add(testCase1);

        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(initialTestCases)
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        List<TestCase> newTestCases = new ArrayList<>();
        newTestCases.add(testCase2);
        problem.setTestCases(newTestCases);

        Assertions.assertEquals(1, problem.getTestCases().size());
        Assertions.assertEquals(testCase2, problem.getTestCases().get(0));
    }

    @Test
    void testSetTimeLimit() {
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(new ArrayList<>())
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        problem.setTimeLimit(2000);
        Assertions.assertEquals(2000, problem.getTimeLimit());
    }

    @Test
    void testSetMemoryLimit() {
        Problem problem = ProblemBuilder.newInstance()
                .withTestCases(new ArrayList<>())
                .withTimeLimit(1000)
                .withMemoryLimit(1024)
                .build();

        problem.setMemoryLimit(2048);
        Assertions.assertEquals(2048, problem.getMemoryLimit());
    }
}