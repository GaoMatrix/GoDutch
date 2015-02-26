
package mobidever.godutch.controls;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import mobidever.godutch.R;
import mobidever.godutch.adapter.AdapterSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class SlideMenuView {

    private Activity mActivity;
    private List mMenuList;
    private boolean mIsClosed;
    private RelativeLayout mBottomBoxLayout;
    private OnSlideMenuListener mOnSlideMenuListener;
    
    public interface OnSlideMenuListener {
        public abstract void onSlideMenuItemClick(View view, SlideMenuItem slideMenuItem);
    }

    public SlideMenuView(Activity activity) {
        mActivity = activity;
        mOnSlideMenuListener = (OnSlideMenuListener)activity;
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
        mBottomBoxLayout.setOnClickListener(new OnSlideMenuClick());
        mBottomBoxLayout.setFocusableInTouchMode(true);
        mBottomBoxLayout.setOnKeyListener(new OnKeyListener() {
            
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_UP) {
                    toggle();
                }
                return false;
            }
        });
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
                RelativeLayout.LayoutParams.FILL_PARENT, 98);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
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
    public void add(SlideMenuItem slideMenuItem) {
        mMenuList.add(slideMenuItem);
    }
    
    public void bindList() {
        AdapterSlideMenu adapterSlideMenu = new AdapterSlideMenu(mActivity, mMenuList);
        ListView listView = (ListView) mActivity.findViewById(R.id.lvSlideList);
        listView.setAdapter(adapterSlideMenu);
        listView.setOnItemClickListener(new OnSlideMenuItemClick());
    }

    private void onSlideMenuClick() {

    }

    private class OnSlideMenuClick implements OnClickListener {

        @Override
        public void onClick(View v) {
            toggle();
        }

    }

    private class OnSlideMenuItemClick implements OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
            SlideMenuItem slideMenuItem = (SlideMenuItem) adapterView.getItemAtPosition(position);;
            mOnSlideMenuListener.onSlideMenuItemClick(view, slideMenuItem);
        }
        
    }
}
