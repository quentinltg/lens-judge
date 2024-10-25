package lens.judge.b5.execution;

import lens.judge.b5.process.IProcess;

import java.io.File;

public interface IExecutionStrategy {

    void execute(File inputFile) throws Exception;
    IProcess getProcess();


}
