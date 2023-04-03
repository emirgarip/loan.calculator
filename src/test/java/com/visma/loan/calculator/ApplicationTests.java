package com.visma.loan.calculator;

import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads(ApplicationContext context) {
		assertNotNull(context);
	}

}
