package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The MultipleSolutionsComparer class implements the Verifier interface.
 * It provides a comparison of the output and expected values by checking if the output
 * matches any of the expected solutions.
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
        // Split the expected content into lines and store them in a Set
        Set<String> expectedLines = new HashSet<>(List.of(expected.split("\n")));

        // Split the output content into lines
        List<String> outputLines = List.of(output.split("\n"));

        // Check if the output matches any of the expected solutions
        for (String line : outputLines) {
            if (expectedLines.contains(line)) {
                return true;
            }
        }
        return false;
    }
}