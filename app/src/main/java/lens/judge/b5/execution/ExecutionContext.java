package lens.judge.b5.execution;

import java.io.File;

/**
 * Represents the context in which an execution strategy is used.
 */
public class ExecutionContext {

    private IExecutionStrategy execution;

    /**
     * Sets the execution strategy to be used.
     *
     * @param execution the execution strategy to set
     */
    public void setExecutionStrategy(IExecutionStrategy execution) {
        this.execution = execution;
    }

    /**
     * Executes the current strategy with the provided input file.
     *
     * @param inputFile the input file to be used by the execution strategy
     * @throws Exception if an error occurs during execution
     */
    public void executeStrategy(File inputFile) throws Exception {
        execution.execute(inputFile);
    }
}