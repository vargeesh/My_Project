package com.myapp.cktscore.view;

import com.myapp.cktscore.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class FullImageView extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageview);
		ImageView image = (ImageView) findViewById(R.id.tVHeader2);
		Intent intent =getIntent();
		int image1 = intent.getIntExtra("Image", 0);
		image.setImageResource(image1);
	}
}
