package lens.judge.b5.compiler;

import lens.judge.b5.process.ProcessAdapter;

/**
 * Abstract class representing a compilation strategy.
 * This class provides a template for compiling source files.
 */
public abstract class AbstractCompilationStrategy implements ICompilationStrategy {

    /**
     * Compiles the given source file.
     *
     * @param sourceFile the path to the source file
     * @throws Exception if an error occurs during compilation
     */
    @Override
    public void compile(String sourceFile) throws Exception {
        String binaryName = getBinaryName(sourceFile);
        String compileCommand = getCompileCommand(sourceFile, binaryName);
        executeCommand(compileCommand);
    }

    /**
     * Constructs the compile command for the given source file and binary name.
     *
     * @param sourceFile the path to the source file
     * @param binaryName the name of the binary to be produced
     * @return the compile command as a string
     */
    protected abstract String getCompileCommand(String sourceFile, String binaryName);

    /**
     * Executes the given command.
     *
     * @param command the command to execute
     * @throws Exception if the command execution fails
     */
    public void executeCommand(String command) throws Exception {
        ProcessAdapter process = new ProcessAdapter(command.split(" "));
        process.start();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new Exception("Process exited with non-zero code: " + exitCode);
        }
    }
}