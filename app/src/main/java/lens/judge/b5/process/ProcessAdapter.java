package lens.judge.b5.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * The ProcessAdapter class implements the IProcess interface and provides
 * an adapter for managing and interacting with system processes.
 */
public class ProcessAdapter implements IProcess {
    private ProcessBuilder processBuilder;
    private Process process;

    /**
     * Constructs a ProcessAdapter with the specified command.
     *
     * @param command the command to execute
     */
    public ProcessAdapter(String... command) {
        this.processBuilder = new ProcessBuilder(command);
    }

    /**
     * Starts the process.
     */
    @Override
    public void start() {
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            process = null;
        }
    }

    /**
     * Stops the process.
     */
    @Override
    public void stop() {
        if (process != null) {
            process.destroy();
        }
    }

    /**
     * Waits for the process to complete and returns the exit value.
     *
     * @return the exit value of the process
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    @Override
    public int waitFor() throws InterruptedException {
        return process.waitFor();
    }

    /**
     * Returns the exit value of the process.
     *
     * @return the exit value of the process
     * @throws IllegalThreadStateException if the process has not been started or has not yet terminated
     */
    @Override
    public int exitValue() {
        if (process == null) {
            throw new IllegalThreadStateException("Process has not been started.");
        }
        if (process.isAlive()) {
            throw new IllegalThreadStateException("Process has not yet terminated.");
        }
        return process.exitValue();
    }

    /**
     * Returns the input stream of the process.
     *
     * @return the input stream of the process
     */
    @Override
    public InputStream getInputStream() {
        return process.getInputStream();
    }

    /**
     * Returns the error stream of the process.
     *
     * @return the error stream of the process
     */
    @Override
    public InputStream getErrorStream() {
        return process.getErrorStream();
    }

    /**
     * Returns the output stream of the process.
     *
     * @return the output stream of the process
     */
    @Override
    public OutputStream getOutputStream() {
        return process.getOutputStream();
    }

    /**
     * Returns the output of the process as a String.
     *
     * @return the output of the process
     */
    @Override
    public String getOutput() {
        return captureStream(process.getInputStream());
    }

    /**
     * Returns the error output of the process as a String.
     *
     * @return the error output of the process
     */
    @Override
    public String getErrorOutput() {
        return captureStream(process.getErrorStream());
    }

    /**
     * Captures the content of the given input stream and returns it as a String.
     *
     * @param inputStream the input stream to capture
     * @return the captured content as a String
     */
    private String captureStream(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error capturing stream: " + e.getMessage());
        }
        return builder.toString();
    }
}