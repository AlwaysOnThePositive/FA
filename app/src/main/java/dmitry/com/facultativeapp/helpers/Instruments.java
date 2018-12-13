package dmitry.com.facultativeapp.helpers;

import android.graphics.Bitmap;
import android.view.View;

public class Instruments {

    //Класс в котором располагаются воспомогательные методы

    private static Bitmap takeScreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takeScreenshotOfRootView(View v) {
        return takeScreenshot(v.getRootView());
    }

}
