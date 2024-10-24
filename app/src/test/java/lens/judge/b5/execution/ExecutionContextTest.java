package lens.judge.b5.execution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExecutionContextTest {

    @Test
    void executeStrategyRunsSuccessfully() {
        ExecutionContext context = new ExecutionContext();
        PythonExecutionStrategy strategy = new PythonExecutionStrategy("app/src/test/resources/test.py");
        context.setExecutionStrategy(strategy);
        context.executeStrategy();
        assertNotNull(strategy.getProcess());
    }

    @Test
    void executeStrategyHandlesNullStrategy() {
        ExecutionContext context = new ExecutionContext();
        context.setExecutionStrategy(null);
        assertThrows(NullPointerException.class, context::executeStrategy);
    }

}