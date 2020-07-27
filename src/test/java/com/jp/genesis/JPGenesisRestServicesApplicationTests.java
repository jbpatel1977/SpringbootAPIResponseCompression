package com.jp.genesis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import com.jp.genesis.controller.EmployeeRestController;


//@ExtendWith(SpringExtension.class)

@SpringBootTest
//@ActiveProfiles("integrationtest")
@Profile("integrationtest")
class JPGenesisRestServicesApplicationTests {


	@Autowired
	private EmployeeRestController employeeRestController;


//	  @Test
//	  void applicationContextLoads() {
//	  }

	@Test
	public void contexLoads() throws Exception {
		assertThat(employeeRestController).isNotNull();

	}



}
