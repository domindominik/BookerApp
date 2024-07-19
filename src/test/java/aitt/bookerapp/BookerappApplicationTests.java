package aitt.bookerapp;

import aitt.bookerapp.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //https://github.com/spring-io/start.spring.io/issues/1247
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class BookerappApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(BookerappApplicationTests.class);

	@MockBean
	private AuthenticationProvider authenticationProvider;

	@MockBean
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Test
	void contextLoads() {
		logger.info("Starting contextLoads test");
		assertThat(authenticationProvider).isNotNull();
		assertThat(userDetailsService).isNotNull();
		assertThat(authenticationManager).isNotNull();
		logger.info("Finished contextLoads test");
	}
}
