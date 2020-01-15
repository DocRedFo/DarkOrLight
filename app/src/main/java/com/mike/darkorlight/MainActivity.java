package com.mike.darkorlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    MediaPlayer mediaPlayer;
    float light = 50; //Статичное значение освещенности, так как я не смог сделать "систему", при которой максимальное и минимальное значения заполняются автоматически
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);//MrRobot Soundtrack

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


    }

    //Установка listener
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
    }


    //Не нужно к заполнению сейчас
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //Запускаем, чтобы можно было остановить
        mediaPlayer.start();

        float[] l = event.values.clone();
        double n = l[0];

        if (n >= light){
            textView.setText(R.string.text0);
            mediaPlayer.pause();

        }
        else if (n < light){
            textView.setText(R.string.text1);
            mediaPlayer.start();
        }

    }
}
