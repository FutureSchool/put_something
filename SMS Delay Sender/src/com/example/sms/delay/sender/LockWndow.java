package com.example.sms.delay.sender;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LockWndow extends Activity {

	Button help;
	String address;
	String name;
	String phoneNo;
	String sms;
	private MediaPlayer mp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lock_window);

		help = (Button) findViewById(R.id.help_button);

		Bundle extras = getIntent().getExtras();

		ParametersToPass parameters = (ParametersToPass) extras
				.getSerializable("parameters");

		name = parameters.usersName;
		address = parameters.usersAddress;
		phoneNo = parameters.helpPhoneNumber;

		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dispatchSms(phoneNo, createSms(address, name));
			}
		});
	}

	public String createSms(String address, String name) {
		String smsString;
		smsString = name + "\n" + address;
		return smsString;
	}

	public void dispatchSms(String No, String message) {
		Sms s = new Sms();
		s.SendSMS(No, message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lock_wndow, menu);
		return true;
	}
	
	public void alarm(){
	int resId;
	resId = R.raw.car_alarm;
	mp = MediaPlayer.create(this, resId);
	mp.start();
	}
}