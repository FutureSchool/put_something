package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class Alarm extends Activity {

	AudioManager mAudioManager;
	int originalVolume;

	public MediaPlayer mp;

	public void panic(Boolean alarm, Context context) {
		if (alarm == true) {
		
			int resId;
			resId = R.raw.car_alarm;
			mp = MediaPlayer.create(context, resId);
			mp.setLooping(true);

			if (mp.isPlaying()) {
				return;
			}

//			mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
//			originalVolume = mAudioManager
//					.getStreamVolume(AudioManager.STREAM_MUSIC);
//			mAudioManager
//					.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager
//							.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
			mp.start();
		} else {
			if (mp != null) {
				if (mp.isPlaying())
					mp.stop();
				mp.release();
//				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
//						originalVolume, 0);
			}
		}
	}
}
