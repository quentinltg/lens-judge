package lens.judge.b5.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ProcessAdapter implements IProcess {
    private ProcessBuilder processBuilder;
    private Process process;

    public ProcessAdapter(String... command) {
        this.processBuilder = new ProcessBuilder(command);
    }

    @Override
    public void start() {
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            process = null;
        }
    }

    @Override
    public void stop() {
        if (process != null) {
            process.destroy();
        }
    }

    @Override
    public int waitFor() throws InterruptedException {
        return process.waitFor();
    }

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

    @Override
    public InputStream getInputStream() {
        return process.getInputStream();
    }

    @Override
    public InputStream getErrorStream() {
        return process.getErrorStream();
    }

    @Override
    public OutputStream getOutputStream() {
        return process.getOutputStream();
    }

    @Override
    public String getOutput() {
        return captureStream(process.getInputStream());
    }

    @Override
    public String getErrorOutput() {
        return captureStream(process.getErrorStream());
    }

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