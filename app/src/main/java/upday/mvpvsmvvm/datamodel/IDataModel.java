package upday.mvpvsmvvm.datamodel;

import android.support.annotation.NonNull;

import io.reactivex.Observable;


public interface IDataModel {

    @NonNull
    Observable<String> getStandardGreeting();

    @NonNull
    Observable<String> getDroidconGreeting();

}
