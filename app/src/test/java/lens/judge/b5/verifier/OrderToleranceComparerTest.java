package lens.judge.b5.verifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class OrderToleranceComparerTest {

    @Test
    void verifyReturnsTrueForIdenticalStringsIgnoringOrder() {
        OrderToleranceComparer comparer = new OrderToleranceComparer();
        assertTrue(comparer.verify("line1 line2", "line2 line1"));
    }

    @Test
    void verifyReturnsFalseForDifferentStringsIgnoringOrder() {
        OrderToleranceComparer comparer = new OrderToleranceComparer();
        assertFalse(comparer.verify("line1 line2", "line1 line3"));
    }

    @Test
    void verifyReturnsTrueForIdenticalFilesIgnoringOrder() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "line1 line2");
        File expectedFile = createTempFileWithContent("expected.txt", "line2 line1");
        OrderToleranceComparer comparer = new OrderToleranceComparer();
        assertTrue(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseForDifferentFilesIgnoringOrder() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "line1 line2");
        File expectedFile = createTempFileWithContent("expected.txt", "line1 line3");
        OrderToleranceComparer comparer = new OrderToleranceComparer();
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