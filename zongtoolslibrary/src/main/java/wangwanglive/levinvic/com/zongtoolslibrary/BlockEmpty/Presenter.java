package wangwanglive.levinvic.com.zongtoolslibrary.BlockEmpty;

import com.google.gson.JsonObject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;
import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Network.ExceptionHandle;

public class Presenter {
    private static final String TAG = Presenter.class.getSimpleName();
    private Model model;
    private Contract.View view;
    private Contract.ViewDialogFragment viewDialogFragment;
    private Contract.ViewFragment viewFragment;
    private CompositeDisposable mDisposable_view;
    private CompositeDisposable mDisposable_DialogFragment;
    private CompositeDisposable mDisposable_Fragment;

    Presenter(Model model, Contract.ViewFragment viewFragment) {
        this.model = model;
        this.viewFragment = viewFragment;
        mDisposable_Fragment = new CompositeDisposable();
    }

    Presenter(Model model, Contract.ViewDialogFragment viewDialogFragment) {
        this.model = model;
        this.viewDialogFragment = viewDialogFragment;
        mDisposable_DialogFragment = new CompositeDisposable();
    }

    Presenter(Model model, Contract.View view) {
        this.model = model;
        this.view = view;
        mDisposable_view = new CompositeDisposable();
    }

    void dispose() {
        if (mDisposable_Fragment != null) {
            mDisposable_Fragment.dispose();
        }
        if (mDisposable_view != null) {
            mDisposable_view.dispose();
        }
        if (mDisposable_DialogFragment != null) {
            mDisposable_DialogFragment.dispose();
        }
    }

    // 處理成功或失敗
    private void getDataSuccess(String type, Response<JsonObject> response) {
        if (response.isSuccessful()) {
            JsonObject jsonObject = response.body();
            if (view != null) {
                view.getDataSuccess(type, jsonObject);
            } else if (viewDialogFragment != null) {
                viewDialogFragment.getDataSuccess(type, jsonObject);
            } else if (viewFragment != null) {
                viewFragment.getDataSuccess(type, jsonObject);
            }
        } else {
            getDataFail(response.code(), response.message());
        }
    }

    private void getDataFail(Throwable e) {
        if (view != null) {
            view.getDataFail(ExceptionHandle.handleException(e));
        } else if (viewDialogFragment != null) {
            viewDialogFragment.getDataFail(ExceptionHandle.handleException(e));
        } else if (viewFragment != null) {
            viewFragment.getDataFail(ExceptionHandle.handleException(e));
        }
    }

    private void getDataFail(int code, String message) {
        if (view != null) {
            view.getDataFail(code, message);
        } else if (viewDialogFragment != null) {
            viewDialogFragment.getDataFail(code, message);
        } else if (viewFragment != null) {
            viewFragment.getDataFail(code, message);
        }
    }
    //////////////////////////新增方法//////////////////////////


}
