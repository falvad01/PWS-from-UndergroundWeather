package es.dc.pws;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    double temperature = -10;
    @RequiresApi(api = Build.VERSION_CODES.P)
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

             temperature = w.getTemperature();
            //temperature = -10;
            ProgressBar progresTemp = findViewById(R.id.progresBarTemperature);
            progresTemp.setProgress((int) temperature);


            if (temperature <= 2) {
                Drawable progressDrawable = progresTemp.getProgressDrawable().mutate();
                progressDrawable.setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
                progresTemp.setProgressDrawable(progressDrawable);
                temp.setTextColor(Color.BLUE);
            } else if (temperature > 2 && temperature <= 15) {
                Drawable progressDrawable = progresTemp.getProgressDrawable().mutate();
                progressDrawable.setColorFilter(Color.CYAN, android.graphics.PorterDuff.Mode.SRC_IN);
                progresTemp.setProgressDrawable(progressDrawable);
                temp.setTextColor(Color.CYAN);
            } else if (temperature > 15 && temperature <= 20) {
                Drawable progressDrawable = progresTemp.getProgressDrawable().mutate();
                progressDrawable.setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                progresTemp.setProgressDrawable(progressDrawable);
                temp.setTextColor(Color.GREEN);
            } else if (temperature > 20 && temperature <= 25) {
                Drawable progressDrawable = progresTemp.getProgressDrawable().mutate();
                progressDrawable.setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                progresTemp.setProgressDrawable(progressDrawable);
                temp.setTextColor(Color.YELLOW);
            } else if (temperature > 25) {
                Drawable progressDrawable = progresTemp.getProgressDrawable().mutate();
                progressDrawable.setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                progresTemp.setProgressDrawable(progressDrawable);
                temp.setTextColor(Color.RED);
            } else {
                Drawable progressDrawable = progresTemp.getProgressDrawable().mutate();
                progressDrawable.setColorFilter(Color.GRAY, android.graphics.PorterDuff.Mode.SRC_IN);
                progresTemp.setProgressDrawable(progressDrawable);
                temp.setTextColor(Color.GRAY);
            }


            Log.i("INFO", (String.valueOf(temperature)));

            temp.setText(String.valueOf(temperature) + " ºC");

            TextView hum = findViewById(R.id.humidity);

            hum.setText(String.valueOf(w.getHumidity()) + "%");

            temperature = temperature + 0.2;




    }
}
