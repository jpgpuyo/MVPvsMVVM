package upday.mvpvsmvvm.mvvm;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import upday.mvpvsmvvm.datamodel.IDataModel;

/**
 * View model for the main activity.
 */
public class ViewModel implements IViewModel {

    @NonNull
    private final IDataModel mDataModel;

    private CompositeDisposable mSubscription;

    public ObservableField<String> greetingMessage = new ObservableField<>();

    int numberOfClicks;

    public ViewModel(@NonNull final IDataModel dataModel) {
        mDataModel = dataModel;
        numberOfClicks = 0;
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
        Observable<String> greetingObservable;
        numberOfClicks++;
        if (numberOfClicks % 2 == 0) {
            greetingObservable = mDataModel.getStandardGreeting();
        } else {
            greetingObservable = mDataModel.getDroidconGreeting();
        }
        mSubscription.add(greetingObservable
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(greeting -> {
                    greetingMessage.set(greeting);
                }));
    }
}
