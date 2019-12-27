package com.newsTest.controllertest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.news.MyApplication;
import com.news.controller.SignupController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MyApplication.class)

public class springboottest {
	private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void SuccessfulSignup() throws Exception {
		LOGGER.info("Testing for Succuessful Signup..");
		String testData = "{\"username\":\"ghghjyjyt\"" + ","
	            + "\"email\":\"llll@gmail.com\"" + "," + "\"password\":\"sdgfrr\"" + "," + "\"activeStatus\":\"true\"}";
		LOGGER.debug("Test Data -> {}", testData);
		mockMvc.perform(post("/api/signup").content(testData).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());
	}

	@Test
	public void UnsuccessfulSignupExistingEmail() throws Exception {
		LOGGER.info("Testing for unsuccessful Signup with existing email..");
		String testData = "{\"username\":\"tdswdest\"" + ","
	            + "\"email\":\"ayushi@gmail.com\"" + "," + "\"password\":\"tesxsdct123455\"" + "," + "\"activeStatus\":\"true\"}";
		LOGGER.debug("Test Data -> {}", testData);
		mockMvc.perform(post("/signup").content(testData).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isConflict());
	}
	
	
	@Test
	public void UnsuccessfulSignupEmptyEmail() throws Exception {
		LOGGER.info("Testing for unsuccessful Signup with empty email field..");  
			String testData = "{\"email\":\"\"" + "," + "\"username\":\"abc\"" + "," + "\"activeStatus\":\"true\"" + ","
			            + "\"password\":\"1234567\"}"; 
		LOGGER.debug("Test Data -> {}", testData);
		mockMvc.perform(post("/signup").content(testData).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void invalidEmail() throws Exception {		
		String testData = "{\"email\":\"ayushigmail.com.com\"" + "," + "\"userName\":\"abc\"" + "," + "\"roles\":\"2\""
				+ "," + "\"activeStatus\":\"true\"" + ","
	            + "\"password\":\"ayushi123\"}";
		LOGGER.debug("Test Data -> {}", testData);
		mockMvc.perform(post("/api/signup").content(testData).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void allEmptyFieldsSignup() throws Exception {
		String testData = "{\"email\":\"\"" + "," + "\"userName\":\"\"" + "," + "\"roles\":\"\""
				+ "," + "\"activeStatus\":\"\"" + ","
	            + "\"password\":\"\"}";
		LOGGER.debug("Test Data -> {}", testData);
		mockMvc.perform(post("/api/signup").content(testData).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void SuccessfulLogin()throws Exception
	{String testData = "{\"email\":\"ayushi@gmail.com\"" + ","  + "\"password\":\"ayushi123\"}";
		LOGGER.debug("Test Data -> {}", testData);
		mockMvc.perform(post("/api/login").content(testData).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void unSuccessfulLogin()throws Exception
	{
		String testData = "{\"email\":\"vv@gmail.com\"" + "," + "\"password\":\"vvvvvv\"}"; 
		mockMvc.perform(post("/api/login").content(testData).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isConflict());
	}

	
	@Test
	public void sucessfulSave()throws Exception
	{
		String testData = "{\"userName\":\"bat@bat.com\"" + "," + "\"searchWords\":\"bbbbbb\"}"; 
		mockMvc.perform(post("/api/searchedText").content(testData).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getUserList() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/searchUser/{userName}","ayushi@gmail.com").contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk());	
	}
	
	@Test
	public void unSucessfullSave() throws Exception
	{
	String testData = "{\"userName\":\"ayushi@gmail.com\"" + "," + "\"searchwords\":\"you\"}"; 
		mockMvc.perform(post("/api/searchedText").content(testData).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isInternalServerError());
	}
	
	@Test
	public void emptySearchWord() throws Exception
	{
	String testData = "{\"userName\":\"ayushi@gmail.com\"" + "," + "\"searchwords\":\"\"}"; 
		mockMvc.perform(post("/api/searchedText").content(testData).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isInternalServerError());
	}
	
	
	@Test
	public void getSearchList() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/searchList/{UserName}","ayushi@gmail.com").contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk());
	}
	
	
	@Test
	public void emptySearchList() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/searchList/{UserName}","a@gmail.com").contentType("application/json;charset=UTF-8"))
		.andExpect(status().isNoContent());
	}
	
	@Test
	public void removeSearchItemsucessfull() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/remove/{id}",28).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void unSucessfullremoveSearchItem() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/remove/{id}",28).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isConflict());
	}
	
	@Test
	public void blackListUser() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/blacklistuser/{id}",27).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk());	
	}
	
	@Test
	public void blackListNonExistingUser() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/blacklistuser/{id}",273435).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isConflict());	
	}
	
	
	
}
