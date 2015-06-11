package com.myapp.cktscore.view;

import java.util.ArrayList;

import com.myapp.cktscore.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

public class GridView extends Activity {
	android.widget.GridView gv;
	CustomAdapter adapter;
	public GridView CustomGridView = null;
	Context context = null;
	public ArrayList<ListModel> CustomGridViewValuesArr = new ArrayList<ListModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);

		CustomGridView = this;
		context = this;
		setListData();
		Resources res1 = getResources();

		gv = (android.widget.GridView) findViewById(R.id.grdView);
		adapter = new CustomAdapter(context, CustomGridViewValuesArr, res1);
		gv.setAdapter(adapter);

	}

	public void setListData() {

		for (int i = 1; i <= 11; i++) {

			final ListModel sched = new ListModel();

			/******* Firstly take data in model object ******/
			sched.setTitle("Title " + i);
			sched.setImage("image" + i);
			sched.setDescription("http:\\www." + i);
			sched.setCondition(false);
			/******** Take Model Object in ArrayList **********/
			CustomGridViewValuesArr.add(sched);
		}
	}

	public void onItemClick(int mPosition) {
		ListModel tempValues = (ListModel) CustomGridViewValuesArr
				.get(mPosition);

		// SHOW ALERT

		Toast.makeText(
				CustomGridView,
				"" + tempValues.getTitle() + "Image:" + tempValues.getImage()
						+ "Url:" + tempValues.getDescription(),
				Toast.LENGTH_LONG).show();
	}
}
