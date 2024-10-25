package lens.judge.b5.compiler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JavaCompilationStrategyTest {

    @Test
    void isCompatibleReturnsTrueForJavaFile() {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        assertTrue(strategy.isCompatible("example.java"));
    }

    @Test
    void isCompatibleReturnsFalseForNonJavaFile() {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        assertFalse(strategy.isCompatible("example.cpp"));
    }

    @Test
    void getBinaryNameReturnsCorrectNameForJavaFile() {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        assertEquals("example.class", strategy.getBinaryName("example.java"));
    }

    @Test
    void getCompileCommandReturnsCorrectCommand() {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        String expectedCommand = "javac -d app/bin/ example.java";
        assertEquals(expectedCommand, strategy.getCompileCommand("example.java", "example.class"));
    }
}