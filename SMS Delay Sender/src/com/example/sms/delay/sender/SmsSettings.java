package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SmsSettings extends Activity {
	EditText pass1;
	EditText pass2;
	String password;
	Button set_pass;
	Button reset;

//	private SharedPreferences.Editor prefs = getPreferences(MODE_PRIVATE)
//			.edit();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);

		pass1 = (EditText) findViewById(R.id.abort_password);
		pass2 = (EditText) findViewById(R.id.abort_password_repeat);
		set_pass = (Button) findViewById(R.id.set_password);
		reset = (Button) findViewById(R.id.reset_button);

		set_pass.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (pass1 == pass2) {
					password = pass1.getText().toString();
//					prefs.putString("password", getToken());
				}
			}
		});

		reset.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String s = null;
//				prefs.putString("password", s).apply();
			}
		});
		stopService(getIntent());
	}

	public String getToken() {
		String token = PasswordToken.makeDigest(password);
		return token;
	}
}
