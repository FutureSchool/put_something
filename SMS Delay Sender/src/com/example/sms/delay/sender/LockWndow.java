package com.example.sms.delay.sender;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LockWndow extends Activity {
	// 1. In your screen, there's two options: Emergency alarm and Deactivate
	// alarm.
	// For immediate alarm, the user clicks the Emergency button. Start with
	// that one.
	// It's the most important part of the app and it should work flawless.
	// You can work on it without Chandran's screen, just hard-code the five
	// string fields:
	// Name, Address, SMSNumber, EmailAddress, PhoneNumber. After Chandran's
	// first step is ready,
	// you can use "getExtras" to get the correct data.
	// Try to use separate functions for all the steps: Create SMS text, send
	// SMS, Create Email text,
	// Send Email, Call phone, Sound Alarm.

	Button help;
	EditText textPhoneNo;
	EditText address;
	EditText name;
	String phoneNo;
	String sms;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lock_window);

		help = (Button) findViewById(R.id.help_button);
		textPhoneNo = (EditText) findViewById(R.id.contact_numbers_sms);
		address = (EditText) findViewById(R.id.users_address);
		name = (EditText) findViewById(R.id.users_name);

		phoneNo = textPhoneNo.getText().toString();
//		
//		phoneNo = "1234567890";
//		sms = "Hlep me!";
	
		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dispatchSms(phoneNo,createSms(address,name));
			}
		});
	}
	
	public String createSms(EditText address, EditText name ){
		String smsString;
		smsString = name.getText().toString() + "\n" + address.getText().toString();
		return smsString;
		}
	
	public void dispatchSms (String No, String message){
		Sms s = new Sms();
		s.SendSMS(No, message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lock_wndow, menu);
		return true;
	}
}