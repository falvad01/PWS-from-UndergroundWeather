package es.dc.pws;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    double temperature = -10;
    private final int TIEMPO = 1000;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




        ProgressBar progres = findViewById(R.id.progresBar);
        final TextView time = findViewById(R.id.lastUpdate);

        Drawable progressDrawabl = progres.getProgressDrawable().mutate();
        progressDrawabl.setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.SRC_IN);
        progres.setProgressDrawable(progressDrawabl);

        paintDataTemperature();//Pintamos la informacion por primera vez
        paintDataWind();


        final Handler handler = new Handler(); // En esta zona creamos el objeto Handler
        final long startTime = System.nanoTime();

        handler.postDelayed(new Runnable() {
            public void run() {

                long elapsedTime = System.nanoTime()-startTime;
                long remainingTime = 6000 - elapsedTime;


                //Estas fgunciones se repetiran cada X tiempo
                paintDataTemperature();
                paintDataWind();





                handler.postDelayed(this, TIEMPO);
            }

        }, TIEMPO);

    }

    private void paintDataTemperature(){


        TextView temp = findViewById(R.id.temperature);
         Web w = new Web();
        temperature = w.getTemperature();

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

        temp.setText(String.format("%s ºC", temperature));

    }

    private void paintDataWind(){

        Web w = new Web();
        String windDirection = w.getWind();
        ImageView compass = findViewById(R.id.aguja);
        TextView windSpeedText = findViewById(R.id.windSpeed);
        TextView windDirectionText = findViewById(R.id.windDirection);

        compass.setRotation(getOrientatioDegrees(windDirection));
        windSpeedText.setText(String.format("%s k/h", w.getWindSpeed()));
        windDirectionText.setText(String.format("%s", windDirection));

    }


    private float getOrientatioDegrees(String orientation){
        Log.i("YA", "direcion trans grados " + orientation);
        float degrees = 0;
        switch (orientation){

            case "N":
                degrees = 0f;
                break;
            case "NNE":
                degrees = 22.5f;
                break;
            case "NE":
                degrees = 45f;
                break;
            case "NSE":
                degrees = 67.5f;
                break;
            case "E":
                degrees = 90f;
                break;
            case "SNE":
                degrees = 112.5f;
                break;
            case "SE":
                degrees = 135f;
                break;
            case "SSE":
                degrees = 157.5f;
                break;
            case "S":
                degrees = 180f;
                break;
            case "SSW":
                degrees = 202.5f;
                break;
            case "SW":
                degrees = 225f;
                break;
            case "SNW":
                degrees = 247.5f;
                break;
            case "W":
                degrees = 270f;
                break;
            case "NSW":
                degrees = 292.5f;
                break;
            case "NW":
                degrees = 315f;
                break;
            case "NNW":
                degrees = 352.5f;
                break;
        }


        Log.i("YA","grados brujula: " + degrees);
        return degrees;
    }

}
