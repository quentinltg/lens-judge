package lens.judge.b5.verifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * The PrecisionToleranceComparer class implements the Verifier interface.
 * It provides a comparison of the output and expected values with a specified tolerance.
 */
public class PrecisionToleranceComparer implements Verifier {
    private float tolerance;

    /**
     * Default constructor for PrecisionToleranceComparer with a default tolerance of 0.0001.
     */
    public PrecisionToleranceComparer() {
        this(0.0001f);
    }

    /**
     * Constructor for PrecisionToleranceComparer with a specified tolerance.
     *
     * @param tolerance The tolerance value for comparison.
     */
    public PrecisionToleranceComparer(float tolerance) {
        this.tolerance = tolerance;
    }

    /**
     * Verifies the output file against the expected file with the specified tolerance.
     *
     * @param output The file containing the actual output.
     * @param expected The file containing the expected output.
     * @return true if the output matches the expected values within the specified tolerance, false otherwise.
     */
    @Override
    public boolean verify(File output, File expected) {
        List<Number> outputValues = readValues(output);
        List<Number> expectedValues = readValues(expected);

        if (outputValues.size() != expectedValues.size()) {
            return false;
        }

        for (int i = 0; i < outputValues.size(); i++) {
            float difference = Math.abs(outputValues.get(i).floatValue() - expectedValues.get(i).floatValue());
            if (difference > tolerance) {
                return false;
            }
        }

        return true;
    }

    /**
     * Verifies the output string against the expected string with the specified tolerance.
     *
     * @param output The actual output as a string.
     * @param expected The expected output as a string.
     * @return true if the output matches the expected values within the specified tolerance, false otherwise.
     */
    @Override
    public boolean verify(String output, String expected) {
        List<Number> outputValues = readValues(output);
        List<Number> expectedValues = readValues(expected);

        if (outputValues.size() != expectedValues.size()) {
            return false;
        }

        for (int i = 0; i < outputValues.size(); i++) {
            float difference = Math.abs(outputValues.get(i).floatValue() - expectedValues.get(i).floatValue());
            if (difference > tolerance) {
                return false;
            }
        }

        return true;
    }

    /**
     * Reads numerical values from the given file.
     *
     * @param file The file to read values from.
     * @return A list of numerical values read from the file.
     */
    private List<Number> readValues(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(this::parseNumber)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + file.getPath(), e);
        }
    }

    /**
     * Reads numerical values from the given content string.
     *
     * @param content The content string to read values from.
     * @return A list of numerical values read from the content string.
     */
    private List<Number> readValues(String content) {
        return Arrays.stream(content.split("\\s+"))
                .map(this::parseNumber)
                .toList();
    }

    /**
     * Parses a string into a numerical value.
     *
     * @param str The string to parse.
     * @return The parsed numerical value.
     * @throws RuntimeException if the string cannot be parsed into a number.
     */
    private Number parseNumber(String str) {
        try {
            if (str.contains(".")) {
                return Float.parseFloat(str);
            } else {
                return Integer.parseInt(str);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing number: " + str, e);
        }
    }

    /**
     * Sets the tolerance value for comparison.
     *
     * @param tolerance The tolerance value to set.
     */
    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
    }


}