package com.myapp.cktscore.view;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	Context context;
//	public Fragment[] fragments = new Fragment[] { new TopRatedFragment(), GamesFragment(), MoviesFragment()};
	
	// public static class MyPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm ) {
		super(fm);
		// List<Fragment> fragments
//		this.fragments = fragments;

	}

	@Override
	public Fragment getItem(int index) {
//		return this.fragments.get(index);
		switch(index){
		/*case 0:
		        return Fragment.instantiate(context, TopRatedFragment.class.getName(),
		              null);			
		case 1:
			 return Fragment.instantiate(context, GamesFragment.class.getName(),
		              null);			
		case 2:
			 return Fragment.instantiate(context, MoviesFragment.class.getName(),
		              null);*/
		case 0:
			return new TopRatedFragment();
		case 1:
			return new GamesFragment();
		case 2:
			return new MoviesFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
//		return this.fragments.size();
		return 3;
	}
	// }

}
