package com.cookandroid.project_tab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androdocs.httprequest.HttpRequest;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    String CITY;
    String API = "6306525efd4d8ea2bde64d2b749cd04e";
    ImageView search;
    EditText etCity;
    TextView city,country,time,temp,forecast,humidity,min_temp,max_temp,sunrises,sunsets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);
        etCity = (EditText) findViewById(R.id.Your_city);
        search = (ImageView) findViewById(R.id.search);
//         CALL ALL ANSWERS :
        city = (TextView) findViewById(R.id.city);
        country = (TextView) findViewById(R.id.country);
        time = (TextView) findViewById(R.id.time);
        temp = (TextView) findViewById(R.id.temp);
        forecast = (TextView) findViewById(R.id.forecast);
        humidity = (TextView) findViewById(R.id.humidity);
        min_temp = (TextView) findViewById(R.id.min_temp);
        max_temp = (TextView) findViewById(R.id.max_temp);
        sunrises = (TextView) findViewById(R.id.sunrises);
        sunsets = (TextView) findViewById(R.id.sunsets);
//         CLICK ON SEARCH BUTTON :
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CITY = etCity.getText().toString();
                System.out.println("CITY: " + CITY);
                WeatherActivity.WeatherTask wt = new WeatherActivity.WeatherTask();
                wt.execute();
            }
        });
    }

    class WeatherTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Before back");
        }
        @Override
        protected String doInBackground(String... args) {
            String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&units=metric&appid=" + API;
            String response = HttpRequest.excuteGet(weatherURL);
            return response;
        }
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject main = jsonObj.getJSONObject("main");
                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);
                JSONObject sys = jsonObj.getJSONObject("sys");
                // CALL VALUE IN API :
                String city_name = jsonObj.getString("name");
                String countryname = sys.getString("country");
                Long updatedAt = jsonObj.getLong("dt");
                String updatedAtText = "Last Updated at: " + new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000));
                String temperature = main.getString("temp");
                String cast = weather.getString("description");
                String humi_dity = main.getString("humidity");
                String temp_min = main.getString("temp_min");
                String temp_max = main.getString("temp_max");
                Long rise = sys.getLong("sunrise");
                String sunrise = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(rise * 1000));
                Long set = sys.getLong("sunset");
                String sunset = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(set * 1000));
                // SET ALL VALUES IN TEXTBOX :
                city.setText(city_name);
                country.setText(countryname);
                time.setText(updatedAtText);
                temp.setText(temperature + "°C");
                forecast.setText(cast);
                humidity.setText(humi_dity);
                min_temp.setText(temp_min);
                max_temp.setText(temp_max);
                sunrises.setText(sunrise);
                sunsets.setText(sunset);
            } catch (Exception e) {
                Toast.makeText(WeatherActivity.this, "Error:" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
