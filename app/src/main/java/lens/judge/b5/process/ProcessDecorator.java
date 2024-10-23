package lens.judge.b5.process;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class ProcessDecorator implements IProcess {
    protected IProcess decoratedProcess;

    protected ProcessDecorator(IProcess decoratedProcess) {
        this.decoratedProcess = decoratedProcess;
    }

    @Override
    public String getOutput() {
        return decoratedProcess.getOutput();
    }

    @Override
    public String getErrorOutput() {
        return decoratedProcess.getErrorOutput();
    }

    @Override
    public void start() {
        decoratedProcess.start();
    }

    @Override
    public void stop() {
        decoratedProcess.stop();
    }

    @Override
    public int waitFor() throws InterruptedException {
        return decoratedProcess.waitFor();
    }

    @Override
    public int exitValue() {
        return decoratedProcess.exitValue();
    }

    @Override
    public InputStream getInputStream() {
        return decoratedProcess.getInputStream();
    }

    @Override
    public InputStream getErrorStream() {
        return decoratedProcess.getErrorStream();
    }

    @Override
    public OutputStream getOutputStream() {
        return decoratedProcess.getOutputStream();
    }
}