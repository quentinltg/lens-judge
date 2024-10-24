package lens.judge.b5.execution;

import lens.judge.b5.process.IProcess;

public interface IExecutionStrategy {

    void execute();
    IProcess getProcess();

}
