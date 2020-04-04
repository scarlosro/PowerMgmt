package mx.tec.powermgmt

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var powerStatus: TextView? = null


    private val plugInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {

            val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        }
    }
    private var batteryTxt: TextView? = null
    private val mBatInfoReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {



            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)



            batteryTxt!!.text = "$level%"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myContext = applicationContext
        setContentView(R.layout.activity_main)



        
        batteryTxt = findViewById<View>(R.id.batteryTxt) as TextView
        powerStatus = findViewById<View>(R.id.powerStatus) as TextView



        this.registerReceiver(mBatInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        this.registerReceiver(plugInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
}