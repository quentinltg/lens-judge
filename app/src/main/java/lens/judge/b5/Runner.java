/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package lens.judge.b5;

import lens.judge.b5.Process.ProcessAdapter;
import lens.judge.b5.compiler.JavaCompilationStrategy;
import lens.judge.b5.execution.IExecutionStrategy;
import lens.judge.b5.execution.JavaExecutionStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Runner {

    private static final String JAVA_FILE = "app/src/test/resources/Test.java";
    private static final String C_FILE = "app/src/test/resources/test.c";
    private static final String CPP_FILE = "app/src/test/resources/test.cpp";
    private static final String PYTHON_FILE = "app/src/test/resources/test.py";
    private static final String BIN_DIR = "app/bin/";

    public static void main(String[] args) throws InterruptedException, IOException {

        System.out.println("Welcome to the Lens Judge!");

        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
        String command = strategy.getCompileCommand(JAVA_FILE, BIN_DIR + "Test.class");

        // Debug: Afficher la commande exécutée
        // System.out.println("Executing Java compile command: " + command);

        ProcessAdapter process = new ProcessAdapter(command.split(" "));
        process.start();

        // Debug: Vérifier si le fichier .class a été créé
        // System.out.println("Checking if compiled Java class exists: " + BIN_DIR + "Test.class");

        // boolean classFileExists = Files.exists(Paths.get(BIN_DIR + "Test.class"));
        // System.out.println("Class file exists: " + classFileExists);

        IExecutionStrategy executionStrategy = new JavaExecutionStrategy("Test");
        executionStrategy.execute();

        System.out.println(executionStrategy.getProcess().getOutput());

    }
}
