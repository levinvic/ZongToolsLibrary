package wangwanglive.levinvic.com.zongtoolslibrary.BlockEmpty;

import com.google.gson.JsonObject;

import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Network.ExceptionHandle;

public class Contract {

    public interface Persenter {
        void getCarList(String userId);
    }

    public interface ViewFragment {

        void getDataFail(int code, String message);

        void getDataFail(ExceptionHandle.ResponseThrowable responseThrowable);

        void getDataSuccess(String type, JsonObject jsonObject);
    }

    public interface ViewDialogFragment {

        void getDataFail(int code, String message);

        void getDataFail(ExceptionHandle.ResponseThrowable responseThrowable);

        void getDataSuccess(String type, JsonObject jsonObject);
    }

    public interface View {
        void getIMSuccess(JsonObject jsonObject);

        void getIMFail(String s);

        void getDataSuccess(JsonObject jsonObject);

        void getDataFail(ExceptionHandle.ResponseThrowable responseThrowable);

        void getDataFail(int code, String message);

        void getDataSuccess(String type, JsonObject jsonObject);
    }

    public interface Model {

    }
}
