package dmitry.com.facultativeapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;
import java.util.Timer;

import dmitry.com.facultativeapp.R;

public class FragmentSensor extends Fragment {

    private TextView tvAccelerometer;
    private SensorManager sensorManager;
    private Sensor sensor;
    private Timer timer;
    private StringBuilder sb = new StringBuilder();

    private Button btnTakePhoto;
    private Button btnSavePhoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sensor, null);

        tvAccelerometer = v.findViewById(R.id.tvAccelerometer);
        btnSavePhoto = v.findViewById(R.id.btnSavePhoto);
        btnTakePhoto = v.findViewById(R.id.btnTakePhoto);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        sensorManager = (SensorManager) (Objects.requireNonNull(getActivity())).getSystemService
                (Context.SENSOR_SERVICE);
        assert sensorManager != null;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        return v;
    }

    float[] valueAccelerometer = new float[3];
    // Accelerometer's listener
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            System.arraycopy(event.values, 0, valueAccelerometer, 0, 3);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

//    // must override for listener
//    @Override
//    public void onPause() {
//        super.onPause();
//
//        sensorManager.unregisterListener(listener);
//        timer.cancel();
//    }
//
//    // must override for listener
//    @Override
//    public void onResume() {
//        super.onResume();
//
//        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
//
//        timer = new Timer();
//
//        // СДЕЛАТЬ ТАЙМЕР!
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showInfo();
//                    }
//                });
//            }
//        };
//        timer.schedule(task, 0, 400);
//
//    }

    void showInfo() {
        sb.setLength(0);
        sb.append("Accelerometer: ").append(format(valueAccelerometer));
        tvAccelerometer.setText(sb);
    }

    @SuppressLint("DefaultLocale")
    String format(float values[]) {
        return String.format("%1$.1f\t\t%2$.1f\t\t%3$.1f", values[0], values[1],
                values[2]);
    }



}
