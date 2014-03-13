package com.example.sms.delay.sender;

import android.telephony.SmsManager;
import android.widget.Toast;

public class Sms {
	public void SendSMS(String phoneNo, String sms) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null, sms, null, null);
			Toast.makeText(null, "SMS Sent!", Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(null, "SMS faild, please try again later!",
					Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
}
