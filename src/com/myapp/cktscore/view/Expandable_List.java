package com.myapp.cktscore.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.myapp.cktscore.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class Expandable_List extends Activity {
	ExpandableListAdapter exAdapter;
	ExpandableListView exListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listChildData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandablelist);
		exListView = (ExpandableListView) findViewById(R.id.expan);
		
		prepareListData();
		
		exAdapter = new ExpandableListAdapter(this, listDataHeader, listChildData);
		exListView.setAdapter(exAdapter);
		
	}
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listChildData = new HashMap<String, List<String>>();
		
		 // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");
        
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");
        
        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");
 
        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");
        
        listChildData.put(listDataHeader.get(0), top250);
        listChildData.put(listDataHeader.get(1), nowShowing);
        listChildData.put(listDataHeader.get(2), comingSoon);
	}
}
