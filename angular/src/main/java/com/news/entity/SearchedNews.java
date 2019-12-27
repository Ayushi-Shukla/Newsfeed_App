package com.news.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="news_search")
public class SearchedNews {
@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
 int id;
private String userName;
@Column(unique = true)
@NotBlank
private String searchWords;


public String getUserName() {
	return userName;
}
public SearchedNews()
{
	
}
public SearchedNews(String userName, String searchWords) {
	super();
	this.userName = userName;
	this.searchWords = searchWords;
}

public void setUserName(String userName) {
	this.userName = userName;
}


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getSearchWords() {
	return searchWords;
}

public void setSearchWords(String searchWords) {
	this.searchWords = searchWords;
}
}
