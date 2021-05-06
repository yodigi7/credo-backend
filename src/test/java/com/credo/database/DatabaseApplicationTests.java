package com.credo.database;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-local.properties")
class DatabaseApplicationTests {
	@Test
	void contextLoads() {
	}
}
