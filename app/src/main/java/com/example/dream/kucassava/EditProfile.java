package com.example.dream.kucassava;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditProfile extends AppCompatActivity {
    private EditText idCardInput, firstNameInput, lastNameInput, addressInput, tambonInput, amphoeInput, provinceInput, zipcodeInput
            , emailInput, telNoInput, usernameInput, passwordInput, confirmPasswordInput;
    private Spinner genderSpinner;
    private Button submitBtn;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        title = (TextView) findViewById(R.id.titleRegister);
        idCardInput = (EditText) findViewById(R.id.idNo);
        firstNameInput = (EditText) findViewById(R.id.firstName);
        lastNameInput = (EditText) findViewById(R.id.lastName);
        addressInput = (EditText) findViewById(R.id.address);
        tambonInput = (EditText) findViewById(R.id.tambon);
        amphoeInput = (EditText) findViewById(R.id.amphoe);
        provinceInput = (EditText) findViewById(R.id.province);
        zipcodeInput = (EditText) findViewById(R.id.zipcode);
        emailInput = (EditText) findViewById(R.id.email);
        telNoInput = (EditText) findViewById(R.id.telNo);
        genderSpinner = (Spinner) findViewById(R.id.gender);
        submitBtn = (Button) findViewById(R.id.submit);

        usernameInput = (EditText) findViewById(R.id.username);
        passwordInput = (EditText) findViewById(R.id.password);
        confirmPasswordInput = (EditText) findViewById(R.id.confirmPassword);
        usernameInput.setFocusable(false);
        passwordInput.setFocusable(false);
        confirmPasswordInput.setFocusable(false);

        title.setText("แก้ไขข้อมูล");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

        addGenderList();
        loadProfileData();
    }

    private void addGenderList() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.gender_arrays, android.R.layout.simple_dropdown_item_1line);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(arrayAdapter);
    }

    private void editProfile() {
        String gender = genderSpinner.getSelectedItem().toString();
        String idCardNo = idCardInput.getText().toString();
        String firtName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String address = addressInput.getText().toString();
        String tambon = tambonInput.getText().toString();
        String amphoe = amphoeInput.getText().toString();
        String province = provinceInput.getText().toString();
        String zipcode = zipcodeInput.getText().toString();
        String email = emailInput.getText().toString();
        String telNo = telNoInput.getText().toString();

        editProfileToServer(firtName, lastName, address, tambon, amphoe, province, zipcode, email, telNo, gender, idCardNo);
    }

    private void editProfileToServer(String firtName, String lastName, String address, String tambon, String amphoe, String province, String zipcode, String email, String telNo, String gender, String idCardNo) {
        SharedPreferences pref = getApplication().getSharedPreferences("Cassava", MODE_PRIVATE);
        String id = pref.getString("user_id", "");
        String url = String.format("http://g5714450141.com/api/index.php/users/post/id/%s", id);
        RequestBody formBody = new FormBody.Builder()
                .add("firstname", firtName)
                .add("lastname", lastName)
                .add("address", address)
                .add("address2", tambon)
                .add("city", amphoe)
                .add("province", province)
                .add("zipcode", zipcode)
                .add("email", email)
                .add("telephone_number", telNo)
                .add("image", "1")
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplication(), "Cannot Edit Profile", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code() == 200){
                    finish();
                }
            }
        });
    }

    private void loadProfileData(){
        SharedPreferences pref = getApplication().getSharedPreferences("Cassava", MODE_PRIVATE);
        String firstname = pref.getString("firstname", "");
        String lastname = pref.getString("lastname", "");
        String address = pref.getString("address", "");
        String tambon = pref.getString("tambon", "");
        String amphoe = pref.getString("amphoe", "");
        String province = pref.getString("province", "");
        String zipcode = pref.getString("zipcode", "");
        String telNo = pref.getString("telNo", "");
        String email = pref.getString("email", "");

        firstNameInput.setText(firstname);
        lastNameInput.setText(lastname);
        addressInput.setText(address);
        tambonInput.setText(tambon);
        amphoeInput.setText(amphoe);
        provinceInput.setText(province);
        zipcodeInput.setText(zipcode);
        telNoInput.setText(telNo);
        emailInput.setText(email);
    }


}
