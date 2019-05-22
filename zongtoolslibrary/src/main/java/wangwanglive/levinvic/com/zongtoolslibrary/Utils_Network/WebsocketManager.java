package wangwanglive.levinvic.com.zongtoolslibrary.Utils_Network;

import android.util.Log;

import com.google.gson.JsonObject;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.Nullable;
import wangwanglive.levinvic.com.zongtoolslibrary.BlockEmpty.Contract;
import wangwanglive.levinvic.com.zongtoolslibrary.BuildConfig;
import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Tools.DebugClass;

public class WebsocketManager {

    /**
     * Instance
     */
    private static WebsocketManager instance = new WebsocketManager();

    public static WebsocketManager getInstance() {
        return instance;
    }

    private final String TAG = "WebsocketManager";

    private Contract.View view;

    /**
     * WebSocket config
     */
    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 5000;
//    private static final String DEF_URL = "ws://192.168.8.123:8000/ws/chat/roomid/";
    private static final String DEF_URL = "ws://192.168.8.123:8000/ws/";
    private String url;

    /**
     * OutSide Methods
     */
    private WebSocket ws;
    private WsListener mListener;

    public void create_websocket() {
        try {
            ws = new WebSocketFactory().createSocket(DEF_URL, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE) // 設置幀列最大值為5
                    .setMissingCloseFrameAllowed(false) // 設置不允許服務端關閉連接卻未發送關閉幀
                    .addListener(mListener = new WsListener()) // 添加監聽
                    .connectAsynchronously(); // 異步連接
            DebugClass.getInstance().setDebugLog("連接中");
            setStatus(WsStatus.CONNECTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (ws != null) {
            ws.removeListener(mListener).disconnect();
        }
    }

    public void reconnect() {
        if (ws != null) {
            ws.removeListener(mListener).disconnect();
        }
        create_websocket();
    }

    /**
     * 操作方法
     *
     * @param str_send_msg 送出字串
     */
    public void sendMessage(String str_send_msg) {
        ws.sendText(str_send_msg);
    }

    /**
     * Websocket interface
     */
    private WsInterface mInterface;

    public interface WsInterface {
        void getDataSuccess(JsonObject response);

        void getDataFail(String s);
    }

    public void setWsListener(WsInterface listener){
        this.mInterface = listener;
    }

    /**
     * Websocket Adapter
     */
    public class WsListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            DebugClass.getInstance().setDebugLog(getClass().getSimpleName(), text);
            JsonObject jsonObject = new JsonObject();
            JsonObject response = jsonObject.getAsJsonObject(text);
            mInterface.getDataSuccess(response);
        }

        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
            DebugClass.getInstance().setDebugLog(headers);
            setStatus(WsStatus.CONNECT_SUCCESS);
        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
            super.onConnectError(websocket, exception);
            DebugClass.getInstance().setDebugLog("exception", exception);
            setStatus(WsStatus.CONNECT_FAIL);
            mInterface.getDataFail(exception.getMessage());
        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            setStatus(WsStatus.DISCONNECT);

        }
    }

    public void logger(String message, @Nullable String otherMessage) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, otherMessage + message);
        }
    }


    /**
     * Websocket Status
     */
    private enum WsStatus {
        CONNECT_SUCCESS,
        CONNECT_FAIL,
        CONNECTING,
        DISCONNECT
    }

    private WsStatus status;

    public WsStatus getStatus() {
        return status;
    }

    public void setStatus(WsStatus status) {
        this.status = status;
    }


}
