package com.example.sms.delay.sender;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LockWndow extends Activity {

	Button help;
	Button stop;
	String address, name, phoneNo, sms, recipients;
	
	private MediaPlayer mp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lock_window);

		help = (Button) findViewById(R.id.help_button);
		stop = (Button) findViewById(R.id.stop_alarm_button);

		Bundle extras = getIntent().getExtras();

		ParametersToPass parameters = (ParametersToPass) extras
				.getSerializable("parameters");

		name = parameters.usersName;
		address = parameters.usersAddress;
		phoneNo = parameters.helpPhoneNumber;
		recipients = parameters.helpEmailAddress;
	
		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dispatchSms(phoneNo, createSms(address, name));
			}
		});
		stop.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				stopAl();
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

	public void alarm() {
		int resId;
		resId = R.raw.car_alarm;
		mp = MediaPlayer.create(this, resId);
		mp.start();
	}

	public void stopAl() {
		mp.stop();
	}
	
	 protected void sendEmail() {

	      Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
	      // prompts email clients only
	      email.setType("message/rfc822");

	      email.putExtra(Intent.EXTRA_EMAIL, recipients);
	      email.putExtra(Intent.EXTRA_SUBJECT, "Help");
	      email.putExtra(Intent.EXTRA_TEXT, "Help");

	      try {
		    // the user can choose the email client
	         startActivity(Intent.createChooser(email, "Choose an email client from..."));
	     
	      } catch (android.content.ActivityNotFoundException ex) {
	         Toast.makeText(LockWndow.this, "No email client installed.",
	        		 Toast.LENGTH_LONG).show();
	      }
	   }
}