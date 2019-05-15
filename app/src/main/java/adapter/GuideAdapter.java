package adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class GuideAdapter extends PagerAdapter {
    public List <View>mlist;
    public GuideAdapter(List <View>list){
        mlist=list;
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView(mlist.get(position));
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ((ViewPager)container).addView(mlist.get(position));
        return mlist.get(position);
    }
}
