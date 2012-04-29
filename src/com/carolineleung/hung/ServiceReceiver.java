package com.carolineleung.hung;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * BroadcastReceiver of phone call states.
 * 
 * @author carolineleung
 */
public class ServiceReceiver extends BroadcastReceiver {

	private static final int VIBRATE_TIME_MS = 1000;

	@Override
	public void onReceive(Context context, Intent intent) {

		String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
			handleCallEnded(context);

		} else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
			// Toast.makeText(context, "Call state idle", Toast.LENGTH_LONG).show();

		} else if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
			// Toast.makeText(context, "Call state ringing", Toast.LENGTH_LONG).show();
		}

	}

	private void handleCallEnded(Context context) {
		Toast.makeText(context, "Call has hung up", Toast.LENGTH_LONG).show();
		playNotification(context);
		vibrate(context);
	}

	private void playNotification(Context context) {
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone r = RingtoneManager.getRingtone(context, notification);
		r.play();
	}

	private void vibrate(Context context) {
		Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		vibe.vibrate(VIBRATE_TIME_MS); // time in ms
	}
}