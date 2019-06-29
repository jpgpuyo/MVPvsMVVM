package upday.mvpvsmvvm.datamodel;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

public class DataModel implements IDataModel {

    @NonNull
    @Override
    public Observable<String> getStandardGreeting() {
        return Observable.just("Hello, World!");
    }

    @NonNull
    @Override
    public Observable<String> getDroidconGreeting() {
        return Observable.just("Hello, DroidCon!");
    }
}
