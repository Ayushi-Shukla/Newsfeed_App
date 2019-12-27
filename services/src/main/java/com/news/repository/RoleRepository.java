package com.news.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.*;
@Repository
public interface RoleRepository  extends CrudRepository<Roles,Integer> {
	Roles findById(int roleid);
}
