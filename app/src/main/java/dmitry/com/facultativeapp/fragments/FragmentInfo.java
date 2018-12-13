package dmitry.com.facultativeapp.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dmitry.com.facultativeapp.R;
import dmitry.com.facultativeapp.helpers.Instruments;


public class FragmentInfo extends Fragment {

    //Фрагмент отвечающий за вывод информации о телефоне
    private TextView wifiIpTV;
    private TextView mobileIpTV;
    private TextView tvModel;
    private TextView tvVersion;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_info, container, false);

        wifiIpTV = v.findViewById(R.id.wifiIpTV);
        mobileIpTV = v.findViewById(R.id.mobileIpTV);
        tvModel = v.findViewById(R.id.tvModel);
        tvVersion = v.findViewById(R.id.tvVersion);

        return  v;
    }

    @Override
    public void onStart() {
        super.onStart();

        String mobileIP = Instruments.getMobileIPAddress();
        String wifiIP = Instruments.getWifiIPAddress(getContext());

        wifiIpTV.setText("IPv4: " + wifiIP);
        mobileIpTV.setText("IPv6: " + mobileIP);

        String modelValue = Build.MODEL;
        tvModel.setText("Model: " + modelValue);

        String version = Build.VERSION.RELEASE;
        tvVersion.setText("Version: " + version);

    }
}
