package com.myapp.cktscore.view;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.myapp.cktscore.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavDrawerAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<navDrawerItem> navDrawerItems;

	public NavDrawerAdapter(Context context,
			ArrayList<navDrawerItem> navDrawerItems) {
		super();
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		return navDrawerItems.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View ConvertView, ViewGroup Parent) {
		if (ConvertView == null) {
			LayoutInflater inflate = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			ConvertView = inflate.inflate(R.layout.drawer_list_item, null);
		}
		ImageView imgView = (ImageView) ConvertView.findViewById(R.id.ImgView);
		TextView txtTitle = (TextView) ConvertView.findViewById(R.id.txtTitle);
		TextView txtCount = (TextView) ConvertView
				.findViewById(R.id.txtCounter);

		imgView.setImageResource(navDrawerItems.get(position).getIcon());
		txtTitle.setText(navDrawerItems.get(position).getTitle());

		if (navDrawerItems.get(position).getCounterVisible()) {
			txtCount.setText(navDrawerItems.get(position).getCount());
		} else {
			txtCount.setVisibility(View.GONE);
		}
		return ConvertView;
	}

}
