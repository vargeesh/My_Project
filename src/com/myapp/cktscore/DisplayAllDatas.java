package com.myapp.cktscore;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DisplayAllDatas extends Activity {
	MySQLiteClass db = new MySQLiteClass(this);
	Cursor c;
	String team_name, overs, Score, wicket;
	TextView id, Team_name, Overs, Scores, Wicket;
	LinearLayout llayout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("CKTSCORE");
		getActionBar().setBackgroundDrawable(getWallpaper());
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.displayalldatas);
		llayout = (LinearLayout) findViewById(R.id.lLayout);
		DisplayDB();
	}

	public void DisplayDB() {
		String Database_name = MySQLiteClass.DATABASE_TABLE;
		int ScoreCountNO = db.getProfilesCount(Database_name);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		for (int i = ScoreCountNO; i >= 0; i--) {
			Team_name = new TextView(this);
			Overs = new TextView(this);
			Scores = new TextView(this);
			Wicket = new TextView(this);
			Team_name.setLayoutParams(params);
			Overs.setLayoutParams(params);
			Scores.setLayoutParams(params);
			Wicket.setLayoutParams(params);
			
			c = db.getScore(ScoreCountNO, Database_name);
			team_name = c.getString(1);
			overs = c.getString(2);
			Score = c.getString(3);
			wicket = c.getString(4);

			
			Team_name.setText(team_name);
			Overs.setText(overs);
			Scores.setText(Score);
			Wicket.setText(wicket + "\n");
		}
		llayout.addView(Team_name);
		llayout.addView(Overs);
		llayout.addView(Scores);
		llayout.addView(Wicket);
	}
}
