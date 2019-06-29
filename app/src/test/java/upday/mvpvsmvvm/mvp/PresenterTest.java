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
    public void testGetGreeting_set_whenGreetingClicked() {
        String greeting = "Hello!";
        Mockito.when(mDataModel.getGreeting()).thenReturn(Observable.just(greeting));

        mPresenter.bind();
        mPresenter.onGreetingClicked();

        Mockito.verify(mView).setGreeting(greeting);
    }
}
