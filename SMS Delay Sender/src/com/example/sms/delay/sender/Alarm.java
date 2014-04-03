package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class Alarm extends Activity {

	final AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
	final int originalVolume = mAudioManager
			.getStreamVolume(AudioManager.STREAM_MUSIC);
	private MediaPlayer mp;

	public void panic(Boolean alarm, Context context) {
		if (alarm == true) {
			int resId;
			resId = R.raw.car_alarm;
			mp = MediaPlayer.create(context, resId);
			mp.setLooping(true);
			mAudioManager
					.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager
							.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
			mp.start();
		} else {
			stopAl();
		}
	}

	public void stopAl() {
		mp.setLooping(false);
		mp.stop();
		mp.release();
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
				originalVolume, 0);
	}
}
