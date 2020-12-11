package com.example.chucknorris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.chuckNorris.MESSAGE";

    private Button button;
    public TextView jokeDisplay;

    protected String getJoke(String sURL) {
        try {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                JSONObject json = new JSONObject(IOUtils.toString(new URL(sURL), Charset.forName("UTF-8")));
                return json.getString("value");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String jsonLocation = "https://api.chucknorris.io/jokes/random";


            jokeDisplay = (TextView) this.findViewById(R.id.jokeDisplay);
            button = (Button) this.findViewById(R.id.getJoke);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jokeDisplay.setText(getJoke("https://api.chucknorris.io/jokes/random?category=food"));
                }
            });
    }
}