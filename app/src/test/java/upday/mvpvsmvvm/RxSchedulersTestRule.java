package upday.mvpvsmvvm;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

public class RxSchedulersTestRule implements TestRule {

    private TestScheduler mainThreadScheduler;
    private TestScheduler ioScheduler;
    private TestScheduler computationScheduler;
    private TestScheduler newThreadScheduler;

    public RxSchedulersTestRule() {
        initSchedulers();
    }

    private void initSchedulers() {
        mainThreadScheduler = new TestScheduler();
        ioScheduler = new TestScheduler();
        computationScheduler = new TestScheduler();
        newThreadScheduler = new TestScheduler();
    }

    public TestScheduler mainScheduler() {
        return mainThreadScheduler;
    }

    public TestScheduler ioScheduler() {
        return ioScheduler;
    }

    public TestScheduler computationScheduler() {
        return computationScheduler;
    }

    public TestScheduler newThreadScheduler() {
        return newThreadScheduler;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxAndroidPlugins.reset();
                RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> mainThreadScheduler);

                RxJavaPlugins.reset();
                RxJavaPlugins.setIoSchedulerHandler(scheduler -> ioScheduler);
                RxJavaPlugins.setComputationSchedulerHandler(scheduler -> computationScheduler);
                RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> newThreadScheduler);

                // execute test
                try {
                    base.evaluate();
                } finally {
                    RxJavaPlugins.reset();
                    RxAndroidPlugins.reset();
                }
            }
        };
    }
}
