package upday.mvpvsmvvm.dialoghelper;

import android.app.AlertDialog;
import android.content.Context;

import upday.mvpvsmvvm.R;

public class DialogHelper {

    public void showStandardGreetingDialog(Context context, String title, String message) {
        showDialog(context, title, message, R.style.standardGreeting);
    }

    public void showDroidconGreetingDialog(Context context, String title, String message) {
        showDialog(context, title, message, R.style.droidconGreeting);
    }

    private void showDialog(Context context, String title, String message, int theme) {
        AlertDialog alertDialog = new AlertDialog.Builder(context, theme)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss())
                .create();
        alertDialog.show();
    }
}
