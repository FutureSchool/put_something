package com.example.sms.delay.sender;

import android.app.Activity;
import android.media.MediaPlayer;

public class Alarm extends Activity{
	private MediaPlayer mp;

	public void panic(Boolean alarm) {
		if (alarm = true) {
			alarm();
		} else {
			stopAl();
		}
	}

	public void alarm() {
		int resId;
		resId = R.raw.car_alarm;
		mp = MediaPlayer.create(this, resId);
		mp.start();
	}

	public void stopAl() {
		mp.stop();
	}
}
