package com.myapp.cktscore.view;

import java.util.HashMap;
import java.util.List;

import com.myapp.cktscore.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	Context context;
	private List<String> listHeader;
	private HashMap<String, List<String>> listChild;
	private static LayoutInflater inflater = null;


	public ExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<String>> listChildData) {
		this.context = context;
		this.listHeader = listDataHeader;
		this.listChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return this.listChild.get(this.listHeader.get(groupPosition)).get(
				childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String ChildText = (String) getChild(groupPosition, childPosition);
		if(convertView == null){
			inflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.expan_listitem, null);
		}
		TextView txtview = (TextView) convertView.findViewById(R.id.lblListItem);
		txtview.setText(ChildText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.listChild.get(this.listHeader.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.listHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.listHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String HeaderText = (String) getGroup(groupPosition);
		if(convertView ==null){
			inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.expan_header, null);
			}
		TextView txtHeader = (TextView) convertView.findViewById(R.id.listHeader);
		txtHeader.setTypeface(null, Typeface.BOLD);
		txtHeader.setText(HeaderText);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
