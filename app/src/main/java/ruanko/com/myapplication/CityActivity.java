package ruanko.com.myapplication;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


import java.util.ArrayList;
import java.util.List;

import adapter.CityAdapter;
import enity.City;
import enity.RseponseObject;
import util.Const;

import static android.widget.Toast.LENGTH_SHORT;



public class CityActivity extends Activity {
    @ViewInject(R.id.city_list_view)
    private ListView city_list_view;
    private CityAdapter madapter;
    private List<City>cities=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ViewUtils.inject(this);
        loadCity();
    }
    @OnClick(value = {R.id.city_back,R.id.city_refresh,R.id.city_list_view})
    private void onClick(View v){
        switch (v.getId()){
            case R.id.city_back:
                finish();
                break;
            case R.id.city_refresh:
                loadCity();
                break;
            case R.id.city_list_view:
                city_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
        }

    }
    //加载服务端数据
    public void loadCity(){
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, Const.CITY_LIST, new RequestCallBack<String>() {
            @Override
            public void onFailure(HttpException e, String msg) {
                Log.e("错误信息",msg);
               Toast.makeText(CityActivity.this,msg,LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //将服务端返回的json字符串转换为实体类
                Gson gson=new Gson().newBuilder().create();
                RseponseObject <List<City>> result=gson.fromJson(responseInfo.result, new TypeToken<RseponseObject <List<City>>>(){}.getType());
                Log.e("结果",result.toString());
                madapter=new CityAdapter(result.getDatas());
                city_list_view.setAdapter(madapter);
            }
        });
    }
}
