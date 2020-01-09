package ir.amirhooshmand.silencekill.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.jaeger.library.StatusBarUtil;

import java.util.Calendar;

import ir.amirhooshmand.silencekill.R;
import ir.amirhooshmand.silencekill.receiver.RingerReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTransparent(MainActivity.this);
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_main);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            BroadcastReceiver receiver = new RingerReceiver();
            IntentFilter filter = new IntentFilter(
                    AudioManager.RINGER_MODE_CHANGED_ACTION);
            registerReceiver(receiver, filter);
        }*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    0);
        }

        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            Log.i("Here", "run");

            Calendar time = Calendar.getInstance();
            time.setTimeInMillis(System.currentTimeMillis());
            AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getApplicationContext(), RingerReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
            alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis() + (1 * 10 * 1000), pendingIntent);
        }

        NotificationManager n = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (n.isNotificationPolicyAccessGranted()) {

            } else {
                // Ask the user to grant access
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                startActivityForResult(intent, 0);
            }
        }
    }


}
