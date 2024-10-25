package lens.judge.b5;

import lens.judge.b5.problem.Problem;
import lens.judge.b5.problem.TestCase;

import lens.judge.b5.runner.Runner;
import lens.judge.b5.runner.RunnerBuilder;
import lens.judge.b5.runner.Verdict;
import lens.judge.b5.verifier.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LensJudge {

    /**
     * The main method is the entry point of the LensJudge application.
     * It takes three command-line arguments: the source file, the input file, and the expected output file.
     * It creates test cases, initializes various verifiers, and runs the tests using different verifiers.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: lensjudge <sourceFile> <inputFile> <expectedOutputFile>");
            return;
        }

        String sourceFile = args[0];
        File inputFile = new File(args[1]);
        File expectedOutputFile = new File(args[2]);

        // Create a list of test cases
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(inputFile, expectedOutputFile));

        // Create instances of different comparers
        Verifier strictComparer = new StrictComparer();
        Verifier caseInsensitiveComparer = new CaseInsensitiveComparer(strictComparer);
        Verifier whiteSpaceToleranceComparer = new WhiteSpaceToleranceComparer(strictComparer);
        Verifier orderToleranceComparer = new OrderToleranceComparer();
        Verifier multipleSolutionsComparer = new MultipleSolutionsComparer();

        // Change the tolerance value as needed using the constructor
        Verifier precisionToleranceComparer = new PrecisionToleranceComparer();

        // Create a list of comparers
        List<Verifier> comparers = List.of(
                strictComparer,
                caseInsensitiveComparer,
                whiteSpaceToleranceComparer,
                orderToleranceComparer,
                multipleSolutionsComparer,
                precisionToleranceComparer
        );

        // Iterate over each comparer
        for (Verifier comparer : comparers) {
            // Create a problem with the test cases and the comparer
            Problem problem = new Problem(testCases, 1000, 256, comparer);

            // Iterate over the test cases in the problem
            for (TestCase tc : problem) {
                // Build and run the runner
                Runner runner = new RunnerBuilder()
                        .withTestCase(tc)
                        .withSourceFile(sourceFile)
                        .build();

                Verdict verdict = runner.run(inputFile, expectedOutputFile, comparer);  // Exécuter le programme sans vérification pour le moment
                System.out.println("\nComparer: " + comparer.getClass().getSimpleName());
                System.out.println("Verdict: " + verdict);
            }
        }
        System.out.println();
    }
}