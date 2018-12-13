package dmitry.com.facultativeapp.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tomash.androidcontacts.contactgetter.entity.ContactData;
import com.tomash.androidcontacts.contactgetter.main.contactsGetter.ContactsGetterBuilder;

import java.util.ArrayList;
import java.util.List;

import dmitry.com.facultativeapp.R;
import dmitry.com.facultativeapp.adapters.ContactsListAdapter;

public class FragmentContacts extends Fragment {

    //Класс который отвечает за отображение контактов

    private RecyclerView contactRecyclerView;
    private LinearLayoutManager layoutManager;
    private ContactsListAdapter contactsListAdapter;
    private List<ContactData> contactDataList = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        contactRecyclerView = view.findViewById(R.id.contactRecyclerView);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        contactRecyclerView.setLayoutManager(layoutManager);
        contactsListAdapter = new ContactsListAdapter(contactDataList);
        contactRecyclerView.setAdapter(contactsListAdapter);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        permissionsRequest();
    }

    //Метод отрисовки контактов на экране
    private void showContacts() {
        contactDataList.clear();
        contactDataList.addAll(new ContactsGetterBuilder(getContext())
                .allFields()
                .buildList());
        contactsListAdapter.notifyDataSetChanged();
    }

    private void permissionsRequest() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED ) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 1);
        } else {
            showContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(getActivity(), "Until you grant the permission, we cannot display the " +
                        "names", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
