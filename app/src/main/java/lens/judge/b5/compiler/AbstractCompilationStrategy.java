package lens.judge.b5.compiler;

import lens.judge.b5.process.IProcess;
import lens.judge.b5.process.ProcessAdapter;

public abstract class AbstractCompilationStrategy implements ICompilationStrategy {

    @Override
    public void compile(String sourceFile) {
        String binaryName = getBinaryName(sourceFile);
        String compileCommand = getCompileCommand(sourceFile, binaryName);
        executeCommand(compileCommand);
    }

    protected abstract String getCompileCommand(String sourceFile, String binaryName);

    protected void executeCommand(String command) {
        try {
            ProcessBuilder builder = new ProcessBuilder(command.split(" "));
            ProcessAdapter process = new ProcessAdapter(builder);
            process.start();
            process.stop();

            if (!process.getErrors().isEmpty()) {
                System.out.println("Compilation Errors: " + process.getErrors());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
