package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

public class Alarm extends Activity{
	private MediaPlayer mp;

	public void panic(Boolean alarm,Context context) {
		if (alarm == true) {
			int resId;
			resId = R.raw.car_alarm;
			mp = MediaPlayer.create(context, resId);
			mp.setLooping(true);
			mp.start();
		} else {
			stopAl();
		}
	}

	public void stopAl() {
		mp.setLooping(false);
		mp.stop();
		mp.release();
	}
}
