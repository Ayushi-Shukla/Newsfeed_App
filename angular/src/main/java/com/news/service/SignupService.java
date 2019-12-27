package com.news.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.news.dao.RegisterDao;
import com.news.entity.*;
import com.news.repository.*;



@Service
public class SignupService implements ISignupService,UserDetailsService{
	@Autowired
	private CusRepository cusRepository;
//	@Autowired
//	private newsRepository newsRepository;
	@Autowired
	private RoleRepository rolerepo;
	@Autowired
	private RegisterDao registerDao;
	@Autowired
	SearchRepository searchrepo;
	
	SignUpStatus signupStatus = new SignUpStatus();

	public AnalystDetails getUserByemail(String email) {
		AnalystDetails obj = cusRepository.findByemail(email);
		return obj;
	}	
	
	public synchronized SignUpStatus register(AnalystDetails analystDetails){
		boolean stat=cusRepository.existsByemail(analystDetails.getemail());
		if(stat)
		{	SignUpStatus status=new SignUpStatus();
			status.setError("fghfh");
			System.out.println("1");
			status.setError("Email Already Exists");
			status.setStatus(false);
			return status;
		}
		else
		{SignUpStatus status=new SignUpStatus();
		cusRepository.save(analystDetails);
		status.setError("sucess");
		status.setStatus(true);
			return status;
		}
	}
	
	public synchronized boolean login(login login){
	   List<AnalystDetails> list = cusRepository.findByemailAndPassword(login.getEmail(), login.getPassword()); 	
     if (list.size() > 0) {
  	   return true;
     } else {
  	   return false;
     }   
		}
	
	public boolean adminlogin(login Login1) {
		List<AnalystDetails> list = cusRepository.findByemailAndPassword(Login1.getEmail(), Login1.getPassword());
        System.out.println(Login1.getEmail());
        System.out.println("hello");

		if ((Login1.getEmail().equals("admin@gmail.com"))&&(Login1.getPassword().equals("admin123"))){
			return true;
			
		}
		else {
		  	   return false;
		     }
		}
	
	
	public boolean storeSearch(SearchedNews searchText){
	boolean stat=searchrepo.existsByUserNameAndSearchWords(searchText.getUserName(), searchText.getSearchWords());
	if(stat)
	{		return false;	
	}
	else
	{
		searchrepo.save(searchText);
			return true;
	}	
	}
	
	
	public List<SearchedNews> getSearchList(String userName) {
		List<SearchedNews> searchList = searchrepo.findByuserName(userName);
		return searchList ;
	}
	
	public boolean remove(int id)
	{
	
		if (searchrepo.existsById(id))
		{
			searchrepo.deleteById(id);
			if (searchrepo.existsById(id))
			return true ;
			else
				return false;
		}
		else 
		{	return true;
		}
	}
	
	
	public AnalystDetails getUserDetails(String username)
	{
		AnalystDetails userDetails=cusRepository.findByusername(username);
		return userDetails;
	}
	
	public boolean blacklistUser(long Id)
	{
		if(cusRepository.existsById(Id))
		{
			AnalystDetails analystdetails1=cusRepository.findById(Id);
			if(analystdetails1.getActiveStatus()==true)
			{
				analystdetails1.setActiveStatus(false);
				cusRepository.save(analystdetails1);
			}
			else if (analystdetails1.getActiveStatus()==false)
			{
			analystdetails1.setActiveStatus(true);
			cusRepository.save(analystdetails1);
			}
		return true;
		}
		else
		return false;
	}

	
	

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	AnalystDetails analystdetails=cusRepository.findByemail(email);
	Roles roles=rolerepo.findById(analystdetails.getRoles());
	List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
	authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
	User user=new User(analystdetails.getemail(),analystdetails.getPassword(),authorities);
	return user;
	
}
}

	

