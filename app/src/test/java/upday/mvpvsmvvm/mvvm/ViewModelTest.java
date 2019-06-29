package upday.mvpvsmvvm.mvvm;

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
    public void testGreetingSubject_set_whenGreetingClicked() {
        String greeting = "Hello!";
        Mockito.when(mDataModel.getGreeting()).thenReturn(Observable.just(greeting));
        TestObserver<String> testObserver = new TestObserver<>();

        mViewModel.bind();
        mViewModel.greetingSubject.subscribe(testObserver);
        mViewModel.onGreetingClicked();

        rxSchedulersTestRule.computationScheduler().triggerActions();
        rxSchedulersTestRule.mainScheduler().triggerActions();

        testObserver.assertValue(greeting);
    }

}

