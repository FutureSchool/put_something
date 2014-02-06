package com.example.sms.delay.sender;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
