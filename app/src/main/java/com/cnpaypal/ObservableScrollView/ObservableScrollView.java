package com.cnpaypal.ObservableScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * �Զ�������࣬������ǩ
 */
public class ObservableScrollView extends ScrollView{
    private ScrollCallBack mCallBack;

    //ע��ص��¼�
    public interface ScrollCallBack{
        void onScrollChanged(int verticalDistances);
    }

    //���캯��
    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCallBack(ScrollCallBack callBack){
        this.mCallBack = callBack;
    }


    /**
     * ����Ļ���Ͻ�Ϊ����ԭ�� ��0,0��
     * l	Current horizontal scroll origin. ��ǰˮƽ�������ĵ�
     * t	Current vertical scroll origin.      ��ǰ��ֱ�������ĵ�
     * oldl	Previous horizontal scroll origin. ����ǰˮƽ�ĵ�
     * oldt	Previous vertical scroll origin.     ����ǰ��ֱ�ĵ�
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(mCallBack!=null){
            mCallBack.onScrollChanged(t);
        }
    }


    /**
     * �ɴ�ֱ�����������������д�ֱ��Χ��ȱʡ�ķ�Χ�ǵ�ǰ��ͼ�Ļ�ͼ�߶ȡ�
     */
    public int computeVerticalScrollRange(){
        return super.computeVerticalScrollRange();
    }
}
