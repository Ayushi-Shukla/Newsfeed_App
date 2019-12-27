package com.newsTest.serviceTest;
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

import com.news.dao.*;
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
	{
		SignUpStatus expectedStatus = new SignUpStatus();
		expectedStatus.setStatus(true);
		AnalystDetails analystdetails=new AnalystDetails();
		analystdetails.setActiveStatus(true);
		analystdetails.setemail("qqq@gmail.com");
		analystdetails.setPassword("qqq111");
		analystdetails.setRoles(1);
		analystdetails.setusername("qqq");
		when(cusrepository.existsByemail(analystdetails.getemail())).thenReturn(true);
		SignUpStatus actualStatus = signupservice.register(analystdetails);
		System.out.println("expected: "+expectedStatus);
		System.out.println("actual: "+actualStatus);
		System.out.println(expectedStatus.equals(actualStatus));
		Mockito.verify(cusrepository).save(analystdetails);
		assertEquals(true,expectedStatus.equals(actualStatus));
	}
	
	
	@Test
	public void unSuccessfulSignup()
	{
		SignUpStatus expectedStatus = new SignUpStatus();
		expectedStatus.setStatus(false);
		AnalystDetails analystdetails = new AnalystDetails();
		analystdetails.setActiveStatus(true);
		analystdetails.setemail("ayushi@gmail.com");
		analystdetails.setPassword("qqq111");
		analystdetails.setRoles(1);
		analystdetails.setusername("qqq");
		when(cusrepository.existsByemail(analystdetails.getemail())).thenReturn(true);
		SignUpStatus actualStatus = signupservice.register(analystdetails);
		System.out.println("expected "+expectedStatus);
		System.out.println("actual "+actualStatus);
		System.out.println(expectedStatus.equals(actualStatus));
		assertEquals(true,expectedStatus.equals(actualStatus));
	}

	
	@Test
	public void saveSearchSucessful()
	{
		Boolean expectedStatus=true;
		SearchedNews searchedNews=new SearchedNews();
		searchedNews.setUserName("jack@jack.com");
		searchedNews.setSearchWords("vehicle");
		when(searchRepository.existsByUserNameAndSearchWords("jack@jack.com","vehicle")).thenReturn(false);
		Boolean status=signupservice.storeSearch(searchedNews);
		assertEquals(true,expectedStatus.equals(status));
	}
	
	@Test
	public void UnSucessfulsaveSearch()
	{
		Boolean expectedStatus=false;
		System.out.println("expectedStatus "+expectedStatus);
		SearchedNews searchedNews=new SearchedNews();
		searchedNews.setUserName("ayushi@gmail.com");
		searchedNews.setSearchWords("we");
		when(searchRepository.existsByUserNameAndSearchWords(searchedNews.getSearchWords(),searchedNews.getUserName()))
		.thenReturn(true);
		Boolean actualstatus=signupservice.storeSearch(searchedNews);
		System.out.println("actualstatus "+actualstatus);
		assertEquals(false,expectedStatus.equals(actualstatus));
	}
	
	@Test
	public void searchWordSaved()
	{
		SearchedNews searchedNews=new SearchedNews();
		Boolean expectedStatus=true;
		when(searchRepository.existsByUserNameAndSearchWords(searchedNews.getUserName(),searchedNews.getSearchWords())).thenReturn(false);
		Boolean Status = signupservice.storeSearch(searchedNews);
		System.out.println(expectedStatus.equals(Status));
		Mockito.verify(searchRepository).save(searchedNews);
		assertEquals(true,expectedStatus.equals(Status));
	}
	
	@Test
	public void checkgetSearchList()
	{
		String email="aaaaa@gmail.com";
		signupservice.getSearchList(email);
		Mockito.verify(searchRepository).findByuserName(email);
	}
	
	@Test
	public void checkFindByEmail()
	{
		AnalystDetails analystdetails = new AnalystDetails();
		analystdetails.setemail("abcd@gmail.com");
		signupservice.getUserByemail(analystdetails.getemail());
		when(cusrepository.findByemail(analystdetails.getemail())).thenReturn(analystdetails);
		Mockito.verify(cusrepository).findByemail(analystdetails.getemail());
	}
	
	
}
