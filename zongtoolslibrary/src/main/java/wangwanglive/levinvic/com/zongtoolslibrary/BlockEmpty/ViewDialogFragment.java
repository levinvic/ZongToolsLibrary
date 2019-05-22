package wangwanglive.levinvic.com.zongtoolslibrary.BlockEmpty;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.JsonObject;

import wangwanglive.levinvic.com.zongtoolslibrary.R;
import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Base.BaseViewDialogFragment;
import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Network.ExceptionHandle;

public class ViewDialogFragment extends BaseViewDialogFragment implements Contract.ViewDialogFragment {

    private Dialog mDialog;
    private Context mContext;
    private Presenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mDialog = new Dialog(mContext, R.style.dialog);         // style要改
        mDialog.setContentView(R.layout.dialog_empty);    // layout 要改
        mDialog.setCancelable(false);
        return mDialog;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitComponent();        // 初始化元件
        InitIntent();           // 取前頁帶來的值
        InitData();             // 初始化資料
        InitEventListener();    // 初始化事件

    }

    private void InitComponent() {

    }

    private void InitIntent() {

    }

    private void InitEventListener() {

    }

    private void InitData() {
        presenter = new Presenter(new Model(), ViewDialogFragment.this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        presenter.dispose();
    }

    @Override
    public void getDataFail(int code, String message) {

    }

    @Override
    public void getDataFail(ExceptionHandle.ResponseThrowable responseThrowable) {

    }

    @Override
    public void getDataSuccess(String type, JsonObject jsonObject) {
        InitView();
    }

    private void InitView() {

    }


    @Override
    public void Call_API() {

    }
}
