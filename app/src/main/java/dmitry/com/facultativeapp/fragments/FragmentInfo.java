package dmitry.com.facultativeapp.fragments;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import dmitry.com.facultativeapp.R;


public class FragmentInfo extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_info, null);

        TextView tvIp = v.findViewById(R.id.tvIp);
        TextView tvModel = v.findViewById(R.id.tvModel);
        TextView tvVersion = v.findViewById(R.id.tvVersion);

        WifiManager wifiManager = (WifiManager) Objects.requireNonNull(getActivity()).getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        assert wifiManager != null;
        String ip = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());


        tvIp.setText(tvIp.getText()+ ip);

        String modelValue = Build.MODEL;
        tvModel.setText(tvModel.getText() + modelValue);

        String version = Build.VERSION.RELEASE;
        tvVersion.setText(tvVersion.getText() + version);

        return  v;
    }
}
