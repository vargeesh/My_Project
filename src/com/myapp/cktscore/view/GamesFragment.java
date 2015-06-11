package com.myapp.cktscore.view;

import com.myapp.cktscore.R;

//import android.app.Fragment;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GamesFragment extends Fragment {
	
	/*public static GamesFragment newInstance() {
		GamesFragment fragmentFirst = new GamesFragment();
//        Bundle args = new Bundle();
//        args.putInt("someInt", page);
//        args.putString("someTitle", title);
//        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.fragment_games, container, false);
		return rootview;
	}
}
