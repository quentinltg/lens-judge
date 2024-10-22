package lens.judge.b5.compiler;

public class CppCompilationStrategy extends AbstractCompilationStrategy {

    @Override
    public boolean isCompatible(String sourceFile) {
        return sourceFile.endsWith(".cpp") || sourceFile.endsWith(".cc") || sourceFile.endsWith(".cxx");
    }

    @Override
    public String getBinaryName(String sourceFile) {
        return sourceFile.replaceFirst("\\.(cpp|cc|cxx)$", "");
    }

    @Override
    protected String getCompileCommand(String sourceFile, String binaryName) {
        return "g++ -x c++ -Wall -O2 -static -pipe -o " + binaryName + " " + sourceFile;
    }
}
