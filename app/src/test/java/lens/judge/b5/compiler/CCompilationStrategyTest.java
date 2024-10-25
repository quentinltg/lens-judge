package lens.judge.b5.compiler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CCompilationStrategyTest {

    @Test
    void isCompatibleReturnsTrueForCFile() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        assertTrue(strategy.isCompatible("example.c"));
    }

    @Test
    void isCompatibleReturnsFalseForNonCFile() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        assertFalse(strategy.isCompatible("example.cpp"));
    }

    @Test
    void getBinaryNameReturnsCorrectName() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        assertEquals("example", strategy.getBinaryName("path/to/example.c"));
    }

    @Test
    void getCompileCommandReturnsCorrectCommand() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        String expectedCommand = "gcc -x c -Wall -O2 -static -pipe -lm -o app/bin/example path/to/example.c";
        assertEquals(expectedCommand, strategy.getCompileCommand("path/to/example.c", "example"));
    }

    @Test
    void getPathPrintsExistsMessageWhenBinaryExists() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        // Assuming the binary exists at the specified path
        strategy.getPath("app/bin/example");
    }

    @Test
    void getPathPrintsDoesNotExistMessageWhenBinaryDoesNotExist() {
        CCompilationStrategy strategy = new CCompilationStrategy();
        // Assuming the binary does not exist at the specified path
        strategy.getPath("app/bin/nonexistent");
    }

    @AfterEach
    void cleanUp() throws IOException {
        Path appDirectory = Paths.get("app");
        if (Files.exists(appDirectory)) {
            Files.walk(appDirectory)
                    .sorted((path1, path2) -> path2.compareTo(path1))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }
}