package com.news.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.entity.*;
import com.news.service.*;
import com.news.security.*;

@Controller
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class SignupController extends ErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);

	@Autowired
	private SignupService signupservice;
	@Autowired
	private SignUpStatus signupStatus;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenProvider jwttokenprovider;
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public ResponseEntity<SignUpStatus> register(@Valid @RequestBody AnalystDetails analystDetails) {
		analystDetails.setPassword(passwordEncoder.encode(analystDetails.getPassword()));
		SignUpStatus flag = signupservice.register(analystDetails);
		if (flag.isStatus() == false) {
			return new ResponseEntity<SignUpStatus>(flag, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<SignUpStatus>(flag, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody login login) {
		AnalystDetails analystdetails = signupservice.getUserByemail(login.getEmail());
		System.out.println(analystdetails.isActiveStatus());
		if (analystdetails.isActiveStatus()) {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwttokenprovider.generateToken(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			System.out.println(jwt);

			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
		} else {
			String jwtToken = null;
			return ResponseEntity.ok(new JwtResponse(jwtToken, login.getEmail(), null));
		}
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/searchedText")
	public ResponseEntity<SearchedNews> storeSearch(@RequestBody SearchedNews searchText) {
		System.out.println(searchText.getAnalystdetails());
		AnalystDetails analyst = searchText.getAnalystdetails();
		boolean status = signupservice.storeSearch(searchText);
		if (status)
			return new ResponseEntity<SearchedNews>(HttpStatus.OK);
		else
			return new ResponseEntity<SearchedNews>(HttpStatus.CONFLICT);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("searchList/{UserName}")
	public ResponseEntity<List<SearchedNews>> searchList(@PathVariable("UserName") String UserName) {
		List<SearchedNews> list = signupservice.getSearchNewsList(UserName);
		if (list.size() > 0)
			return new ResponseEntity<List<SearchedNews>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<List<SearchedNews>>(list, HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("remove/{id}")
	public ResponseEntity<HttpStatus> remove(@PathVariable("id") int id) {
		boolean status = signupservice.remove(id);
		if (status == true) {
			String message = "Delete Successfull";
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} else {
			String message = "Delete Unsuccessfull";
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("searchuserList/{Username}")
	public ResponseEntity<List<AnalystDetails>> searchuserlist(@PathVariable("Username") String Username) {
		List<AnalystDetails> list = signupservice.getSearchuserList(Username);
		if (list.size() > 0)
			return new ResponseEntity<List<AnalystDetails>>(list, HttpStatus.OK);
		else
			return new ResponseEntity<List<AnalystDetails>>(list, HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("blacklistuser/{Id}")
	public ResponseEntity<AnalystDetails> blackListUser(@PathVariable("Id") long Id) {
		boolean status = signupservice.blacklistUser(Id);
		if (status)
			return new ResponseEntity<AnalystDetails>(HttpStatus.OK);
		else
			return new ResponseEntity<AnalystDetails>(HttpStatus.CONFLICT);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("getallusers")
	public ResponseEntity<List<AnalystDetails>> getAllUsers() {
		List<AnalystDetails> list = signupservice.getAllUsers();
		return new ResponseEntity<List<AnalystDetails>>(list, HttpStatus.OK);
	}

}
