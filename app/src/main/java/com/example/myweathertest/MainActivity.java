package com.example.myweathertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView t1_temp,t1_city,t1_description,t1_date,t2_temp,t2_city,t2_description,t2_date,
            t3_temp,t3_city,t3_description,t3_date,t4_temp,t4_city,t4_description,t4_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1_temp = (TextView)findViewById(R.id.textTemp1);
        t1_city = (TextView)findViewById(R.id.textCity1);
        t1_description = (TextView)findViewById(R.id.textDesc1);
        t1_date = (TextView)findViewById(R.id.textDate1);

        t2_temp = (TextView)findViewById(R.id.textTemp2);
        t2_city = (TextView)findViewById(R.id.textCity2);
        t2_description = (TextView)findViewById(R.id.textDesc2);
        t2_date = (TextView)findViewById(R.id.textDate2);

        t3_temp = (TextView)findViewById(R.id.textTemp3);
        t3_city = (TextView)findViewById(R.id.textCity3);
        t3_description = (TextView)findViewById(R.id.textDesc3);
        t3_date = (TextView)findViewById(R.id.textDate3);

        t4_temp = (TextView)findViewById(R.id.textTemp4);
        t4_city = (TextView)findViewById(R.id.textCity4);
        t4_description = (TextView)findViewById(R.id.textDesc4);
        t4_date = (TextView)findViewById(R.id.textDate4);

        find_weather();
    }

    public void find_weather()
    {
        String url ="https://samples.openweathermap.org/data/2.5/weather?q=serres,gr&appid=377d3707cd27c6f9c8080e4841d8540b";

        JsonObjectRequest jor= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String description = object.getString("description");
                    String city = response.getString("name");




                    t1_temp.setText(temp);
                    t2_city.setText(city);
                    t3_description.setText(description);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
                    String formatted_date = sdf.format(calendar.getTime());

                    t4_date.setText(formatted_date);

                    double temp_int = Double.parseDouble(temp);
                    double centi = (temp_int - 32) / 1.8000;
                    centi = Math.round(centi);
                    int i = (int)centi;
                    t1_temp.setText(String.valueOf(i));



                }catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);


    }
}
