package com.socialoauthlinkedin;

import com.socialoauthlinkedin.R;
import com.socialoauthlinkedin.linkedin.DialogListener;
import com.socialoauthlinkedin.linkedin.SocialAuthAdapter;
import com.socialoauthlinkedin.linkedin.SocialAuthAdapter.SocialProvider;
import com.socialoauthlinkedin.linkedin.SocialAuthError;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SocialOauthLinkedin extends Activity {

	private SocialAuthAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView textview = (TextView)findViewById(R.id.text);
		textview.setText("Welcome to SocialAuth Demo. We are sharing " + 
				"text SocialAuth Android by share button.");
		
		// Single button
		Button share = (Button)findViewById(R.id.sharebutton);
		share.setText("Share");
		share.setTextColor(Color.WHITE);
		//	    share.setBackgroundResource(R.drawable.button_gradient);
		adapter = new SocialAuthAdapter(new ResponseListener());
		adapter.addProvider(SocialProvider.FACEBOOK, R.drawable.facebook);  
		adapter.addProvider(SocialProvider.TWITTER, R.drawable.twitter);
		adapter.addProvider(SocialProvider.LINKEDIN, R.drawable.linkedin);
		adapter.addProvider(SocialProvider.MYSPACE, R.drawable.myspace);
		adapter.enable(share);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


	private final class ResponseListener implements DialogListener {

		@Override
		public void onCancel() {
		}

		@Override
		public void onComplete(Bundle arg0) {
			Log.d("ShareButton" , "Authentication Successful");
			adapter.updateStatus("Sample application post.."); 
		}

		@Override
		public void onError(SocialAuthError e) {
			e.printStackTrace();
			Log.d("ERROR" , "Authentication Error >> ");
		}

		@Override
		public void onBack() {
			
		}

		@Override
		public void onPost() {
			Toast.makeText(getApplicationContext(), "Message Posted....", Toast.LENGTH_LONG).show();
		}

	}

}
