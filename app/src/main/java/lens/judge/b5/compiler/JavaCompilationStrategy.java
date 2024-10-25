package lens.judge.b5.compiler;

/**
 * Compilation strategy for Java source files.
 * This class provides methods to check compatibility, generate compile commands, and verify the existence of compiled binaries.
 */
public class JavaCompilationStrategy extends AbstractCompilationStrategy {

    /**
     * Checks if the given source file is compatible with this compilation strategy.
     *
     * @param sourceFile the path to the source file
     * @return true if the file is a Java source file, false otherwise
     */
    @Override
    public boolean isCompatible(String sourceFile) {
        return sourceFile.endsWith(".java");
    }

    /**
     * Generates the binary name from the given source file.
     *
     * @param sourceFile the path to the source file
     * @return the name of the binary file to be produced
     */
    @Override
    public String getBinaryName(String sourceFile) {
        return sourceFile.replace(".java", ".class");
    }

    /**
     * Constructs the compile command for the given source file and binary name.
     *
     * @param sourceFile the path to the source file
     * @param binaryName the name of the binary to be produced
     * @return the compile command as a string
     */
    @Override
    public String getCompileCommand(String sourceFile, String binaryName) {
        // System.out.println("Compiling Java code : javac -d app/bin/ " + sourceFile);
        return "javac -d app/bin/ " + sourceFile;
    }
}