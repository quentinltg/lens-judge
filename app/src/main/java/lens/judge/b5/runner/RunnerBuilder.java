package lens.judge.b5.runner;

import lens.judge.b5.Process.ProcessAdapter;
import lens.judge.b5.compiler.CCompilationStrategy;
import lens.judge.b5.compiler.CppCompilationStrategy;
import lens.judge.b5.compiler.ICompilationStrategy;
import lens.judge.b5.compiler.JavaCompilationStrategy;
import lens.judge.b5.execution.CExecutionStrategy;
import lens.judge.b5.execution.IExecutionStrategy;
import lens.judge.b5.execution.JavaExecutionStrategy;
import lens.judge.b5.problem.Problem;
import lens.judge.b5.problem.TestCase;

public class RunnerBuilder {

    private IExecutionStrategy executionStrategy;
    private ICompilationStrategy compilationStrategy;

    private String sourceFile;

    public RunnerBuilder() {}

    public RunnerBuilder newInstance() {
        return new RunnerBuilder();
    }

    public RunnerBuilder withExecutionStrategy(IExecutionStrategy executionStrategy) {
        this.executionStrategy = executionStrategy;
        return this;
    }

    public RunnerBuilder withCompilationStrategy(ICompilationStrategy compilationStrategy) {
        this.compilationStrategy = compilationStrategy;
        return this;
    }

    public RunnerBuilder withSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
        return this;
    }

    public Runner build() {
        chooseStrategies();
        return new Runner(executionStrategy, compilationStrategy, sourceFile);
    }

    public RunnerBuilder chooseStrategies() {
        String extension = getFileExtension(sourceFile);

        switch (extension) {
            case ".java":  // L'extension est maintenant sans le point
                this.compilationStrategy = new JavaCompilationStrategy();
                // Indiquer le chemin du fichier class compilé
                this.executionStrategy = new JavaExecutionStrategy("Test");
                break;
      case ".c":
          this.compilationStrategy = new CCompilationStrategy();
          this.executionStrategy = new CExecutionStrategy("test");
          break;
      case ".cpp":
          this.compilationStrategy = new CppCompilationStrategy();
          this.executionStrategy = new CExecutionStrategy("test");
          break;
            default:
                throw new IllegalArgumentException("Unsupported file type: " + extension);
        }

        return this;
    }

    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // Pas d'extension
        }
        return fileName.substring(lastIndexOfDot);
    }

}
