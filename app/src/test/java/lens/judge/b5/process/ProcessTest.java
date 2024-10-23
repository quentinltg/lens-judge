package lens.judge.b5.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

class ProcessTest {
    private IProcess process;
    private IProcess timeLimitedProcess;

    @BeforeEach
    public void setUp() {
        process = new ProcessAdapter("echo", "Hello, World!");
        timeLimitedProcess = new TimeLimitDecorator(process, 1000); // 1 second time limit
    }

    @Test
    void testProcessStart() {
        process.start();
        assertNotNull(process.getInputStream());
    }

    @Test
    void testProcessStop() {
        process.stop();
        assertThrows(IllegalThreadStateException.class, () -> process.exitValue());
    }

    @Test
    void testProcessOutput() throws InterruptedException {
        process.start();
        process.waitFor();
        assertEquals("Hello, World!\n", process.getOutput());
    }

    @Test
    void testTimeLimitDecorator() {
        IProcess process2 = new ProcessAdapter("ping", "-c", "5", "localhost");
        timeLimitedProcess = new TimeLimitDecorator(process2, 10000);
        timeLimitedProcess.start();
        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() ->
                assertThrows(IllegalThreadStateException.class, () -> timeLimitedProcess.exitValue())
        );
        timeLimitedProcess.stop();
    }

    @Test
    void testTimeLimitDecoratorOutput() throws InterruptedException {
        timeLimitedProcess.start();
        timeLimitedProcess.waitFor();
        assertEquals("Hello, World!\n", timeLimitedProcess.getOutput());
    }
    @Test
    void testProcessExitValue() throws InterruptedException {
        process.start();
        process.waitFor();
        assertEquals(0, process.exitValue());
    }

    @Test
    void testExitValueProcessNotStarted() {
        assertThrows(IllegalThreadStateException.class, () -> process.exitValue());
    }

    @Test
    void testExitValueProcessRunning() {
        IProcess process2 = new ProcessAdapter("ping", "-c", "5", "localhost");
        timeLimitedProcess = new TimeLimitDecorator(process2, 10000);
        timeLimitedProcess.start();
        assertThrows(IllegalThreadStateException.class, () -> process.exitValue());
        timeLimitedProcess.stop();
    }

    @Test
    void testProcessErrorOutput() throws InterruptedException {
        IProcess errorProcess = new ProcessAdapter("sh", "-c", "echo 'Error!' 1>&2");
        errorProcess.start();
        errorProcess.waitFor();
        assertEquals("Error!\n", errorProcess.getErrorOutput());
    }




}