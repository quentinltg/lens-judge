package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<String> outputLines = readLines(output);
        List<String> expectedLines = readLines(expected);

        // Check if the sets of lines are equal, ignoring order
        Set<String> outputSet = new HashSet<>(outputLines);
        Set<String> expectedSet = new HashSet<>(expectedLines);

        return outputSet.equals(expectedSet);
    }

    private List<String> readLines(String content) {
        if (content == null || content.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(content.split("\\s+"));
    }
}