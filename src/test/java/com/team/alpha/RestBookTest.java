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

import com.team.alpha.controller.AdminBookController;
import com.team.alpha.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminBookController.class, secure = false)
public class RestBookTest {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private BookService bookservice;

	@Test
	public void retrieveBooks() throws Exception {	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/administrator/book/").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
	}

}
