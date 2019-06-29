package upday.mvpvsmvvm.mvvm;

/**
 * Interface for the Presenter class in the MVP pattern.
 */
public interface IViewModel {

    void bind();

    void unBind();

    void onGreetingClicked();
}
