package lens.judge.b5.problem;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class TestCaseTest {

    @Test
    public void testGetOutput() {
        File outputFile = new File("output1");
        File expectedFile = new File("expected1");
        TestCase testCase = new TestCase(outputFile, expectedFile);
        assertEquals(outputFile, testCase.getOutput());
    }

    @Test
    public void testGetExpected() {
        File outputFile = new File("output1");
        File expectedFile = new File("expected1");
        TestCase testCase = new TestCase(outputFile, expectedFile);
        assertEquals(expectedFile, testCase.getExpected());
    }
}