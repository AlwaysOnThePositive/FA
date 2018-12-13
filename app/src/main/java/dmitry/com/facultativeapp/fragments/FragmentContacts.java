package dmitry.com.facultativeapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dmitry.com.facultativeapp.R;

public class FragmentContacts extends Fragment {

    //Класс который отвечает за отображение контактов

//    public static FragmentContacts newInstance(String param1, String param2) {
//        FragmentContacts fragment = new FragmentContacts();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }


}
