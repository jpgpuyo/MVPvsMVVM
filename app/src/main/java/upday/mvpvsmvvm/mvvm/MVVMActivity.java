package upday.mvpvsmvvm.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import upday.mvpvsmvvm.DroidconApplication;
import upday.mvpvsmvvm.R;
import upday.mvpvsmvvm.databinding.ActivityMainBinding;
import upday.mvpvsmvvm.datamodel.IDataModel;

public class MVVMActivity extends AppCompatActivity {

    @NonNull
    private ViewModel mViewModel;

    @NonNull
    private Button buttonGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        mViewModel = new ViewModel(getDataModel());
        binding.setViewModel(mViewModel);

        setupViews();
    }

    private void setupViews() {
        buttonGreeting = findViewById(R.id.buttonGreeting);
        buttonGreeting.setOnClickListener(v -> mViewModel.onGreetingClicked());
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

    @NonNull
    private IDataModel getDataModel() {
        return ((DroidconApplication) getApplication()).getDataModel();
    }
}
