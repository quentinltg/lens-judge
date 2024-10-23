package lens.judge.b5.compiler;

import lens.judge.b5.Process.ProcessAdapter;

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
            ProcessAdapter process = new ProcessAdapter(String.valueOf(builder));
            process.start();
            process.stop();

            if (!process.getErrorOutput().isEmpty()) {
                System.out.println("Compilation Errors: " + process.getErrorOutput());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
