package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		buttonSend = (Button) findViewById(R.id.help_button);
		textPhoneNo = (EditText) findViewById(R.id.contact_numbers_sms);
		

		
		ParametersToPass par= new ParametersToPass();

		par.usersName = findViewById(R.id.users_name).toString();
		par.usersAddress = findViewById(R.id.users_address).toString();
		par.helpPhoneNumber = findViewById(R.id.contact_numbers_call).toString();
		par.helpEmailAddress = findViewById(R.id.contact_emails).toString();
		par.helpSmsNumber= findViewById(R.id.contact_numbers_sms).toString();
		
		
		if (par.usersName.length() != 0 && par.usersAddress.length() != 0) {
			
			
			if (par.helpPhoneNumber.length() != 0 && 
					par.helpEmailAddress.length() != 0 && 
					par.helpSmsNumber.length() != 0) {
				Toast toast = Toast.makeText(getBaseContext(),
						"Please fill atleast one\n" +
						"of the contact fields,\n" +
						"so that the app can contact\n" +
						"people in an emergency", Toast.LENGTH_LONG);
				toast.show();

			} else {
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

		
//		textSMS = (EditText) findViewById(R.id.editTextSMS);

//		buttonSend.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//
////				String phoneNo = textPhoneNo.getText().toString();
////				String sms = textSMS.getText().toString();
//				
//				String phoneNo = textPhoneNo.getText().toString();
//				String sms = textSMS.getText().toString();
//
//	
//				
//				Sms s = new Sms();
//				s.SendSMS(phoneNo,sms);
//			}
//		});

	}
}
