package com.newsTest.controllertest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.springframework.http.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



import com.news.MyApplication;
import com.news.controller.*;
import com.news.entity.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MyApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)

public class springboottest {
	private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoYXRAZ21haWwuY29tIiwiaWF0IjoxNTUxNzc1OTkxLCJleHAiOjE1NjA0MTU5OTF9.14k_G_6uQVxCRoO7DDx9s6PXOP052O4P7GrVZSgnQLXNhXsyuZD_zNjpHnZpa0Fz5UCokpgfmgx3179a6Yr0ag";
	String admintoken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE1NTE3NzgwNzQsImV4cCI6MTU2MDQxODA3NH0.MRLpT8F63mGJjXS7oUo3rJzvY_cKpBjL5NXSAr5FLi_9TsUeAa4lixLhiGRZI9rmkEQ6oQ0l41DYYPk54KmfbQ";
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	

	
	@Test
	public void SuccessfulSignup() throws Exception {
		AnalystDetails analystdetails = new AnalystDetails("ccc","cufcdd@gmail.com","ccc123",true,2);
		ResponseEntity<SignUpStatus> response = restTemplate.postForEntity("/api/signup", analystdetails, SignUpStatus.class);
		LOGGER.info("Response->{}",response.getBody().isStatus());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
		assertThat(response.getBody().getError(),containsString("sucess"));
	}
	
	
	
	
	@Test
	public void UnsuccessfulSignupExistingEmail() throws Exception {
		AnalystDetails analystdetails = new AnalystDetails("aass","aass@gmail.com","aass123",true,2);
		ResponseEntity<SignUpStatus> response = restTemplate.postForEntity("/api/signup", analystdetails, SignUpStatus.class);
		LOGGER.info("Response->{}",response.getBody().isStatus());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.CONFLICT));
		assertThat(response.getBody().getError(),containsString("Email Already Exists"));
	}
	
	
	@Test
	public void UnsuccessfulSignupEmptyEmail() throws Exception {
		AnalystDetails analystdetails = new AnalystDetails("aass","","aass123",true,2);
		ResponseEntity<SignUpStatus> response = restTemplate.postForEntity("/api/signup", analystdetails, SignUpStatus.class);
		LOGGER.info("Response->{}",response.getBody().isStatus());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.BAD_REQUEST));
	}
	
	
	
	
	@Test
	public void invalidEmailSignup() throws Exception {		
		AnalystDetails analystdetails = new AnalystDetails("ayushi","ayushigmail","aass123",true,2);
		ResponseEntity<SignUpStatus> response = restTemplate.postForEntity("/api/signup", analystdetails, SignUpStatus.class);
		LOGGER.info("Response->{}",response.getBody().isStatus());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.BAD_REQUEST));
	}
	
	
	
	
	@Test
	public void UnsuccessfulSignupMinimunLengthUsername() throws Exception {
		AnalystDetails analystdetails = new AnalystDetails("ay","ayushiii@gmail","aass123",true,2);
		ResponseEntity<SignUpStatus> response = restTemplate.postForEntity("/api/signup", analystdetails, SignUpStatus.class);
		LOGGER.info("Response->{}",response.getBody().isStatus());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.BAD_REQUEST));
	}
	
	
	
	
	@Test
	public void UnsuccessfulSignupMaximumLengthUsername() throws Exception {
		AnalystDetails analystdetails = new AnalystDetails("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","ayushiii@gmail","aass123",true,2);
		ResponseEntity<SignUpStatus> response = restTemplate.postForEntity("/api/signup", analystdetails, SignUpStatus.class);
		LOGGER.info("Response->{}",response.getBody().isStatus());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.BAD_REQUEST));
	}
	
	
	
	
	@Test
	public void UnsuccessfulSignupEmptyPassword() throws Exception {
		AnalystDetails analystdetails = new AnalystDetails("ayushi","ayushiii@gmail","",true,2);
		ResponseEntity<SignUpStatus> response = restTemplate.postForEntity("/api/signup", analystdetails, SignUpStatus.class);
		LOGGER.info("Response->{}",response.getBody().isStatus());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.BAD_REQUEST));
	}
	
	
	
	
	@Test
	public void SuccessfulUserLogin()throws Exception
	{
	AnalystDetails analystdetails = new AnalystDetails("ayushi@gmail.com","ayushi123");
	ResponseEntity<Void> response = restTemplate.postForEntity("/api/login", analystdetails, Void.class);
	assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	
	
	
	@Test
	public void unSuccessfulUserLogin()throws Exception
	{
		AnalystDetails analystdetails = new AnalystDetails("ayushiii@gmail.com","ayushi123");
		ResponseEntity<Void> response = restTemplate.postForEntity("/api/login", analystdetails, Void.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	
	
	
	@Test
	public void unSuccessfulLoginEmptyEmail()throws Exception
	{
		AnalystDetails analystdetails = new AnalystDetails("","ayushi123");
		ResponseEntity<Void> response = restTemplate.postForEntity("/api/login", analystdetails, Void.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	
	
	@Test
	public void unSuccessfulLoginEmptyPassword()throws Exception
	{
		AnalystDetails analystdetails = new AnalystDetails("ayushi@gmail.com","");
		ResponseEntity<Void> response = restTemplate.postForEntity("/api/login", analystdetails, Void.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	
	
	
	@Test
	public void unSuccessfulLoginEmptyEmailandPassword()throws Exception
	{
		AnalystDetails analystdetails = new AnalystDetails("","ayushi123");
		ResponseEntity<Void> response = restTemplate.postForEntity("/api/login", analystdetails, Void.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	
	
	
	
	
	@Test
	public void SuccessfulAdminLogin()throws Exception
	{
	AnalystDetails analystdetails = new AnalystDetails("admin@gmail.com","admin123");
	ResponseEntity<Void> response = restTemplate.postForEntity("/api/login", analystdetails, Void.class);
	assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	
	
	
	@Test
	public void saveSearchedNewsSuccessfulByUser()throws Exception
	{	
		AnalystDetails analystdetails = new AnalystDetails("ayushi@gmail.com","ayushi123");
		SearchedNews search = new SearchedNews();
		search.setSearchwords("aaaaa");
		search.setAnalystdetails(analystdetails);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+token);
		HttpEntity<?> entity = new HttpEntity<Object>(search,headers);
		ResponseEntity<SearchedNews> response = restTemplate.postForEntity("/api/searchedText",  entity, SearchedNews.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	
	
	
	@Test
	public void saveSearchedNewsSuccessfulByAdmin()throws Exception
	{	
		AnalystDetails analystdetails = new AnalystDetails("admin@gmail.com","admin123");
		SearchedNews search = new SearchedNews();
		search.setSearchwords("aaaaa");
		search.setAnalystdetails(analystdetails);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+admintoken);
		HttpEntity<?> entity = new HttpEntity<Object>(search,headers);
		ResponseEntity<SearchedNews> response = restTemplate.postForEntity("/api/searchedText",  entity, SearchedNews.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	
	
	@Test
	public void saveSearchedNewsUnsuccessful() throws Exception
	{
		//Without using token
		AnalystDetails analystdetails = new AnalystDetails("ayushi@gmail.com","ayushi123");
		SearchedNews search = new SearchedNews();
		search.setSearchwords("aaaaa");
		search.setAnalystdetails(analystdetails);
		ResponseEntity<Void> response = restTemplate.postForEntity("/api/searchedText",search,Void.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.UNAUTHORIZED));
	}
	
	
	
	
	@Test
	public void emptySearchedWord() throws Exception
	{
		AnalystDetails analystdetails = new AnalystDetails("ayushi@gmail.com","ayushi123");
		SearchedNews search = new SearchedNews();
		search.setSearchwords("");
		search.setAnalystdetails(analystdetails);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+token);
		HttpEntity<?> entity = new HttpEntity<Object>(search,headers);
		ResponseEntity<SearchedNews> response = restTemplate.postForEntity("/api/searchedText",  entity, SearchedNews.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	
	
	
	@Test
	public void getUserListbyAdmin() throws Exception
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+admintoken);
		HttpEntity<?> entity = new HttpEntity<Object>(null,headers);
		ResponseEntity<SearchedNews[]> response = restTemplate.exchange("/api/searchuserList/{Username}", HttpMethod.GET, entity, SearchedNews[].class, "ayushi");
		List<SearchedNews> list = Arrays.asList(response.getBody());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	
	@Test
	public void getAllUserListbyAdmin() throws Exception
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+admintoken);
		HttpEntity<?> entity = new HttpEntity<Object>(null,headers);
		ResponseEntity<SearchedNews[]> response = restTemplate.exchange("/api/getallusers", HttpMethod.GET, entity, SearchedNews[].class, "ayushi");
		List<SearchedNews> list = Arrays.asList(response.getBody());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	
	
	@Test
	public void getSavedNewsListByUser() throws Exception
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+token);
		HttpEntity<?> entity = new HttpEntity<Object>(null,headers);
		ResponseEntity<SearchedNews[]> response = restTemplate.exchange("/api/searchList/{UserName}", HttpMethod.GET, entity, SearchedNews[].class, "ayushi@gmail.com");
		List<SearchedNews> list = Arrays.asList(response.getBody());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	
	@Test
	public void getSavedNewsListByAdmin() throws Exception
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+admintoken);
		HttpEntity<?> entity = new HttpEntity<Object>(null,headers);
		ResponseEntity<SearchedNews[]> response = restTemplate.exchange("/api/searchList/{UserName}", HttpMethod.GET, entity, SearchedNews[].class, "ayushi@gmail.com");
		List<SearchedNews> list = Arrays.asList(response.getBody());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	@Test
	public void removeSearchItemSucessfullByUser() throws Exception
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+token);
		HttpEntity<?> entity = new HttpEntity<Object>(null,headers);
		ResponseEntity<SearchedNews> response = restTemplate.exchange("/api/remove/{id}", HttpMethod.GET, entity, SearchedNews.class, 5);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	@Test
	public void removeSearchItemSucessfullByAdmin() throws Exception
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+admintoken);
		HttpEntity<?> entity = new HttpEntity<Object>(null,headers);
		ResponseEntity<SearchedNews> response = restTemplate.exchange("/api/remove/{id}", HttpMethod.GET, entity, SearchedNews.class, 5);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	
	@Test
	public void blackListUserByAdmin() throws Exception
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+admintoken);
		HttpEntity<?> entity = new HttpEntity<Object>(null,headers);
		ResponseEntity<SearchedNews> response = restTemplate.exchange("/api/blacklistuser/{id}", HttpMethod.GET, entity, SearchedNews.class, 6);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	
	
	@Test
	public void blackListUserByUser() throws Exception
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+token);
		HttpEntity<?> entity = new HttpEntity<Object>(null,headers);
		ResponseEntity<SearchedNews> response = restTemplate.exchange("/api/blacklistuser/{id}", HttpMethod.GET, entity, SearchedNews.class, 6);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	
	
	
	@Test
	public void blackListNonExistingUser() throws Exception
	{
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+admintoken);
		HttpEntity<?> entity = new HttpEntity<Object>(null,headers);
		ResponseEntity<SearchedNews> response = restTemplate.exchange("/api/blacklistuser/{id}", HttpMethod.GET, entity, SearchedNews.class, 86);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.CONFLICT));
	}
	
	
	
}
