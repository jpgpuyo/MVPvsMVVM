package upday.mvpvsmvvm.mvp;

import android.support.annotation.NonNull;

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

    public Presenter(@NonNull final IDataModel dataModel,
                     @NonNull final IView view) {
        mDataModel = dataModel;
        mView = view;
    }

    @Override
    public void bind() {
        mSubscription = new CompositeDisposable();
    }

    public void onGreetingClicked() {
        mSubscription.add(mDataModel.getGreeting()
                .subscribe(this::setGreeting));
    }

    @Override
    public void unBind() {
        mSubscription.clear();
    }

    private void setGreeting(@NonNull final String greeting) {
        mView.setGreeting(greeting);
    }
}
