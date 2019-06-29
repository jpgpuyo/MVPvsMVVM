package upday.mvpvsmvvm.mvvm;

import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import upday.mvpvsmvvm.datamodel.IDataModel;

/**
 * View model for the main activity.
 */
public class ViewModel implements IViewModel {

    @NonNull
    private final IDataModel mDataModel;

    private CompositeDisposable mSubscription;

    public PublishSubject<String> greetingSubject;

    public ViewModel(@NonNull final IDataModel dataModel) {
        mDataModel = dataModel;
        greetingSubject = PublishSubject.create();
    }

    @Override
    public void bind() {
        mSubscription = new CompositeDisposable();
    }

    @Override
    public void unBind() {
        mSubscription.clear();
    }

    @Override
    public void onGreetingClicked() {
        mSubscription.add(mDataModel.getGreeting()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(greeting -> greetingSubject.onNext(greeting)));
    }
}
