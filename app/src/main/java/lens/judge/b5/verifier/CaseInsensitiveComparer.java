package lens.judge.b5.verifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The CaseInsensitiveComparer class implements the Verifier interface.
 * It provides a case-insensitive comparison of the output and expected values.
 */
public class CaseInsensitiveComparer extends VerifierDecorator {
    public CaseInsensitiveComparer(Verifier wrappedVerifier) {
        super(wrappedVerifier);
    }

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
        String normalizedOutput = normalize(output);
        String normalizedExpected = normalize(expected);
        return super.verify(normalizedOutput, normalizedExpected);
    }

    private String normalize(String string) {
        if (string == null) {
            return null;
        }
        return string.toLowerCase().replaceAll("\\s+", " ").trim();
    }
}