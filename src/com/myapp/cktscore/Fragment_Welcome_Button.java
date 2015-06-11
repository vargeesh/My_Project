package com.myapp.cktscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.myapp.cktscore.view.ListviewAndroid;
import com.myapp.cktscore.view.ViewPagerr;

public class Fragment_Welcome_Button extends Fragment {
	public View view;
	Button btnFB, btnListt, btnGridList, btnViewPage;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_welcome_button, container,
				false);
		TextView txtView = (TextView) view.findViewById(R.id.txtLink);
		  txtView.setClickable(true);
		  btnFB = (Button) view.findViewById(R.id.btnText);
		  btnListt = (Button)view.findViewById(R.id.btnList);
		  btnGridList = (Button) view.findViewById(R.id.btnList1);
		  btnViewPage = (Button) view.findViewById(R.id.btnviewPager);
		  txtView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity().getBaseContext(), teamname.class);
				startActivity(intent);
			}
		});
		  btnFB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity().getBaseContext(), FaceBook_Page.class);
				startActivity(intent);
			}
		});
		  btnListt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity().getBaseContext(), My_ListView.class);
				startActivity(intent);
			}
		});
		  btnGridList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity().getBaseContext(),ListviewAndroid.class);
		    	startActivity(intent);
			}
		});
		  btnViewPage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity().getBaseContext(), ViewPagerr.class);
				startActivity(intent);
			}
		});
		return view;
	}
	
	/*public void btnHlink(View view) {
		Intent intent = new Intent(getActivity().getBaseContext(), teamname.class);
		startActivity(intent);
	}

	public void btnHlink1(View view) {
		Intent intent = new Intent(getActivity().getBaseContext(), FaceBook_Page.class);
		startActivity(intent);
	}

	public void btnListView(View view) {
		Intent intent = new Intent(getActivity().getBaseContext(), My_ListView.class);
		startActivity(intent);
	}

	public void btnListView1(View view) {
		Intent intent = new Intent(getActivity().getBaseContext(), ListviewAndroid.class);
		startActivity(intent);
	}

	public void btnViewPager(View view) {
		Intent intent = new Intent(getActivity().getBaseContext(), ViewPagerr.class);
		startActivity(intent);
	}*/
}
