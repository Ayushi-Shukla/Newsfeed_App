package com.newsTest.serviceTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.news.entity.*;
import com.news.repository.*;
import com.news.service.*;


public class mockitotest {
	@Mock
	private CusRepository cusrepository;
	
	@Mock
	private SearchRepository searchRepository;

	@InjectMocks
	private SignupService signupservice;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(mockitotest.class);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void SuccessfulSignup()
	{LOGGER.info("test for Successful Signup");
			SignUpStatus expectedStatus = new SignUpStatus();
			expectedStatus.setStatus(true);
			AnalystDetails analystdetails=new AnalystDetails();
			analystdetails.setActiveStatus(true);
			analystdetails.setemail("abcde@gmail.com");
			analystdetails.setPassword("123456");
			analystdetails.setRoles(1);
			analystdetails.setusername("abcd");
			when(cusrepository.existsByemail(analystdetails.getemail())).thenReturn(false);
			SignUpStatus Status = signupservice.register(analystdetails);
			System.out.println("expected: " +expectedStatus);
			System.out.println("actual: " +Status);
			System.out.println(expectedStatus.equals(Status));
			Mockito.verify(cusrepository).save(analystdetails);
			assertEquals(true,expectedStatus.equals(Status));
	}
	
	
	@Test
	public void UnsuccessfulSignupExistingEmail()
	{LOGGER.info("test for unSuccessful Signup with existing email");
		SignUpStatus expectedStatus = new SignUpStatus();
		expectedStatus.setStatus(false);
		AnalystDetails analystdetails=new AnalystDetails();
		analystdetails.setActiveStatus(true);
		analystdetails.setemail("abcd@gmail.com");
		analystdetails.setPassword("123456");
		analystdetails.setRoles(1);
		analystdetails.setusername("abcd");
		when(cusrepository.existsByemail(analystdetails.getemail())).thenReturn(true);
		SignUpStatus Status = signupservice.register(analystdetails);
		System.out.println(expectedStatus.equals(Status));
		assertEquals(true,expectedStatus.equals(Status));
	}
	
	


	
	@Test
	public void saveSearchSucessful()
	{
		Boolean expectedStatus=true;
		AnalystDetails analystdetails=new AnalystDetails();
		SearchedNews searchedNews=new SearchedNews("aa",analystdetails);
		when(cusrepository.existsByemail(searchedNews.getAnalystdetails().getemail())).thenReturn(true);
		Boolean status=signupservice.storeSearch(searchedNews);
		assertEquals(true,expectedStatus.equals(status));
	}
	
	
	
	@Test
	public void UnSucessfulsaveSearch()
	{
		Boolean expectedStatus=true;
		AnalystDetails analystdetails=new AnalystDetails();
		SearchedNews searchedNews=new SearchedNews("aa",analystdetails);
		Boolean status=signupservice.storeSearch(searchedNews);

		when(cusrepository.existsByemail(searchedNews.getAnalystdetails().getemail())).thenReturn(false);
		assertEquals(false,expectedStatus.equals(status));
	}
	
	
	
	
	@Test
	public void getSearchNewsList()
	{
	
	String email="ayushi@gmail.com";
	AnalystDetails analystdetails=cusrepository.findByemail(email);
	List<SearchedNews> searchList = searchRepository.findAllByAnalystdetails(analystdetails);
	when(searchRepository.findAllByAnalystdetails(analystdetails)).thenReturn(searchList);
	Mockito.verify(searchRepository).findAllByAnalystdetails(analystdetails);
	}
	
	
	
	@Test
	public void checkRemove()
	{
		LOGGER.info("Test for removing by id");
		int id= 4;
		when(searchRepository.existsById(id)).thenReturn(true);
		boolean status=signupservice.remove(id);
		assertEquals(true,status==true);
	}
	
	@Test
	public void blacklistUserSucessful()
	{
		LOGGER.info("Test for blacklisting user");
		long id=3;
		Boolean expectedStatus=true;
		AnalystDetails analystdetails=new AnalystDetails();
		when(cusrepository.existsById(id)).thenReturn(true);
		when(cusrepository.findById(id)).thenReturn(analystdetails);
		Boolean actualStatus=signupservice.blacklistUser(id);
		System.out.println(actualStatus);
		assertEquals(expectedStatus, actualStatus);
		Mockito.verify(cusrepository).save(analystdetails);
	}
	
	
	
	@Test 
	public void getUserDetails()
	{	LOGGER.info("Test for getting User details");
		String email="ayushi@gmail.com";
		AnalystDetails analystdetails=signupservice.getUserDetails(email);
		when(cusrepository.findByusername(email)).thenReturn(analystdetails);
		Mockito.verify(cusrepository).findByusername(email);
	}


	
}
