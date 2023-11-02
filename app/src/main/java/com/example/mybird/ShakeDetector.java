import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.widget.Switch;

public class ShakeDetector implements SensorEventListener {
    private static final int SHAKE_THRESHOLD = 800; // Adjust this value as needed
    private long lastShakeTime;
    private Vibrator vibrator;
    private Switch shakeSwitch;

    public ShakeDetector(Context context, Switch shakeSwitch) {
        this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        this.shakeSwitch = shakeSwitch;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (shakeSwitch.isChecked()) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                long currentTime = System.currentTimeMillis();
                if ((currentTime - lastShakeTime) > 1000) {
                    float x = event.values[0];
                    float y = event.values[1];
                    float z = event.values[2];

                    double acceleration = Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;
                    if (acceleration > SHAKE_THRESHOLD) {
                        lastShakeTime = currentTime;
                        vibrateDevice();
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void registerSensor(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void unregisterSensor(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
    }

    private void vibrateDevice() {
        if (vibrator != null) {
            vibrator.vibrate(500);
        }
    }
}
