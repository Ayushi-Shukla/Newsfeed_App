package com.news.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="news_search")
public class SearchedNews {


@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
 int id;


@NotBlank
private String searchwords; 

@ManyToOne
private AnalystDetails analystdetails;


public SearchedNews()
{
	
}

public SearchedNews(@NotBlank String searchwords, AnalystDetails analystdetails) {
	this.searchwords = searchwords;
	this.analystdetails=analystdetails;
}


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public AnalystDetails getAnalystdetails() {
	return analystdetails;
}

public void setAnalystdetails(AnalystDetails analystdetails) {
	this.analystdetails = analystdetails;
}

public String getSearchwords() {
	return searchwords;
}


public void setSearchwords(String searchwords) {
	this.searchwords = searchwords;
}


}
