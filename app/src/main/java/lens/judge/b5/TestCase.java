package lens.judge.b5;

public class TestCase {
    private String input;
    private String output;

    // Constructor
    public void TestCase(String input, String output) {
        this.input = input;
        this.output = output;
    }

    // Getters and setters
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
