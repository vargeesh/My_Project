package com.myapp.cktscore.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.cktscore.R;

public class ViewPagerr extends FragmentActivity implements
		ActionBar.TabListener {
	public ViewPager viewpager;
	public TabsPagerAdapter adapter;
	public ActionBar actionbar;
	public String[] tabs = { "Top Rated", "Games", "Movies" };
	List<Fragment> fragments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);
//		List<Fragment> fragments = getFragments();

		viewpager = (ViewPager) findViewById(R.id.pager);
		actionbar = getActionBar();
		adapter = new TabsPagerAdapter(getSupportFragmentManager());//, fragments);
		
//		fragments = new Vector<Fragment>();
//        fragments.add(Fragment.instantiate(this, TopRatedFragment.class.getName(),savedInstanceState));
//        fragments.add(Fragment.instantiate(this, GamesFragment.class.getName(),savedInstanceState));
//        fragments.add(Fragment.instantiate(this, MoviesFragment.class.getName(),savedInstanceState));

        

		viewpager.setAdapter(adapter);
		actionbar.setHomeButtonEnabled(false);
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (String tab_name : tabs) {
			actionbar.addTab(actionbar.newTab().setText(tab_name)
					.setTabListener(this));
		}
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				actionbar.setSelectedNavigationItem(arg0);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

/*	private List<Fragment> getFragments() {
		List<Fragment> fList = new ArrayList<Fragment>();

		fList.add(MyFragment.newInstance("Fragment 1"));

		fList.add(MyFragment.newInstance("Fragment 2"));

		fList.add(MyFragment.newInstance("Fragment 3"));

		return fList;

	}
*/
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewpager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

/*	public static class MyFragment extends Fragment {

		public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

		public static final MyFragment newInstance(String message)

		{

			MyFragment f = new MyFragment();

			Bundle bdl = new Bundle(1);

			bdl.putString(EXTRA_MESSAGE, message);

			f.setArguments(bdl);

			return f;

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,

		Bundle savedInstanceState) {

			String message = getArguments().getString(EXTRA_MESSAGE);

			View v = inflater.inflate(R.layout.top_rated, container, false);
			TextView messageTextView = (TextView) v.findViewById(R.id.tv1);

			messageTextView.setText(message);

			return v;

		}

	}
*/
}
