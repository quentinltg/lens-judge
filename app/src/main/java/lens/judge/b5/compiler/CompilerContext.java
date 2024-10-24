package lens.judge.b5.compiler;

public class CompilerContext {
    private ICompilationStrategy strategy;

    public void setCompilationStrategy(ICompilationStrategy strategy) {
        this.strategy = strategy;
    }

    public void compile(String sourceFile) throws Exception {
        if (strategy.isCompatible(sourceFile)) {
            strategy.compile(sourceFile);
        } else {
            System.out.println("Incompatible file type: " + sourceFile);
        }
    }
}
