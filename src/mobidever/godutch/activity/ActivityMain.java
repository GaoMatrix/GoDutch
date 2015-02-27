
package mobidever.godutch.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import mobidever.godutch.R;
import mobidever.godutch.activity.base.ActivityFrame;
import mobidever.godutch.adapter.AdapterAppGrid;
import mobidever.godutch.controls.SlideMenuItem;
import mobidever.godutch.controls.SlideMenuView.OnSlideMenuListener;

public class ActivityMain extends ActivityFrame implements OnSlideMenuListener{
    private GridView mGridView;
    private AdapterAppGrid mAdapterAppGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appendMainBody(R.layout.main_body);
        initVariable();
        initView();
        initListeners();
        bindData();
        //在ActivityFrame里面调用SlideMenu再封装一层，这样外面Activity调用的
        //时候就简化多了。只需要下面一句话就可以了。
        createSlideMenu(R.array.SlideMenuActivityMain);
        //test
    }
    
    private void initVariable() {
        mAdapterAppGrid = new AdapterAppGrid(this);
    }
    
    private void initView() {
        mGridView = (GridView)findViewById(R.id.gvAppGrid);
    }
    
    private void initListeners() {
        mGridView.setOnItemClickListener(new onAppGridItemClickListener());
    }
    
    private void bindData() {
        mGridView.setAdapter(mAdapterAppGrid);
    }
    
    private class onAppGridItemClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView adapterView, View view, int position, long arg3) {
            String menuName = (String) adapterView.getAdapter().getItem(position);
            if (menuName.equals(getString(R.string.appGridTextUserManage))) {
                openActivity(ActivityUser.class);
                return;
            }
           /* if (_MenuName.equals(getString(R.string.appGridTextAccountBookManage))) {
                OpenActivity(ActivityAccountBook.class);
                return;
            }
            if (_MenuName.equals(getString(R.string.appGridTextCategoryManage))) {
                OpenActivity(ActivityCategory.class);
                return;
            }
            if (_MenuName.equals(getString(R.string.appGridTextPayoutAdd))) {
                OpenActivity(ActivityPayoutAddOrEdit.class);
                return;
            }
            if (_MenuName.equals(getString(R.string.appGridTextPayoutManage))) {
                OpenActivity(ActivityPayout.class);
                return;
            }
            if (_MenuName.equals(getString(R.string.appGridTextStatisticsManage))) {
                OpenActivity(ActivityStatistics.class);
                return;
            }*/
        }
    }

    @Override
    public void onSlideMenuItemClick(View view, SlideMenuItem slideMenuItem) {}
    
}
