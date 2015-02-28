
package mobidever.godutch.activity;

import mobidever.godutch.R;
import mobidever.godutch.activity.base.ActivityFrame;
import mobidever.godutch.adapter.AdapterUser;
import mobidever.godutch.business.BusinessUser;
import mobidever.godutch.controls.SlideMenuItem;
import mobidever.godutch.controls.SlideMenuView.OnSlideMenuListener;
import mobidever.godutch.model.User;
import mobidever.godutch.utility.RegexTools;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityUser extends ActivityFrame implements OnSlideMenuListener {
    private ListView mListView;
    private AdapterUser mAdapterUser;
    private BusinessUser mBusinessUser;
    private User mSelectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appendMainBody(R.layout.user);
        initVariable();
        initView();
        initListeners();
        bindData();
        // 在ActivityFrame里面调用SlideMenu再封装一层，这样外面Activity调用的
        // 时候就简化多了。只需要下面一句话就可以了。
        createSlideMenu(R.array.SlideMenuUser);
    }

    private void initVariable() {
        mBusinessUser = new BusinessUser(this);
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.lvUserList);
    }

    private void initListeners() {
        registerForContextMenu(mListView);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo)menuInfo;
        ListAdapter listAdapter = mListView.getAdapter();
        mSelectedUser = (User) listAdapter.getItem(adapterContextMenuInfo.position);
        
        menu.setHeaderIcon(R.drawable.user_small_icon);
        menu.setHeaderTitle(mSelectedUser.getName());
        createMenu(menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                showUserAddOrEditDialog(mSelectedUser);
                break;
            case 2:
                delete();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void bindData() {
        mAdapterUser = new AdapterUser(this);
        mListView.setAdapter(mAdapterUser);
    }

    @Override
    public void onSlideMenuItemClick(View view, SlideMenuItem slideMenuItem) {
      slideMenuToggle();
      if (slideMenuItem.getItemID() == 0) {
        showUserAddOrEditDialog(null);
    }
    }

    private void showUserAddOrEditDialog(User user) {
        View _View = getLayoutInflater().inflate(R.layout.user_add_or_edit, null);

        EditText mUserNameEditText = (EditText) _View.findViewById(R.id.etUserName);

        if (user != null) {
            mUserNameEditText.setText(user.getName());
        }

        String title;

        if (user == null) {
            title = getString(R.string.DialogTitleUser, new Object[] {
                getString(R.string.TitleAdd)
            });
        } else {
            title = getString(R.string.DialogTitleUser, new Object[] {
                getString(R.string.TitleEdit)
            });
        }

        AlertDialog.Builder _Builder = new AlertDialog.Builder(this);
        _Builder.setTitle(title)
                .setView(_View)
                .setIcon(R.drawable.user_big_icon)
                .setNeutralButton(getString(R.string.ButtonTextSave),
                        new OnAddOrEditUserListener(user, mUserNameEditText, true))
                .setNegativeButton(getString(R.string.ButtonTextCancel),
                        new OnAddOrEditUserListener(null, null, false)).show();
    }

    private class OnAddOrEditUserListener implements DialogInterface.OnClickListener {
        private User mUser;
        private EditText mUserNameEditText;
        private boolean mIsSaveButton;

        public OnAddOrEditUserListener(User user, EditText userNameEditText, Boolean isSaveButton) {
            mUser = user;
            mUserNameEditText = userNameEditText;
            mIsSaveButton = isSaveButton;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (mIsSaveButton == false) {
                setAlertDialogIsClose(dialog, true);
                return;
            }

            if (mUser == null) {
                mUser = new User();
            }

            String userName = mUserNameEditText.getText().toString().trim();

            boolean checkResult = RegexTools.IsChineseEnglishNum(userName);

            if (!checkResult) {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.CheckDataTextChineseEnglishNum, new Object[] {
                            mUserNameEditText.getHint()
                        }), SHOW_TIME).show();
                setAlertDialogIsClose(dialog, false);
                return;
            } else {
                setAlertDialogIsClose(dialog, true);
            }

            checkResult = mBusinessUser.isExistUserByUserName(userName, mUser.getId());

            if (checkResult) {
                Toast.makeText(getApplicationContext(), getString(R.string.CheckDataTextUserExist),
                        SHOW_TIME).show();

                setAlertDialogIsClose(dialog, false);
                return;
            } else {
                setAlertDialogIsClose(dialog, true);
            }

            mUser.setName(mUserNameEditText.getText().toString());

            boolean result = false;

            if (mUser.getId() == 0) {
                result = mBusinessUser.insertUser(mUser);
            } else {
                result = mBusinessUser.updateUserByUserID(mUser);
            }

            if (result) {
                bindData();//重新绑定，刷新。
            } else {
                Toast.makeText(ActivityUser.this, getString(R.string.TipsAddFail), 1).show();
            }
        }

    }
    
    private void delete() {
        String message = getString(R.string.DialogMessageUserDelete, new Object[] {
            mSelectedUser.getName()
        });
        showAlertDialog(R.string.DialogTitleDelete, message, new OnDeleteClickListener());
    }

    private class OnDeleteClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            boolean result = mBusinessUser.hideUserByUserID(mSelectedUser.getId());

            if (result == true) {
                bindData();
            }
        }
    }
}
