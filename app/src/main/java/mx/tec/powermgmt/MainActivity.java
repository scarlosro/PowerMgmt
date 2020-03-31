package mx.tec.powermgmt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView powerStatus;

    private BroadcastReceiver plugInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

            if (plugged == BatteryManager.BATTERY_PLUGGED_AC)
                powerStatus.setText("Plugged to AC");
            else if (plugged == BatteryManager.BATTERY_PLUGGED_USB)
                powerStatus.setText("Plugged to USB");
            else if (plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS)
                powerStatus.setText("Plugged to Wireless Charger");
            else
                powerStatus.setText("Not plugged");

        }
    };


    private TextView batteryTxt;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            batteryTxt.setText(String.valueOf(level) + "%");


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context myContext = getApplicationContext();
        setContentView(R.layout.activity_main);
        
        batteryTxt = (TextView) this.findViewById(R.id.batteryTxt);
        powerStatus = (TextView) this.findViewById(R.id.powerStatus);

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        this.registerReceiver(this.plugInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

}
