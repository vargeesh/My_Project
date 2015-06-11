package com.myapp.cktscore.view;

import java.util.ArrayList;

import com.myapp.cktscore.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ListviewAndroid extends Activity {
	ListView list;
	CustomAdapter adapter;
	public ListviewAndroid CustomListView = null;
	Context context = null;
	public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
	ImageButton imgButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_listview);
		CustomListView = this;
		context = this;
		setListData();

		Resources res = getResources();
		list = (ListView) findViewById(R.id.lstView); // List defined in XML (//
														// See Below )
		// list.setDivider(null);
		list.setDividerHeight(1);
		/**************** Create Custom Adapter *********/
		adapter = new CustomAdapter(context, CustomListViewValuesArr, res);
		list.setAdapter(adapter);
	}

	public void setListData() {

		for (int i = 1; i <= 11; i++) {

			final ListModel sched = new ListModel();

			/******* Firstly take data in model object ******/
			sched.setTitle("Title " + i);
			sched.setImage("image" + i);
			sched.setDescription("http:\\www." + i + ".com");
			sched.setCondition(true);
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(sched);
		}
	}

	public void onItemClick(int mPosition) {
		ListModel tempValues = (ListModel) CustomListViewValuesArr
				.get(mPosition);

		// SHOW ALERT

		Toast.makeText(
				CustomListView,
				"" + tempValues.getTitle() + "Image:" + tempValues.getImage()
						+ "Url:" + tempValues.getDescription(),
				Toast.LENGTH_LONG).show();
	}

	public void viewSwitch(View view) {
		Intent intent = new Intent(getBaseContext(), GridView.class);
		startActivity(intent);
	}
	public void viewSwitch1(View view) {
		Intent intent = new Intent(getBaseContext(), Expandable_List.class);
		startActivity(intent);
	}
}
