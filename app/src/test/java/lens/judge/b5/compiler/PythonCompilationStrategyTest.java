package lens.judge.b5.compiler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PythonCompilationStrategyTest {

    @Test
    void isCompatibleReturnsTrueForPythonFile() {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        assertTrue(strategy.isCompatible("example.py"));
    }

    @Test
    void isCompatibleReturnsFalseForNonPythonFile() {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        assertFalse(strategy.isCompatible("example.java"));
    }

    @Test
    void getBinaryNameReturnsNullForPythonFile() {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        assertNull(strategy.getBinaryName("example.py"));
    }

    @Test
    void getCompileCommandReturnsCorrectCommandForPythonFile() {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        String expectedCommand = "python3 -m py_compile example.py";
        assertEquals(expectedCommand, strategy.getCompileCommand("example.py", null));
    }
}