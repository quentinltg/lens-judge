package lens.judge.b5.execution;

import lens.judge.b5.process.ProcessAdapter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CExecutionStrategyTest {

    @Test
    void executeRunsCCodeSuccessfully() {
        CExecutionStrategy strategy = new CExecutionStrategy("testC");
        strategy.execute();
        ProcessAdapter process = strategy.getProcess();
        assertNotNull(process);
    }

    @Test
    void getProcessReturnsNullBeforeExecution() {
        CExecutionStrategy strategy = new CExecutionStrategy("testC");
        assertNull(strategy.getProcess());
    }
}