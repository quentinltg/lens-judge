package lens.judge.b5.verifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class WhiteSpaceToleranceComparerTest {

    @Test
    void verifyReturnsFalseForDifferentStringsIgnoringSpaces() {
        Verifier baseVerifier = new StrictComparer();
        WhiteSpaceToleranceComparer comparer = new WhiteSpaceToleranceComparer(baseVerifier);
        assertFalse(comparer.verify("line1  line2", "line1 line3"));
    }

    @Test
    void verifyReturnsFalseForDifferentFilesIgnoringSpaces() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "line1  line2");
        File expectedFile = createTempFileWithContent("expected.txt", "line1 line3");
        Verifier baseVerifier = new StrictComparer();
        WhiteSpaceToleranceComparer comparer = new WhiteSpaceToleranceComparer(baseVerifier);
        assertFalse(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseWhenOutputFileDoesNotExist() {
        File outputFile = new File("nonexistent_output.txt");
        File expectedFile = new File("expected.txt");
        Verifier baseVerifier = new StrictComparer();
        WhiteSpaceToleranceComparer comparer = new WhiteSpaceToleranceComparer(baseVerifier);
        assertFalse(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseWhenExpectedFileDoesNotExist() {
        File outputFile = new File("output.txt");
        File expectedFile = new File("nonexistent_expected.txt");
        Verifier baseVerifier = new StrictComparer();
        WhiteSpaceToleranceComparer comparer = new WhiteSpaceToleranceComparer(baseVerifier);
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