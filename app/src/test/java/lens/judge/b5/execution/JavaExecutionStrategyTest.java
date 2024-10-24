package lens.judge.b5.execution;

import lens.judge.b5.process.ProcessAdapter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JavaExecutionStrategyTest {

    @Test
    void executeRunsJavaCodeSuccessfully() {
        JavaExecutionStrategy strategy = new JavaExecutionStrategy("Test");
        strategy.execute();
        ProcessAdapter process = strategy.getProcess();
        assertNotNull(process);
    }

    @Test
    void getProcessReturnsNullBeforeExecution() {
        JavaExecutionStrategy strategy = new JavaExecutionStrategy("Test");
        assertNull(strategy.getProcess());
    }
}