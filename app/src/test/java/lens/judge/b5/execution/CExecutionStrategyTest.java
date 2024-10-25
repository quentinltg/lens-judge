package lens.judge.b5.execution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class CExecutionStrategyTest {

    @Test
    void executeThrowsExceptionForNonExistentBinary() {
        CExecutionStrategy strategy = new CExecutionStrategy("nonExistentBinary");
        File inputFile = new File("input.txt");
        assertThrows(IOException.class, () -> strategy.execute(inputFile));
    }

    @Test
    void executeThrowsExceptionForInvalidInputFile() {
        CExecutionStrategy strategy = new CExecutionStrategy("testBinary");
        File inputFile = new File("nonexistent_input.txt");
        assertThrows(IOException.class, () -> strategy.execute(inputFile));
    }

    private File createTempFileWithContent(String fileName, String content) throws IOException {
        File tempFile = File.createTempFile(fileName, null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(content.getBytes());
        }
        return tempFile;
    }
}