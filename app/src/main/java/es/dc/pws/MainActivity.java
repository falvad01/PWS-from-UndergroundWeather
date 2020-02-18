package es.dc.pws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Web w = new Web();
        TextView temp = findViewById(R.id.temperature);

        temp.setText(String.valueOf(w.getTemperature()) + " ºC");

        TextView hum = findViewById(R.id.humidity);

        hum.setText(String.valueOf(w.getHumidity()) + "%");





    }
}
