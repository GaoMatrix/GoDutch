
package mobidever.godutch.activity.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import mobidever.godutch.R;
import mobidever.godutch.controls.SlideMenuItem;
import mobidever.godutch.controls.SlideMenuView;

public class ActivityFrame extends ActivityBase {
    private SlideMenuView mSlideMenuView;

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
    
    /**
     * Every activity has slidemenu, so it need to package this function in ActivityFrame, which contains
     * common business.
     */
    protected void createSlideMenu(int resID) {
        mSlideMenuView = new SlideMenuView(this);
        String[] menuItemArray = getResources().getStringArray(resID);
        for (int i = 0; i < menuItemArray.length; i++) {
            SlideMenuItem slideMenuItem = new SlideMenuItem(i, menuItemArray[i]);
            mSlideMenuView.add(slideMenuItem);
        }
        mSlideMenuView.bindList();
    }
}
