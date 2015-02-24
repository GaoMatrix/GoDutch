
package mobidever.godutch.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class ActivityBase extends Activity {

    protected void showMsg(String pMsg) {
        Toast.makeText(this, pMsg, 1).show();
    }
    
    protected void openActivity(Class<?> pClass) {
        Intent intent = new Intent();
        intent.setClass(this, pClass);
        startActivity(intent);
    }
    
    
}
