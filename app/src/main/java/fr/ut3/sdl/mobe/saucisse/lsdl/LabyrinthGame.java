package fr.ut3.sdl.mobe.saucisse.lsdl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import fr.ut3.sdl.mobe.saucisse.lsdl.parts.Ball;
import fr.ut3.sdl.mobe.saucisse.lsdl.parts.Labyrinth;

public class LabyrinthGame extends AppCompatActivity implements SensorEventListener {
    public static final int COLUMN_NUMBER = 21;
    public static final int ROW_NUMBER = 7;
    public static final int BALL_STARTING_X = 0;
    public static final int BALL_STARTING_Y = 7;
    private Labyrinth labyrinth;
    private Ball ball;
    private TextView labyrinthTextView;
    private SensorManager sensorManager = null;
    private final float[] accelerometerReading = new float[3];
    private final float[] magnetometerReading = new float[3];
    private final float[] rotationMatrix    = new float[9];
    private final float[] orientationAngles = new float[3];
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateOrientationAngles();

            int pitch = (int)(Math.toDegrees(orientationAngles[1])+360)%360;
            int roll = (int)(Math.toDegrees(orientationAngles[2])+360)%360;

            //Hysteresis
            int pitchInfBoundary = (pitch + 330)%360;
            int pitchSupBoundary = (pitch + 390)%360;

            if(pitchInfBoundary != pitch || pitchSupBoundary != pitch) {
                //Smooth value
                pitch = (pitch / 45) % 8;
            }

            int rollInfBoundary = (roll + 330)%360;
            int rollSupBoundary = (roll + 390)%360;

            if(rollInfBoundary != roll || rollSupBoundary != roll) {
                //Smooth value
                roll = (roll / 45) % 8;
            }

            //Log.d("SensorChanged", "pitch : " + pitch + " roll : " + roll);

            if(roll == 1 && ball.getPosY()-1 >= 0  && labyrinth.getCase(ball.getPosX(), ball.getPosY()-1) != 1){
                Log.d("SensorChanged", "Up !");
                ball.goUp();
                displayLabyrinth();
            }else if(roll == 6 && ball.getPosY()+1 < 20  && labyrinth.getCase(ball.getPosX(), ball.getPosY()+1) != 1){
                Log.d("SensorChanged", "Down !");
                ball.goDown();
                displayLabyrinth();
            }else if(pitch == 2 && ball.getPosX() - 1 >= 0 && labyrinth.getCase(ball.getPosX()-1, ball.getPosY()) != 1){
                Log.d("SensorChanged", "Left !");
                ball.goLeft();
                displayLabyrinth();
            }else if(pitch == 6 && ball.getPosX()+1 < 20 && labyrinth.getCase(ball.getPosX()+1, ball.getPosY()) != 1){
                Log.d("SensorChanged", "Right !");
                ball.goRight();
                displayLabyrinth();
            }

            handler.postDelayed(runnable, 1000);
        }
    };
    private void displayLabyrinth(){
        Log.d("Display", "Display !");
        String labyrinthString = labyrinth.transform(
                ball.getPosX(), ball.getPosY(), COLUMN_NUMBER, ROW_NUMBER);
        Spannable spannable = new SpannableString(labyrinthString);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.RED);
        spannable.setSpan(colorSpan, 76, 77, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        labyrinthTextView.setText(spannable);
    }

    //Accelerometer --------------------------------------------------------------------------------

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensor = event.sensor.getType();
        float [] values = event.values;

        synchronized (this){
            //Accelerometer
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                System.arraycopy(event.values, 0, accelerometerReading,
                        0, accelerometerReading.length);
                //Magnetic field
            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                System.arraycopy(event.values, 0, magnetometerReading,
                        0, magnetometerReading.length);

            }
        }
    }

    // Compute the three orientation angles based on the most recent readings from
    // the device's accelerometer and magnetometer.
    public void updateOrientationAngles() {
        // Update rotation matrix, which is needed to update orientation angles.
        SensorManager.getRotationMatrix(rotationMatrix, null,
                accelerometerReading, magnetometerReading);

        // "rotationMatrix" now has up-to-date information.

        SensorManager.getOrientation(rotationMatrix, orientationAngles);

        // "orientationAngles" now has up-to-date information.
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get updates from the accelerometer and magnetometer at a constant rate.
        // To make batch operations more efficient and reduce power consumption,
        // provide support for delaying updates to the application.
        //
        // In this example, the sensor reporting delay is small enough such that
        // the application receives an update before the system checks the sensor
        // readings again.
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
        Sensor magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magneticField != null) {
            sensorManager.registerListener(this, magneticField,
                    SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener((SensorEventListener) this);
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Don't receive any more updates from either sensor.
        sensorManager.unregisterListener(this);
    }
    //Initialisation -------------------------------------------------------------------------------
    private void setup(){
        ball = new Ball(BALL_STARTING_X, BALL_STARTING_Y);
        labyrinth = new Labyrinth();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }
    private void setupView(){
        labyrinthTextView = (TextView) this.findViewById(R.id.labyrinthText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labyrinth_game);

        setup();
        setupView();

        displayLabyrinth();
        handler.postDelayed(runnable, 100);
    }
}