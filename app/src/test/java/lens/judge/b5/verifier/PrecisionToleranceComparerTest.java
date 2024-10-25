package lens.judge.b5.verifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class PrecisionToleranceComparerTest {

    @Test
    void verifyReturnsTrueForIdenticalFilesWithinTolerance() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "1.0001 2.0001");
        File expectedFile = createTempFileWithContent("expected.txt", "1.0002 2.0002");
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer(0.001f);
        assertTrue(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseForFilesOutsideTolerance() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "1.0001 2.0001");
        File expectedFile = createTempFileWithContent("expected.txt", "1.002 2.002");
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer(0.001f);
        assertFalse(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsTrueForIdenticalStringsWithinTolerance() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer(0.001f);
        assertTrue(comparer.verify("1.0001 2.0001", "1.0002 2.0002"));
    }

    @Test
    void verifyReturnsFalseForStringsOutsideTolerance() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer(0.001f);
        assertFalse(comparer.verify("1.0001 2.0001", "1.002 2.002"));
    }

    @Test
    void verifyReturnsFalseForDifferentNumberOfValuesInFiles() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "1.0001 2.0001");
        File expectedFile = createTempFileWithContent("expected.txt", "1.0001");
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer(0.001f);
        assertFalse(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseForDifferentNumberOfValuesInStrings() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer(0.001f);
        assertFalse(comparer.verify("1.0001 2.0001", "1.0001"));
    }

    private File createTempFileWithContent(String fileName, String content) throws IOException {
        File tempFile = File.createTempFile(fileName, null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(content.getBytes());
        }
        return tempFile;
    }
}