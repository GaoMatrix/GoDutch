
package mobidever.godutch.activity;

import mobidever.godutch.R;
import mobidever.godutch.activity.base.ActivityFrame;
import mobidever.godutch.adapter.AdapterUser;
import mobidever.godutch.controls.SlideMenuItem;
import mobidever.godutch.controls.SlideMenuView.OnSlideMenuListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityUser extends ActivityFrame implements OnSlideMenuListener{
    private ListView mListView;
    private AdapterUser mAdapterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(R.layout.user);
        initVariable();
        initView();
        initListeners();
        bindData();
        //在ActivityFrame里面调用SlideMenu再封装一层，这样外面Activity调用的
        //时候就简化多了。只需要下面一句话就可以了。
        createSlideMenu(R.array.SlideMenuUser);
        //test
    }
    
    private void initVariable() {
        mAdapterUser = new AdapterUser(this);
    }
    
    private void initView() {
        mListView = (ListView)findViewById(R.id.lvUserList);
    }
    
    private void initListeners() {
        
    }
    
    private void bindData() {
        mListView.setAdapter(mAdapterUser);
    }

    @Override
    public void onSlideMenuItemClick(View view, SlideMenuItem slideMenuItem) {
        Toast.makeText(this, slideMenuItem.getTitle(), Toast.LENGTH_LONG).show();
    }
    
}
