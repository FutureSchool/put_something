package com.example.sms.delay.sender;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class SendEmail extends Activity{
	protected void sendEmail(String recipients, String subject, String text) {

		Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
		// prompts email clients only
		email.setType("message/rfc822");

		email.putExtra(Intent.EXTRA_EMAIL, recipients);
		email.putExtra(Intent.EXTRA_SUBJECT, subject);
		email.putExtra(Intent.EXTRA_TEXT, text);

		try {
			// the user can choose the email client
			startActivity(Intent.createChooser(email,
					"Choose an email client from..."));

		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(null, "No email client installed.",
					Toast.LENGTH_LONG).show();
		}
	}
}
