package lens.judge.b5.execution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class ExecutionContextTest {

    @Test
    void executeStrategyRunsSuccessfullyWithValidInput() throws Exception {
        ExecutionContext context = new ExecutionContext();
        IExecutionStrategy strategy = new JavaExecutionStrategy("TestClass");
        context.setExecutionStrategy(strategy);

        File inputFile = createTempFileWithContent("input.txt", "input data");
        context.executeStrategy(inputFile);

        assertNotNull(((JavaExecutionStrategy) strategy).getProcess());
    }

    @Test
    void executeStrategyThrowsExceptionForInvalidInputFile() {
        ExecutionContext context = new ExecutionContext();
        IExecutionStrategy strategy = new JavaExecutionStrategy("TestClass");
        context.setExecutionStrategy(strategy);

        File inputFile = new File("nonexistent_input.txt");
        assertThrows(IOException.class, () -> context.executeStrategy(inputFile));
    }

    private File createTempFileWithContent(String fileName, String content) throws IOException {
        File tempFile = File.createTempFile(fileName, null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(content.getBytes());
        }
        return tempFile;
    }
}