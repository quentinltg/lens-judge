package lens.judge.b5.verifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The PrecisionToleranceComparer class implements the Verifier interface.
 * It provides a comparison of the output and expected values with a specified tolerance.
 */
public class PrecisionToleranceComparer implements Verifier {
    private float tolerance;

    public PrecisionToleranceComparer() {
        this(0.0001f);
    }

    public PrecisionToleranceComparer(float tolerance) {
        this.tolerance = tolerance;
    }

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

    private List<Number> readValues(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(this::parseNumber)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + file.getPath(), e);
        }
    }

    private List<Number> readValues(String content) {
        return Arrays.stream(content.split("\\s+"))
                .map(this::parseNumber)
                .collect(Collectors.toList());
    }

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

    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
    }

    public float getTolerance() {
        return tolerance;
    }
}