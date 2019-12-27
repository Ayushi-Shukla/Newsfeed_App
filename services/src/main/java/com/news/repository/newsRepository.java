package com.news.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.news.entity.*;

public interface newsRepository extends CrudRepository<SearchedNews, Long>  {
	
    List<SearchedNews> findBysearchwords(String searchWords);

}
