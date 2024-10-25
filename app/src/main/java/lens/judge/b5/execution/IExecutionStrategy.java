package lens.judge.b5.execution;

import lens.judge.b5.process.IProcess;

import java.io.File;

/**
 * Interface representing an execution strategy.
 */
public interface IExecutionStrategy {

    /**
     * Executes the strategy with the provided input file.
     *
     * @param inputFile the input file to be used by the execution strategy
     * @throws Exception if an error occurs during execution
     */
    void execute(File inputFile) throws Exception;

    /**
     * Returns the process associated with this execution strategy.
     *
     * @return the process associated with this execution strategy
     */
    IProcess getProcess();
}