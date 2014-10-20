package com.example.buwfc_test;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class BUWFC_Page1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_buwfc__page1);
		
		Button addEvent = (Button)findViewById(R.id.addEvent);
		addEvent.setOnClickListener(new view.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(BUWFC_Page1.this, addEvent.class);
				startActivity(i);
				
			}
		})
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.buwfc__page1, menu);
		return true;
	}

}
