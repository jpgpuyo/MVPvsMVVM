package upday.mvpvsmvvm.mvvm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import upday.mvpvsmvvm.RxSchedulersTestRule;
import upday.mvpvsmvvm.datamodel.IDataModel;

import static org.junit.Assert.assertEquals;

public class ViewModelTest {

    @Mock
    private IDataModel mDataModel;

    @Rule
    public RxSchedulersTestRule rxSchedulersTestRule = new RxSchedulersTestRule();

    private ViewModel mViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mViewModel = new ViewModel(mDataModel);
    }

    @Test
    public void showDroidconGreeting_whenGreetingClickedAndPreviousNumberOfClicksIsEven() {
        String standardGreeting = "Hello!";
        String droidconGreeting = "Hello Droidcon!";
        Mockito.when(mDataModel.getStandardGreeting()).thenReturn(Observable.just(standardGreeting));
        Mockito.when(mDataModel.getDroidconGreeting()).thenReturn(Observable.just(droidconGreeting));
        TestObserver<String> testObserver = new TestObserver<>();

        mViewModel.bind();
        mViewModel.numberOfClicks = 4;
        mViewModel.onGreetingClicked();

        rxSchedulersTestRule.computationScheduler().triggerActions();
        rxSchedulersTestRule.mainScheduler().triggerActions();

        assertEquals(GreetingType.DROIDCON, mViewModel.greetingType.get());
        assertEquals(droidconGreeting, mViewModel.greetingMessage.get());
    }

    @Test
    public void showStandardGreeting_whenGreetingClickedAndPreviousNumberOfClicksIsOdd() {
        String standardGreeting = "Hello!";
        String greetingDroidcon = "Hello Droidcon!";
        Mockito.when(mDataModel.getStandardGreeting()).thenReturn(Observable.just(standardGreeting));
        Mockito.when(mDataModel.getDroidconGreeting()).thenReturn(Observable.just(greetingDroidcon));

        mViewModel.bind();
        mViewModel.numberOfClicks = 5;
        mViewModel.onGreetingClicked();

        rxSchedulersTestRule.computationScheduler().triggerActions();
        rxSchedulersTestRule.mainScheduler().triggerActions();

        assertEquals(GreetingType.STANDARD, mViewModel.greetingType.get());
        assertEquals(standardGreeting, mViewModel.greetingMessage.get());
    }

}

