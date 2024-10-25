package lens.judge.b5.verifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class StrictComparerTest {

    @Test
    void verifyReturnsTrueForIdenticalStrings() {
        StrictComparer comparer = new StrictComparer();
        assertTrue(comparer.verify("line1\nline2", "line1\nline2"));
    }

    @Test
    void verifyReturnsFalseForDifferentStrings() {
        StrictComparer comparer = new StrictComparer();
        assertFalse(comparer.verify("line1\nline2", "line1\nline3"));
    }

    @Test
    void verifyReturnsFalseForDifferentLineCounts() {
        StrictComparer comparer = new StrictComparer();
        assertFalse(comparer.verify("line1\nline2", "line1"));
    }

    @Test
    void verifyReturnsTrueForIdenticalFiles() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "line1\nline2");
        File expectedFile = createTempFileWithContent("expected.txt", "line1\nline2");
        StrictComparer comparer = new StrictComparer();
        assertTrue(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseForDifferentFiles() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "line1\nline2");
        File expectedFile = createTempFileWithContent("expected.txt", "line1\nline3");
        StrictComparer comparer = new StrictComparer();
        assertFalse(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseWhenOutputFileDoesNotExist() {
        File outputFile = new File("nonexistent_output.txt");
        File expectedFile = new File("expected.txt");
        StrictComparer comparer = new StrictComparer();
        assertFalse(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseWhenExpectedFileDoesNotExist() {
        File outputFile = new File("output.txt");
        File expectedFile = new File("nonexistent_expected.txt");
        StrictComparer comparer = new StrictComparer();
        assertFalse(comparer.verify(outputFile, expectedFile));
    }

    private File createTempFileWithContent(String fileName, String content) throws IOException {
        File tempFile = File.createTempFile(fileName, null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(content.getBytes());
        }
        return tempFile;
    }
}