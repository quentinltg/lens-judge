package lens.judge.b5.process;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The TimeLimitDecorator class extends the ProcessDecorator class and adds a time limit
 * to the execution of a process. If the process exceeds the specified time limit, it is stopped.
 */
public class TimeLimitDecorator extends ProcessDecorator {
    private int timeLimit;
    private Timer timer;

    /**
     * Constructs a TimeLimitDecorator with the specified decorated process and time limit.
     *
     * @param decoratedProcess the IProcess instance to be decorated
     * @param timeLimit the time limit in milliseconds
     */
    public TimeLimitDecorator(IProcess decoratedProcess, int timeLimit) {
        super(decoratedProcess);
        this.timeLimit = timeLimit;
    }

    /**
     * Starts the decorated process and checks the time limit.
     */
    @Override
    public void start() {
        super.start();
        checkTimeLimit();
    }

    /**
     * Stops the decorated process and cancels the timer if it is running.
     */
    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
        super.stop();
    }

    /**
     * Checks the time limit and stops the process if it exceeds the specified time limit.
     */
    public void checkTimeLimit() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stop();
            }
        }, timeLimit);
    }
}