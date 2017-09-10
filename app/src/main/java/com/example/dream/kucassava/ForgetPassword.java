package com.example.dream.kucassava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForgetPassword extends AppCompatActivity {
    private Button cancelBtn, confirmBtn;
    private EditText usernameInput, telephoneNoInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        usernameInput = (EditText) findViewById(R.id.username);
        telephoneNoInput = (EditText) findViewById(R.id.idNo);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recoverPassword();
            }
        });
    }

    private void recoverPassword() {
        String username = usernameInput.getText().toString();
        String telephoneNo = telephoneNoInput.getText().toString();
        String url = String.format("http://g5714450141.com/api/index.php/users/password?username=%s&telephone_number=%s",
                username, telephoneNo);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ForgetPassword.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplication(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                ForgetPassword.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String res = null;
                        try {
                            res = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(TextUtils.isEmpty(res)){
                            Toast.makeText(getApplication(), "Wrong Username Or Telephone Number", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(res);
                            String password = jsonObject.getString("password");
                            Toast.makeText(getApplication(), "Your Password is " + password, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
