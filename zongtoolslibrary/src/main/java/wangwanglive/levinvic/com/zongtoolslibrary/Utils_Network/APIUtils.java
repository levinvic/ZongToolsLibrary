package wangwanglive.levinvic.com.zongtoolslibrary.Utils_Network;


import wangwanglive.levinvic.com.zongtoolslibrary.Utils_Tools.DebugClass;

public class APIUtils {

//    public static String BASE_URL = "http://192.168.8.123:8000/";
//    public static String BASE_URL = "https://www.niya.nctu.me/smxxth/api/";
//    public static String BASE_URL = "";

    // GCP
    public static String BASE_URL = "https://www.niya.nctu.me/smxxth/api/";
    public static String BASE_DOMAIN = "https://www.niya.nctu.me/";

    // local API
//    public static String BASE_URL = "http://192.168.8.123:8000/smxxth/api/";
//    public static String BASE_DOMAIN = "http://192.168.8.123:8000/";


    public static String URL = "";

    public static APIService getClient() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static APIService getRxClient() {
        DebugClass.getInstance().setDebugLog("TAGG","BASE_URL" + BASE_URL);
        return RxRetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
