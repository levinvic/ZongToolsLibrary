package wangwanglive.levinvic.com.zongtoolslibrary.BlockEmpty;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;

import wangwanglive.levinvic.com.zongtoolslibrary.R;
import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Base.BaseViewFragment;
import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Network.ExceptionHandle;

public class ViewFragment extends BaseViewFragment implements Contract.ViewFragment {

    private Presenter presenter;
    private Activity activity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_empty, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitData();
        InitEventListener();
    }

    private void InitEventListener() {

    }

    private void InitData() {
        presenter = new Presenter(new Model(), this);
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
    public void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void Call_API() {

    }
}
