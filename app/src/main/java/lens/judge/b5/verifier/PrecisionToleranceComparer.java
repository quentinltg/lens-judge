package lens.judge.b5.verifier;

/**
 * The PrecisionToleranceComparer class implements the Verifier interface.
 * It provides a comparison of the output and expected values with a specified tolerance.
 */
public class PrecisionToleranceComparer implements Verifier {
    /**
     * The tolerance value for the comparison.
     */
    private float tolerance;

    /**
     * Verifies the output against the expected value using a tolerance.
     *
     * @param output the output string to be verified
     * @param expected the expected string to compare against
     * @return true if the difference between the output and expected values is within the tolerance, false otherwise
     */
    @Override
    public boolean verify(String output, String expected) {
        float outputValue = convertToFloat(output);
        float expectedValue = convertToFloat(expected);
        float difference = Math.abs(outputValue - expectedValue);
        return difference <= getTolerance();
    }

    /**
     * Converts the given string to a float value.
     *
     * @param value the string to be converted
     * @return the float value of the string, or 0.0f if the string is null
     */
    private float convertToFloat(String value) {
        if (value == null) {
            return 0.0f;
        }
        else {
            return Float.parseFloat(value);
        }
    }

    /**
     * Sets the tolerance value for the comparison.
     *
     * @param tolerance the tolerance value to be set
     */
    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
    }

    /**
     * Gets the tolerance value for the comparison.
     *
     * @return the tolerance value
     */
    public float getTolerance() {
        return tolerance;
    }
}