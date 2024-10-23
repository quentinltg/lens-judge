package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class OrderToleranceComparer implements Verifier {

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
        List<String> outputLines = readExpectedLines(output);
        List<String> expectedLines = readExpectedLines(expected);

        int expectedIndex = 0;
        for (String outputLine : outputLines) {
            if (expectedIndex < expectedLines.size() && outputLine.equals(expectedLines.get(expectedIndex))) {
                expectedIndex++;
            }
        }
        return expectedIndex == expectedLines.size();
    }

    private List<String> readExpectedLines(String expected) {
        if (expected == null || expected.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(expected.split("\n"));
    }

    private boolean isLineInCollection(String line, List<String> collection) {
        return collection.contains(line);
    }
}