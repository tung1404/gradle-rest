package vn.tungnt.service.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vn.tungnt.service.config.CXFApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CXFApp.class)
@WebIntegrationTest("server.port:0")
public class FilmResourceTest {
	
	@Value("${local.server.port}")
	private int port;
	
	private String baseURL;
	
	@Before
	public void setup() {
		this.baseURL = "http://localhost:" + port;
	}

	@Test
	public void asynchronousRequest() {
		ResponseEntity<String> response = new TestRestTemplate()
				.getForEntity(this.baseURL + "/services/films", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertEquals("Hello Jersey", response.getBody());

	}
}
