package lens.judge.b5.execution;

import lens.judge.b5.Process.IProcess;
import lens.judge.b5.Process.ProcessAdapter;

public class JavaExecutionStrategy implements IExecutionStrategy {

    private String className;
    private ProcessAdapter process;

    public JavaExecutionStrategy(String className) {
        this.className = className;
    }

    public ProcessAdapter getProcess() {
        return process;
    }

    public void execute() {
        System.out.println("Executing Java code...");
        process = new ProcessAdapter("java", "-cp", "bin", className);
        //process = new ProcessAdapter("pwd");
        process.start();
    }

}
