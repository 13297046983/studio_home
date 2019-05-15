package frag;


import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import ruanko.com.myapplication.CityActivity;
import ruanko.com.myapplication.R;


public class FragmentIndex extends Fragment implements View.OnClickListener {
    @ViewInject(R.id.home_city)
    public TextView home_city;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(getActivity()).inflate(R.layout.frag_index,null);
        ViewUtils.inject(getActivity());
        home_city=view.findViewById(R.id.home_city);
        home_city.setOnClickListener(this);
        return view;
    }


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if(this.getView()!=null){
            this.getView().setVisibility(menuVisible?View.VISIBLE:View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_city://地址
                startActivity(new Intent(getActivity(),CityActivity.class));
                break;
            case R.id.home_map://地图
                break;
            case R.id.home_search://搜索
                break;
        }
    }
}
