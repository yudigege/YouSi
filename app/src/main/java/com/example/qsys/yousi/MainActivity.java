package com.example.qsys.yousi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.qsys.yousi.activity.BaseActivity;
import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.fragment.main.bookshelf.BookShelfFragment;
import com.example.qsys.yousi.fragment.main.idea.IdeaFragment;
import com.example.qsys.yousi.fragment.main.mainpage.MainPageFragment;
import com.example.qsys.yousi.fragment.main.mine.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

/*    @BindView(R.id.frl_frag_container_main)
    FrameLayout frlFragContainerMain;*/
    @BindView(R.id.bottom_navigation_bar_main)
    BottomNavigationBar bottomNavigationBarMain;
    public Fragment mainPageFragment;
    public Fragment bookShelfFragment;
    public Fragment ideaFragment;
    public Fragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomNavigationBarMain.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBarMain.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBarMain.addItem(new BottomNavigationItem(R.mipmap.ic_bottomtabbar_discover, R.string.main_page).setActiveColorResource(R.color.burlywood).setInActiveColorResource(R.color.lightgrey))
                .addItem(new BottomNavigationItem(R.mipmap.ic_bottomtabbar_more, R.string.book_stock).setActiveColorResource(R.color.burlywood).setInActiveColorResource(R.color.lightgrey))
                .addItem(new BottomNavigationItem(R.mipmap.ic_bottomtabbar_db, R.string.idea).setActiveColorResource(R.color.burlywood).setInActiveColorResource(R.color.lightgrey))
                .addItem(new BottomNavigationItem(R.mipmap.ic_bottomtabbar_feed, R.string.mine).setActiveColorResource(R.color.burlywood).setInActiveColorResource(R.color.lightgrey))//依次添加item,分别icon和名称
                .setFirstSelectedPosition(0)//设置默认选择item
                .initialise();//初始化
        bottomNavigationBarMain.setTabSelectedListener(this);
        setDefaultFragment();

    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();  //获取FragmentManager
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();//开启一个事务
        fragmentTransaction.add(R.id.frl_frag_container_main, MainPageFragment.newInstance());
        fragmentTransaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        LogUtils.d(position);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (mainPageFragment == null) {
                    mainPageFragment = MainPageFragment.newInstance();
                }
                fragmentTransaction.replace(R.id.frl_frag_container_main, mainPageFragment);
                break;
            case 1:
                if (bookShelfFragment == null) {
                    bookShelfFragment = BookShelfFragment.newInstance();
                }
                fragmentTransaction.replace(R.id.frl_frag_container_main,bookShelfFragment);
                break;
            case 2:
                if (ideaFragment == null) {
                    ideaFragment = IdeaFragment.newInstance();
                }
                fragmentTransaction.replace(R.id.frl_frag_container_main, ideaFragment);
                break;
            case 3:
                if (mineFragment == null) {
                    mineFragment = MineFragment.newInstance();
                }
                fragmentTransaction.replace(R.id.frl_frag_container_main, mineFragment);
                break;

        }
        fragmentTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

}
