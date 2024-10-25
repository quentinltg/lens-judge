package lens.judge.b5.process;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * The IProcess interface defines the contract for a process that can be started, stopped, and monitored.
 */
public interface IProcess {

    /**
     * Returns the output of the process as a String.
     *
     * @return the output of the process
     */
    String getOutput();

    /**
     * Returns the error output of the process as a String.
     *
     * @return the error output of the process
     */
    String getErrorOutput();

    /**
     * Starts the process.
     */
    void start();

    /**
     * Stops the process.
     */
    void stop();

    /**
     * Waits for the process to complete and returns the exit value.
     *
     * @return the exit value of the process
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    int waitFor() throws InterruptedException;

    /**
     * Returns the exit value of the process.
     *
     * @return the exit value of the process
     */
    int exitValue();

    /**
     * Returns the input stream of the process.
     *
     * @return the input stream of the process
     */
    InputStream getInputStream();

    /**
     * Returns the error stream of the process.
     *
     * @return the error stream of the process
     */
    InputStream getErrorStream();

    /**
     * Returns the output stream of the process.
     *
     * @return the output stream of the process
     */
    OutputStream getOutputStream();
}