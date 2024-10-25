package lens.judge.b5.compiler;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Compilation strategy for C++ source files.
 * This class provides methods to check compatibility, generate compile commands, and verify the existence of compiled binaries.
 */
public class CppCompilationStrategy extends AbstractCompilationStrategy {

    /**
     * Checks if the given source file is compatible with this compilation strategy.
     *
     * @param sourceFile the path to the source file
     * @return true if the file is a C++ source file, false otherwise
     */
    @Override
    public boolean isCompatible(String sourceFile) {
        return sourceFile.endsWith(".cpp") || sourceFile.endsWith(".cc") || sourceFile.endsWith(".cxx");
    }

    /**
     * Generates the binary name from the given source file.
     *
     * @param sourceFile the path to the source file
     * @return the name of the binary file to be produced
     */
    @Override
    public String getBinaryName(String sourceFile) {
        String [] parts = sourceFile.split("/");
        String fileNameWithExtension = parts[parts.length - 1];
        return fileNameWithExtension.replaceFirst("\\.(cpp|cc|cxx)$", "");
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
        // System.out.println("Compiling C++ code : g++ -x c++ -Wall -O2 -static -pipe -o app/bin/" + binaryName + sourceFile);
        return "g++ -x c++ -Wall -O2 -static -pipe -o app/bin/" + binaryName + " " + sourceFile;
    }

    /**
     * Checks if the compiled binary file exists at the given path.
     *
     * @param binaryName the name of the binary file
     */
    public void getPath(String binaryName) {
        if (Files.exists(Paths.get(binaryName))) {
            System.out.println("Compiled C++ file exists: " + binaryName);
        } else {
            System.err.println("Compiled C++ file does not exist.");
        }
    }
}