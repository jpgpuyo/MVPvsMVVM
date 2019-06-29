package upday.mvpvsmvvm.mvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import upday.mvpvsmvvm.DroidconApplication;
import upday.mvpvsmvvm.R;
import upday.mvpvsmvvm.datamodel.IDataModel;
import upday.mvpvsmvvm.dialoghelper.DialogHelper;

public class MVVMActivity extends AppCompatActivity {

    @NonNull
    private ViewModel mViewModel;

    @NonNull
    private DialogHelper dialogHelper;

    @NonNull
    private Button buttonGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModel(getDataModel());
        dialogHelper = new DialogHelper();
        setupViews();
    }

    private void setupViews() {
        buttonGreeting = findViewById(R.id.buttonGreeting);
        buttonGreeting.setOnClickListener(v -> mViewModel.onGreetingClicked());
        mViewModel.greetingSubject.subscribe(this::setGreeting);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.bind();
    }

    @Override
    protected void onPause() {
        mViewModel.unBind();
        super.onPause();
    }

    private void setGreeting(@NonNull final String greeting) {
        dialogHelper.showDialog(this, greeting, greeting);
    }

    @NonNull
    private IDataModel getDataModel() {
        return ((DroidconApplication) getApplication()).getDataModel();
    }
}
