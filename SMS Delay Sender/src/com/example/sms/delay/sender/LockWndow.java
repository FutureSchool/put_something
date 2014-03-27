package com.example.sms.delay.sender;

import android.media.AudioManager;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LockWndow extends Activity {

	Button help;
	String address, name, phoneNo, sms, recipients;
	Alarm alarm = new Alarm();
	AudioManager myAudio;

	@SuppressWarnings("deprecation")
	@TargetApi(9)
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
		phoneNo = parameters.helpSmsNumber;
		recipients = parameters.helpEmailAddress;

		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

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
		});
		myAudio = (AudioManager)getApplicationContext().getSystemService(AUDIO_SERVICE);
		
		if (!myAudio.isWiredHeadsetOn()) {
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
							"Please enter the password.", Toast.LENGTH_LONG)
							.show();
				} else {
					SharedPreferences prefs = getPreferences(MODE_PRIVATE);
					// ... GET token using the shared preferences

					String token1 = prefs.getString("password", null);

					if (PasswordToken.validate(pass, token1)) {
						alarm.panic(false, getApplicationContext());

						dialog.dismiss();
					} else {
						Toast.makeText(getApplicationContext(),
								"Invalid password please try again.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
		});

		// Show the dialog
		dialog.show();
	}

	public String getToken() {
		String testpass = "hello";
		String token = PasswordToken.makeDigest(testpass);
		return token;
	}

	public String createSms(String address, String name) {
		String smsString;
		smsString = name + "\n" + address;
		return smsString;
	}

	public void dispatchSms(String No, String message) {
		Sms s = new Sms();
		s.SendSMS(No, message, getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.global_menu, menu);
		return true;
	}
}
