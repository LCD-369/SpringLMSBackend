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

import com.team.alpha.controller.AdminPublisherController;
import com.team.alpha.service.PublisherService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdminPublisherController.class, secure = false)
public class RestPublisherTest {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private PublisherService publisherservice;

	@Test
	public void retrievePublishers() throws Exception {	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/administrator/publisher/").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		
	}
	

}
