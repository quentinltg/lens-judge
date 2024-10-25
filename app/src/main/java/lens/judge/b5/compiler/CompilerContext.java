package lens.judge.b5.compiler;

/**
 * Context class for managing the compilation strategy.
 * This class allows setting a compilation strategy and compiling source files using the set strategy.
 */
public class CompilerContext {
    private ICompilationStrategy strategy;

    /**
     * Sets the compilation strategy to be used.
     *
     * @param strategy the compilation strategy to set
     */
    public void setCompilationStrategy(ICompilationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Compiles the given source file using the set compilation strategy.
     *
     * @param sourceFile the path to the source file
     * @throws Exception if an error occurs during compilation
     */
    public void compile(String sourceFile) throws Exception {
        if (strategy.isCompatible(sourceFile)) {
            strategy.compile(sourceFile);
        } else {
            System.out.println("Incompatible file type: " + sourceFile);
        }
    }
}