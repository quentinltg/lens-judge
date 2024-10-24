package lens.judge.b5.execution;

import lens.judge.b5.process.IProcess;
import lens.judge.b5.process.ProcessAdapter;

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
        System.out.println("Executing Java code : java -cp app/bin " + className);
        process = new ProcessAdapter("java", "-cp", "app/bin", className);
        process.start();
    }

}
