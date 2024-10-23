package lens.judge.b5.compiler;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class RealFileTest {

    private static final String JAVA_FILE = "src/test/resources/Test.java";
    private static final String C_FILE = "src/test/resources/test.c";
    private static final String CPP_FILE = "src/test/resources/test.cpp";
    private static final String PYTHON_FILE = "src/test/resources/test.py";
    private static final String BIN_DIR = "bin/";

    @BeforeClass
    public static void setUp() throws IOException {
        // Ensure the bin directory exists
        Files.createDirectories(Paths.get(BIN_DIR));
    }

    @Test
    public void compileJavaFile() throws IOException, InterruptedException {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        String command = strategy.getCompileCommand(JAVA_FILE, BIN_DIR + "test.class");

        // Debug: Afficher la commande exécutée
        System.out.println("Executing Java compile command: " + command);

        Process process = Runtime.getRuntime().exec(command);

        // Debug: Log de la sortie du processus (normal et erreur)
        logProcessOutput(process);

        int exitCode = process.waitFor();

        // Debug: Afficher le code de sortie
        System.out.println("Java compile process exit code: " + exitCode);

        // Debug: Vérifier si le fichier .class a été créé
        System.out.println("Checking if compiled Java class exists: " + BIN_DIR + "test.class");

        boolean classFileExists = Files.exists(Paths.get(BIN_DIR + "test.class"));
        System.out.println("Class file exists: " + classFileExists);

        assertTrue(classFileExists);
        assertTrue(exitCode == 0);
    }
    @Test
    public void compileCFile() throws IOException, InterruptedException {
        CCompilationStrategy strategy = new CCompilationStrategy();
        String command = strategy.getCompileCommand(C_FILE, BIN_DIR + "testC");
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();
        strategy.getPath(BIN_DIR + "testC");
        assertTrue(Files.exists(Paths.get(BIN_DIR + "testC")));
        assertTrue(exitCode == 0);
    }

    @Test
    public void compileCppFile() throws IOException, InterruptedException {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        String command = strategy.getCompileCommand(CPP_FILE, BIN_DIR + "testCpp");
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();
        strategy.getPath(BIN_DIR + "testCpp");
        assertTrue(Files.exists(Paths.get(BIN_DIR + "testCpp")));
        assertTrue(exitCode == 0);
    }

    @Test
    public void compilePythonFile() throws IOException, InterruptedException {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        String command = strategy.getCompileCommand(PYTHON_FILE, null);
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();
        //assertTrue(Files.exists(Paths.get(PYTHON_FILE + "c")));
        assertTrue(exitCode == 0);
    }

    private void logProcessOutput(Process process) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
             BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }
        }
    }

}