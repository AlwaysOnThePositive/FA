package dmitry.com.facultativeapp.fragments;

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

//        WifiManager wifiManager = (WifiManager) Objects.requireNonNull(getActivity()).getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        assert wifiManager != null;
//        String ip = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());

//        String modelValue = Build.MODEL;
//        tvModel.setText(tvModel.getText() + modelValue);

//        String version = Build.VERSION.RELEASE;
//        tvVersion.setText(tvVersion.getText() + version);
        return  v;
    }

    @Override
    public void onStart() {
        super.onStart();

        String mobileIP = Instruments.getMobileIPAddress();
        String wifiIP = Instruments.getWifiIPAddress(getContext());

        wifiIpTV.setText(wifiIP);
        mobileIpTV.setText(mobileIP);

    }
}
