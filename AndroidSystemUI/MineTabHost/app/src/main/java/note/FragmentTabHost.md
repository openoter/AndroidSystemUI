# FragmentTabHost+Fragment实现Tab切换

1. 主页面
```java
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
```

2. 主页面布局文件
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <FrameLayout
        android:id="@+id/fl_content"
        android:layout_width="match_parent"
        android:layout_height="0dip"
        android:layout_weight="1" />

    <android.support.v4.app.FragmentTabHost
        android:id="@android:id/tabhost"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#fff">

        <FrameLayout
            android:id="@android:id/tabcontent"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_weight="0" />
    </android.support.v4.app.FragmentTabHost>

</LinearLayout>
```

3. 每一个tab对应的布局
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_gravity="center"
    android:gravity="center"
    android:layout_height="match_parent">

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="3dp"
        android:id="@+id/imageView" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="New Text"
        android:textSize="12sp"
       android:textColor="@color/selector_tab_text"
        android:gravity="center"
        android:id="@+id/textView" />
</LinearLayout>
```

4. 图标下文字的颜色变化
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_selected="true" android:color="@color/colorAccent"/>
    <item android:state_selected="false" android:color="@color/black"/>
    <item android:state_active="true" android:color="@color/colorAccent"/>
    <item android:state_active="false" android:color="@color/black"/>
</selector>
```
注意：在'res'下新建一个`color`后再在文件夹下新建`selector_tab_text.xml'

5. 每一个图标选中和非选中的变化(只写一个，其他一次类推)
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- Non focused states -->
    <item android:state_focused="false" android:state_selected="false" android:state_pressed="false" android:drawable="@drawable/friend" />
    <item android:state_focused="false" android:state_selected="true" android:state_pressed="false" android:drawable="@drawable/friend_down" />
    <!-- Focused states -->
    <item android:state_focused="true" android:state_selected="false" android:state_pressed="false" android:drawable="@drawable/friend_down" />
    <item android:state_focused="true" android:state_selected="true" android:state_pressed="false" android:drawable="@drawable/friend_down" />
    <!-- Pressed -->
    <item android:state_selected="true" android:state_pressed="true" android:drawable="@drawable/friend_down" />
    <item android:state_pressed="true" android:drawable="@drawable/friend_down" />
</selector>
```

6. 每个页面的Fragment
```java
package com.openoter.app.minetabhost.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openoter.app.minetabhost.R;

public class FindFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.find_fragment, container, false);
    }
}
```

7. 每一个Fragment对应的xml文件
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="Find"/>
</LinearLayout>
```