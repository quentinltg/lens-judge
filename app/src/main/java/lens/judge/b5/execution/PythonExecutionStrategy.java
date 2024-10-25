package lens.judge.b5.execution;

import lens.judge.b5.process.IProcess;
import lens.judge.b5.process.ProcessAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Execution strategy for Python scripts.
 */
public class PythonExecutionStrategy implements IExecutionStrategy {

    private String scriptName;
    private ProcessAdapter process;

    /**
     * Constructs a new PythonExecutionStrategy with the specified script name.
     *
     * @param scriptName the name of the Python script to execute
     */
    public PythonExecutionStrategy(String scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * Returns the process associated with this execution strategy.
     *
     * @return the process associated with this execution strategy
     */
    @Override
    public IProcess getProcess() {
        return process;
    }

    /**
     * Executes the Python script with the provided input file.
     *
     * @param inputFile the input file to be passed to the Python script
     * @throws Exception if an error occurs during execution
     */
    @Override
    public void execute(File inputFile) throws Exception {
        // System.out.println("Executing Python code : python3 " + scriptName);
        process = new ProcessAdapter("python3", scriptName);
        process.start();

        // Write the input to the process
        try (FileInputStream fis = new FileInputStream(inputFile);
             OutputStream processInput = process.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                processInput.write(buffer, 0, bytesRead);
            }
            processInput.flush();
        } catch (IOException e) {
            System.out.println("Error writing to process input: " + e.getMessage());
            throw e;
        }
    }
}