package lens.judge.b5.compiler;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.*;


public class CompilationStrategyTests {

    @Test
    public void isCompatibleWithCFile() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        assertTrue(strategy.isCompatible("test.c"));
    }

    @Test
    public void isNotCompatibleWithNonCFile() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        assertFalse(strategy.isCompatible("test.java"));
    }

    @Test
    public void getBinaryNameForCFile() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        assertEquals("test", strategy.getBinaryName("test.c"));
    }

    @Test
    public void getCompileCommandForCFile() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        assertEquals("gcc -x c -Wall -O2 -static -pipe -lm -o test test.c", strategy.getCompileCommand("test.c", "test"));
    }

    @Test
    public void isCompatibleWithCppFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertTrue(strategy.isCompatible("test.cpp"));
    }

    @Test
    public void isNotCompatibleWithNonCppFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertFalse(strategy.isCompatible("test.java"));
    }

    @Test
    public void getBinaryNameForCppFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertEquals("test", strategy.getBinaryName("test.cpp"));
    }

    @Test
    public void getCompileCommandForCppFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertEquals("g++ -x c++ -Wall -O2 -static -pipe -o test test.cpp", strategy.getCompileCommand("test.cpp", "test"));
    }

    @Test
    public void isCompatibleWithJavaFile() {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        assertTrue(strategy.isCompatible("test.java"));
    }

    @Test
    public void isNotCompatibleWithNonJavaFile() {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        assertFalse(strategy.isCompatible("test.cpp"));
    }

    @Test
    public void getBinaryNameForJavaFile() {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        assertEquals("test.class", strategy.getBinaryName("test.java"));
    }

    @Test
    public void getCompileCommandForJavaFile() {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        assertEquals("javac test.java", strategy.getCompileCommand("test.java", "test.class"));
    }

    @Test
    public void isCompatibleWithPythonFile() {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        assertTrue(strategy.isCompatible("test.py"));
    }

    @Test
    public void isNotCompatibleWithNonPythonFile() {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        assertFalse(strategy.isCompatible("test.java"));
    }

    @Test
    public void getBinaryNameForPythonFile() {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        assertNull(strategy.getBinaryName("test.py"));
    }

    @Test
    public void getCompileCommandForPythonFile() {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        assertEquals("python3 -m py_compile test.py", strategy.getCompileCommand("test.py", null));
    }
}