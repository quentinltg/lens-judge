package lens.judge.b5.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ProcessTest {
    private IProcess process;
    private IProcess timeLimitedProcess;

    @BeforeEach
    public void setUp() {
        process = new ProcessAdapter("echo", "Hello, World!");
        timeLimitedProcess = new TimeLimitDecorator(process, 1000); // 1 second time limit
    }

    @Test
    public void testProcessStart() {
        process.start();
        assertNotNull(process.getInputStream());
    }

    @Test
    public void testProcessStop() {
        process.stop();
        assertThrows(IllegalThreadStateException.class, () -> process.exitValue());
    }

    @Test
    public void testProcessOutput() throws InterruptedException {
        process.start();
        process.waitFor();
        assertEquals("Hello, World!\n", process.getOutput());
    }

    @Test
    public void testTimeLimitDecorator() throws InterruptedException {
        IProcess process2 = new ProcessAdapter("ping", "-c", "5", "localhost"); // Ping localhost 5 times
        timeLimitedProcess = new TimeLimitDecorator(process2, 10000);
        timeLimitedProcess.start();
        Thread.sleep(2000); // Wait for 2 seconds to ensure the process is stopped by the timer
        assertThrows(IllegalThreadStateException.class, () -> timeLimitedProcess.exitValue());
        timeLimitedProcess.stop(); // Ensure the process is stopped to avoid resource leaks
    }

    @Test
    public void testTimeLimitDecoratorOutput() throws InterruptedException {
        timeLimitedProcess.start();
        timeLimitedProcess.waitFor();
        assertEquals("Hello, World!\n", timeLimitedProcess.getOutput());
    }
    @Test
    public void testProcessExitValue() throws InterruptedException {
        process.start();
        process.waitFor();
        assertEquals(0, process.exitValue());
    }

    @Test
    public void testExitValueProcessNotStarted() {
        assertThrows(IllegalThreadStateException.class, () -> process.exitValue());
    }

    @Test
    public void testExitValueProcessRunning() {
        IProcess process2 = new ProcessAdapter("ping", "-c", "5", "localhost");
        timeLimitedProcess = new TimeLimitDecorator(process2, 10000);
        timeLimitedProcess.start();
        assertThrows(IllegalThreadStateException.class, () -> process.exitValue());
        timeLimitedProcess.stop();
    }

    @Test
    public void testProcessErrorOutput() throws InterruptedException {
        IProcess errorProcess = new ProcessAdapter("sh", "-c", "echo 'Error!' 1>&2");
        errorProcess.start();
        errorProcess.waitFor();
        assertEquals("Error!\n", errorProcess.getErrorOutput());
    }




}