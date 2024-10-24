package lens.judge.b5.execution;

import lens.judge.b5.process.ProcessAdapter;

public class PythonExecutionStrategy implements IExecutionStrategy {

    private String scriptName;
    private ProcessAdapter process;

    public PythonExecutionStrategy(String className) {
        this.scriptName = className;
    }

    public ProcessAdapter getProcess() {
        return process;
    }

    public void execute() {
        System.out.println("Executing Python code : python3 " + scriptName);
        process = new ProcessAdapter("python3", scriptName);
        process.start();
    }

}
