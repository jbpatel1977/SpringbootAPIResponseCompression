package com.jp.genesis.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.genesis.controller.EmployeeRestController;
import com.jp.genesis.model.Employee;

@SpringBootTest
@AutoConfigureMockMvc
//@ActiveProfiles("devtest")
public class EmployeeWebLayerTest {
	public static final Logger LOG = LoggerFactory.getLogger(EmployeeWebLayerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void healthCheckShouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/jp/genesis/healthcheck")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("applications is up")));
	}

	@Test
	public void getEmployeeWithSuccess() throws Exception {
		ResultActions resultActions = this.mockMvc.perform(get("/jp/genesis/employees/10010"));
		
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		MockHttpServletResponse serlvetResponse = resultActions.andReturn().getResponse();
		
		assertThat(serlvetResponse.getStatus()).isEqualTo(200);
		
		ObjectMapper objectMapper = new ObjectMapper();		
		// Convert JSON string into Employee Object
		Employee empResponse = objectMapper.readValue(serlvetResponse.getContentAsString(), Employee.class);
		assertThat(empResponse.getEmp_no()).isEqualTo(10010);
		
		
		// Compare object to object
		Employee mockEmp = new Employee(10010, new Date(1963,6,1),  "Duangkaew", "Piveteau", 'F', new Date(1989,8,24));		
		assertThat(empResponse).isEqualToComparingOnlyGivenFields(mockEmp, "emp_no", "first_name", "last_name", "gender");
		

	}
}

