package com.carolineleung.hung;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;

/**
 * BroadcastReceiver of phone call states.
 * 
 * @author carolineleung
 */
public class PhoneStateUpdateReceiver extends BroadcastReceiver {

	private static final int VIBRATE_TIME_MS = 800;

	@Override
	public void onReceive(Context context, Intent intent) {
		String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
			onCallEnded(context);
		}
	}

	private void onCallEnded(Context context) {
		playNotification(context);
		vibrate(context);
	}

	private void playNotification(Context context) {
		if (isNotificationEnable(context)) {
			Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(context, notification);
			r.play();
		}
	}

	private void vibrate(Context context) {
		if (isVibrationEnable(context)) {
			Vibrator vibrationSvc = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			vibrationSvc.vibrate(VIBRATE_TIME_MS); // time in ms
		}
	}

	private SharedPreferences getSharedPrefs(Context context) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		return prefs;
	}

	private boolean isVibrationEnable(Context context) {
		return isSettingEnable(context, R.string.vibration_enable);
	}

	private boolean isNotificationEnable(Context context) {
		return isSettingEnable(context, R.string.notification_enable);
	}

	private boolean isSettingEnable(Context context, int settingId) {
		SharedPreferences prefs = getSharedPrefs(context);
		boolean vibrationEnabled = prefs.getBoolean(context.getString(settingId), true);
		return vibrationEnabled;
	}

}