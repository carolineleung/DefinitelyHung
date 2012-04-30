package com.carolineleung.hung;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class HungPreferenceActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.hung_preferences);
	}

}