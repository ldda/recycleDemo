package com.cnpaypal.ObservableScrollView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.cnpaypal.home.R;

/**
 * scrollview ����ĸ����ı�ǩ�Ľ���
 */
public class ScrollStickyActivity extends Activity implements ObservableScrollView.ScrollCallBack{
    private TextView txtContent;
    private View mPlaceholderView;
    private ObservableScrollView observableScrollView;

    /**
     * �ϻ���״̬
     */
    private static final int STATE_ONSCREEN = 0;
    /**
     * �ϻ�������ȫ�ڸ�סmPlaceholderView
     */
    private static final int STATE_OFFSCREEN = 1;
    /**
     * ��ȫ�ڸ�סʱ���»�״̬
     */
    private static final int STATE_RETURING = 2;
    private int mState = STATE_ONSCREEN;
    /**
     * �߶�
     */
    private int mViewHeight;
    private int minRaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_sticky);

        initView();
    }

    private void initView(){
        //��λ sticky λ�õ�һ����ǵ�view,�ᱻ���յ�sticky����
        mPlaceholderView = findViewById(R.id.placeholder);

        //�����ĸ�����view ,���᲻��ִ��λ�ƶ���
        txtContent = (TextView)findViewById(R.id.sticky);
        txtContent.setText("ScrollView sticky");

        //�ҵ���Ӧ�Ĳ��֣������ü������¼������лص��Ĵ���
        observableScrollView = (ObservableScrollView)findViewById(R.id.scroll_sticky);
        observableScrollView.setCallBack(this);

        //�����ֻ�����ȫ��ʱ�����ǲſ��Եõ�view.getTop(),����ʼ�� ������ľ����λ��
        observableScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
//                mViewHeight = txtContent.getBottom()-txtContent.getTop();
                onScrollChanged(observableScrollView.getScrollY());
            }
        });

    }

    @Override
    public void onScrollChanged(int verticalDistances) {
        int translation = Math.max(verticalDistances , mPlaceholderView.getTop());
        //����˵��  translation in y of the view.
        txtContent.setTranslationY(translation);

//        //����һ����Եľ��룬��ʵ��scrollview�Ķ������� ������ť�ľ��룬
//        int relativeDistances = mPlaceholderView.getTop()-verticalDistances;
//
//        //�����㴹ֱ�����ľ���
//        int translationY = 0;
//
//        switch (mState){
//            case STATE_ONSCREEN:
//                if(relativeDistances<-mViewHeight){
//                    mState = STATE_OFFSCREEN;
//                    minRaw = relativeDistances;
//                }
//                translationY = relativeDistances;
//                break;
//
//            case STATE_OFFSCREEN:
//                if (relativeDistances<=minRaw){
//                    minRaw = relativeDistances;
//                }else{
//                    mState = STATE_RETURING;
//                }
//                translationY = relativeDistances;
//                break;
//
//            case STATE_RETURING:
//                translationY = (relativeDistances - minRaw) - mViewHeight;
//                if (translationY > 0) {
//                    translationY = 0;
//                    minRaw = relativeDistances - mViewHeight;
//                }
//
//                if (relativeDistances > 0) {
//                    mState = STATE_ONSCREEN;
//                    translationY = relativeDistances;
//                }
//
//                if (translationY < -mViewHeight) {
//                    mState = STATE_OFFSCREEN;
//                    minRaw = relativeDistances;
//                }
//                break;
//        }
//        txtContent.setTranslationY(translationY+verticalDistances);

    }
}
