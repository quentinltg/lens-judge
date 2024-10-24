package lens.judge.b5.process;

import java.io.InputStream;
import java.io.OutputStream;

public interface IProcess {
    String getOutput();
    String getErrorOutput();
    void start();
    void stop();
    int waitFor() throws InterruptedException;
    int exitValue();
    InputStream getInputStream();
    InputStream getErrorStream();
    OutputStream getOutputStream();
}