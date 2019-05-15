package ruanko.com.myapplication;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import adapter.GuideAdapter;

public class WhatsNewActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button btnstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_new);
        viewPager=findViewById(R.id.view_pager);
        btnstart=findViewById(R.id.start_btn);
        //开启监听事件，点击后跳转到主界面
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WhatsNewActivity.this,MainActivity.class));
            }
        });
        //初始化viewpager
        initViewpager();
    }
    private void initViewpager(){
        List<View> list=new ArrayList<View>();
        ImageView image1=new ImageView(this);
        image1.setImageResource(R.mipmap.guide_1);
        list.add(image1);

        ImageView image2=new ImageView(this);
        image2.setImageResource(R.mipmap.guide_2);
        list.add(image2);

        ImageView image3=new ImageView(this);
        image3.setImageResource(R.mipmap.guide_3);
        list.add(image3);
        //添加适配器
        GuideAdapter guideAdapter=new GuideAdapter(list);
        viewPager.setAdapter(guideAdapter);
        //viewpager的监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //当滑到第三个界面可以进行点击，否则无法点击
                    if(position==2)
                        btnstart.setVisibility(View.VISIBLE);
                    else
                        btnstart.setVisibility(View.GONE);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
