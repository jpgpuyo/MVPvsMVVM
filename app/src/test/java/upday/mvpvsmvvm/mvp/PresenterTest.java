package upday.mvpvsmvvm.mvp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import upday.mvpvsmvvm.datamodel.IDataModel;

public class PresenterTest {

    @Mock
    private IDataModel mDataModel;

    @Mock
    private IView mView;

    private Presenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mPresenter = new Presenter(mDataModel, mView);
    }

    @Test
    public void showDroidconGreeting_whenGreetingClickedAndPreviousNumberOfClicksIsEven() {
        String standardGreeting = "Hello!";
        String droidconGreeting = "Hello Droidcon!";
        Mockito.when(mDataModel.getStandardGreeting()).thenReturn(Observable.just(standardGreeting));
        Mockito.when(mDataModel.getDroidconGreeting()).thenReturn(Observable.just(droidconGreeting));

        mPresenter.bind();
        mPresenter.numberOfClicks = 4;
        mPresenter.onGreetingClicked();

        Mockito.verify(mView).setGreeting(droidconGreeting);
    }

    @Test
    public void showStandardGreeting_whenGreetingClickedAndPreviousNumberOfClicksIsOdd() {
        String standardGreeting = "Hello!";
        String droidconGreeting = "Hello Droidcon!";
        Mockito.when(mDataModel.getStandardGreeting()).thenReturn(Observable.just(standardGreeting));
        Mockito.when(mDataModel.getDroidconGreeting()).thenReturn(Observable.just(droidconGreeting));

        mPresenter.bind();
        mPresenter.numberOfClicks = 5;
        mPresenter.onGreetingClicked();

        Mockito.verify(mView).setGreeting(standardGreeting);
    }
}
