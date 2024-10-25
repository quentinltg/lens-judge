package lens.judge.b5.compiler;

import org.junit.jupiter.api.Test;

class CompilerContextTest {

    @Test
    void compilePrintsIncompatibleMessageForNonCFile() throws Exception {
        CompilerContext context = new CompilerContext();
        ICompilationStrategy strategy = new CCompilationStrategy();
        context.setCompilationStrategy(strategy);

        context.compile("example.cpp");
    }

    @Test
    void setCompilationStrategyChangesStrategy() {
        CompilerContext context = new CompilerContext();
        ICompilationStrategy strategy1 = new CCompilationStrategy();
        ICompilationStrategy strategy2 = new CCompilationStrategy();
        context.setCompilationStrategy(strategy1);

        context.setCompilationStrategy(strategy2);
    }
}