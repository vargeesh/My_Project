package com.myapp.cktscore.view;

import com.myapp.cktscore.R;

//import android.app.Fragment;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MoviesFragment extends Fragment {
	
	/*public MoviesFragment() {
		super();
		MoviesFragment fragmentFirst = new MoviesFragment();
//      Bundle args = new Bundle();
//      args.putInt("someInt", page);
//      args.putString("someTitle", title);
//      fragmentFirst.setArguments(args);
      return;
	}*/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.fragment_movies, container, false);
		return rootview;
	}
}
