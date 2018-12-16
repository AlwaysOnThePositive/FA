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
    //ClientId приложения на гите
    private static final String cliendId = "830e851082bc0c849162";
    //ClientSecret приложения на гите
    private static final String clientSecret = "58c5b23435daf4ea2c2dc099a5be5ed7a7f75678";
    //CallBack для окончания авторизации
    private static final String redirectUri = "dmitry.com.facultativeapp://callback";
    private static String USERNAME;
    private static NetClient netClient;
    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        //проводим инициализацию карты
        MapKitFactory.setApiKey("c47a3cf9-06fd-48e2-8cfc-978bb2cfc119");
        MapKitFactory.initialize(this);
        TransportFactory.initialize(this);

        sharedPreferences = getSharedPreferences(String.valueOf(R.string.prefs_name), MODE_PRIVATE);
        USERNAME = sharedPreferences.getString(String.valueOf(R.string.username), null);
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

    //Метод сохранения токена на устройстве
    public static void setAccessToken(String token) {
        sharedPreferences.edit().putString(String.valueOf(R.string.token), token).apply();
    }

    //Метод получения токена с устройства
    public static String getAccessToken() {
        return sharedPreferences.getString(String.valueOf(R.string.token), null);
    }

    //Метод очистки токена на устройстве
    public static void clearAccessToken(String token) {
        sharedPreferences.edit().putString(String.valueOf(R.string.token), token).apply();
    }

    //Мтеод для очистки пользователя
    public static void clearUserName(String userName) {
        sharedPreferences.edit().putString(String.valueOf(R.string.username), userName).apply();
    }

    public static String getCliendId() {
        return cliendId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }

    public static String getRedirectUri() {
        return redirectUri;
    }

    //Метод получения
    public static String getUsername() {
        return USERNAME;
    }

    public static void setUsername(String username) {
        USERNAME = username;
        sharedPreferences.edit().putString(String.valueOf(R.string.username), username).apply();
    }
}
