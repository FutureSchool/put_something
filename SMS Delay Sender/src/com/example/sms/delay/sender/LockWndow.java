package com.example.sms.delay.sender;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
		phoneNo = parameters.helpSmsNumber;
		recipients = parameters.helpEmailAddress;

		help.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Alarm alarm = new Alarm();
				alarm.panic(true, getApplicationContext());
				dispatchSms(phoneNo, createSms(address, name));
				inflateView();

				// SendEmail mail = new SendEmail();
				// mail.sendEmail(recipients,
				// //subject
				// "hlep",
				// //text
				// "me");
			}
		});
	}

	@SuppressWarnings("deprecation")
	public void inflateView() {
		// Initialize a new dialog
		final Dialog dialog = new Dialog(this);

		// Set the dialog to modal (not cancelable)
		dialog.setCancelable(false);
		
		// Set the contents of the dialog
        dialog.setContentView(R.layout.pop_up_pass);
        dialog.setTitle("Unlock");
        
        // Get the stop button
        Button StopAlarmButton=(Button)dialog.findViewById(R.id.stop_alarm_button);

        // Set the onclick listener of the stop alarm button
        StopAlarmButton.setOnClickListener(new OnClickListener() {
        	
        	/**
        	 * The onClick will be called when user clicks on Stop alarm button
        	 */
        	public void onClick(View v) {
                // initialize the password field
                final EditText Password=(EditText)v.findViewById(R.id.password_given);

                //TODO: Test for password here and stop the alarm and dismiss only if password correct.
        		dialog.dismiss();
        	}
        });

        // Show the dialog
        dialog.show();
	}

	public String createSms(String address, String name) {
		String smsString;
		smsString = name + "\n" + address;
		return smsString;
	}

	public void dispatchSms(String No, String message) {
		Sms s = new Sms();
		s.SendSMS(No, message, getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lock_wndow, menu);
		return true;
	}
}