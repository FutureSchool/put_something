package com.example.sms.delay.sender;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

public class Sms {
	// public void SendSMS(String phoneNo, String sms, Context context) {
	// try {
	// SmsManager smsManager = SmsManager.getDefault();
	// smsManager.sendTextMessage(phoneNo, null, sms, null, null);
	// Toast.makeText(context, "SMS Sent!", Toast.LENGTH_LONG).show();
	// } catch (Exception e) {
	// Toast.makeText(context, "SMS faild, please try again later!",(int) 0.5);
	// e.printStackTrace();
	// }
	//
	public void SendSMS(String phoneNo, String un, String coord, String uadd,
			String ucont, Context context) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			String message;
			message = un + "could be in danger" + un + "'s coordinates are"
					+ coord + ". Pleae try to contact/help" + un + "\n" + un + "'s address is" + uadd;
			smsManager.sendTextMessage(phoneNo, null, message, null, null);
			
			Toast.makeText(context, "SMS Sent!", Toast.LENGTH_LONG).show();
		
		} catch (Exception e) {
			
			Toast.makeText(context, "SMS faild, please try again later!",
					(int) 0.5).show();
			
			
			e.printStackTrace();
		}
		 	
	}

}
