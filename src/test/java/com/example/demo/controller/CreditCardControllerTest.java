package com.example.demo.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.CreditcardtestApplication;
import com.example.demo.model.CreditCard;
import com.example.demo.service.CreditCardService;



@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CreditCardController.class)
@WithMockUser
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = CreditcardtestApplication.class)
@AutoConfigureMockMvc
public class CreditCardControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CreditCardService ccService;
	
	CreditCard ccDetails = new CreditCard("123456789007", "john sutter", "$0");

	String exampleCCJson = "{\"cardnumber\":\"123456789007\",\"cardname\":\"john sutter\",\"creditlimit\":\"$0\"}";

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		Mockito.when(((OngoingStubbing) ccService.list()).thenReturn(ccService.list()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/v1/cc/list").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"cardnumber\":\"123456789007\",\"cardname\":\"john sutter\",\"creditlimit\":\"$0\"}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void createStudentCourse() throws Exception {
		CreditCard ccDetails = new CreditCard("123456789007", "john sutter", "$0");
		
		String exampleCCJson = "{\"cardnumber\":\"123456789007\",\"cardname\":\"john sutter\",\"creditlimit\":\"$0\"}";

		// credit card Service to respond back with mock credit card
		Mockito.when(((OngoingStubbing) ccService.list()).thenReturn(ccService.list()));

		// Send credit card
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/v1/cc/add")
				.accept(MediaType.APPLICATION_JSON).content(exampleCCJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		assertEquals("localhost:8080/api/v1/cc/add",
				response.getHeader(HttpHeaders.LOCATION));

	}
	
}
