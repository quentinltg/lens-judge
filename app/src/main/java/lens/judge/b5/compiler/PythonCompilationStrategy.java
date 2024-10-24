package lens.judge.b5.compiler;

public class PythonCompilationStrategy extends AbstractCompilationStrategy {

    @Override
    public boolean isCompatible(String sourceFile) {
        return sourceFile.endsWith(".py");
    }

    @Override
    public String getBinaryName(String sourceFile) {
        return null;
    }

    @Override
    protected String getCompileCommand(String sourceFile, String binaryName) {
        System.out.println("Compiling Python code : python3 -m py_compile " + sourceFile);
        return "python3 -m py_compile " + sourceFile;
    }
}
