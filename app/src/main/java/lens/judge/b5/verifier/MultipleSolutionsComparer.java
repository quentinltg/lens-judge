package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The MultipleSolutionsComparer class implements the Verifier interface.
 * It provides a comparison of the output and expected values by checking if all expected lines
 * are present in the output lines, considering multiple solutions.
 */
public class MultipleSolutionsComparer implements Verifier {

    @Override
    public boolean verify(File outputFile, File expectedFile) {
        try {
            String output = new String(Files.readAllBytes(outputFile.toPath()));
            String expected = new String(Files.readAllBytes(expectedFile.toPath()));
            return verify(output, expected);
        } catch (IOException e) {
            throw new RuntimeException("Error reading files", e);
        }
    }

    @Override
    public boolean verify(String output, String expected) {
        List<String> outputLines = readOutputLines(output);
        List<String> expectedLines = readOutputLines(expected);

        if (outputLines.size() != expectedLines.size()) {
            return false;
        }

        Set<String> seenLines = new HashSet<>();
        for (String expectedLine : expectedLines) {
            if (!outputLines.contains(expectedLine) || seenLines.contains(expectedLine)) {
                return false;
            }
            seenLines.add(expectedLine);
        }
        return true;
    }

    private List<String> readOutputLines(String output) {
        if (output == null || output.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(output.split("\n"));
    }
}