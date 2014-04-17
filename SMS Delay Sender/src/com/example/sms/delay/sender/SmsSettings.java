package com.example.sms.delay.sender;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
@TargetApi(9)
public class SmsSettings extends Activity {
	EditText pass1, pass2;
	Button set_pass, reset, done;
	Boolean security = false;

	SharedPreferences.Editor prefs;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);

		pass1 = (EditText) findViewById(R.id.abort_password);
		pass2 = (EditText) findViewById(R.id.abort_password_repeat);
		set_pass = (Button) findViewById(R.id.set_password);
		reset = (Button) findViewById(R.id.reset_button);
		done = (Button) findViewById(R.id.setting_done);

		inflateView();

		set_pass.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				prefs = getSharedPreferences(
						getApplicationContext().getPackageName(), MODE_PRIVATE)
						.edit();
				String p1 = pass1.getText().toString();
				String p2 = pass2.getText().toString();

				if (p1.equals(p2)) {
					String password;
					password = p1;
					if (security) {
						prefs.putString("password", getToken(password))
								.commit();
						Toast.makeText(getApplicationContext(), "Password set",
								Toast.LENGTH_SHORT).show();
						pass1.setText("");
						pass2.setText("");
					}
				}
			}
		});

		reset.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (security) {
					prefs = getSharedPreferences(
							getApplicationContext().getPackageName(),
							MODE_PRIVATE).edit();
					String s = "";
					prefs.putString("password", s).apply();
				}
			}
		});

		done.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
		stopService(getIntent());
	}

	public void inflateView() {
		// Initialize a new dialog
		final Dialog dialog = new Dialog(this);

		// Set the dialog to modal (not cancelable)
		dialog.setCancelable(false);

		// Set the contents of the dialog
		dialog.setContentView(R.layout.security);
		dialog.setTitle("Unlock");

		// Get the stop button
		Button ok = (Button) dialog.findViewById(R.id.varify_button);
		ok.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
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
						security = true;
						dialog.dismiss();
					}
				}
			}
		});
		// Show the dialog
		dialog.show();
	}

	public String getToken(String passPhrase) {
		String token = PasswordToken.makeDigest(passPhrase);
		return token;
	}
}
