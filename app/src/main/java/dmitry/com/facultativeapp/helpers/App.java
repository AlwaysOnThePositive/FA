package dmitry.com.facultativeapp.helpers;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.transport.TransportFactory;

import dmitry.com.facultativeapp.R;
import dmitry.com.facultativeapp.sync.NetClient;

public class App extends Application {


    //Класс для управления нетклиентом
    private static final String AUTH_URL = "https://github.com/";
    private static final String BASE_URL = "https://api.github.com/";
    private static final String USERNAME = "AlwaysOnThePositive";
    private static NetClient netClient;
    private static SharedPreferences sharedPreferences;

    public static String getUsername() {
        return USERNAME;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //проводим инициализацию карты
        MapKitFactory.setApiKey("c47a3cf9-06fd-48e2-8cfc-978bb2cfc119");
        MapKitFactory.initialize(this);
        TransportFactory.initialize(this);

        sharedPreferences = getSharedPreferences(String.valueOf(R.string.prefs_name), MODE_PRIVATE);
        if (sharedPreferences.getString(String.valueOf(R.string.token), null) != null) {
            setBaseNetClient();
        } else {
            setAuthNetClient();
        }

        //Вообще все важные инициализации надо проводить тут - где инициализация идет через статик методы
    }

    public static NetClient getNetClient() {
        return netClient;
    }

    //Мы будем использовать этот нетклиент для авторизации
    public static void setAuthNetClient() {
        netClient = new NetClient(AUTH_URL, null);
    }

    //А этот нетклиент для отправки запрос к апи
    public static void setBaseNetClient() {
        netClient = new NetClient(BASE_URL, getAccessToken());
    }

    public static void setAccessToken(String token) {
        sharedPreferences.edit().putString(String.valueOf(R.string.token), token).apply();
    }

    public static String getAccessToken() {
        return sharedPreferences.getString(String.valueOf(R.string.token), null);
    }

    public static void clearAccessToken(String token) {
        sharedPreferences.edit().putString(String.valueOf(R.string.token), null).apply();
    }

    public static String getUSERNAME() {
        return USERNAME;
    }
}
