package lens.judge.b5;

import lens.judge.b5.Process.ProcessAdapter;
import lens.judge.b5.compiler.JavaCompilationStrategy;
import lens.judge.b5.execution.IExecutionStrategy;
import lens.judge.b5.execution.JavaExecutionStrategy;
import lens.judge.b5.problem.Problem;
import lens.judge.b5.problem.TestCase;
import lens.judge.b5.runner.Runner;
import lens.judge.b5.runner.RunnerBuilder;
import lens.judge.b5.runner.Verdict;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Utilisation du chemin absolu du fichier source Java
        // String sourceFile = "app/src/test/resources/Test.java";
        // String sourceFile = "test.c";
        // String sourceFile = "test.cpp";
        String sourceFile = "app/src/test/resources/test.py";

        // Créer un problème avec un TestCase
        TestCase testCase = new TestCase("input1", "expectedOutput1");
        Problem problem = new Problem();
        problem.addTestCase(testCase);

        // Itérer sur les TestCases du problème
        for (TestCase tc : problem) {
            // Construire et exécuter le Runner
            Runner runner = new RunnerBuilder()
//                    .withTestCase(tc)
                    .withSourceFile(sourceFile)  // Chemin complet du fichier source
                    .build();  // Le builder choisit les stratégies en fonction du fichier

            Verdict verdict = runner.run();  // Exécuter le programme sans vérification pour le moment
            System.out.println("TestCase verdict: " + verdict);
        }


    }
}




//        private static final String JAVA_FILE = "app/src/test/resources/Test.java";
//        private static final String C_FILE = "app/src/test/resources/test.c";
//        private static final String CPP_FILE = "app/src/test/resources/test.cpp";
//        private static final String PYTHON_FILE = "app/src/test/resources/test.py";
//        private static final String BIN_DIR = "app/bin/";

//        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
//        String command = strategy.getCompileCommand(JAVA_FILE, BIN_DIR + "Test.class");
//
//        JavaCompilationStrategy strategy = new JavaCompilationStrategy();
//        String command = strategy.getCompileCommand(JAVA_FILE, BIN_DIR + "Test.class");
//
//        // Debug: Afficher la commande exécutée
//        // System.out.println("Executing Java compile command: " + command);
//
//        ProcessAdapter process = new ProcessAdapter(command.split(" "));
//        process.start();
//
//        // Debug: Vérifier si le fichier .class a été créé
//        // System.out.println("Checking if compiled Java class exists: " + BIN_DIR + "Test.class");
//
//        // boolean classFileExists = Files.exists(Paths.get(BIN_DIR + "Test.class"));
//        // System.out.println("Class file exists: " + classFileExists);
//
//        IExecutionStrategy executionStrategy = new JavaExecutionStrategy("Test");
//        executionStrategy.execute();
//
//        System.out.println(executionStrategy.getProcess().getOutput());
