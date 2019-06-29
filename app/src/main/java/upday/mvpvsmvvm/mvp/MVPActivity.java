package upday.mvpvsmvvm.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import upday.mvpvsmvvm.DroidconApplication;
import upday.mvpvsmvvm.R;
import upday.mvpvsmvvm.datamodel.IDataModel;
import upday.mvpvsmvvm.dialoghelper.DialogHelper;

/**
 * Implements the view class of the MVP pattern.
 */
public class MVPActivity extends AppCompatActivity implements IView {

    @NonNull
    private IPresenter mPresenter;

    @NonNull
    private DialogHelper dialogHelper;

    @Nullable
    private Button buttonGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new Presenter(getDataModel(), this);
        dialogHelper = new DialogHelper();
        setupViews();
    }

    private void setupViews() {
        buttonGreeting = findViewById(R.id.buttonGreeting);
        buttonGreeting.setOnClickListener(v -> mPresenter.onGreetingClicked());
    }

    @Override
    public void showStandardGreetingDialog(@NonNull final String greeting) {
        dialogHelper.showStandardGreetingDialog(this, greeting, greeting);
    }

    @Override
    public void showDroidconGreetingDialog(@NonNull final String greeting) {
        dialogHelper.showDroidconGreetingDialog(this, greeting, greeting);
    }

    @NonNull
    private IDataModel getDataModel() {
        return ((DroidconApplication) getApplication()).getDataModel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unBind();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.bind();
    }
}
