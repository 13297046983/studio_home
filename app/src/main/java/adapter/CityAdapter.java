package adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

import enity.City;
import ruanko.com.myapplication.R;

public class CityAdapter extends BaseAdapter implements SectionIndexer {
    private List<City> mlist;

    public CityAdapter(List<City>list){
        this.mlist=list;
    }
    @Override
    public int getCount() {
        return (mlist!=null)?mlist.size():0;
    }

    @Override
    public Object getItem(int position) {
        return (mlist!=null&&mlist.size()>position)?mlist.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHandler viewHandler=null;
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_city_row,null);
            viewHandler=new ViewHandler();
            ViewUtils.inject(viewHandler,convertView);
            convertView.setTag(viewHandler);
        }else {
            viewHandler= (ViewHandler) convertView.getTag();
        }
        //将数据渲染到界面中
        City city=mlist.get(position);
        //得到城市名数据
        viewHandler.item_city.setText(city.getName());
        //viewHandler.item_city_selction.setText(city.getSortkey());
        //需要逻辑判断是否显示拼音首字母
        int selction=getSectionForPosition(position);
        if(getPositionForSection(selction)==position){
            //显示字母
            viewHandler.item_city_selction.setVisibility(View.VISIBLE);
            viewHandler.item_city_selction.setText(city.getSortkey());
        }else {
            viewHandler.item_city_selction.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }
    @Override
    public int getPositionForSection(int sectionIndex) {
        int length=getCount();
        for(int i=0;i<length;i++){
            char fristchar=mlist.get(i).getSortkey().toUpperCase().charAt(0);
            if(fristchar==sectionIndex){
                return i;
            }
        }
        return -1;
    }
    @Override
    public int getSectionForPosition(int position) {
        //将拼音首字母改成大写
        return mlist.get(position).getSortkey().toUpperCase().charAt(0);
    }
    //listview的复用
    class ViewHandler{
        @ViewInject(R.id.item_city_selction)
        TextView item_city_selction;
        @ViewInject(R.id.item_city)
        TextView item_city;
    }
}
