package upday.mvpvsmvvm.dialoghelper;

import android.app.AlertDialog;
import android.content.Context;

import upday.mvpvsmvvm.R;

public class DialogHelper {

    public void showDialog(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss())
                .create();
        alertDialog.show();
    }
}
