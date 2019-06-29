package upday.mvpvsmvvm.mvvm;

import android.databinding.BindingAdapter;
import android.view.View;

import upday.mvpvsmvvm.dialoghelper.DialogHelper;

public class MainActivityBindingAdapter {

    @BindingAdapter({"greetingMessage"})
    public static void showAlertDialog(View view, String greetingMessage) {
        new DialogHelper().showDialog(view.getContext(), greetingMessage, greetingMessage);
    }
}
