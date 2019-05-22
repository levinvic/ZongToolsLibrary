package wangwanglive.levinvic.com.zongtoolslibrary.Utils_Tools;

import android.util.Log;

import com.google.gson.JsonElement;

import java.util.Map;

import wangwanglive.levinvic.com.zongtoolslibrary.BuildConfig;

public class DebugClass {

    private static DebugClass instance = new DebugClass();

    private boolean booDebug;

    private DebugClass() {
        booDebug = BuildConfig.DEBUG;
    }

    public static DebugClass getInstance() {
        return instance;
    }

    public void setDebugLog(Object message) {
        if (booDebug) {
            Log.d("DebugClass", String.valueOf(message));
        }
    }

    public void setDebugLog(String otherMessage, Object message) {
        if (booDebug) {
            Log.d("DebugClass", otherMessage + ": " + String.valueOf(message));
        }
    }

    public void setErrorLog(Object message) {
        Log.e("DebugClass", String.valueOf(message));
    }

    public void setErrorLog(String otherMessage, Object message) {
        Log.e("DebugClass", otherMessage + ": " + String.valueOf(message));
    }

    public boolean isJsonNull(Map.Entry<String, JsonElement> elementEntry) {
        if (elementEntry.getValue().isJsonNull()) {
            setDebugLog("空值的key: ", elementEntry.getKey());
            setDebugLog("空值的value: ", elementEntry.getValue());
            return true;
        } else {
            return false;
        }
    }
}
