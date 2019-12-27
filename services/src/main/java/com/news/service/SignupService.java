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

import com.news.entity.*;
import com.news.repository.*;

@Service
public class SignupService implements ISignupService, UserDetailsService {
	@Autowired
	private CusRepository cusRepository;

	@Autowired
	private RoleRepository rolerepo;

	@Autowired
	SearchRepository searchrepo;

	SignUpStatus signupStatus = new SignUpStatus();

	public AnalystDetails getUserByemail(String email) {
		AnalystDetails obj = cusRepository.findByemail(email);
		return obj;
	}

	public synchronized SignUpStatus register(AnalystDetails analystDetails) {
		boolean stat = cusRepository.existsByemail(analystDetails.getemail());
		if (stat) {
			SignUpStatus status = new SignUpStatus();
			status.setError("Email Already Exists");
			status.setStatus(false);
			return status;
		} else {
			SignUpStatus status = new SignUpStatus();
			cusRepository.save(analystDetails);
			status.setError("sucess");
			status.setStatus(true);
			return status;
		}
	}

	public synchronized boolean login(login login) {
		List<AnalystDetails> list = cusRepository.findByemailAndPassword(login.getEmail(), login.getPassword());
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean adminlogin(login Login1) {
		if ((Login1.getEmail().equals("admin@gmail.com")) && (Login1.getPassword().equals("admin123"))) {
			return true;
		} else {
			return false;
		}
	}

	public boolean storeSearch(SearchedNews searchText) {

		if ((cusRepository.existsByemail(searchText.getAnalystdetails().getemail()))) {
			searchText.setAnalystdetails(cusRepository.findByemail(searchText.getAnalystdetails().getemail()));
			searchrepo.save(searchText);
			return true;
		} else {
			return false;
		}
	}

	public List<SearchedNews> getSearchNewsList(String userName) {

		AnalystDetails analystdetails = cusRepository.findByemail(userName);
		List<SearchedNews> searchList = searchrepo.findAllByAnalystdetails(analystdetails);
		return searchList;

	}

	public List<AnalystDetails> getSearchuserList(String username) {
		List<AnalystDetails> searchList1 = cusRepository.findByUsername(username);
		return searchList1;
	}

	public boolean remove(int id) {
		boolean status = searchrepo.existsById(id);
		if (status == true) {
			searchrepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public AnalystDetails getUserDetails(String username) {
		AnalystDetails userDetails = cusRepository.findByusername(username);
		return userDetails;
	}

	public boolean blacklistUser(long Id) {
		AnalystDetails analyst = cusRepository.findById(Id);
		if (analyst != null) {
			boolean changedStatus = !analyst.getActiveStatus();
			analyst.setActiveStatus(changedStatus);
			cusRepository.save(analyst);
//			if (analystdetails1.getActiveStatus()) {
//				analystdetails1.setActiveStatus(false);
//				cusRepository.save(analystdetails1);
//			} else {
//				analystdetails1.setActiveStatus(true);
//				cusRepository.save(analystdetails1);
//			}
			return true;
		} else
			return false;
	}

	public List<AnalystDetails> getAllUsers() {
		List<AnalystDetails> list = new ArrayList<>();
		cusRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AnalystDetails analystdetails = cusRepository.findByemail(email);
		Roles roles = rolerepo.findById(analystdetails.getRoles());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
		User user = new User(analystdetails.getemail(), analystdetails.getPassword(), authorities);
		return user;
	}

}
