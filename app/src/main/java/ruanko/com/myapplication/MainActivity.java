package ruanko.com.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import frag.FragmentIndex;
import frag.FragmentMore;
import frag.FragmentMy;
import frag.FragmentNearBy;

public class MainActivity extends FragmentActivity {
    @ViewInject(R.id.bottom_bar)
    private RadioGroup bottom_bar;
    @ViewInject(R.id.layout_content)
    private FrameLayout layout_content;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        bottom_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index=0;
                switch (checkedId){
                    case R.id.radio0:
                        index=0;
                        break;
                    case R.id.radio1:
                        index=1;
                        break;
                    case R.id.radio2:
                        index=2;
                        break;
                    case R.id.radio3:
                        index=3;
                        break;
                }
                Fragment fragment= (Fragment) fragmentStatePagerAdapter.instantiateItem(layout_content,index);
                fragmentStatePagerAdapter.setPrimaryItem(layout_content,0,fragment);
                fragmentStatePagerAdapter.finishUpdate(layout_content);
            }
        });
        fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0://团购
                        fragment=new FragmentIndex();
                        break;
                    case 1://附近
                        fragment=new FragmentNearBy();
                        break;
                    case 2://我的
                        fragment=new FragmentMy();
                        break;
                    case 3://更多
                        fragment=new FragmentMore();
                        break;
                    default:
                        fragment=new FragmentIndex();
                        break;
                }
                return fragment;
            }
        };
    }


}