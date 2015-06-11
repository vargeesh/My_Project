package com.myapp.cktscore;

import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class teamname extends Activity {
	Context context;
	Bundle extras;
	EditText edtText;
	TextView tView, vs, oppteam, tName;
	Button btnUpdate, btnSave, btnGo;
	Locale defloc = Locale.getDefault();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getActionBar().setTitle("CKTSCORE");
		getActionBar().setBackgroundDrawable(getWallpaper());
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.teamname);
	    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

	}
	
	public void btnUpdate(View view) {
		// TODO Auto-generated method stub
		edtText = (EditText) findViewById(R.id.txtTeam);
		String input = edtText.getText().toString();
		if (input.length() < 1) {
			Toast.makeText(getBaseContext(), "Give Your Team Name",
					Toast.LENGTH_SHORT).show();
		} else {
			tView = (TextView) findViewById(R.id.myteam);
			vs = (TextView) findViewById(R.id.vs);
			oppteam = (TextView) findViewById(R.id.OpponentTeam);
			tView.setVisibility(View.VISIBLE);
			vs.setVisibility(View.VISIBLE);
			oppteam.setVisibility(View.VISIBLE);
			String input2 = (input).toLowerCase(defloc);
			String output = Character.toUpperCase(input2.charAt(0))
					+ input2.substring(1);
			tView.setText(output);
			oppteam.setText("??");

			tName = (TextView) findViewById(R.id.Teamname);
			tName.setText("Give Oppenent TeamName");
			edtText.setText("");
			edtText.setHint("OppenentTeam");
			btnUpdate = (Button) findViewById(R.id.btnUpdate);
			btnSave = (Button) findViewById(R.id.btnSave);
			btnUpdate.setVisibility(View.GONE);
			btnSave.setVisibility(View.VISIBLE);
		}
	}

	public void btnSave(View view) {
		String input1 = edtText.getText().toString();
		if (input1.length() < 1) {

			Toast.makeText(getBaseContext(), "Give Oppenent Team Name",
					Toast.LENGTH_SHORT).show();
		} else {
			String input2 = (input1).toLowerCase(defloc);
			String output1 = Character.toUpperCase(input2.charAt(0))
					+ input2.substring(1);
			oppteam.setText(output1);
			btnGo = (Button) findViewById(R.id.btnGo);
			edtText.setText("");
			edtText.setEnabled(false);
			tName.setEnabled(false);
			btnSave.setVisibility(View.INVISIBLE);
			btnGo.setVisibility(View.VISIBLE);
		}
	}

	public void btnGo(View view) {
		/*Intent data = new Intent();
		data.setData(Uri.parse(tView.getText().toString()));*/
		Bundle bundle = new Bundle();
		bundle.putString("Team1", tView.getText().toString());
		bundle.putString("Team2", oppteam.getText().toString());
		Intent intent = new Intent(getBaseContext(), Toss.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	// Hide Soft Keyboard when we touch outside on the screen
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		InputMethodManager inputmethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		inputmethod.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
				0);
		return true;
	}
}
