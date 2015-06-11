package com.myapp.cktscore.view;

public class navDrawerItem {
	private String title;
	private int icon;
	private String count = "0";
	private Boolean counterVisible = false;

	public navDrawerItem(String title, int icon, String count,
			Boolean counterVisible) {
		super();
		this.title = title;
		this.icon = icon;
		this.count = count;
		this.counterVisible = counterVisible;
	}

	public navDrawerItem(String title, int icon) {
		super();
		this.title = title;
		this.icon = icon;
	}

	public navDrawerItem() {
		super();
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public Boolean getCounterVisible() {
		return counterVisible;
	}

	public void setCounterVisible(Boolean counterVisible) {
		this.counterVisible = counterVisible;
	}
}
