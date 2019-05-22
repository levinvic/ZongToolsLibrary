package wangwanglive.levinvic.com.zongtoolslibrary.Utils_Network;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

public class ExceptionHandle {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT =504;

    public static ResponseThrowable handleException(Throwable e){
        ResponseThrowable ex;
        if (e instanceof HttpException){
            HttpException httpException =(HttpException) e;
            ex = new ResponseThrowable(e , ERROR.HTTP_ERROR);
            switch (httpException.code()){
                case UNAUTHORIZED:
                    ex.message = httpException.code() + " UNAUTHORIZED";
                    break;
                case FORBIDDEN:
                    ex.message = httpException.code() + " FORBIDDEN";
                    break;
                case NOT_FOUND:
                    ex.message = httpException.code() + " NOT_FOUND";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = httpException.code() + " REQUEST_TIMEOUT";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = httpException.code() + " GATEWAY_TIMEOUT";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = httpException.code() + " INTERNAL_SERVER_ERROR";
                    break;
                case BAD_GATEWAY:
                    ex.message = httpException.code() + " BAD_GATEWAY";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = httpException.code() + " SERVICE_UNAVAILABLE";
                    break;
                default:
                    ex.message = "其他網路錯誤: " + httpException.code();
                    break;
            }
            return ex;
        }else if (e instanceof ServerException){
            ServerException resultException = (ServerException) e;
            ex = new ResponseThrowable(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        }else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException){
            ex = new ResponseThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "回傳JSON解析錯誤";
            return ex;
        }else if (e instanceof ConnectException){
            ex = new ResponseThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "連接失敗，請檢查網路連線";
            return ex;
        }else if (e instanceof SSLHandshakeException){
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "連接超時";
            return ex;
        }else if (e instanceof SocketTimeoutException){
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "連接超時";
            return ex;
        }else {
            ex = new ResponseThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知錯誤";
            return ex;
        }
    }

    /**
     * 約定異常
     */
    class ERROR{

        /**
         * 未知錯誤
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析錯誤
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 網路錯誤
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 協議錯誤
         */
        public static final int HTTP_ERROR = 1003;
        /**
         * 証書錯誤
         */
        public static final int SSL_ERROR = 1005;
        /**
         * 連接超時
         */
        public static final int TIMEOUT_ERROR = 1006;

    }


    public static class ResponseThrowable extends Exception{
        public int code;
        public String message;

        public ResponseThrowable(Throwable throwable,int code){
            super(throwable);
            this.code = code;
        }
    }

    public class ServerException extends RuntimeException{
        public int code;
        public String message;
    }
}
