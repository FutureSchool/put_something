package com.example.sms.delay.sender;

import android.os.Handler;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LockWndow extends Activity {

	Button help;
	String address, name, phoneNo, sms, recipients;
	Boolean checkBox;
	AudioManager myAudio;
	int count = 0;
	Alarm alarm = null;
	MyLocationListener mlocListener;

	@TargetApi(9)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lock_window);

		/* Use the LocationManager class to obtain GPS locations */

		LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				mlocListener);

		help = (Button) findViewById(R.id.help_button);
		Bundle extras = getIntent().getExtras();

		ParametersToPass parameters = (ParametersToPass) extras
				.getSerializable("parameters");

		name = parameters.usersName;
		address = parameters.usersAddress;
		phoneNo = parameters.helpSmsNumber;
		recipients = parameters.helpEmailAddress;
		checkBox = parameters.earphoneCheck;
		//
		// SharedPreferences.Editor prefs = getPreferences(MODE_PRIVATE).edit();
		// prefs.putString("password", getToken()).apply();
		//
		// SharedPreferences prefs2 = getPreferences(MODE_PRIVATE);
		// String test;
		// test = prefs2.getString("password", "no password");
		// Toast.makeText(getApplicationContext(), test,
		// Toast.LENGTH_LONG).show();

		help.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startAlarm();
			}
		});

		if (checkBox == true) {
			earphone();
		}
	}

	public void startAlarm() {
		if (alarm == null) {
			alarm = new Alarm();
		}
		alarm.panic(true, getApplicationContext());
		dispatchSms(phoneNo, createSms(address, name));
		inflateView();

		// SendEmail mail = new SendEmail();
		// mail.sendEmail(recipients,
		// //subject
		// "hlep",
		// //text
		// "me");
	}

	public void inflateView() {
		// Initialize a new dialog
		final Dialog dialog = new Dialog(this);

		// Set the dialog to modal (not cancelable)
		dialog.setCancelable(false);

		// Set the contents of the dialog
		dialog.setContentView(R.layout.pop_up_pass);
		dialog.setTitle("Unlock");

		// Get the stop button
		Button StopAlarmButton = (Button) dialog
				.findViewById(R.id.stop_alarm_button);

		// Set the onclick listener of the stop alarm button
		StopAlarmButton.setOnClickListener(new OnClickListener() {

			/**
			 * The onClick will be called when user clicks on Stop alarm button
			 */
			public void onClick(View v) {
				// initialize the password field
				final EditText passField = (EditText) dialog
						.findViewById(R.id.password_given);
				String pass;
				pass = passField.getText().toString();

				// only if password correct.
				if (pass.matches("")) {
					Toast.makeText(getApplicationContext(),
							"Please enter the password.", Toast.LENGTH_SHORT)
							.show();
				} else {
					SharedPreferences prefs = getSharedPreferences(
							getApplicationContext().getPackageName(),
							MODE_PRIVATE);
					// ... GET token using the shared preferences

					String token1 = prefs.getString("password", null);

					if (PasswordToken.validate(pass, token1)) {
						alarm.panic(false, getApplicationContext());

						dialog.dismiss();
					} else {
						if (count == 3) {
							dialog.dismiss();

							Handler handler = new Handler();
							handler.postDelayed(new Runnable() {
								public void run() {
									startAlarm();
								}
							}, 20000);

						} else {
							passField.setText("");
							Toast.makeText(getApplicationContext(),
									"Invalid password please try again.",
									Toast.LENGTH_SHORT).show();
							count++;
						}
					}
				}
			}
		});

		// Show the dialog
		dialog.show();
	}

	@SuppressWarnings("deprecation")
	public void earphone() {
		myAudio = (AudioManager) getApplicationContext().getSystemService(
				AUDIO_SERVICE);

		if (!myAudio.isWiredHeadsetOn()) {
			alarm = new Alarm();
			alarm.panic(true, getApplicationContext());
			dispatchSms(phoneNo, createSms(address, name));
			inflateView();
		}
	}

	public String createSms(String address, String name) {
		String smsString;
		smsString = name + "\n" + address;
		return smsString;
	}

	public void dispatchSms(String No, String message) {
		Sms s = new Sms();
//		String un = getString(R.id.users_name);
//		String uadd = getString(R.id.users_address);
//		String ucont = getString(R.id.users_address)
//				+ " the phone number that you have been contacted by";
		
		
		try {
			Location loc = mlocListener.phoneLocation;
			String coord = Double.toString(loc.getLatitude()) + Double.toString(loc.getLongitude());

			s.SendSMS(No, name, coord, address, ".", getApplicationContext());
			
		} catch (Exception e) {
			s.SendSMS(No, name, "location unknown", address, ".", getApplicationContext());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.global_menu, menu);
		return true;
	}
}
