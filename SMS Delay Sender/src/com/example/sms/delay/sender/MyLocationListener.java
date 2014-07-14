package com.example.sms.delay.sender;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListener implements LocationListener

{

	Location phoneLocation;
	
	public void onLocationChanged(Location loc)

	{
		
		phoneLocation = loc;


	}

	public void onProviderDisabled(String provider)

	{

		// Toast.makeText(getApplicationContext(), "Gps disabled",
		// Toast.LENGTH_SHORT).show();

	}

	public void onProviderEnabled(String provider)

	{

		// Toast.makeText( getApplicationContext(), "GPS Enabled",

		// Toast.LENGTH_SHORT).show();

	}

	public void onStatusChanged(String provider, int status, Bundle extras)

	{

	}
}
