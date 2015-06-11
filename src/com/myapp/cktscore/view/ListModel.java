package com.myapp.cktscore.view;


public class ListModel{
	private String title= "";
	private String image="";
	private String description= "";
	private Boolean condition ;
	
	// Get and set method for Title
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	// Get and Set method for Image
	public String getImage() {
		return this.image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	// Get and Set method for Description
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getCondition() {
		return condition;
	}
	public void setCondition(Boolean condition) {
		this.condition = condition;
	}

	
}
