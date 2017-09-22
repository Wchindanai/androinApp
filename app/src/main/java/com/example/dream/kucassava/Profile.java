package com.example.dream.kucassava;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {

    private TextView nameText, addressText, tambonText, amphoeText, provinceText, emailText, zipcodeText, telNoText;
    private Button editProfileBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameText = (TextView) view.findViewById(R.id.name);
        addressText = (TextView) view.findViewById(R.id.address);
        tambonText = (TextView) view.findViewById(R.id.tambon);
        amphoeText = (TextView) view.findViewById(R.id.amphoe);
        provinceText = (TextView) view.findViewById(R.id.province);
        emailText = (TextView) view.findViewById(R.id.email);
        zipcodeText = (TextView) view.findViewById(R.id.zipcode);
        telNoText = (TextView) view.findViewById(R.id.telophoneNo);
        editProfileBtn = (Button) view.findViewById(R.id.editProfile);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        readDataFromSharedPref();
    }

    private void readDataFromSharedPref() {
        SharedPreferences pref = getActivity().getSharedPreferences("Cassava", MODE_PRIVATE);
        String firstname = pref.getString("firstname", "");
        String lastname = pref.getString("lastname", "");
        String address = pref.getString("address", "");
        String tambon = pref.getString("tambon", "");
        String amphoe = pref.getString("amphoe", "");
        String province = pref.getString("province", "");
        String zipcode = pref.getString("zipcode", "");
        String telNo = pref.getString("telNo", "");
        String email = pref.getString("email", "");

        nameText.setText(firstname + " " + lastname);
        addressText.setText(String.format("ที่อยู่: %s", address));
        tambonText.setText(String.format("ตำบล: %s", tambon));
        amphoeText.setText(String.format("อำเภอ: %s", amphoe));
        provinceText.setText(String.format("จังหวัด: %s", province));
        zipcodeText.setText(String.format("รหัสไปรษณีย์: %s", zipcode));
        telNoText.setText(String.format("เบอร์โทรศัพท์ : %s", telNo));
        emailText.setText(String.format("email: %s", email));
    }
}
