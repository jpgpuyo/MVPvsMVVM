package upday.mvpvsmvvm.mvp;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import upday.mvpvsmvvm.datamodel.IDataModel;

/**
 * Implementation class for the Presenter in the MVP model.
 */
public class Presenter implements IPresenter {

    @NonNull
    private final IDataModel mDataModel;

    @NonNull
    private final IView mView;

    private CompositeDisposable mSubscription;

    int numberOfClicks;

    public Presenter(@NonNull final IDataModel dataModel,
                     @NonNull final IView view) {
        mDataModel = dataModel;
        mView = view;
        numberOfClicks = 0;
    }

    @Override
    public void bind() {
        mSubscription = new CompositeDisposable();
    }

    public void onGreetingClicked() {
        numberOfClicks++;

        if (numberOfClicks % 2 == 0) {
            mSubscription.add(mDataModel.getStandardGreeting()
                    .subscribe(mView::showStandardGreetingDialog));
        } else {
            mSubscription.add(mDataModel.getDroidconGreeting()
                    .subscribe(mView::showDroidconGreetingDialog));
        }
    }

    @Override
    public void unBind() {
        mSubscription.clear();
    }
}
