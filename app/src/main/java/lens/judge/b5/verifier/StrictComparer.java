package lens.judge.b5.verifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The StrictComparer class implements the Verifier interface.
 * It provides a strict comparison of the output and expected strings.
 */
public class StrictComparer implements Verifier {

    /**
     * Verifies the output against the expected value using strict equality.
     *
     * @param output the output string to be verified
     * @param expected the expected string to compare against
     * @return true if the output matches the expected string exactly, false otherwise
     */
    @Override
    public boolean verify(String output, String expected) {
        List<String> outputLines = readLines(output);
        List<String> expectedLines = readLines(expected);

        if (outputLines.size() != expectedLines.size()) {
            return false;
        }

        for (int i = 0; i < outputLines.size(); i++) {
            if (!outputLines.get(i).equals(expectedLines.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifies the output file against the expected file using strict equality.
     *
     * @param outputFile the output file to be verified
     * @param expectedFile the expected file to compare against
     * @return true if the output file matches the expected file exactly, false otherwise
     */
    @Override
    public boolean verify(File outputFile, File expectedFile) {
        try {
            String output = readFile(outputFile);
            String expected = readFile(expectedFile);
            return verify(output, expected);
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
            return false;
        }
    }

    /**
     * Reads the lines from the given string.
     *
     * @param text the string to be read
     * @return a list of lines from the string
     */
    private List<String> readLines(String text) {
        if (text == null || text.isEmpty()) {
            return List.of();
        }
        return List.of(text.split("\n"));
    }

    /**
     * Reads the content of a file into a string.
     *
     * @param file the file to be read
     * @return the content of the file as a string
     * @throws IOException if an I/O error occurs
     */
    private String readFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}