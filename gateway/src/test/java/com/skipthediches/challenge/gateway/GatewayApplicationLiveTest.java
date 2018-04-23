package com.skipthediches.challenge.gateway;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class GatewayApplicationLiveTest {

	private final static String TEST_URL = "http://localhost:8080";

	@Test
	public void accessSimplePageThatNOTNecessaryAutheticate() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();

		ResponseEntity<String> response = testRestTemplate
				.getForEntity(TEST_URL + "/withoutAuthentication.html", String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void accessSimplePageThatNECESSARYAutheticate() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();

		ResponseEntity<String> response = testRestTemplate
				.getForEntity(TEST_URL + "/home.html", String.class);

		Assert.assertEquals(HttpStatus.FOUND, response.getStatusCode());
		Assert.assertEquals("http://localhost:8080/login", response.getHeaders()
				.get("Location").get(0));

		HttpEntity<String> httpEntity = doLogin(TEST_URL);

		response = testRestTemplate.exchange(TEST_URL + "/home.html",
				HttpMethod.GET, httpEntity, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());

	}

	@Test
	public void accessServiceThatNOTNecessaryAutheticate() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();

		ResponseEntity<String> response = testRestTemplate
				.getForEntity(TEST_URL + "/challenge-service/products", String.class);

		response = testRestTemplate.exchange(TEST_URL + "/challenge-service/products",
				HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());

	}

	@Test
	public void accessServiceThatNECESSARYAutheticateButNotAuthenticated() {

		TestRestTemplate testRestTemplate = new TestRestTemplate();

		ResponseEntity<String> response = testRestTemplate
				.getForEntity(TEST_URL + "/challenge-service/customers", String.class);

		response = testRestTemplate.exchange(TEST_URL + "/challenge-service/customers",
				HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class);
		Assert.assertEquals(HttpStatus.FOUND, response.getStatusCode());
	}

	@Test
	public void accessServiceThatNECESSARYAutheticate() {

		HttpEntity<String> httpEntity = doLogin(TEST_URL);

		TestRestTemplate testRestTemplate = new TestRestTemplate();

		ResponseEntity<String> response = testRestTemplate
				.getForEntity(TEST_URL + "/challenge-service/customers", String.class);

		response = testRestTemplate.exchange(TEST_URL + "/challenge-service/customers",
				HttpMethod.GET, httpEntity, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertNotNull(response.getBody());

	}

	private HttpEntity<String> doLogin(String testUrl) {

		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("username", "user");
		form.add("password", "password");
		ResponseEntity<String> response = new TestRestTemplate()
				.postForEntity(testUrl + "/login", form, String.class);

		String sessionCookie = response.getHeaders().get("Set-Cookie")
				.get(0).split(";")[0];
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", sessionCookie);
		return new HttpEntity<>(headers);
	}


}
