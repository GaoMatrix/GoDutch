
package mobidever.godutch.controls;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import mobidever.godutch.R;

import java.util.ArrayList;
import java.util.List;

public class SlideMenuView {

    private Activity mActivity;
    private List mMenuList;
    private boolean mIsClosed;
    private RelativeLayout mBottomBoxLayout;

    public SlideMenuView(Activity activity) {
        mActivity = activity;
        initVariable();
        initView();
        initListeners();
    }

    private void initVariable() {
        mMenuList = new ArrayList();
        mIsClosed = true;
    }

    private void initView() {
        mBottomBoxLayout = (RelativeLayout) mActivity.findViewById(R.id.includeBottom);
    }

    private void initListeners() {
        // TODO Auto-generated method stub

    }

    /**
     * Adjust the layout dynamically.
     */
    private void open() {
        // when open the slide, it will fill content.
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParams.addRule(RelativeLayout.BELOW , R.id.includeTitle);
        mBottomBoxLayout.setLayoutParams(layoutParams);
        mIsClosed = false;
    }

    private void close() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, 68);
        layoutParams.addRule(RelativeLayout.BELOW , R.id.includeTitle);
        mBottomBoxLayout.setLayoutParams(layoutParams);
        mIsClosed = true;
    }

    /**
     * 如果外面调用开关，还要判断当前是开还是关，会很麻烦，
     * 一般正常是点一下开，点一下关
     */
    private void toggle() {
        if (mIsClosed) {
            open();
        }else {
            close();
        }
    }

    /**
     * 添加的可以包含id,title,是否高亮，是否有描述。。。
     * 所以要封装成实体类。
     * 随意随时随地都要有抽象和封装的思想，对代码的影响减少到最低
     */
    private void add(SlideMenuItem slideMenuItem) {
        mMenuList.add(slideMenuItem);
    }

    private void onSlideMenuClick() {

    }

    private class OnSlideMenuClick implements OnClickListener {

        @Override
        public void onClick(View v) {

        }

    }

}
