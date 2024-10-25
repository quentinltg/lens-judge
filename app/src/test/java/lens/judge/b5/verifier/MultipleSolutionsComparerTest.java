package lens.judge.b5.verifier;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class MultipleSolutionsComparerTest {

    @Test
    void verifyReturnsTrueWhenOutputMatchesAnyExpectedSolution() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "solution1\nsolution2");
        File expectedFile = createTempFileWithContent("expected.txt", "solution2\nsolution3");
        MultipleSolutionsComparer comparer = new MultipleSolutionsComparer();
        assertTrue(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsFalseWhenOutputDoesNotMatchAnyExpectedSolution() throws IOException {
        File outputFile = createTempFileWithContent("output.txt", "solution4\nsolution5");
        File expectedFile = createTempFileWithContent("expected.txt", "solution2\nsolution3");
        MultipleSolutionsComparer comparer = new MultipleSolutionsComparer();
        assertFalse(comparer.verify(outputFile, expectedFile));
    }

    @Test
    void verifyReturnsTrueWhenOutputMatchesAnyExpectedSolutionInStrings() {
        MultipleSolutionsComparer comparer = new MultipleSolutionsComparer();
        assertTrue(comparer.verify("solution1\nsolution2", "solution2\nsolution3"));
    }

    @Test
    void verifyReturnsFalseWhenOutputDoesNotMatchAnyExpectedSolutionInStrings() {
        MultipleSolutionsComparer comparer = new MultipleSolutionsComparer();
        assertFalse(comparer.verify("solution4\nsolution5", "solution2\nsolution3"));
    }

    private File createTempFileWithContent(String fileName, String content) throws IOException {
        File tempFile = File.createTempFile(fileName, null);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(content.getBytes());
        }
        return tempFile;
    }
}