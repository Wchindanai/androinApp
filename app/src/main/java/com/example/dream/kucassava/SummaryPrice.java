package com.example.dream.kucassava;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SummaryPrice extends AppCompatActivity {
    private TextView marketNameText, priceText, summary_price;
    private Spinner plantationName;
    private String _userId, _price;
    private List<String> plantNameList = new ArrayList<>();
    private List<PlantationDetailModel> plantationDetailModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_price);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("marketName");
        name = "ตลาด "+name;
        _price = bundle.getString("price");
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Cassava", MODE_PRIVATE);
        _userId = pref.getString("user_id", null);
        marketNameText = (TextView) findViewById(R.id.market_name);
        priceText = (TextView) findViewById(R.id.price);
        summary_price = (TextView) findViewById(R.id.summary_price);
        plantationName = (Spinner) findViewById(R.id.plant_name);

        marketNameText.setText(name);
        priceText.setText(_price);

        fetchPlantationDetail();
        plantationName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Double expectProduct = Double.parseDouble(plantationDetailModelList.get(position).getExpect_product());
                Double price = Double.parseDouble(_price);
                String summary = summaryPrice(price, expectProduct);
                summary_price.setText(summary);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fetchPlantationDetail() {
        String url = String.format("http://g5714450141.com/api/index.php/plantationdetails/plantation?user_id=%s", _userId);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplication(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                return;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray arrayData = jsonObject.getJSONArray("data");
                    for(int i = 0; i < arrayData.length(); i++){
                        String name = arrayData.getJSONObject(i).getString("plantation_name");
                        String expectProduct = arrayData.getJSONObject(i).getString("expect_product");
                        PlantationDetailModel plantationDetailModel = new PlantationDetailModel(name, expectProduct);
                        plantationDetailModelList.add(plantationDetailModel);
                        plantNameList.add(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setSpinner();
                        Double expectProduct = Double.parseDouble(plantationDetailModelList.get(0).getExpect_product());
                        Double price = Double.parseDouble(_price);
                        String summary = summaryPrice(price, expectProduct);
                        summary_price.setText(summary);
                    }
                });
            }
        });
    }

    private void setSpinner() {
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, plantNameList);
        plantationName.setAdapter(adapterSpinner);
    }

    private String summaryPrice(Double marketPrice, Double expectProduct){
        String summary = (expectProduct*1000*marketPrice)+"";
        summary = "+" + summary + " บาท";
        return summary;
    }


}
