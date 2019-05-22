package wangwanglive.levinvic.com.zongtoolslibrary.Utils_Tools;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import wangwanglive.levinvic.com.zongtoolslibrary.R;


public class PopupWindowMgr {
    private Context mContext;
    private PopupWindow popupWindow;
    private View view;
    private PopupWindow.OnDismissListener dismissListener;

    public PopupWindowMgr(init builder) {
        mContext = builder.mContext;
        view = LayoutInflater.from(mContext).inflate(builder.mShowView, null);
        popupWindow = new PopupWindow(view, builder.mWidth, builder.mHeight, builder.mFocusable);
        popupWindow.setOutsideTouchable(builder.mOutSideClickClose);
        popupWindow.setBackgroundDrawable(builder.mColorDrawable);
        popupWindow.setSoftInputMode(builder.mMode);
        popupWindow.setAnimationStyle(builder.mAnimStyle);

    }

    public void setOnDismissListener(PopupWindow.OnDismissListener dismissListener){
        popupWindow.setOnDismissListener(dismissListener);
    }

    /**
     * @param parent  主頁面(父類別)Xml
     * @param gravity 是否置中，不知道要打什麼的話輸入"Gravity.BOTTOM"
     * @param x       不知道要打什麼的話輸入"0"
     * @param y       不知道要打什麼的話輸入"0"
     * @return
     */
    public PopupWindowMgr showAtLocation(int parent, int gravity, int x, int y) {
        if (popupWindow != null) {
            View vShowAtLocation = LayoutInflater.from(mContext).inflate(parent, null);
            popupWindow.showAtLocation(vShowAtLocation, gravity, x, y);
        }
        return this;
    }

    /**
     * @param view 取得元件
     * @return
     */
    public View getView(int view) {
        if (popupWindow != null) {
            return this.view.findViewById(view);
        }
        return null;
    }

    public PopupWindowMgr showAsLocation(View targeParent, int gravity, int x, int y) {
        if (popupWindow != null) {
            popupWindow.showAsDropDown(targeParent, gravity, x, y);
        }
        return this;
    }

    public void setFocusListener(int view, View.OnFocusChangeListener listener) {
        View vFocusListener = getView(view);
        vFocusListener.setOnFocusChangeListener(listener);
    }

    public void dismiss() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public static class init {

        private Context mContext;
        private int mShowView;    //設定Xml
        private int mWidth = WindowManager.LayoutParams.MATCH_PARENT;   //設定寬，預設手機寬
        private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;  //設定高，預設Xml高
        private boolean mFocusable = true; //是否取得焦點
        private boolean mOutSideClickClose = true;  //是否取消外部點擊
        private int mAnimStyle; //設定動畫
        private int mMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;    //適配虛擬鍵
        private ColorDrawable mColorDrawable = new ColorDrawable(0x00000000);   //設定背景，預設透明

        public init setContext(Context context) {
            this.mContext = context;
            return this;
        }

        public init setShowView(int showView) {
            this.mShowView = showView;
            return this;
        }

        public init setWidth(int width) {
            this.mWidth = width;
            return this;
        }

        public init setHeight(int height) {
            this.mHeight = height;
            return this;
        }

        public init setFocusable(boolean focusable) {
            this.mFocusable = focusable;
            return this;
        }

        public init setOutSideClickClose(boolean outSideClickClose) {
            this.mOutSideClickClose = outSideClickClose;
            return this;
        }

        public init setAnimStyle(int animStyle) {
            this.mAnimStyle = animStyle;
            return this;
        }

        public init setBackground(ColorDrawable colorDrawable) {
            this.mColorDrawable = colorDrawable;
            return this;
        }

        public init setSoftInputMode(int mode) {
            this.mMode = mode;
            return this;
        }

        public PopupWindowMgr build() {
            return new PopupWindowMgr(this);
        }
    }
}
