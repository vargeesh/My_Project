package com.myapp.cktscore;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Toss extends Activity {
	public String message, message1;
	MySQLiteClass db = new MySQLiteClass(this);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("CKTSCORE");
		getActionBar().setBackgroundDrawable(getWallpaper());
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.toss);
		Bundle bundle = getIntent().getExtras();
		message = bundle.getString("Team1");
		message1 = bundle.getString("Team2");
		TextView teamName = (TextView) findViewById(R.id.teamA);
		teamName.setText(message + " Will Choose an");
	}

	public void btnBatting(View view) {

		Intent data = new Intent(getBaseContext(), ScoreDetails.class);
		data.putExtra("Options", "Batting");
		data.putExtra("team1", message.toString());
		data.putExtra("team2", message1.toString());
		db.addTeam(message, message1);
		startActivity(data);
	}

	public void btnBowling(View view) {
		Intent intent = new Intent(getBaseContext(), ScoreDetails.class);
		intent.putExtra("team1", message.toString());
		intent.putExtra("team2", message1.toString());
		db.addTeam(message1,message);
		startActivity(intent);
	}
}
