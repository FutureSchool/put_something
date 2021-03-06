package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class WatchME extends Activity {

	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;
	CheckBox earphone;
	AudioManager myAudio;

	public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// buttonSend = (Button) findViewById(R.id.help_button);
		// textPhoneNo = (EditText) findViewById(R.id.contact_numbers_sms);
		SharedPreferences list = getSharedPreferences(
				getApplicationContext().getPackageName(),
				MODE_PRIVATE);
		// SharedPreferences list = getSharedPreferences(PREFS_NAME,
		// MODE_PRIVATE);

		((EditText) findViewById(R.id.users_name)).setText(list.getString(
				"usersName", ""));
		((EditText) findViewById(R.id.users_address)).setText(list.getString(
				"usersAddress", ""));
		((EditText) findViewById(R.id.contact_numbers_call)).setText(list
				.getString("helpPhoneNumber", ""));
		((EditText) findViewById(R.id.contact_numbers_sms)).setText(list
				.getString("helpSmsNumber", ""));
		((EditText) findViewById(R.id.contact_emails)).setText(list.getString(
				"helpEmailAddress", ""));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.global_menu, menu);
		return true;
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// MenuInflater inflater = getMenuInflater();
	// inflater.inflate(R.menu.global_menu, menu);
	// return true;
	// }

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// action with ID action_refresh was selected
		case R.id.menu_settings:
//			Toast.makeText(this, "Option1", Toast.LENGTH_SHORT).show();
//			 return true;
			 Intent activity_1 = new Intent(this, SmsSettings.class);
//			 Intent activity_1 = new Intent(this, LockWndow.class);
			 startActivity(activity_1);

			 return true;
		}

		 return true;
	}

	public void startSecurity(View view) {

		if (check_settings() == true) {

			ParametersToPass par = new ParametersToPass();

			par.usersName = ((EditText) findViewById(R.id.users_name))
					.getText().toString();
			par.usersAddress = ((EditText) findViewById(R.id.users_address))
					.getText().toString();
			par.helpPhoneNumber = ((EditText) findViewById(R.id.contact_numbers_call))
					.getText().toString();
			par.helpSmsNumber = ((EditText) findViewById(R.id.contact_numbers_sms))
					.getText().toString();
			par.helpEmailAddress = ((EditText) findViewById(R.id.contact_emails))
					.getText().toString();

			earphone = (CheckBox) findViewById(R.id.checkBox1);
			Boolean isChecked = earphone.isChecked();
			if (isChecked == true) {
				par.earphoneCheck = true;
			} else {
				par.earphoneCheck = false;
			}
			if (par.usersName.length() != 0 && par.usersAddress.length() != 0) {

				if (par.helpPhoneNumber.length() == 0
						&& par.helpEmailAddress.length() == 0
						&& par.helpSmsNumber.length() == 0) {
					Toast toast = Toast.makeText(getBaseContext(),
							"Please fill atleast one\n"
									+ "of the contact fields,\n"
									+ "so that the app can contact\n"
									+ "people in an emergency",
							Toast.LENGTH_LONG);
					toast.show();

				} else {
					SharedPreferences list = getSharedPreferences(
							getApplicationContext().getPackageName(),
							MODE_PRIVATE);
					// SharedPreferences list = getSharedPreferences(PREFS_NAME,
					// MODE_PRIVATE);
					SharedPreferences.Editor editor = list.edit();
					editor.putString("usersName", par.usersName);
					editor.putString("usersAddress", par.usersAddress);
					editor.putString("helpPhoneNumber", par.helpPhoneNumber);
					editor.putString("helpEmailAddress", par.helpEmailAddress);
					editor.putString("helpSmsNumber", par.helpSmsNumber);

					// Commit the edits!
					editor.commit();

					Intent activity_2 = new Intent(this, LockWndow.class);
					// activity_2.putExtra(activityPath, R.id.start_path);

					activity_2.putExtra("parameters", par);
					startActivityForResult(activity_2, RESULT_OK);

				}
			} else {
				Toast toast = Toast
						.makeText(getBaseContext(),
								"Please enter your name and address",
								Toast.LENGTH_LONG);
				toast.show();
			}
		}
	}

	public Boolean check_settings() {
		 String password;
		
		 SharedPreferences prefs = getSharedPreferences(
					getApplicationContext().getPackageName(),
					MODE_PRIVATE);
		 password = prefs.getString("password", null);
		 if (password == "") {
			 Intent activity_1 = new Intent(this, SmsSettings.class);
			 startActivity(activity_1);
		 }
		return true;
	}
}
