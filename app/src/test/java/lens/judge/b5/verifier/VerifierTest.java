package lens.judge.b5.verifier;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerifierTest {

    @Test
    void verifyReturnsTrueWhenValuesAreWithinTolerance() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer();
        comparer.setTolerance(0.1f);
        assertTrue(comparer.verify("1.0", "1.05"));
    }

    @Test
    void verifyReturnsFalseWhenValuesAreOutsideTolerance() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer();
        comparer.setTolerance(0.1f);
        assertFalse(comparer.verify("1.0", "1.2"));
    }

    @Test
    void verifyReturnsTrueWhenValuesAreExactlyEqual() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer();
        comparer.setTolerance(0.0f);
        assertTrue(comparer.verify("1.0", "1.0"));
    }

    @Test
    void verifyReturnsTrueWhenValuesAreEqualAndToleranceIsNonZero() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer();
        comparer.setTolerance(0.1f);
        assertTrue(comparer.verify("1.0", "1.0"));
    }

    @Test
    void verifyReturnsFalseWhenOneValueIsNull() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer();
        comparer.setTolerance(0.1f);
        assertFalse(comparer.verify(null, "1.0"));
    }

    @Test
    void verifyReturnsTrueWhenBothValuesAreNull() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer();
        comparer.setTolerance(0.1f);
        assertTrue(comparer.verify((File) null, null));
    }

    @Test
    void verifyReturnsFalseWhenOneValueIsEmptyString() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer();
        comparer.setTolerance(0.1f);
        assertFalse(comparer.verify("", "1.0"));
    }

    @Test
    void verifyReturnsTrueWhenBothValuesAreEmptyString() {
        PrecisionToleranceComparer comparer = new PrecisionToleranceComparer();
        comparer.setTolerance(0.1f);
        assertTrue(comparer.verify("", ""));
    }
}