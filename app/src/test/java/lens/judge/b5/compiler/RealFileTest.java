package lens.judge.b5.compiler;

import lens.judge.b5.process.ProcessAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



class RealFileTest {

    private static final String JAVA_FILE = "src/test/resources/Test.java";
    private static final String C_FILE = "src/test/resources/test.c";
    private static final String CPP_FILE = "src/test/resources/test.cpp";
    private static final String PYTHON_FILE = "src/test/resources/test.py";
    private static final String BIN_DIR = "bin/";

    @BeforeAll
    public static void setUp() throws IOException {
        // Ensure the bin directory exists
        Files.createDirectories(Paths.get(BIN_DIR));
    }

    @Test
    void compileJavaFile() throws InterruptedException {
        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        String command = strategy.getCompileCommand(JAVA_FILE, BIN_DIR + "Test.class");
        System.out.println("Executing Java compile command: " + command);
        ProcessAdapter process = new ProcessAdapter(command.split(" "));
        process.start();
        logProcessOutput(process);
        int exitCode = process.waitFor();
        System.out.println("Java compile process exit code: " + exitCode);
        System.out.println("Checking if compiled Java class exists: " + BIN_DIR + "Test.class");
        boolean classFileExists = Files.exists(Paths.get(BIN_DIR + "Test.class"));
        System.out.println("Class file exists: " + classFileExists);
        Assertions.assertTrue(classFileExists, "Class file should exist");
        Assertions.assertEquals(0, exitCode, "Exit code should be 0");
    }

    @Test
    void compileCFile() throws InterruptedException {
        CCompilationStrategy strategy = new CCompilationStrategy();
        String command = strategy.getCompileCommand(C_FILE, BIN_DIR + "testC");
        ProcessAdapter process = new ProcessAdapter(command.split(" "));
        process.start();
        int exitCode = process.waitFor();
        strategy.getPath(BIN_DIR + "testC");
        Assertions.assertTrue(Files.exists(Paths.get(BIN_DIR + "testC")));
        Assertions.assertEquals(0, exitCode);
    }

    @Test
    void compileCppFile() throws InterruptedException {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        String command = strategy.getCompileCommand(CPP_FILE, BIN_DIR + "testCpp");
        ProcessAdapter process = new ProcessAdapter(command.split(" "));
        process.start();
        int exitCode = process.waitFor();
        strategy.getPath(BIN_DIR + "testCpp");
        Assertions.assertTrue(Files.exists(Paths.get(BIN_DIR + "testCpp")));
        Assertions.assertEquals(0, exitCode);
    }

    @Test
    void compilePythonFile() throws InterruptedException {
        PythonCompilationStrategy strategy = new PythonCompilationStrategy();
        String command = strategy.getCompileCommand(PYTHON_FILE, null);
        ProcessAdapter process = new ProcessAdapter(command.split(" "));
        process.start();
        int exitCode = process.waitFor();
        Assertions.assertEquals(0, exitCode);
    }

    private void logProcessOutput(ProcessAdapter process) {
        System.out.println(process.getOutput());
        System.err.println(process.getErrorOutput());
    }
}

