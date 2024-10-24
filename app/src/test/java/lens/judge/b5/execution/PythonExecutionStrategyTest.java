package lens.judge.b5.execution;

import lens.judge.b5.process.ProcessAdapter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PythonExecutionStrategyTest {

    @Test
    void executeRunsPythonCodeSuccessfully() {
        PythonExecutionStrategy strategy = new PythonExecutionStrategy("app/src/test/resources/test.py");
        strategy.execute();
        ProcessAdapter process = strategy.getProcess();
        assertNotNull(process);
    }

    @Test
    void getProcessReturnsNullBeforeExecution() {
        PythonExecutionStrategy strategy = new PythonExecutionStrategy("app/src/test/resources/test.py");
        assertNull(strategy.getProcess());
    }
}