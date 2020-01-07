package jrh.library.common.widgets.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import aly.library.common.base.R;


public class ShareDialog extends BaseDialog{
    //界面显示的数据
    private String[] mTexts;
    private int[] mImgs;//图片数据

    private OnClickListener mListener;
    private Activity context;

    public ShareDialog(Activity context, String[] mtext , int[] mImgs) {
        super(context);
        this.context = context;
        this.mTexts = mtext;
        this.mImgs = mImgs;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Dialog没有标题。需在setContentView之前设置，在之后设置会报错
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置Dialog背景透明效果，必须设置一个背景，否则会有系统的Dialog样式：外部白框
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.setCanceledOnTouchOutside(true);
        LayoutInflater inflater = LayoutInflater.from(context);
        mCreateView = inflater.inflate(R.layout.dialog_share, null);
        setContentView(mCreateView);//添加视图布局
        setLayout();
        initView();
    }
    private void initView() {
        TextView cancel = mCreateView.findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog.this.dismiss();
            }
        });
        int num=mTexts.length;
        int number=4;//一行显示的数量 //最外面的布局
        LinearLayout group=mCreateView.findViewById(R.id.ll_group);
        //设置内容
        for(int i=0,j=0;i<num;){
            j++;
            LinearLayout ll =new LinearLayout(mContext);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,1.0f);//设置权重
            ll.setLayoutParams(params);
            ll.setBackgroundColor(Color.WHITE);
            ll.setOrientation(LinearLayout.HORIZONTAL);//设置水平方向
            ll.setGravity(Gravity.CENTER_HORIZONTAL);
            group.addView(ll);
            for(;i<j*number && i<num;i++){//一行三个
                //设置分享图文控件
                CustomTextView item=new CustomTextView(mContext);
                params=new LinearLayout.LayoutParams(
                        mScreenWidth/number,
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
                params.topMargin=10*mDensity;
                params.bottomMargin=10*mDensity;
                item.setLayoutParams(params);
                item.setBounds(50, 50);//设置图片的宽高
                Drawable top = mContext.getResources().getDrawable(mImgs[i]);
                item.setCompoundDrawablesWithIntrinsicBounds(null, top , null, null);//给DrawableTop设置图片
                item.setText(mTexts[i]);//设置文字
                item.setGravity(Gravity.CENTER);//字居中
                MyOnClickListener listener=new MyOnClickListener(i);
                item.setOnClickListener(listener);//设置点击监听器
                ll.addView(item);
            }
        }
    }
    public class MyOnClickListener implements android.view.View.OnClickListener{
        private int mPosition;
        public MyOnClickListener(int position){
            mPosition=position;
        }
        @Override
        public void onClick(View v) {
            ShareDialog.this.cancel();
            if(mListener!=null){
                mListener.OnClick(v,mPosition);
            }
        }
    }
    public interface OnClickListener {
        void OnClick(View v,int position);
    }
    public void setOnClickListener(OnClickListener listener){
        mListener=listener;
    }
    private void setLayout(){
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Window window = getWindow();
        WindowManager.LayoutParams params= window.getAttributes();
        Point outSize = new Point();
        display.getSize(outSize);//不能省略,必须有
        params.width=outSize.x;
        params.gravity=Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;//水平居中、底部
        window.setAttributes(params);
    }

}
