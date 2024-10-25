package lens.judge.b5.execution;

import lens.judge.b5.process.IProcess;
import lens.judge.b5.process.ProcessAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Execution strategy for C/C++ binaries.
 */
public class CExecutionStrategy implements IExecutionStrategy {

    private String binaryName;
    private ProcessAdapter process;

    /**
     * Constructs a new CExecutionStrategy with the specified binary name.
     *
     * @param binaryName the name of the binary to execute
     */
    public CExecutionStrategy(String binaryName) {
        this.binaryName = binaryName;
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
     * Executes the binary with the provided input file.
     *
     * @param inputFile the input file to be passed to the binary
     * @throws Exception if an error occurs during execution
     */
    @Override
    public void execute(File inputFile) throws Exception {
        process = new ProcessAdapter("app/bin/" + binaryName);
        process.start();

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