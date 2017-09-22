package com.example.dream.kucassava;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    private TextView forgetBtn;
    private Button loginBtn;
    private Button registerBtn;
    private EditText usernameInput, passwordInput;
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameInput = (EditText) findViewById(R.id.username);
        passwordInput = (EditText) findViewById(R.id.password);

        forgetBtn = (TextView) findViewById(R.id.forgetPassword);
        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), ForgetPassword.class));
            }
        });

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogingIn();
            }
        });

        registerBtn = (Button) findViewById(R.id.register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Registration.class));
            }
        });
    }

    private void LogingIn() {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        boolean validate = validateInput(username, password);
        if(!validate){
            Toast.makeText(getApplication(), "Please Enter Username and Password", Toast.LENGTH_SHORT).show();
            return;
        }
        authenToServer(username, password);
    }

    private void authenToServer(String username, String password) {
        String url = String.format("http://g5714450141.com/api/index.php/users/login?username=%s&password=%s", username, password);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+ e.hashCode());
                Toast.makeText(getApplication(), "Username Or Password Not Correct", Toast.LENGTH_SHORT).show();
                return;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code() == 200){
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("Cassava", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject data = jsonObject.getJSONObject("data");
                        editor.putString("user_id", data.getString("user_id"));
                        editor.putString("address", data.getString("address"));
                        editor.putString("tambon", data.getString("address2"));
                        editor.putString("amphoe", data.getString("city"));
                        editor.putString("province", data.getString("province"));
                        editor.putString("zipcode", data.getString("zipcode"));
                        editor.putString("email", data.getString("email"));
                        editor.putString("telNo", data.getString("telephone_number"));
                        editor.putString("firstname", data.getString("firstname"));
                        editor.putString("lastname", data.getString("lastname"));
                        editor.commit();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(getApplication(), MenuPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean validateInput(String username, String password) {
        return !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password));
    }
}
