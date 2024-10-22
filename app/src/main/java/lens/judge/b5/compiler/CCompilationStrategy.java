package lens.judge.b5.compiler;

public class CCompilationStrategy extends AbstractCompilationStrategy {

    @Override
    public boolean isCompatible(String sourceFile) {
        return sourceFile.endsWith(".c");
    }

    @Override
    public String getBinaryName(String sourceFile) {
        return sourceFile.replace(".c", "");
    }

    @Override
    protected String getCompileCommand(String sourceFile, String binaryName) {
        return "gcc -x c -Wall -O2 -static -pipe -lm -o " + binaryName + " " + sourceFile;
    }
}
