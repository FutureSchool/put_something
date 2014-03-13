package com.example.sms.delay.sender;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class LockWndow extends Activity {

	Button help;
	String address, name, phoneNo, sms, recipients;

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
		phoneNo = parameters.helpPhoneNumber;
		recipients = parameters.helpEmailAddress;

		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Alarm alarm = new Alarm();
				alarm.panic(true);
				inflateView();
				dispatchSms(phoneNo, createSms(address, name));
			
				SendEmail mail = new SendEmail();
				 mail.sendEmail(recipients,
						 //subject
						 "hlep",
						 //text
						 "me");
			}
		});
	}

	public void inflateView() {
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.pop_up_pass,
				(ViewGroup) findViewById(R.id.popup_element));
		PopupWindow pw = new PopupWindow(layout, 100, 100, true);
		pw.showAtLocation(layout, Gravity.BOTTOM, 0, 0);

	}

	public String createSms(String address, String name) {
		String smsString;
		smsString = name + "\n" + address;
		return smsString;
	}

	public void dispatchSms(String No, String message) {
		Sms s = new Sms();
		s.SendSMS(No, message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lock_wndow, menu);
		return true;
	}
}
