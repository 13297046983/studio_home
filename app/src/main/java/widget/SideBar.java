package widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import ruanko.com.myapplication.R;

/**
 * 作者：WP-S10 on 2019/5/8 10 08
 * 邮箱：zhoumeng@ruanko.com
 */
public class SideBar extends View {
    private String []b={"#","A","B","C","D","E","F","G","H","I",
            "J","K","L","M","N","O","P","Q","S","R","T","U","V",
            "W","X","Y","Z"};
    //当前字母选中的索引
    private int choose;
    //画笔
    private Paint paint=new Paint();
    //new 时被调用
    public SideBar(Context context) {
        super(context);
    }

    private OnTouchingLetterChagedLister onTouchingLetterChagedLister;
    //XML方式被调用
    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //组件的宽和高
        int width=getWidth();
        int height=getHeight();
        //字母的高度
        int sigleHeight=height/b.length;
        //设置画笔的属性
        paint.setColor(Color.rgb(154,154,154));
        paint.setTypeface(Typeface.DEFAULT);//默认字体
        paint.setAntiAlias(true);//字体设置抗锯齿
        paint.setTextSize(30);
        for(int i=0;i<b.length;i++){
            //选中字母状态需要设置显示效果
            if(i==choose){
                paint.setColor(Color.parseColor("#3d3d3d"));
                paint.setFakeBoldText(true);//字体加粗
            }
            //计算绘制到的位置
            float xPos=width/2-paint.measureText(b[1])/2;
            float yPos=sigleHeight*i+sigleHeight;
            canvas.drawText(b[i],xPos,yPos,paint);
            paint.reset();
        }
    }
    public interface OnTouchingLetterChagedLister{
        public void OnTouchingLetterChaged(String s);
    }

    public void setOnTouchingLetterChagedLister(OnTouchingLetterChagedLister onTouchingLetterChagedLister) {
        this.onTouchingLetterChagedLister = onTouchingLetterChagedLister;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action=event.getAction();//获取事件的状态
        final float y=event.getY();//点击y坐标
        final int oldChoose=choose;
        final OnTouchingLetterChagedLister lister=onTouchingLetterChagedLister;
        final int c=(int)y/getHeight()*b.length;
        switch (action){
            case MotionEvent.ACTION_UP://事件放下状态
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose=-1;
                invalidate();//重绘
                break;
            default:
                setBackgroundResource(R.drawable.sidebar_background);
                if(oldChoose!=c){
                    if(c>=0&&c<b.length){
                        if(lister!=null){
                            lister.OnTouchingLetterChaged(b[c]);
                        }
                        choose=c;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }
}
