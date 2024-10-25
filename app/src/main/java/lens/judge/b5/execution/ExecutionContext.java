package lens.judge.b5.execution;

import java.io.File;

public class ExecutionContext {

    private IExecutionStrategy execution;

    public void setExecutionStrategy(IExecutionStrategy execution) {
        this.execution = execution;
    }

    public void executeStrategy(File inputFile) throws Exception {
        execution.execute(inputFile);
    }
}
