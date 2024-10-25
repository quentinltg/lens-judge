package lens.judge.b5.compiler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CppCompilationStrategyTest {

    @Test
    void isCompatibleReturnsTrueForCppFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertTrue(strategy.isCompatible("example.cpp"));
    }

    @Test
    void isCompatibleReturnsTrueForCcFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertTrue(strategy.isCompatible("example.cc"));
    }

    @Test
    void isCompatibleReturnsTrueForCxxFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertTrue(strategy.isCompatible("example.cxx"));
    }

    @Test
    void isCompatibleReturnsFalseForNonCppFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertFalse(strategy.isCompatible("example.c"));
    }

    @Test
    void getBinaryNameReturnsCorrectNameForCppFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertEquals("example", strategy.getBinaryName("path/to/example.cpp"));
    }

    @Test
    void getBinaryNameReturnsCorrectNameForCcFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertEquals("example", strategy.getBinaryName("path/to/example.cc"));
    }

    @Test
    void getBinaryNameReturnsCorrectNameForCxxFile() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        assertEquals("example", strategy.getBinaryName("path/to/example.cxx"));
    }

    @Test
    void getCompileCommandReturnsCorrectCommand() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        String expectedCommand = "g++ -x c++ -Wall -O2 -static -pipe -o app/bin/example path/to/example.cpp";
        assertEquals(expectedCommand, strategy.getCompileCommand("path/to/example.cpp", "example"));
    }

    @Test
    void getPathPrintsExistsMessageWhenBinaryExists() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
        // Assuming the binary exists at the specified path
        strategy.getPath("app/bin/example");
    }

    @Test
    void getPathPrintsDoesNotExistMessageWhenBinaryDoesNotExist() {
        CppCompilationStrategy strategy = new CppCompilationStrategy();
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