package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;

	public static final String PREFS_NAME = "MyPrefsFile";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//
		// buttonSend = (Button) findViewById(R.id.help_button);
		// textPhoneNo = (EditText) findViewById(R.id.contact_numbers_sms);
		SharedPreferences list = getSharedPreferences(PREFS_NAME, 0);
		
		((EditText) findViewById(R.id.users_name)).setText(list.getString("usersName", ""));
		((EditText) findViewById(R.id.users_name)).setText(list.getString("usersAddress", ""));
		((EditText) findViewById(R.id.users_name)).setText(list.getString("helpPhoneNumber", ""));
		((EditText) findViewById(R.id.users_name)).setText(list.getString("helpSmsNumber", ""));
		((EditText) findViewById(R.id.users_name)).setText(list.getString("helpEmailAddress", ""));



		
	}

	public void startSecurity(View view) {

		ParametersToPass par = new ParametersToPass();

		par.usersName = ((EditText) findViewById(R.id.users_name)).getText()
				.toString();
		par.usersAddress = ((EditText) findViewById(R.id.users_address))
				.getText().toString();
		par.helpPhoneNumber = ((EditText) findViewById(R.id.contact_numbers_call))
				.getText().toString();
		par.helpSmsNumber = ((EditText) findViewById(R.id.contact_numbers_sms))
				.getText().toString();
		par.helpEmailAddress = ((EditText) findViewById(R.id.contact_emails))
				.getText().toString();

		if (par.usersName.length() != 0 && par.usersAddress.length() != 0) {

			if (par.helpPhoneNumber.length() == 0
					&& par.helpEmailAddress.length() == 0
					&& par.helpSmsNumber.length() == 0) {
				Toast toast = Toast.makeText(getBaseContext(),
						"Please fill atleast one\n"
								+ "of the contact fields,\n"
								+ "so that the app can contact\n"
								+ "people in an emergency", Toast.LENGTH_LONG);
				toast.show();

			} else {

				SharedPreferences list = getSharedPreferences(PREFS_NAME, 0);
				SharedPreferences.Editor editor = list.edit();
				editor.putString("usersName", par.usersName);
				editor.putString("usersAddress", par.usersAddress);
				editor.putString("helpPhoneNumber", par.helpPhoneNumber);
				editor.putString("helpEmailAddress0", par.helpEmailAddress);
				editor.putString("helpSmsNumber", par.helpSmsNumber);

				// Commit the edits!
				editor.commit();

				Intent activity_2 = new Intent(this, LockWndow.class);
				// activity_2.putExtra(activityPath, R.id.start_path);

				activity_2.putExtra("parameters", par);
				startActivityForResult(activity_2, RESULT_OK);

			}
		} else {
			Toast toast = Toast.makeText(getBaseContext(),
					"Please enter your name and address", Toast.LENGTH_LONG);
			toast.show();
		}

	}
}