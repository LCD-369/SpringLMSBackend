package com.team.alpha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.team.alpha.controller.AdminAuthorController;
import com.team.alpha.service.AuthorService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminAuthorController.class, secure = false)
public class RestAuthorTest {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private AuthorService authorservice;

	
	@Test
	public void retrieveAuthors() throws Exception {	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/administrator/author/").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
	}

}
