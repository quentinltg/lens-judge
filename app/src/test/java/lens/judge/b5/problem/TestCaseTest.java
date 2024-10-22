package lens.judge.b5.problem;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCaseTest {

    @Test
    public void testGetInput() {
        TestCase testCase = new TestCase("input1", "output1");
        assertEquals("input1", testCase.getInput());
    }

    @Test
    public void testSetInput() {
        TestCase testCase = new TestCase("input1", "output1");
        testCase.setInput("newInput");
        assertEquals("newInput", testCase.getInput());
    }

    @Test
    public void testGetOutput() {
        TestCase testCase = new TestCase("input1", "output1");
        assertEquals("output1", testCase.getOutput());
    }

    @Test
    public void testSetOutput() {
        TestCase testCase = new TestCase("input1", "output1");
        testCase.setOutput("newOutput");
        assertEquals("newOutput", testCase.getOutput());
    }
}