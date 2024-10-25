package lens.judge.b5.runner;

import lens.judge.b5.compiler.*;
import lens.judge.b5.execution.CExecutionStrategy;
import lens.judge.b5.execution.IExecutionStrategy;
import lens.judge.b5.execution.JavaExecutionStrategy;
import lens.judge.b5.execution.PythonExecutionStrategy;
import lens.judge.b5.problem.TestCase;

/**
 * The RunnerBuilder class is responsible for constructing a Runner object
 * with the appropriate compilation and execution strategies based on the source file type.
 */
public class RunnerBuilder {

    private IExecutionStrategy executionStrategy;
    private ICompilationStrategy compilationStrategy;
    private TestCase testCase;
    private String sourceFile;

    /**
     * Default constructor for RunnerBuilder.
     */
    public RunnerBuilder() {}

    /**
     * Sets the source file for the Runner.
     *
     * @param sourceFile the path to the source file
     * @return the current instance of RunnerBuilder
     */
    public RunnerBuilder withSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
        return this;
    }

    /**
     * Builds and returns a Runner object with the chosen strategies.
     *
     * @return a new Runner object
     */
    public Runner build() {
        chooseStrategies();
        return new Runner(executionStrategy, compilationStrategy, sourceFile);
    }

    /**
     * Chooses the appropriate compilation and execution strategies based on the source file extension.
     *
     * @return the current instance of RunnerBuilder
     */
    public RunnerBuilder chooseStrategies() {
        String fileNameWithoutExtension = extractFileNameWithoutExtension(sourceFile);
        String extension = getFileExtension(sourceFile);

        switch (extension) {
            case ".java":
                this.compilationStrategy = new JavaCompilationStrategy();
                this.executionStrategy = new JavaExecutionStrategy(fileNameWithoutExtension);
                break;
            case ".c":
                this.compilationStrategy = new CCompilationStrategy();
                this.executionStrategy = new CExecutionStrategy(fileNameWithoutExtension);
                break;
            case ".cpp":
            case ".cc":
            case ".cxx":
                this.compilationStrategy = new CppCompilationStrategy();
                this.executionStrategy = new CExecutionStrategy(fileNameWithoutExtension);
                break;
            case ".py":
                this.compilationStrategy = new PythonCompilationStrategy();
                this.executionStrategy = new PythonExecutionStrategy(sourceFile);
                break;
            default:
                throw new IllegalArgumentException("Unsupported file type: " + extension);
        }
        return this;
    }

    /**
     * Extracts the file extension from the given file name.
     *
     * @param fileName the name of the file
     * @return the file extension
     */
    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // No extension
        }
        return fileName.substring(lastIndexOfDot);
    }

    /**
     * Sets the test case for the Runner.
     *
     * @param tc the test case to be used
     * @return the current instance of RunnerBuilder
     */
    public RunnerBuilder withTestCase(TestCase tc) {
        this.testCase = tc;
        return this;
    }

    /**
     * Extracts the file name without its extension from the given file path.
     *
     * @param filePath the path to the file
     * @return the file name without its extension
     */
    public static String extractFileNameWithoutExtension(String filePath) {
        String fileNameWithExtension = filePath.substring(filePath.lastIndexOf('/') + 1);
        return fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf('.'));
    }
}