
package mobidever.godutch.activity.base;

import java.lang.reflect.Field;

import mobidever.godutch.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

public class ActivityBase extends Activity {
    protected static final int SHOW_TIME = 1;

    protected void showMsg(String pMsg) {
        Toast.makeText(this, pMsg, 1).show();
    }

    protected void openActivity(Class<?> pClass) {
        Intent intent = new Intent();
        intent.setClass(this, pClass);
        startActivity(intent);
    }

    public void setAlertDialogIsClose(DialogInterface dialog, Boolean isClose) {
        try {
            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog, isClose);
        } catch (Exception e) {
        }
    }
    
    protected AlertDialog showAlertDialog(int titelResID, String message,
            DialogInterface.OnClickListener clickListener) {
        String title = getResources().getString(titelResID);
        return showAlertDialog(title, message, clickListener);
    }

    protected AlertDialog showAlertDialog(String title, String message,
            DialogInterface.OnClickListener clickListener) {
        return new AlertDialog.Builder(this).setTitle(title).setMessage(message)
                .setPositiveButton(R.string.ButtonTextYes, clickListener)
                .setNegativeButton(R.string.ButtonTextNo, null).show();
    }
}
