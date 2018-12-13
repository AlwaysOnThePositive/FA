package dmitry.com.facultativeapp.helpers;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.transport.TransportFactory;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //проводим инициализацию карты
        MapKitFactory.setApiKey("c47a3cf9-06fd-48e2-8cfc-978bb2cfc119");
        MapKitFactory.initialize(this);
        TransportFactory.initialize(this);

        //Вообще все важные инициализации надо проводить тут - где инициализация идет через статик методы
    }
}
