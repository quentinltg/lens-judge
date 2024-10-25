package lens.judge.b5.compiler;

/**
 * Interface representing a compilation strategy.
 * This interface defines methods to check compatibility, generate binary names, and compile source files.
 */
public interface ICompilationStrategy {

    /**
     * Checks if the given source file is compatible with this compilation strategy.
     *
     * @param sourceCode the path to the source file
     * @return true if the file is compatible, false otherwise
     */
    public boolean isCompatible(String sourceCode);

    /**
     * Generates the binary name from the given source file.
     *
     * @param sourceCode the path to the source file
     * @return the name of the binary file to be produced
     */
    public String getBinaryName(String sourceCode);

    /**
     * Compiles the given source file.
     *
     * @param sourceCode the path to the source file
     * @throws Exception if an error occurs during compilation
     */
    public void compile(String sourceCode) throws Exception;

}