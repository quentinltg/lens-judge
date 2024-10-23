package lens.judge.b5.execution;

import lens.judge.b5.Process.IProcess;

public interface IExecutionStrategy {

    void execute();
    IProcess getProcess();

}
