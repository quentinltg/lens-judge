package lens.judge.b5.execution;

import lens.judge.b5.Process.ProcessAdapter;

public class CExecutionStrategy implements IExecutionStrategy {

    private String binaryName;
    private ProcessAdapter process;

    public CExecutionStrategy(String binaryName) {
        this.binaryName = binaryName;
    }

    public ProcessAdapter getProcess() {
        return process;
    }

    public void execute() {
        System.out.println("Executing C/C++ code...");
        process = new ProcessAdapter("gcc " + binaryName);
        process.start();
    }

}
