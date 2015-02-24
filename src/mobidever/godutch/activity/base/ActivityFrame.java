
package mobidever.godutch.activity.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import mobidever.godutch.R;

public class ActivityFrame extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    }

    protected void appendMainBody(int pResID) {
        LinearLayout mainBody = (LinearLayout)findViewById(R.id.layMainBody);
        View view = LayoutInflater.from(this).inflate(pResID, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        mainBody.addView(view, layoutParams);
    }
}
