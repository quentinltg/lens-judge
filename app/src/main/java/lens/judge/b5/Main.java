package lens.judge.b5;

import lens.judge.b5.problem.Problem;
import lens.judge.b5.problem.TestCase;
import lens.judge.b5.verifier.OrderToleranceComparer;
import lens.judge.b5.verifier.PrecisionToleranceComparer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Ask the user to enter the file paths
            System.out.print("Enter the input file path: ");
            String inputFilePath = scanner.nextLine();
            System.out.print("Enter the expected file path: ");
            String expectedFilePath = scanner.nextLine();

            // Create a list of test cases
            List<TestCase> testCases = new ArrayList<>();
            testCases.add(new TestCase(
                    new File("app/src/main/java/lens/judge/b5/verifier/"+inputFilePath),
                    new File("app/src/main/java/lens/judge/b5/verifier/"+expectedFilePath)
            ));

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