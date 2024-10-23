package lens.judge.b5.verifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The OutputReader class provides a method to read the contents of a file
 * and return it as a list of strings, where each string represents a line in the file.
 */

public class OutputReader {

    /**
     * Reads the contents of the specified file and returns it as a list of strings.
     * Each string in the list represents a line in the file.
     *
     * @param file the path to the file to be read
     * @return a list of strings representing the lines in the file, or an empty list if an error occurs
     */
    public List<String> readOutput(String file) {
        try {
            return Files.readAllLines(Paths.get(file));
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}