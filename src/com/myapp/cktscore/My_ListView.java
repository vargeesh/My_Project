package com.myapp.cktscore;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class My_ListView extends Activity {
	
	String[] itemname ={
			 "Safari",
			 "Camera",
			 "Global",
			 "FireFox",
			 "UC Browser",
			 "Android Folder",
			 "VLC Player",
			 "Cold War"
			 };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.my_listview);
	    /*this.setListAdapter(new ArrayAdapter<String>(
	    		 this, R.layout.mylist,
	    		 R.id.Itemname,itemname));*/
	    ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.mylist, R.id.TxtView, itemname);
	    ListView lv = (ListView)findViewById(R.id.lstView);
	    lv.setAdapter(adapter);
	    		 }
	}

