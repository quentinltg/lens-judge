package lens.judge.b5.verifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class CaseInsensitiveComparerTest {

    @Test
    void verifyReturnsTrueForIdenticalStringsIgnoringCase() {
        CaseInsensitiveComparer comparer = new CaseInsensitiveComparer(new OrderToleranceComparer());
        assertTrue(comparer.verify("Line1 Line2", "line1 line2"));
    }

    @Test
    void verifyReturnsFalseForDifferentStringsIgnoringCase() {
        CaseInsensitiveComparer comparer = new CaseInsensitiveComparer(new OrderToleranceComparer());
        assertFalse(comparer.verify("Line1 Line2", "line1 line3"));
    }

    @Test
    void verifyReturnsTrueForIdenticalFilesIgnoringCase() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "Line1 Line2");
        File expectedFile = createTempFileWithContent("expected.txt", "line1 line2");
        CaseInsensitiveComparer comparer = new CaseInsensitiveComparer(new OrderToleranceComparer());
        assertTrue(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseForDifferentFilesIgnoringCase() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "Line1 Line2");
        File expectedFile = createTempFileWithContent("expected.txt", "line1 line3");
        CaseInsensitiveComparer comparer = new CaseInsensitiveComparer(new OrderToleranceComparer());
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