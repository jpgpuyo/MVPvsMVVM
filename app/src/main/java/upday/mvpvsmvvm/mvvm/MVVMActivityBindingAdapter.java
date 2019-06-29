package upday.mvpvsmvvm.mvvm;

import android.databinding.BindingAdapter;
import android.view.View;

import upday.mvpvsmvvm.dialoghelper.DialogHelper;

public class MVVMActivityBindingAdapter {

    @BindingAdapter({"greetingType", "greetingMessage"})
    public static void showAlertDialog(View view, GreetingType greetingType, String greetingMessage) {
        if (GreetingType.STANDARD.equals(greetingType)) {
            new DialogHelper().showStandardGreetingDialog(view.getContext(), greetingMessage, greetingMessage);
        } else if(GreetingType.DROIDCON.equals(greetingType)) {
            new DialogHelper().showDroidconGreetingDialog(view.getContext(), greetingMessage, greetingMessage);
        }
    }
}
