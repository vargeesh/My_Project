package com.myapp.cktscore;

import java.text.DecimalFormat;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ChasingDetails extends Activity implements OnItemSelectedListener {

	TextView txtview, scoreCount, wktCount, oversCount, ballsCount, txtscore,
			txtTotalOvers, team, overs, score, targetCount,battingTeam;
	Button btnupdate, summary;
	Spinner spndrop, spinner;
	LinearLayout llayout, lscrollview;
	EditText edtscore, edtOvers, edtOver;
	public int Ball_count, Score_count, Wicket_Count;
	public double Overs_Count,total_overss;
	String total_overs, team_name, total_score, batting;
	int target;
	Cursor c,c1;
	static String Database_name,Database_name1;
	MySQLiteClass db = new MySQLiteClass(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setTitle("CKTSCORE");
		getActionBar().setBackgroundDrawable(getWallpaper());
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.chasingdetails);
		team = (TextView) findViewById(R.id.team);
		score = (TextView) findViewById(R.id.ScoreCount);
		overs = (TextView) findViewById(R.id.SceCount);
		spndrop = (Spinner) findViewById(R.id.spnList);
		scoreCount = (TextView) findViewById(R.id.ScoreCountt);
		wktCount = (TextView) findViewById(R.id.wktCount);
		oversCount = (TextView) findViewById(R.id.OversCount);
		ballsCount = (TextView) findViewById(R.id.ball_count);
		llayout = (LinearLayout) findViewById(R.id.otherscore);
		edtscore = (EditText) findViewById(R.id.edtOther);
		txtscore = (TextView) findViewById(R.id.txtOther);
		lscrollview = (LinearLayout) findViewById(R.id.layoutscrollview);
		txtTotalOvers = (TextView) findViewById(R.id.TotalOvers);
		btnupdate = (Button) findViewById(R.id.btnUpdate);
		targetCount = (TextView) findViewById(R.id.TargetCount);
		summary = (Button) findViewById(R.id.btnSummary);
		battingTeam = (TextView)findViewById(R.id.BattingTeam);
		Intent intent = getIntent();
		total_overss = intent.getDoubleExtra("Total_Overs", 0.0);

		ArrayAdapter<CharSequence> spnadapter = ArrayAdapter
				.createFromResource(this, R.array.item_array,
						R.layout.spinnerlayout);
		spnadapter.setDropDownViewResource(R.layout.spinnerlayout);
		spndrop.setAdapter(spnadapter);
		spndrop.setOnItemSelectedListener(this);
		displayDB();
	}

	public void displayDB() {
		Database_name = MySQLiteClass.DATABASE_TABLE;
		Database_name1= MySQLiteClass.TEAM_DETAILS;
		int profile_counts = db.getProfilesCount(Database_name);
		int profile_count = db.getProfilesCount(Database_name1);
		c = db.getScore(profile_counts, Database_name);
		if (c.moveToFirst()) {
			team_name = c.getString(1);
			total_overs = c.getString(2);
			total_score = c.getString(3);
			team.setText(team_name + " :");
			score.setText(total_score);
			overs.setText(String.valueOf(total_overss));
			target = Integer.parseInt(total_score) + 1;
			targetCount.setText(String.valueOf(target));
		} else{
			Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
		}
		c1 = db.getScore(profile_count, Database_name1);
		if(c1.moveToFirst()){
			batting = c1.getString(2);
			battingTeam.setText(batting);
		}
		else{
			Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
		}
		db.close();

	}

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		if ((spndrop.getSelectedItem().toString()).equals("No Ball")) {
			txtscore.setVisibility(View.VISIBLE);
			edtscore.setVisibility(View.VISIBLE);
		} else if ((spndrop.getSelectedItem().toString()).equals("RunOut")) {
			txtscore.setVisibility(View.VISIBLE);
			edtscore.setVisibility(View.VISIBLE);
		} else {
			txtscore.setVisibility(View.GONE);
			edtscore.setVisibility(View.GONE);
		}
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
	}

	public void btnUpdate(View view) {

		String scoredrop = spndrop.getSelectedItem().toString();

		int ball = Integer.parseInt((ballsCount.getText().toString()));

		String Overs = oversCount.getText().toString();
		double over = Double.parseDouble(Overs);

		final int Score = Integer.parseInt((scoreCount.getText().toString()));
		int Wicket = Integer.parseInt((wktCount.getText().toString()));
		if (scoredrop.equals("Dot Ball")) {
			if (ball != 6) {
				Ball_count = ball + 1;
				Overs_Count = over + 0.1;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				ballsCount.setText(Integer.toString(Ball_count));
				oversCount.setText(FormattedText);
			} else {
				Ball_count = 1;
				int RoundOf = (int) over;
				Overs_Count = RoundOf + 1.0;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				ballsCount.setText(Integer.toString(Ball_count));
				oversCount.setText(FormattedText);
			}
			HScrollview("0", ball);
			spndrop.setSelection(0);
		} else if (scoredrop.equals("Single")) {
			if (ball != 6) {
				Ball_count = ball + 1;
				Overs_Count = over + 0.1;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 1;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				// oversCount.setText(Double.toString(Overs_Count));
				oversCount.setText(FormattedText);
			} else {
				Ball_count = 1;
				int RoundOf = (int) over;
				Overs_Count = RoundOf + 1.0;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 1;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				oversCount.setText(FormattedText);
			}
			HScrollview("1", ball);
			spndrop.setSelection(0);
		} else if (scoredrop.equals("Two")) {
			if (ball != 6) {
				Ball_count = ball + 1;
				Overs_Count = over + 0.1;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 2;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				// oversCount.setText(Double.toString(Overs_Count));
				oversCount.setText(FormattedText);
			} else {
				Ball_count = 1;
				int RoundOf = (int) over;
				Overs_Count = RoundOf + 1.0;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 2;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				oversCount.setText(FormattedText);
			}
			HScrollview("2", ball);
			spndrop.setSelection(0);
		} else if (scoredrop.equals("Three")) {
			if (ball != 6) {
				Ball_count = ball + 1;
				Overs_Count = over + 0.1;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 3;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				// oversCount.setText(Double.toString(Overs_Count));
				oversCount.setText(FormattedText);
			} else {
				Ball_count = 1;
				int RoundOf = (int) over;
				Overs_Count = RoundOf + 1.0;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 3;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				oversCount.setText(FormattedText);
			}
			HScrollview("3", ball);
			spndrop.setSelection(0);
		} else if (scoredrop.equals("Four")) {
			if (ball != 6) {
				Ball_count = ball + 1;
				Overs_Count = over + 0.1;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 4;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				// oversCount.setText(Double.toString(Overs_Count));
				oversCount.setText(FormattedText);

			} else {
				Ball_count = 1;
				int RoundOf = (int) over;
				Overs_Count = RoundOf + 1.0;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 4;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				oversCount.setText(FormattedText);
			}
			HScrollview("4", ball);
			spndrop.setSelection(0);
		} else if (scoredrop.equals("Six")) {
			if (ball != 6) {
				Ball_count = ball + 1;
				Overs_Count = over + 0.1;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 6;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				// oversCount.setText(Double.toString(Overs_Count));
				oversCount.setText(FormattedText);
			} else {
				Ball_count = 1;
				int RoundOf = (int) over;
				Overs_Count = RoundOf + 1.0;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Score_count = Score + 6;
				scoreCount.setText(Integer.toString(Score_count));
				ballsCount.setText(Integer.toString(Ball_count));
				oversCount.setText(FormattedText);
			}
			HScrollview("6", ball);
			spndrop.setSelection(0);
		} else if (scoredrop.equals("Wide")) {
			Score_count = Score + 1;
			scoreCount.setText(Integer.toString(Score_count));
			HScrollview("Wd", ball);
			spndrop.setSelection(0);
		} else if (scoredrop.equals("Wicket")) {
			if (ball != 6) {
				Ball_count = ball + 1;
				Overs_Count = over + 0.1;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Wicket_Count = Wicket + 1;
				wktCount.setText(Integer.toString(Wicket_Count));
				ballsCount.setText(Integer.toString(Ball_count));
				oversCount.setText(FormattedText);
			} else {
				Ball_count = 1;
				int RoundOf = (int) over;
				Overs_Count = RoundOf + 1.0;
				DecimalFormat form = new DecimalFormat("0.0");
				String FormattedText = form.format(Overs_Count);
				Wicket_Count = Wicket + 1;
				wktCount.setText(Integer.toString(Wicket_Count));
				ballsCount.setText(Integer.toString(Ball_count));
				oversCount.setText(FormattedText);
			}
			HScrollview("W", ball);
			spndrop.setSelection(0);
			spndrop.setSelection(0);
		} else if (scoredrop.equals("No Ball")) {

			if ((edtscore.getText().toString().length()) > 0) {
				edtscore.setHintTextColor(Color.BLACK);
				int score_noball = Integer.parseInt(edtscore.getText()
						.toString());
				Score_count = Score + score_noball;
				scoreCount.setText(Integer.toString(Score_count));
				edtscore.setText("");
				txtscore.setVisibility(View.GONE);
				edtscore.setVisibility(View.GONE);
				spndrop.setSelection(0);
				String sball = Integer.toString(score_noball);
				String ballcon = sball.concat("nb");
				HScrollview(ballcon, ball);
			} else {
				edtscore.setHintTextColor(Color.RED);
				spndrop.setSelection(8);
			}
			spndrop.setSelection(0);
		} else if (scoredrop.equals("RunOut")) {
			if (ball != 6) {
				if ((edtscore.getText().toString().length()) > 0) {
					edtscore.setHintTextColor(Color.BLACK);
					int score_ball = Integer.parseInt(edtscore.getText()
							.toString());
					Score_count = Score + score_ball;
					Ball_count = ball + 1;
					Overs_Count = over + 0.1;
					DecimalFormat form = new DecimalFormat("0.0");
					String FormattedText = form.format(Overs_Count);
					Wicket_Count = Wicket + 1;
					wktCount.setText(Integer.toString(Wicket_Count));
					ballsCount.setText(Integer.toString(Ball_count));
					oversCount.setText(FormattedText);
					scoreCount.setText(Integer.toString(Score_count));
					edtscore.setText("");
					txtscore.setVisibility(View.GONE);
					edtscore.setVisibility(View.GONE);
					spndrop.setSelection(0);
					String sball = Integer.toString(score_ball);
					String ballcon = sball.concat("RO");
					HScrollview(ballcon, ball);
				} else {
					edtscore.setHintTextColor(Color.RED);
					spndrop.setSelection(9);
				}
			} else {
				if ((edtscore.getText().toString().length()) > 0) {
					edtscore.setHintTextColor(Color.BLACK);
					int score_ball = Integer.parseInt(edtscore.getText()
							.toString());
					Score_count = Score + score_ball;
					Ball_count = 1;
					int RoundOf = (int) over;
					Overs_Count = RoundOf + 1.0;
					DecimalFormat form = new DecimalFormat("0.0");
					String FormattedText = form.format(Overs_Count);
					Wicket_Count = Wicket + 1;
					wktCount.setText(Integer.toString(Wicket_Count));
					ballsCount.setText(Integer.toString(Ball_count));
					oversCount.setText(FormattedText);
					scoreCount.setText(Integer.toString(Score_count));
					edtscore.setText("");
					txtscore.setVisibility(View.GONE);
					edtscore.setVisibility(View.GONE);
					spndrop.setSelection(0);
					String sball = Integer.toString(score_ball);
					String ballcon = sball.concat("RO");
					HScrollview(ballcon, ball);
				} else {
					edtscore.setHintTextColor(Color.RED);
					spndrop.setSelection(9);
				}
			}

		} else {
			AlertDialog.Builder dialog = new AlertDialog.Builder(
					new ContextThemeWrapper(this,
							android.R.color.holo_green_light));
			dialog.setTitle("Alert Message");
			dialog.setMessage("                                " + "\n"
					+ " Please select Valid options" + "\n"
					+ "                                  ");
			dialog.setCancelable(false);
			dialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			AlertDialog alert = dialog.create();
			alert.show();
		}
		double final_over = Double.parseDouble(total_overs);
		double final_overs = Double
				.parseDouble(oversCount.getText().toString());
		if (final_over == final_overs || target <= Score_count) {
			btnupdate.setEnabled(false);
			((Spinner) spndrop).getSelectedView().setEnabled(false);
			spndrop.setEnabled(false);
			summary.setVisibility(View.VISIBLE);
			if (target <= Score_count) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						new ContextThemeWrapper(this,
								android.R.color.holo_green_light));
				dialog.setTitle("Match Status");
				dialog.setMessage("                                " + "\n"
						+ team_name + " Lost the match" + "\n"
						+ "                                  ");
				dialog.setCancelable(false);
				dialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				AlertDialog alert = dialog.create();
				alert.show();
			} else if ((target - 1) == Score_count) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						new ContextThemeWrapper(this,
								android.R.color.holo_green_light));
				dialog.setTitle("Match Status");
				dialog.setMessage("                                " + "\n"
						+ " Match Draw " + "\n"
						+ "                                  ");
				dialog.setCancelable(false);
				dialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				AlertDialog alert = dialog.create();
				alert.show();
			} else {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						new ContextThemeWrapper(this,
								android.R.color.holo_green_light));
				dialog.setTitle("Match Status");
				int result = target - Score_count - 1;
				dialog.setMessage("                                " + "\n"
						+ team_name + " Won by " + result + " runs" + "\n"
						+ "                                  ");
				dialog.setCancelable(false);
				dialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				AlertDialog alert = dialog.create();
				alert.show();
			}
		}
	}

	public void HScrollview(String Ball, int Count_ball) {
		LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		final TextView recent = new TextView(this);
		TextView space = new TextView(this);
		TextView separate = new TextView(this);
		recent.setLayoutParams(lparams);
		space.setLayoutParams(lparams);
		separate.setLayoutParams(lparams);
		space.setText("  ");
		if (Ball.equals("0")) {
			recent.setText("0");
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.WHITE);
			if (Count_ball == 6) {
				separate.setVisibility(View.VISIBLE);
				separate.setText("|");
				separate.setTextSize(38);
				separate.setBackgroundColor(Color.WHITE);
			} else {
				separate.setVisibility(View.GONE);
			}
		} else if (Ball.equals("1")) {
			recent.setText(Ball);
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.CYAN);
			if (Count_ball == 6) {
				separate.setVisibility(View.VISIBLE);
				separate.setText("|");
				separate.setTextSize(38);
				separate.setBackgroundColor(Color.WHITE);
			} else {
				separate.setVisibility(View.GONE);
			}
		} else if (Ball.equals("2")) {
			recent.setText(Ball);
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.CYAN);
			if (Count_ball == 6) {
				separate.setVisibility(View.VISIBLE);
				separate.setText("|");
				separate.setTextSize(38);
				separate.setBackgroundColor(Color.WHITE);
			} else {
				separate.setVisibility(View.GONE);
			}
		} else if (Ball.equals("3")) {
			recent.setText(Ball);
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.CYAN);
			if (Count_ball == 6) {
				separate.setVisibility(View.VISIBLE);
				separate.setText("|");
				separate.setTextSize(38);
				separate.setBackgroundColor(Color.WHITE);
			} else {
				separate.setVisibility(View.GONE);
			}
		} else if (Ball.equals("4")) {
			recent.setText(Ball);
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.GREEN);
			if (Count_ball == 6) {
				separate.setVisibility(View.VISIBLE);
				separate.setText("|");
				separate.setTextSize(38);
				separate.setBackgroundColor(Color.WHITE);
			} else {
				separate.setVisibility(View.GONE);
			}
		} else if (Ball.equals("6")) {
			recent.setText(Ball);
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.GREEN);
			if (Count_ball == 6) {
				separate.setVisibility(View.VISIBLE);
				separate.setText("|");
				separate.setTextSize(38);
				separate.setBackgroundColor(Color.WHITE);
			} else {
				separate.setVisibility(View.GONE);
			}
		} else if (Ball.equals("Wd")) {
			recent.setText(Ball);
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.GRAY);
		} else if (Ball.equals("W")) {
			recent.setText(Ball);
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.RED);
			if (Count_ball == 6) {
				separate.setVisibility(View.VISIBLE);
				separate.setText("|");
				separate.setTextSize(38);
				separate.setBackgroundColor(Color.WHITE);
			} else {
				separate.setVisibility(View.GONE);
			}
		} else if (Ball.endsWith("nb")) {
			recent.setText(Ball);
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.GRAY);
		} else if (Ball.endsWith("RO")) {
			recent.setText(Ball);
			recent.setPadding(10, 0, 10, 0);
			recent.setTextSize(20);
			recent.setBackgroundColor(Color.RED);
			if (Count_ball == 6) {
				separate.setVisibility(View.VISIBLE);
				separate.setText("|");
				separate.setTextSize(38);
				separate.setBackgroundColor(Color.WHITE);
			} else {
				separate.setVisibility(View.GONE);
			}

		}
		lscrollview.addView(space);
		lscrollview.addView(recent);
		lscrollview.addView(separate);
		// lscrollview.setHorizontalGravity(View.FOCUS_FORWARD);
		recent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View view) {
				final String Cur_Score = recent.getText().toString();
				final AlertDialog.Builder dialog1 = new AlertDialog.Builder(
						ChasingDetails.this);
				dialog1.setCancelable(false);
				dialog1.setTitle("Edit Score");
				dialog1.setMessage(" Edit Your Score");

				Context context = ChasingDetails.this;
				LinearLayout layout = new LinearLayout(context);
				layout.setOrientation(LinearLayout.VERTICAL);

				spinner = new Spinner(ChasingDetails.this);
				String[] s = getResources().getStringArray(R.array.item_array1);
				ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
						ChasingDetails.this, R.layout.spinnerlayout, s);
				spinnerArrayAdapter
						.setDropDownViewResource(R.layout.spinnerlayout);
				spinner.setAdapter(spinnerArrayAdapter);
				layout.addView(spinner);

				edtOver = new EditText(ChasingDetails.this);
				edtOver.setHintTextColor(Color.BLACK);
				edtOver.setInputType(InputType.TYPE_CLASS_NUMBER);
				edtOver.setVisibility(View.GONE);
				edtOver.setHint("Runs");
				layout.addView(edtOver);
				dialog1.setView(layout);
				spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int pos, long id) {
						if ((spinner.getSelectedItem().toString())
								.equals("No Ball")) {
							edtOver.setVisibility(View.VISIBLE);
						} else if ((spinner.getSelectedItem().toString())
								.equals("RunOut")) {
							edtOver.setVisibility(View.VISIBLE);
						} else {
							edtOver.setVisibility(View.GONE);
						}
					}
				});

				dialog1.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								String edtScore = spinner.getSelectedItem()
										.toString();
								if (Cur_Score.equals("0")) {
									if (edtScore.equals("Dot Ball")) {
										recent.setText("" + 0);
									} else if (edtScore.equals("Single")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 1);
									} else if (edtScore.equals("Two")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 2);
									} else if (edtScore.equals("Three")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 3);
									} else if (edtScore.equals("Four")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 4;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 4);
									} else if (edtScore.equals("Six")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 6;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 6);
									} /*
									 * else if (edtScore.equals("Wide")) { int
									 * score = Integer.parseInt(scoreCount
									 * .getText().toString()); score += 1;
									 * scoreCount.setText(String
									 * .valueOf(score)); recent.setText("Wd");
									 * String overs =
									 * oversCount.getText().toString();
									 * if(overs.endsWith(".0")){ Double over =
									 * Double.parseDouble(overs.toString());
									 * over= over - 1.0; Double overr = over +
									 * 0.5; oversCount.setText(""+overr);
									 * btnupdate.setEnabled(true);
									 * spndrop.setEnabled(true); }else{ Double
									 * over =
									 * Double.parseDouble(overs.toString());
									 * over = over - 0.1;
									 * oversCount.setText(""+over);
									 * btnupdate.setEnabled(true);
									 * spndrop.setEnabled(true); } int ball =
									 * Integer
									 * .parseInt(ballsCount.getText().toString
									 * ()); if(ball==1)
									 * ballsCount.setText(""+1); else
									 * ballsCount.setText(""+(ball-1)); }
									 */else if (edtScore.equals("Wicket")) {
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										recent.setText("W");
									} else if (edtScore.equals("RunOut")) {
										int scoree = 0;
										scoree = Integer.parseInt(edtOver
												.getText().toString());
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += scoree;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText(scoree + "RO");
									}/*
									 * else if (edtScore.equals("No Ball")) {
									 * int scoree = 0; scoree =
									 * Integer.parseInt(
									 * edtOver.getText().toString()); int score
									 * = Integer.parseInt(scoreCount
									 * .getText().toString()); score += scoree;
									 * scoreCount.setText(String
									 * .valueOf(score));
									 * recent.setText(scoree+"NB"); String overs
									 * = oversCount.getText().toString();
									 * if(overs.endsWith(".0")){ Double over =
									 * Double.parseDouble(overs.toString());
									 * over= over - 1.0; Double overr = over +
									 * 0.5; oversCount.setText(""+overr);
									 * btnupdate.setEnabled(true);
									 * spndrop.setEnabled(true); }else{ Double
									 * over =
									 * Double.parseDouble(overs.toString());
									 * over = over - 0.1;
									 * oversCount.setText(""+over);
									 * btnupdate.setEnabled(true);
									 * spndrop.setEnabled(true); } int ball =
									 * Integer
									 * .parseInt(ballsCount.getText().toString
									 * ()); if(ball==1)
									 * ballsCount.setText(""+1); else
									 * ballsCount.setText(""+(ball-1)); }
									 */else {
										Toast.makeText(getBaseContext(),
												"Select the score",
												Toast.LENGTH_SHORT).show();
									}
								} else if (Cur_Score.equals("1")) {
									if (edtScore.equals("Dot Ball")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 0);
									} else if (edtScore.equals("Single")) {
										recent.setText("" + 1);
									} else if (edtScore.equals("Two")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 2);
									} else if (edtScore.equals("Three")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 3);
									} else if (edtScore.equals("Four")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 4);
									} else if (edtScore.equals("Six")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 5;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 6);
									} else if (edtScore.equals("Wicket")) {
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("W");
									} else if (edtScore.equals("RunOut")) {
										int scoree = 0;
										scoree = Integer.parseInt(edtOver
												.getText().toString());
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += scoree - 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText(scoree + "RO");
									} else {
										Toast.makeText(getBaseContext(),
												"Select the score",
												Toast.LENGTH_SHORT).show();
									}
								} else if (Cur_Score.equals("2")) {
									if (edtScore.equals("Dot Ball")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 0);
									} else if (edtScore.equals("Single")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 1);
									} else if (edtScore.equals("Two")) {
										recent.setText("" + 2);
									} else if (edtScore.equals("Three")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 3);
									} else if (edtScore.equals("Four")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 4);
									} else if (edtScore.equals("Six")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 4;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 6);
									} else if (edtScore.equals("Wicket")) {
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("W");
									} else if (edtScore.equals("RunOut")) {
										int scoree = 0;
										scoree = Integer.parseInt(edtOver
												.getText().toString());
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += scoree - 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText(scoree + "RO");
									} else {
										Toast.makeText(getBaseContext(),
												"Select the score",
												Toast.LENGTH_SHORT).show();
									}
								} else if (Cur_Score.equals("3")) {
									if (edtScore.equals("Dot Ball")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 0);
									} else if (edtScore.equals("Single")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 1);
									} else if (edtScore.equals("Two")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 2);
									} else if (edtScore.equals("Three")) {
										recent.setText("" + 3);
									} else if (edtScore.equals("Four")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 4);
									} else if (edtScore.equals("Six")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 6);
									} else if (edtScore.equals("Wicket")) {
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("W");
									} else if (edtScore.equals("RunOut")) {
										int scoree = 0;
										scoree = Integer.parseInt(edtOver
												.getText().toString());
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += scoree - 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText(scoree + "RO");
									} else {
										Toast.makeText(getBaseContext(),
												"Select the score",
												Toast.LENGTH_SHORT).show();
									}
								} else if (Cur_Score.equals("3")) {
									if (edtScore.equals("Dot Ball")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 0);
									} else if (edtScore.equals("Single")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 1);
									} else if (edtScore.equals("Two")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 2);
									} else if (edtScore.equals("Three")) {
										recent.setText("" + 3);
									} else if (edtScore.equals("Four")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 4);
									} else if (edtScore.equals("Six")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 6);
									} else if (edtScore.equals("Wicket")) {
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("W");
									} else if (edtScore.equals("RunOut")) {
										int scoree = 0;
										scoree = Integer.parseInt(edtOver
												.getText().toString());
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += scoree - 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText(scoree + "RO");
									} else {
										Toast.makeText(getBaseContext(),
												"Select the score",
												Toast.LENGTH_SHORT).show();
									}
								} else if (Cur_Score.equals("4")) {
									if (edtScore.equals("Dot Ball")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 4;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 0);
									} else if (edtScore.equals("Single")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 1);
									} else if (edtScore.equals("Two")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 2);
									} else if (edtScore.equals("Three")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 3);
									} else if (edtScore.equals("Four")) {
										recent.setText("" + 4);
									} else if (edtScore.equals("Six")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 6);
									} else if (edtScore.equals("Wicket")) {
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 4;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("W");
									} else if (edtScore.equals("RunOut")) {
										int scoree = 0;
										scoree = Integer.parseInt(edtOver
												.getText().toString());
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += scoree - 4;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText(scoree + "RO");
									} else {
										Toast.makeText(getBaseContext(),
												"Select the score",
												Toast.LENGTH_SHORT).show();
									}
								} else if (Cur_Score.equals("6")) {
									if (edtScore.equals("Dot Ball")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 6;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 0);
									} else if (edtScore.equals("Single")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 5;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 1);
									} else if (edtScore.equals("Two")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 4;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 2);
									} else if (edtScore.equals("Three")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 3);
									} else if (edtScore.equals("Four")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 4);
									} else if (edtScore.equals("Six")) {
										recent.setText("" + 6);
									} else if (edtScore.equals("Wicket")) {
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= 6;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("W");
									} else if (edtScore.equals("RunOut")) {
										int scoree = 0;
										scoree = Integer.parseInt(edtOver
												.getText().toString());
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket += 1;
										wktCount.setText(String.valueOf(Wicket));
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += scoree - 6;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText(scoree + "RO");
									} else {
										Toast.makeText(getBaseContext(),
												"Select the score",
												Toast.LENGTH_SHORT).show();
									}
								} else if (Cur_Score.equals("W")) {
									if (edtScore.equals("Dot Ball")) {
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
										recent.setText("" + 0);
									} else if (edtScore.equals("Single")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 1);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Two")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 2);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Three")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 3);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Four")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 4;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 4);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Six")) {
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += 6;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 6);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Wicket")) {
										recent.setText("W");
									} else if (edtScore.equals("RunOut")) {
										int scoree = 0;
										scoree = Integer.parseInt(edtOver
												.getText().toString());
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += scoree;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText(scoree + "RO");
									} else {
										Toast.makeText(getBaseContext(),
												"Select the score",
												Toast.LENGTH_SHORT).show();
									}
								} else if (Cur_Score.endsWith("RO")) {
									if (edtScore.equals("Dot Ball")) {
										String scoree;
										scoree = recent.getText().toString();
										scoree = scoree.substring(0, 1);
										int score1 = Integer.parseInt(scoree);
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= score1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 0);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Single")) {
										String scoree;
										scoree = recent.getText().toString();
										scoree = scoree.substring(0, 1);
										int score1 = Integer.parseInt(scoree);
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= score1 + 1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 1);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Two")) {
										String scoree;
										scoree = recent.getText().toString();
										scoree = scoree.substring(0, 1);
										int score1 = Integer.parseInt(scoree);
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= score1 + 2;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 2);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Three")) {
										String scoree;
										scoree = recent.getText().toString();
										scoree = scoree.substring(0, 1);
										int score1 = Integer.parseInt(scoree);
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= score1 + 3;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 3);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Four")) {
										String scoree;
										scoree = recent.getText().toString();
										scoree = scoree.substring(0, 1);
										int score1 = Integer.parseInt(scoree);
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= score1 + 4;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 4);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Six")) {
										String scoree;
										scoree = recent.getText().toString();
										scoree = scoree.substring(0, 1);
										int score1 = Integer.parseInt(scoree);
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= score1 + 6;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("" + 6);
										int Wicket = Integer.parseInt(wktCount
												.getText().toString());
										Wicket -= 1;
										wktCount.setText(String.valueOf(Wicket));
									} else if (edtScore.equals("Wicket")) {
										String scoree;
										scoree = recent.getText().toString();
										scoree = scoree.substring(0, 1);
										int score1 = Integer.parseInt(scoree);
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score -= score1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText("W");
									} else if (edtScore.equals("RunOut")) {
										int score1 = 0;
										String scoree;
										scoree = recent.getText().toString();
										scoree = scoree.substring(0, 1);
										int scoree1 = Integer.parseInt(scoree);
										score1 = Integer.parseInt(edtOver
												.getText().toString());
										int score = Integer.parseInt(scoreCount
												.getText().toString());
										score += score1 - scoree1;
										scoreCount.setText(String
												.valueOf(score));
										recent.setText(score1 + "RO");
									} else {
										Toast.makeText(getBaseContext(),
												"Select the score",
												Toast.LENGTH_SHORT).show();
									}
								}
							}
						});
				AlertDialog alert11 = dialog1.create();
				alert11.show();
				setFinishOnTouchOutside(false);
			}
		});
	}

	public void btnSummary(View view) {
		String name = battingTeam.getText().toString();
		int score = Integer.parseInt(scoreCount.getText().toString());
		Double Overs = Double.parseDouble(oversCount.getText().toString());
		int wicket = Integer.parseInt(wktCount.getText().toString());
		String DataBase_Name = MySQLiteClass.DATABASE_TABLEC;
		db.addScore(false,DataBase_Name,name, Overs, score,wicket);
		db.close();
		Intent intent = new Intent(getBaseContext(),MatchSummary.class);
		startActivity(intent);
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
