package wangwanglive.levinvic.com.zongtoolslibrary.BlockEmpty;

import android.os.Bundle;

import com.google.gson.JsonObject;

import wangwanglive.levinvic.com.zongtoolslibrary.R;
import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Network.ExceptionHandle;
import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Base.BaseViewActivity;

public class ViewActivity extends BaseViewActivity implements Contract.View {
    public final String TAG = this.getClass().getSimpleName();

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 設置新的 layout
         */
        setContentView(R.layout.activity_empty);
        presenter = new Presenter(new Model(), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getIMSuccess(JsonObject jsonObject) {

    }

    @Override
    public void getIMFail(String s) {

    }

    @Override
    public void getDataSuccess(JsonObject jsonObject) {

    }

    @Override
    public void getDataFail(ExceptionHandle.ResponseThrowable responseThrowable) {

    }

    @Override
    public void getDataFail(int code, String message) {

    }

    @Override
    public void getDataSuccess(String type, JsonObject jsonObject) {

    }

    @Override
    public void Call_API() {

    }
}
