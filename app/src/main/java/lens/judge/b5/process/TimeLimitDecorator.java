package lens.judge.b5.process;

import java.util.Timer;
import java.util.TimerTask;

public class TimeLimitDecorator extends ProcessDecorator {
    private int timeLimit;
    private Timer timer;

    public TimeLimitDecorator(IProcess decoratedProcess, int timeLimit) {
        super(decoratedProcess);
        this.timeLimit = timeLimit;
    }

    @Override
    public void start() {
        super.start();
        checkTimeLimit();
    }

    @Override
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
        super.stop();
    }

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