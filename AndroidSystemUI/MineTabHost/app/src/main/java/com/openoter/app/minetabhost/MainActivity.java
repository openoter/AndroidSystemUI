package com.openoter.app.minetabhost;


import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.openoter.app.minetabhost.fragment.BeiFriendFragment;
import com.openoter.app.minetabhost.fragment.FindFragment;
import com.openoter.app.minetabhost.fragment.HomeFragment;
import com.openoter.app.minetabhost.fragment.MimeFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;

    //1. 需要显示的Fragment
    private Class arrFragment[] = {
            HomeFragment.class,
            FindFragment.class,
            BeiFriendFragment.class,
            MimeFragment.class
    };
    //2. 需要显示的资源文件（图标）
    private int arrImageRes[] = {
            R.drawable.selector_home,
            R.drawable.selector_find,
            R.drawable.selector_friend,
            R.drawable.selector_me
    };
    //3. 图标下显示的文字
    private String arrTabText[] = {"话题", "发现", "辈友", "我"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);
        for (int i = 0; i < arrFragment.length; i++) {
            //设置文字
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(arrTabText[i]);
            //设置图标
            tabSpec.setIndicator(getTabItemView(i));
            //添加tab
            mTabHost.addTab(tabSpec, arrFragment[i], null);
        }

        mTabHost.setCurrentTab(0);
    }

    public View getTabItemView(int index) {
        View view = mInflater.inflate(R.layout.tab_item_view, null);
        //找到布局文件中的图标和文字
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);

        //设置资源
        imageView.setBackgroundResource(arrImageRes[index]);
        textView.setText(arrTabText[index]);

//        textView.setBackgroundResource(R.drawable.selector_tab_text);

        return view;
    }


}
