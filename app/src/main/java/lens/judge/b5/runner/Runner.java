package lens.judge.b5.runner;

import lens.judge.b5.Process.IProcess;
import lens.judge.b5.compiler.ICompilationStrategy;
import lens.judge.b5.execution.IExecutionStrategy;
import lens.judge.b5.problem.TestCase;

public class Runner {
    private IExecutionStrategy executionStrategy;
    private ICompilationStrategy compilationStrategy;
    private String sourceFile;

    public Runner(IExecutionStrategy executionStrategy, ICompilationStrategy compilationStrategy, String sourceFile) {
        this.executionStrategy = executionStrategy;
        this.compilationStrategy = compilationStrategy;
        this.sourceFile = sourceFile;
    }

    public Verdict run() {
        // 1. Compiler le programme
        try {
            compilationStrategy.compile(sourceFile);
        } catch (Exception e) {
            System.out.println("Compilation failed.");
            return Verdict.COMPILATION_ERROR;
        }

        // 2. Exécuter le programme avec l'entrée du TestCase
        try {
            executionStrategy.execute();
            if (executionStrategy.getProcess().getOutput() == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Execution failed.");
            return Verdict.RUNTIME_ERROR;
        }

        // 3. Afficher le résultat temporairement
        System.out.println("Program output: " + executionStrategy.getProcess().getOutput());

        // Retourner un verdict temporaire
        return Verdict.ACCEPTED;
    }
}

