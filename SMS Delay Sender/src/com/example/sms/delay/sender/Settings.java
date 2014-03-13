package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class Settings extends Activity {
	EditText pass1;
	EditText pass2;
	String password;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);

		SharedPreferences prefs = this.getSharedPreferences(
				"com.example.sms.delay.sender", Context.MODE_PRIVATE);

		pass1 = (EditText) findViewById(R.id.abort_password);
		pass2 = (EditText) findViewById(R.id.abort_password_repeat);

		if (pass1 == pass2) {
			password = pass1.getText().toString();
			prefs.edit().putString(getToken(), password);
		}
	}

	public String getToken() {
		String token = PasswordToken.makeDigest(password);
		return token;
	}
}
