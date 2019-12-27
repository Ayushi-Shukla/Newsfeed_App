package com.news.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.*;


@Repository
public interface SearchRepository  extends CrudRepository<SearchedNews,Integer> {
	
	
	//boolean existsByEmailAndSearchwords(String email,String searchwords);
	//List<SearchedNews> findByemail(String userName);
	List<SearchedNews> findAllByAnalystdetails(AnalystDetails analyst);

}
