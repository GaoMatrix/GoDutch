
package mobidever.godutch.activity.base;

import mobidever.godutch.R;
import mobidever.godutch.controls.SlideMenuItem;
import mobidever.godutch.controls.SlideMenuView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
    
    protected void slideMenuToggle() {
        mSlideMenuView.toggle();
    }
    
    protected void createMenu(Menu menu) {
        int groupID = 0;
        int order = 0;
        int[] itemID = {
                1, 2
        };

        for (int i = 0; i < itemID.length; i++) {
            switch (itemID[i]) {
                case 1:
                    menu.add(groupID, itemID[i], order, R.string.MenuTextEdit);
                    break;
                case 2:
                    menu.add(groupID, itemID[i], order, R.string.MenuTextDelete);
                    break;
                default:
                    break;
            }
        }
    }
}
