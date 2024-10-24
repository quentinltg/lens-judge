package lens.judge.b5.compiler;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CppCompilationStrategy extends AbstractCompilationStrategy {

    @Override
    public boolean isCompatible(String sourceFile) {
        return sourceFile.endsWith(".cpp") || sourceFile.endsWith(".cc") || sourceFile.endsWith(".cxx");
    }

    @Override
    public String getBinaryName(String sourceFile) {
        String [] parts = sourceFile.split("/");
        String fileNameWithExtension = parts[parts.length - 1];
        return fileNameWithExtension.replaceFirst("\\.(cpp|cc|cxx)$", "");
    }

    @Override
    protected String getCompileCommand(String sourceFile, String binaryName) {
        System.out.println("Compiling C++ code : g++ -x c++ -Wall -O2 -static -pipe -o app/bin/" + binaryName + sourceFile);
        return "g++ -x c++ -Wall -O2 -static -pipe -o app/bin/" + binaryName + " " + sourceFile;
    }

    public void getPath(String binaryName) {
        if (Files.exists(Paths.get(binaryName))) {
            System.out.println("Compiled C++ file exists: " + binaryName);
        } else {
            System.err.println("Compiled C++ file does not exist.");
        }
    }
}
