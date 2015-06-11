package com.myapp.cktscore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MatchSummary extends Activity {

	TextView battingfirst, scoreruns, totalwicket, totalovers, battingsecond,
			scoreruns1, totalwicket1, totalovers1, matchs;
	TextView scoree, Wickett, Overr, scoree1, Wickett1, Overr1;
	String team_name, total_overs, total_score, team_name1, total_overs1,
			total_score1, wicket, wicket1;
	MySQLiteClass db = new MySQLiteClass(this);
	Cursor c, c1, c2;
	String Database_name;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("CKTSCORE");
		getActionBar().setBackgroundDrawable(getWallpaper());
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.matchsummary);
		matchs = (TextView) findViewById(R.id.match);
		scoree = (TextView) findViewById(R.id.Score);
		Wickett = (TextView) findViewById(R.id.Wicket);
		Overr = (TextView) findViewById(R.id.Over);
		scoree1 = (TextView) findViewById(R.id.Score1);
		Wickett1 = (TextView) findViewById(R.id.Wicket1);
		Overr1 = (TextView) findViewById(R.id.Over1);
		battingfirst = (TextView) findViewById(R.id.BattingFirst);
		scoreruns = (TextView) findViewById(R.id.ScoreRuns);
		totalwicket = (TextView) findViewById(R.id.TotalWicket);
		totalovers = (TextView) findViewById(R.id.TotalOver);
		battingsecond = (TextView) findViewById(R.id.BattingSecond);
		scoreruns1 = (TextView) findViewById(R.id.ScoreRuns1);
		totalwicket1 = (TextView) findViewById(R.id.TotalWicket1);
		totalovers1 = (TextView) findViewById(R.id.TotalOver1);
		displayDB();
	}

	public void displayDB() {
		Database_name = MySQLiteClass.DATABASE_TABLE;
		String Database_name1 = MySQLiteClass.DATABASE_TABLEC;
		int profile_counts = db.getProfilesCount(Database_name);
		int profile_count = db.getProfilesCount(Database_name1);
		c = db.getScore(profile_counts, Database_name);
		if (c.moveToFirst()) {
			team_name = c.getString(1);
			total_overs = c.getString(2);
			total_score = c.getString(3);
			wicket = c.getString(4);
			battingfirst.setText(team_name);
			scoreruns.setText(total_score);
			totalovers.setText(total_overs);
			totalwicket.setText(wicket);
		} else {
			Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
		}
		c1 = db.getScore(profile_count, Database_name1);
		if (c1.moveToFirst()) {
			team_name1 = c1.getString(1);
			total_overs1 = c1.getString(2);
			total_score1 = c1.getString(3);
			wicket1 = c1.getString(4);
			battingsecond.setText(team_name1);
			scoreruns1.setText(total_score1);
			totalovers1.setText(total_overs1);
			totalwicket1.setText(wicket1);
		} else {
			Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
		}
		db.close();

	}

	public void btnDelete(View view) {
		db.DeleteAll();
		Toast.makeText(getBaseContext(), "Deleted All the rows in database",
				Toast.LENGTH_SHORT).show();
	}

	public void btnDeleteSingle(View view) {
		int profile_counts = db.getProfilesCount(Database_name);
		db.DeleteSigle(profile_counts);
		Toast.makeText(getBaseContext(),
				"Deleted last updated row Successfully", Toast.LENGTH_SHORT)
				.show();
	}

	public void btnSelect(View view) {
		Intent intent = new Intent(getBaseContext(), DisplayAllDatas.class);
		startActivity(intent);
	}

	public void btnWhatsApp(View view) {
		if (isNetworkAvailable()) {
			PackageManager pm = getPackageManager();
			try {

				Intent waIntent = new Intent(Intent.ACTION_SEND);
				waIntent.setType("text/plain");
				// String text = "YOUR TEXT HERE";

				PackageInfo info = pm.getPackageInfo("com.whatsapp",
						PackageManager.GET_META_DATA);
				// Check if package exists or not. If not then code
				// in catch block will be called
				waIntent.setPackage("com.whatsapp");
				// waIntent.putExtra(Intent.EXTRA_TEXT,
				// matchs.getText().toString());
				waIntent.putExtra(Intent.EXTRA_TEXT, matchs.getText()
						.toString()
						+ "\n"
						+ "Batting First"
						+ ":"
						+ battingfirst.getText().toString()
						+ "\n"
						+ scoree.getText().toString()
						+ ":"
						+ scoreruns.getText().toString()
						+ "\n"
						+ Wickett.getText().toString()
						+ ":"
						+ totalwicket.getText().toString()
						+ "\n"
						+ Overr.getText().toString()
						+ ":"
						+ totalovers.getText().toString()
						+ "\n"
						+ "Batting Second"
						+ ":"
						+ battingsecond.getText().toString()
						+ "\n"
						+ scoree1.getText().toString()
						+ ":"
						+ scoreruns1.getText().toString()
						+ "\n"
						+ Wickett1.getText().toString()
						+ ":"
						+ totalwicket1.getText().toString()
						+ "\n"
						+ Overr1.getText().toString()
						+ ":"
						+ totalovers1.getText().toString());

				startActivity(Intent.createChooser(waIntent, "Share with"));

			} catch (NameNotFoundException e) {
				Toast.makeText(this, "WhatsApp not Installed",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getBaseContext(), "Network is Not Available",
					Toast.LENGTH_SHORT).show();
		}
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity == null) {
			return false;
		} else {
//			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info == null)
				return false;
			State network = info.getState();
			return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
		}
	}
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Closing Activity")
				.setMessage("Are you sure you want to close this activity?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}

						}).setNegativeButton("No", null).show();
	}
}
