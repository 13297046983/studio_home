package util;

import android.content.Context;
import android.content.SharedPreferences;

public class Toolkits {
    public static SharedPreferences getSharedPreferences(Context context){
            return context.getSharedPreferences("ruanko.com.myappliccation",Context.MODE_PRIVATE);
    }
    //存储数据
    public static void putBooble(Context context,String key,boolean value){
            SharedPreferences sharedPreferences=getSharedPreferences(context);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean(key,value);
            editor.commit();
    }


    public static boolean fetchBooble(Context context,String key,boolean defaultvalue){

        return getSharedPreferences(context).getBoolean(key,defaultvalue);
    }

}
