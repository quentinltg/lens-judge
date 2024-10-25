package lens.judge.b5;

import lens.judge.b5.problem.Problem;
import lens.judge.b5.problem.TestCase;

import lens.judge.b5.runner.Runner;
import lens.judge.b5.runner.RunnerBuilder;
import lens.judge.b5.runner.Verdict;
import lens.judge.b5.verifier.OrderToleranceComparer;
import lens.judge.b5.verifier.StrictComparer;
import lens.judge.b5.verifier.Verifier;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class LensJudge {
    public static void main(String[] args) {
        // Utilisation du chemin absolu du fichier source Java
        // String sourceFile = "app/src/test/resources/Test.java";
        // String sourceFile = "app/src/test/resources/test.c";
        // String sourceFile = "app/src/test/resources/test.cpp";
        // String sourceFile = "app/src/test/resources/test.py";

        if (args.length < 3) {
            System.out.println("Usage: lensjudge <sourceFile> <inputFile> <expectedOutputFile>");
            return;
        }

        String sourceFile = args[0];
        File inputFile = new File(args[1]);
        File expectedOutputFile = new File(args[2]);


        // Créer un problème avec un TestCase
        StrictComparer comparer = new StrictComparer();
        ArrayList<TestCase> testCases = new ArrayList<>();
        TestCase testCase = new TestCase(inputFile, expectedOutputFile);
        testCases.add(testCase);
        Problem problem = new Problem(testCases, 1000, 256, comparer );  // Limite de temps: 1000 ms, Limite de mémoire: 256 Mo


        // Itérer sur les TestCases du problème
        for (TestCase tc : problem) {
            // Construire et exécuter le Runner
            Runner runner = new RunnerBuilder()
                    .withTestCase(tc)
                    .withSourceFile(sourceFile)  // Chemin complet du fichier source
                    .build();  // Le builder choisit les stratégies en fonction du fichier




                    /*

            try (Scanner scanner = new Scanner(System.in)) {
            // Ask the user to enter the file paths
            System.out.print("Enter the input file path: ");
            String inputFilePath = scanner.nextLine();
            System.out.print("Enter the expected file path: ");
            String expectedFilePath = scanner.nextLine();

         /*
            // Create a list of test cases
            List<TestCase> testCases = new ArrayList<>();
            testCases.add(new TestCase(
                    new File("app/src/main/java/lens/judge/b5/verifier/"+inputFilePath),
                    new File("app/src/main/java/lens/judge/b5/verifier/"+expectedFilePath)
            ));
            */

            boolean verdict = runner.run(inputFile, expectedOutputFile);  // Exécuter le programme sans vérification pour le moment
            System.out.println("TestCase verdict: " + verdict);
        }
    }
}
        /*
            // Create a PrecisionToleranceComparer
            OrderToleranceComparer comparer = new OrderToleranceComparer();

            // Create an instance of Problem with the test cases and the comparer
            Problem problem = new Problem(testCases, 1000, 256, comparer);

            // Verify each test case
            for (TestCase testCase : problem.getTestCases()) {
                boolean result = problem.verifyTestCase(testCase);
                System.out.println("Test case result: " + result);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
*/



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
