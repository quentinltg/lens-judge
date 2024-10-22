package lens.judge.b5.compiler;

public class JavaCompilationStrategy extends AbstractCompilationStrategy {

    @Override
    public boolean isCompatible(String sourceFile) {
        return sourceFile.endsWith(".java");
    }

    @Override
    public String getBinaryName(String sourceFile) {
        return sourceFile.replace(".java", ".class");
    }

    @Override
    protected String getCompileCommand(String sourceFile, String binaryName) {
        return "javac " + sourceFile;
    }
}
