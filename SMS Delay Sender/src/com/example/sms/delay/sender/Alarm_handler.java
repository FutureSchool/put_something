package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class Alarm_handler extends Activity {

	Button stop;
	EditText passField;
	String password;
	String testpass;
	
	private SharedPreferences.Editor prefs = getPreferences(MODE_PRIVATE)
			.edit();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pop_up_pass);

		stop = (Button) findViewById(R.id.stop_alarm_button);
		passField = (EditText) findViewById(R.id.password_given);
		
//		test for password
//		please remove later !!!!!!!!!
		testpass = "hello";
		prefs.putString("password", getToken());
		
		stop.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (passField == null) {
					Toast.makeText(null, "Please enter the password.",
							Toast.LENGTH_LONG).show();
				} else {
					SharedPreferences prefs = getPreferences(MODE_PRIVATE);
					password = passField.getText().toString();
					// ... GET token using the shared preferences

					String token1 = prefs.getString("password", null);

					if (PasswordToken.validate(password, token1)) {
						Alarm alarm = new Alarm();
						alarm.panic(false,getApplicationContext());
					} else {
						Toast.makeText(null,
								"Invalid password please try again.",
								Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}
//	test for password delete later !!!!!

	public String getToken() {
		String token = PasswordToken.makeDigest(testpass);
		return token;
	}
}
