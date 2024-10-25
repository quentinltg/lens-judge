package lens.judge.b5.compiler;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A strategy for compiling C source files.
 * This class extends the AbstractCompilationStrategy and provides
 * specific implementations for compiling C code.
 */
public class CCompilationStrategy extends AbstractCompilationStrategy {

    /**
     * Checks if the given source file is compatible with this compilation strategy.
     *
     * @param sourceFile the path to the source file
     * @return true if the source file ends with ".c", false otherwise
     */
    @Override
    public boolean isCompatible(String sourceFile) {
        return sourceFile.endsWith(".c");
    }

    /**
     * Gets the binary name for the given source file.
     *
     * @param sourceFile the path to the source file
     * @return the binary name derived from the source file name
     */
    @Override
    public String getBinaryName(String sourceFile) {
        String [] parts = sourceFile.split("/");
        String fileNameWithExtension = parts[parts.length - 1];
        return fileNameWithExtension.replace(".c", "");
    }

    /**
     * Constructs the compile command for the given source file and binary name.
     *
     * @param sourceFile the path to the source file
     * @param binaryName the name of the binary to be produced
     * @return the compile command as a string
     */
    @Override
    protected String getCompileCommand(String sourceFile, String binaryName) {
        // System.out.println("Compiling C code : gcc -x c -Wall -O2 -static -pipe -lm -o app/bin/" + binaryName + " " + sourceFile);
        return "gcc -x c -Wall -O2 -static -pipe -lm -o app/bin/" + binaryName + " " + sourceFile;
    }

    /**
     * Checks if the compiled binary exists at the specified path.
     *
     * @param binaryName the name of the binary to check
     */
    public void getPath(String binaryName) {
        if (Files.exists(Paths.get(binaryName))) {
            System.out.println("Compiled C file exists: " + binaryName);
        } else {
            System.err.println("Compiled C file does not exist.");
        }
    }
}