package upday.mvpvsmvvm.mvp;

import android.support.annotation.NonNull;

/**
 * Interface for the view classes in the MVP pattern.
 */
public interface IView {

    void showStandardGreetingDialog(@NonNull final String greeting);

    void showDroidconGreetingDialog(@NonNull final String greeting);
}
