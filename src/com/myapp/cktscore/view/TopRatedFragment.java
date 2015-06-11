package com.myapp.cktscore.view;

import java.util.zip.Inflater;

import com.myapp.cktscore.R;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TopRatedFragment extends Fragment {

	/*public static final TopRatedFragment newInstance()

	{

		TopRatedFragment f = new TopRatedFragment();

		// Bundle bdl = new Bundle(1);
		//
		// bdl.putString(EXTRA_MESSAGE, message);
		//
		// f.setArguments(bdl);

		return f;

	}*/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.top_rated, container, false);
		return rootView;
	}

}
