package lens.judge.b5.runner;

import lens.judge.b5.compiler.ICompilationStrategy;
import lens.judge.b5.execution.IExecutionStrategy;
import lens.judge.b5.verifier.Verifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Runner class is responsible for compiling, executing, and verifying the output of a source file.
 */
public class Runner {
    private IExecutionStrategy executionStrategy;
    private ICompilationStrategy compilationStrategy;
    private String sourceFile;

    /**
     * Constructs a Runner with the specified execution and compilation strategies and source file.
     *
     * @param executionStrategy the strategy to execute the compiled program
     * @param compilationStrategy the strategy to compile the source file
     * @param sourceFile the path to the source file to be compiled and executed
     */
    public Runner(IExecutionStrategy executionStrategy, ICompilationStrategy compilationStrategy, String sourceFile) {
        this.executionStrategy = executionStrategy;
        this.compilationStrategy = compilationStrategy;
        this.sourceFile = sourceFile;
    }

    /**
     * Compiles and executes the source file with the given input file, and verifies the output against the expected output file.
     *
     * @param inputFile the input file to be used during execution
     * @param expectedOuputFile the file containing the expected output
     * @param comparer the verifier to compare the actual output with the expected output
     * @return true if the output matches the expected output, false otherwise
     */
    public boolean run(File inputFile, File expectedOuputFile, Verifier comparer) {
        File outputFile = new File("app/src/main/resources/output.ans");

        // 1. Compile the program
        try {
            compilationStrategy.compile(sourceFile);
            // System.out.println("Compilation successful.");
        } catch (Exception e) {
            System.out.println("Compilation failed: " + e.getMessage());
            return false;
        }

        // 2. Execute the program with the TestCase input
        try {
            executionStrategy.execute(inputFile);

            // Capture the output and errors
            String output = executionStrategy.getProcess().getOutput();
            String errorOutput = executionStrategy.getProcess().getErrorOutput();

            if (!errorOutput.isEmpty()) {
                System.out.println("Execution error: " + errorOutput);
                return false;
            }

            // Write the output to the output file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(output);
            } catch (IOException e) {
                System.out.println("Error writing to output file: " + e.getMessage());
                return false;
            }

            // 3. Verify the output
            System.out.print("\nOutput: " + output);
            boolean result = comparer.verify(outputFile, expectedOuputFile);
            return result;

        } catch (Exception e) {
            System.out.println("Execution failed: " + e.getMessage());
            return false;
        }
    }
}