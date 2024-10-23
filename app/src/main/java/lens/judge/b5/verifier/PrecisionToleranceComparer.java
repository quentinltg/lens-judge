package lens.judge.b5.verifier;

public class PrecisionToleranceComparer implements Verifier {
    private float tolerance;
    @Override
    public boolean verify(String output, String expected) {
        float outputValue = convertToFloat(output);
        float expectedValue = convertToFloat(expected);
        float difference = Math.abs(outputValue - expectedValue);
        return difference <= getTolerance();
    }

    private float convertToFloat(String value) {
        if (value == null) {
            return 0.0f;
        }
        else {
            return Float.parseFloat(value);
        }
    }

    public void setTolerance(float tolerance) {
        this.tolerance = tolerance;
    }

    public float getTolerance() {
        return tolerance;
    }
}