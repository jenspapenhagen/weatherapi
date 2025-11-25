package de.papenhagen.weatherapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.restclient.autoconfigure.RestClientAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {
        RestClientAutoConfiguration.class
})
class WeatherapiApplicationTests {

	@Test
	void contextLoads() {
	}

}
