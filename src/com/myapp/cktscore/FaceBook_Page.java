package com.myapp.cktscore;

import java.util.Arrays;
import java.util.List;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.UiLifecycleHelper;
import com.facebook.Session;
import com.facebook.SessionState;

import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.LoginButton;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.widget.LoginButton.UserInfoChangedCallback;


public class FaceBook_Page extends Activity {
	private UiLifecycleHelper uiHelper;
	private LoginButton loginBtn;
	private Button postImageBtn;
	private TextView username;
	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
	private static String message = "Sample status posted from android app";


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(this, statusCallback);
		uiHelper.onCreate(savedInstanceState);
		setContentView(R.layout.facebookpage);

		username = (TextView) findViewById(R.id.username);
		loginBtn = (LoginButton) findViewById(R.id.fb_login_button);
		loginBtn.setReadPermissions(Arrays.asList("email"));
		loginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
			@Override
			public void onUserInfoFetched(GraphUser user) {
				if (user != null) {
					username.setText("You are currently logged in as "
							+ user.getName());
				} else {
					username.setText("You are not logged in.");
				}
			}
		});
		postImageBtn = (Button) findViewById(R.id.post_image);
		postImageBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				postImage();
			}
		});
	}
	
	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			if (state.isOpened()) {
				Log.d("MainActivity", "Facebook session opened.");
			} else if (state.isClosed()) {
				Log.d("MainActivity", "Facebook session closed.");
			}
		}
	};
	
	public void postImage() {
		if (checkPermissions()) {
			Bitmap img = BitmapFactory.decodeResource(getResources(),
					R.drawable.ic_launcher);
			Request uploadRequest = Request.newUploadPhotoRequest(
					Session.getActiveSession(), img, new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							Toast.makeText(FaceBook_Page.this,
									"Photo uploaded successfully",
									Toast.LENGTH_LONG).show();
						}
					});
			uploadRequest.executeAsync();
		} else {
			requestPermissions();
		}
	}
	public void postStatusMessage() {
		if (checkPermissions()) {
			Request request = Request.newStatusUpdateRequest(
					Session.getActiveSession(), message,
					new Request.Callback() {
						@Override
						public void onCompleted(Response response) {
							if (response.getError() == null)
								Toast.makeText(FaceBook_Page.this,
										"Status updated successfully",
										Toast.LENGTH_LONG).show();
						}
					});
			request.executeAsync();
		} else {
			requestPermissions();
		}
	}
	
	public void requestPermissions() {
		Session s = Session.getActiveSession();
		if (s != null)
			s.requestNewPublishPermissions(new Session.NewPermissionsRequest(
					this, PERMISSIONS));
	}
	public boolean checkPermissions() {
		Session s = Session.getActiveSession();
		if (s != null) {
			return s.getPermissions().contains("publish_actions");
		} else
			return false;
	}
	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}
	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}
	@Override
	public void onSaveInstanceState(Bundle savedState) {
		super.onSaveInstanceState(savedState);
		uiHelper.onSaveInstanceState(savedState);
	}
}
