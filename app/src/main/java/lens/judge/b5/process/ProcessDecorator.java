package lens.judge.b5.process;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * The ProcessDecorator class is an abstract class that implements the IProcess interface.
 * It serves as a base class for decorators that add additional functionality to an IProcess instance.
 */
public abstract class ProcessDecorator implements IProcess {
    protected IProcess decoratedProcess;

    /**
     * Constructs a ProcessDecorator with the specified IProcess instance to be decorated.
     *
     * @param decoratedProcess the IProcess instance to be decorated
     */
    protected ProcessDecorator(IProcess decoratedProcess) {
        this.decoratedProcess = decoratedProcess;
    }

    /**
     * Returns the output of the decorated process as a String.
     *
     * @return the output of the decorated process
     */
    @Override
    public String getOutput() {
        return decoratedProcess.getOutput();
    }

    /**
     * Returns the error output of the decorated process as a String.
     *
     * @return the error output of the decorated process
     */
    @Override
    public String getErrorOutput() {
        return decoratedProcess.getErrorOutput();
    }

    /**
     * Starts the decorated process.
     */
    @Override
    public void start() {
        decoratedProcess.start();
    }

    /**
     * Stops the decorated process.
     */
    @Override
    public void stop() {
        decoratedProcess.stop();
    }

    /**
     * Waits for the decorated process to complete and returns the exit value.
     *
     * @return the exit value of the decorated process
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    @Override
    public int waitFor() throws InterruptedException {
        return decoratedProcess.waitFor();
    }

    /**
     * Returns the exit value of the decorated process.
     *
     * @return the exit value of the decorated process
     */
    @Override
    public int exitValue() {
        return decoratedProcess.exitValue();
    }

    /**
     * Returns the input stream of the decorated process.
     *
     * @return the input stream of the decorated process
     */
    @Override
    public InputStream getInputStream() {
        return decoratedProcess.getInputStream();
    }

    /**
     * Returns the error stream of the decorated process.
     *
     * @return the error stream of the decorated process
     */
    @Override
    public InputStream getErrorStream() {
        return decoratedProcess.getErrorStream();
    }

    /**
     * Returns the output stream of the decorated process.
     *
     * @return the output stream of the decorated process
     */
    @Override
    public OutputStream getOutputStream() {
        return decoratedProcess.getOutputStream();
    }
}