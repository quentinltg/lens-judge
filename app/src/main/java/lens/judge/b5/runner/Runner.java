package lens.judge.b5.runner;

import lens.judge.b5.compiler.ICompilationStrategy;
import lens.judge.b5.execution.IExecutionStrategy;
import lens.judge.b5.verifier.StrictComparer;
import lens.judge.b5.verifier.Verifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Runner {
    private IExecutionStrategy executionStrategy;
    private ICompilationStrategy compilationStrategy;
    private String sourceFile;

    public Runner(IExecutionStrategy executionStrategy, ICompilationStrategy compilationStrategy, String sourceFile) {
        this.executionStrategy = executionStrategy;
        this.compilationStrategy = compilationStrategy;
        this.sourceFile = sourceFile;
    }

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
            // System.out.println("Verification result: " + result);
            return result;

        } catch (Exception e) {
            System.out.println("Execution failed: " + e.getMessage());
            return false;
        }
    }
}