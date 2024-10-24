package lens.judge.b5.execution;

public class ExecutionContext {

    private IExecutionStrategy execution;

    public void setExecutionStrategy(IExecutionStrategy execution) {
        this.execution = execution;
    }

    public void executeStrategy() {
        execution.execute();
    }

}
