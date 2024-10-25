package lens.judge.b5.compiler;

/**
 * Compilation strategy for Python source files.
 * This class provides methods to check compatibility and generate compile commands for Python files.
 */
public class PythonCompilationStrategy extends AbstractCompilationStrategy {

    /**
     * Checks if the given source file is compatible with this compilation strategy.
     *
     * @param sourceFile the path to the source file
     * @return true if the file is a Python source file, false otherwise
     */
    @Override
    public boolean isCompatible(String sourceFile) {
        return sourceFile.endsWith(".py");
    }

    /**
     * Generates the binary name from the given source file.
     * For Python files, this method returns null as Python does not produce a binary file.
     *
     * @param sourceFile the path to the source file
     * @return null as Python does not produce a binary file
     */
    @Override
    public String getBinaryName(String sourceFile) {
        return null;
    }

    /**
     * Constructs the compile command for the given source file.
     * This method generates the command to compile Python source files.
     *
     * @param sourceFile the path to the source file
     * @param binaryName the name of the binary to be produced (not used for Python)
     * @return the compile command as a string
     */
    @Override
    protected String getCompileCommand(String sourceFile, String binaryName) {
        System.out.println("Compiling Python code : python3 -m py_compile " + sourceFile);
        return "python3 -m py_compile " + sourceFile;
    }
}