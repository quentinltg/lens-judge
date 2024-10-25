package lens.judge.b5.compiler;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbstractCompilationStrategyTest {

    @Test
    void compileThrowsExceptionForCompilationError() {
        AbstractCompilationStrategy strategy = new CCompilationStrategy();
        assertThrows(Exception.class, () -> strategy.compile("path/to/invalid.c"));
    }
}