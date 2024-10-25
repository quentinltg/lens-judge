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

    @Override
    public boolean verify(File output, File expected) {
        List<Float> outputValues = readValues(output);
        List<Float> expectedValues = readValues(expected);

        if (outputValues.size() != expectedValues.size()) {
            return false;
        }

        for (int i = 0; i < outputValues.size(); i++) {
            float difference = Math.abs(outputValues.get(i) - expectedValues.get(i));
            if (difference > tolerance) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean verify(String output, String expected) {
        List<Float> outputValues = readValues(output);
        List<Float> expectedValues = readValues(expected);

        if (outputValues.size() != expectedValues.size()) {
            return false;
        }

        for (int i = 0; i < outputValues.size(); i++) {
            float difference = Math.abs(outputValues.get(i) - expectedValues.get(i));
            if (difference > tolerance) {
                return false;
            }
        }

        return true;
    }

    private List<Float> readValues(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .map(Float::parseFloat)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + file.getPath(), e);
        }
    }

    private List<Float> readValues(String content) {
        return Arrays.stream(content.split("\n"))
                .map(Float::parseFloat)
                .collect(Collectors.toList());
    }

    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
    }

    public float getTolerance() {
        return tolerance;
    }
}