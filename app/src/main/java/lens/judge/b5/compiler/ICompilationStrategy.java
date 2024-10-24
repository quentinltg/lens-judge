package lens.judge.b5.compiler;

public interface ICompilationStrategy {

    public boolean isCompatible(String sourceCode);
    public String getBinaryName(String sourceCode);
    public void compile(String sourceCode) throws Exception;

}
