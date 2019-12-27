package com.news.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.entity.AnalystDetails;
import com.news.entity.SearchedNews;
import com.news.entity.SignUpStatus;
import com.news.entity.login;
import com.news.service.SignupService;

@Controller
@CrossOrigin("http://localhost:4200")
@RequestMapping ("/api")
public class SignupController extends ErrorHandler {
	@Autowired
	private SignupService signupservice;
	@Autowired
	private SignUpStatus signupStatus;
	
	@PostMapping("/signup")
	public ResponseEntity<SignUpStatus> register(@Valid @RequestBody AnalystDetails analystDetails){
		SignUpStatus flag = signupservice.register(analystDetails);
        if (flag.isStatus() == false) {
        	return new ResponseEntity<SignUpStatus>(flag, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<SignUpStatus>(flag, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<SignUpStatus> login(@RequestBody login login) {
        SignUpStatus status = new SignUpStatus();
        boolean flag = signupservice.login(login);
        if (flag == false) {
        	status.setStatus(false);
        	status.setError("fail");
        	return new ResponseEntity<SignUpStatus>(status,HttpStatus.CONFLICT);
        }
        else{
               
        	status.setStatus(true);
        	status.setError("success");
        return new ResponseEntity<SignUpStatus>(status,HttpStatus.OK);}
    }
	
	@PostMapping("/adminlogin")
	public ResponseEntity<SignUpStatus> adminlogin(@RequestBody login Login1) {
        SignUpStatus status = new SignUpStatus();
        System.out.println(Login1.getEmail());
        boolean flag = signupservice.adminlogin(Login1);
        if (flag == false) {
        	status.setStatus(false);
        	status.setError("fail");
        	return new ResponseEntity<SignUpStatus>(status,HttpStatus.CONFLICT);
        }
        else{
               
        	status.setStatus(true);
        	status.setError("success");
        return new ResponseEntity<SignUpStatus>(status,HttpStatus.OK);}
    }
	
	
	@PostMapping("/searchedText")
	public ResponseEntity<SearchedNews> storeSearch(@RequestBody SearchedNews searchText)
	{
		boolean status=signupservice.storeSearch(searchText);
		if (status)
       	return new ResponseEntity<SearchedNews>(HttpStatus.OK);
		else
	    return new ResponseEntity<SearchedNews>(HttpStatus.CONFLICT);
	}
	
	
	@GetMapping("searchList/{UserName}")
	public ResponseEntity<List<SearchedNews>> searchList(@PathVariable("UserName") String UserName)
	{
		List<SearchedNews> list = signupservice.getSearchList(UserName);
		if(list.size()>0)
		return new ResponseEntity<List<SearchedNews>>(list, HttpStatus.OK);
		else
		return new ResponseEntity<List<SearchedNews>>(list, HttpStatus.NO_CONTENT);

	}
	
	@GetMapping("remove/{id}")
	public ResponseEntity<SearchedNews> remove(@PathVariable("id") int id)
	{
		if (signupservice.remove(id))
	        return new ResponseEntity<SearchedNews>(HttpStatus.CONFLICT);

		else
			return new ResponseEntity<SearchedNews>(HttpStatus.OK);

	}
	
	
	@GetMapping("searchUser/{Username}")
	public ResponseEntity<AnalystDetails> searchUser(@PathVariable("Username") String Username)
	{
		AnalystDetails list=signupservice.getUserDetails(Username);
		return new ResponseEntity<AnalystDetails>(list, HttpStatus.OK);
	}
	
	@GetMapping("blacklistuser/{Id}")
	public ResponseEntity<AnalystDetails> blackListUser(@PathVariable("Id") long Id)
	{
		boolean status=signupservice.blacklistUser(Id);
		if (status)
			return new ResponseEntity<AnalystDetails>(HttpStatus.OK);
		else
			return new ResponseEntity<AnalystDetails>(HttpStatus.CONFLICT);
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	