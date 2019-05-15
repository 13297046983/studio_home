package ruanko.com.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import util.Toolkits;

/**
 *欢迎引导页
 */
public class WlecomeActivity extends AppCompatActivity {
    public static final String IS_FIRST="is_first";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlecome);
        //线程休眠
        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(Toolkits.fetchBooble(WlecomeActivity.this,IS_FIRST,false)){
                    startActivity(new Intent(WlecomeActivity.this,WhatsNewActivity.class));
                }else {
                    startActivity(new Intent(WlecomeActivity.this,MainActivity.class));
                }
                Toolkits.putBooble(WlecomeActivity.this,IS_FIRST,true);
                return true;
            }
        }).sendEmptyMessageDelayed(0,3000);
    }
}
