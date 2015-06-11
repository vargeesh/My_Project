package com.myapp.cktscore;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import com.myapp.cktscore.view.GamesFragment;
import com.myapp.cktscore.view.MoviesFragment;
import com.myapp.cktscore.view.NavDrawerAdapter;
import com.myapp.cktscore.view.TopRatedFragment;
import com.myapp.cktscore.view.navDrawerItem;

public class Fragment_menu extends FragmentActivity {
	public DrawerLayout drawerLayout;
	public ListView listview;
	public Context context;
	private ActionBarDrawerToggle drawerToggle;
	private CharSequence DrawerTitle, mTitle;

	private String[] navMenuTitles;
	private TypedArray DrawerIcons;
	private ArrayList<navDrawerItem> navDrawerItem;
	private NavDrawerAdapter madapter;
	Button btnfacebook;
	int mBackStackSize = 0;
	public static Fragment_menu rootinstance;

	public static Fragment_menu defaultInstance() {
		return rootinstance;
	}

	public View view;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// adding fragment at runtime:-
		Fragment fragment = new Fragment_Welcome_Button();
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(R.id.Frame_layout, fragment).addToBackStack(null);
		ft.commit();

		setContentView(R.layout.activity_welcome_screen);

		// mTitle = DrawerTitle = getTitle();
		rootinstance = this;

		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		DrawerIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
		listview = (ListView) findViewById(R.id.List_view);

		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		navDrawerItem = new ArrayList<navDrawerItem>();
		navDrawerItem.add(new navDrawerItem(navMenuTitles[0], DrawerIcons
				.getResourceId(0, -1)));
		navDrawerItem.add(new navDrawerItem(navMenuTitles[1], DrawerIcons
				.getResourceId(1, -1)));
		navDrawerItem.add(new navDrawerItem(navMenuTitles[2], DrawerIcons
				.getResourceId(2, -1)));
		navDrawerItem.add(new navDrawerItem(navMenuTitles[3], DrawerIcons
				.getResourceId(3, -1), "22", true));
		navDrawerItem.add(new navDrawerItem(navMenuTitles[4], DrawerIcons
				.getResourceId(4, -1)));
		navDrawerItem.add(new navDrawerItem(navMenuTitles[5], DrawerIcons
				.getResourceId(5, -1)));

		DrawerIcons.recycle();

		madapter = new NavDrawerAdapter(getApplicationContext(), navDrawerItem);
		listview.setAdapter(madapter);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		listview.setOnItemClickListener(new SlideMenuClickListener());
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.balll, R.string.app_name, R.string.app_name) {
			@Override
			public void onDrawerClosed(View drawerView) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);
		if (savedInstanceState == null) {
			// displayView(0);
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.welcome_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerlay = drawerLayout.isDrawerOpen(listview);
		menu.findItem(R.id.action_settings).setVisible(!drawerlay);
		return drawerlay;
	}

	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	private void displayView(int position) {
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new TopRatedFragment();
			break;
		case 1:
			fragment = new MoviesFragment();
			break;
		case 2:
			fragment = new GamesFragment();
			break;
		case 3:
			fragment = new TopRatedFragment();
			break;
		case 4:
			fragment = new MoviesFragment();
			break;
		case 5:
			fragment = new GamesFragment();
			break;

		default:
			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.replace(R.id.Frame_layout, fragment).addToBackStack(null);

			ft.commit();

			// update selected item and title, then close the drawer

			listview.setItemChecked(position, true);
			listview.setSelection(position);
			setTitle(navMenuTitles[position]);
			drawerLayout.closeDrawer(listview);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}
	/*
	 * @Override public void onBackPressed() { super.onBackPressed(); final
	 * FragmentManager fragmentManager = getSupportFragmentManager(); final
	 * Fragment fragment1 = new Fragment_Welcome_Button(); fragmentManager
	 * .addOnBackStackChangedListener(new OnBackStackChangedListener() { public
	 * void onBackStackChanged() { int backCount = getSupportFragmentManager()
	 * .getBackStackEntryCount(); if (backCount == 0) { FragmentTransaction ft =
	 * fragmentManager.beginTransaction(); ft.replace(R.id.Frame_layout,
	 * fragment1).addToBackStack(null);
	 * 
	 * ft.commit(); } } });
	 * 
	 * }
	 */
}
