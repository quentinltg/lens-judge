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
    public String getCompileCommand(String sourceFile, String binaryName) {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        return "javac -d app/bin/ " + sourceFile;
    }
}
