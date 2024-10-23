package lens.judge.b5.compiler;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public void getPath(String binaryName) {
        if (Files.exists(Paths.get(binaryName))) {
            System.out.println("Compiled C file exists: " + binaryName);
        } else {
            System.err.println("Compiled C file does not exist.");
        }
    }
}
