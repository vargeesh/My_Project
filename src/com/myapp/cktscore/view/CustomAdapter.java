package com.myapp.cktscore.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapp.cktscore.R;

public class CustomAdapter extends BaseAdapter {
	private ArrayList data;
	private static LayoutInflater inflater = null;
	public Resources res;
	ListModel tempValues = null;
	public ListviewAndroid listview;
	public GridView gridview;
	int i = 0;
	Context context;
	Boolean condition1;

	public CustomAdapter(Context context, ArrayList d, Resources resLocal) {

		/********** Take passed values **********/
		this.context = context;
		data = d;
		res = resLocal;
		/*********** Layout inflator to call external xml layout () ***********/
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		if (data.size() <= 0)
			return 1;
		else
			return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		public TextView text;
		public TextView text1;
		public ImageView image;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder holder;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.mylist, null);

			holder = new ViewHolder();
			holder.text = (TextView) vi.findViewById(R.id.TxtView);
			holder.text1 = (TextView) vi.findViewById(R.id.TxtView1);
			holder.image = (ImageView) vi.findViewById(R.id.ImgView);

			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();
		if (data.size() <= 0) {
			holder.text.setText("No Data");

		} else {
			tempValues = null;
			tempValues = (ListModel) data.get(position);
			holder.text.setText(tempValues.getTitle());
			holder.text1.setText(tempValues.getDescription());
			final int res1 = res.getIdentifier("com.myapp.cktscore:drawable/"
					+ tempValues.getImage(), null, null);
			/*
			 * holder.image.setImageResource(res.getIdentifier(
			 * "com.myapp.cktscore:drawable/"+tempValues.getImage()
			 * ,null,null));
			 */
			holder.image.setImageResource(res1);
			vi.setOnClickListener(new OnItemClickListener(position));
			holder.image.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, FullImageView.class);
					intent.putExtra("Image", res1);
					context.startActivity(intent);
				}
			});
		}
		return vi;
	}

	private class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {
			condition1 = Boolean.valueOf(tempValues.getCondition());
			if (condition1) {
				ListviewAndroid sct = (ListviewAndroid) context;
				/****
				 * Call onItemClick Method inside CustomListViewAndroidExample
				 * Class ( See Below )
				 ****/
				sct.onItemClick(mPosition);
			} else {
				GridView sct1 = (GridView) context;
				sct1.onItemClick(mPosition);
			}
		}
	}

}
