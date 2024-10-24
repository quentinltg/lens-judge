package lens.judge.b5.compiler;

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
        System.out.println("Compiling C code : gcc -x c -Wall -O2 -static -pipe -lm -o app/bin/" + binaryName + " " + sourceFile);
        return "gcc -x c -Wall -O2 -static -pipe -lm -o app/bin/" + binaryName + " app/src/test/resources/" + sourceFile;
    }

    public void getPath(String binaryName) {
        if (Files.exists(Paths.get(binaryName))) {
            System.out.println("Compiled C file exists: " + binaryName);
        } else {
            System.err.println("Compiled C file does not exist.");
        }
    }
}
