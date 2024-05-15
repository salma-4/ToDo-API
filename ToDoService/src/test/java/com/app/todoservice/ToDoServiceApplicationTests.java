package com.app.todoservice;

import com.app.todoservice.model.items.ItemDetailsDTO;
import com.app.todoservice.model.items.ItemRequestDTO;
import com.app.todoservice.model.items.ItemResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ToDoServiceApplicationTests {

	private final String baseUrl = "http://localhost:8081/todo/item";

	private final RestTemplate restTemplate = new RestTemplate();

	@Test
	void contextLoads() {
	}

	@Test
	public void testFindByTitle() {
		String url = baseUrl + "/title/security";
		ResponseEntity<ItemResponseDTO> response = restTemplate.getForEntity(url, ItemResponseDTO.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetAllToDoItems() {
		String url = baseUrl + "/items";
		ResponseEntity<List<ItemResponseDTO>> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<ItemResponseDTO>>() {
				}
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void testFindById() {
		String url = baseUrl + "/id/1";
		ResponseEntity<ItemResponseDTO> response = restTemplate.getForEntity(url, ItemResponseDTO.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void testAddNewItem() {
		String url = baseUrl;

		ItemRequestDTO newItem = new ItemRequestDTO("New Item", new ItemDetailsDTO("description", LocalDateTime.now(), 1, true));
		String response = restTemplate.postForObject(url, newItem, String.class);
		assertNotNull(response);
		assertTrue(response.contains("added successfully"));
	}

	@Test
	void testUpdateItem() {
		String url = baseUrl + "/id/1/item";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse("2024-05-15T01:51:59", formatter);
		ItemRequestDTO updatedItem = new ItemRequestDTO(1, "security", new ItemDetailsDTO(2, "study math for 3 hours", dateTime, 1, true));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ItemRequestDTO> requestEntity = new HttpEntity<>(updatedItem, headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		String response = responseEntity.getBody();
		assertNotNull(response);
		assertTrue(response.contains("Item updated successfully"));
	}

	@Test
	void testDeleteItemById() {
		String url = baseUrl + "/id/4";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		restTemplate.delete(url);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testReactivateItem() {
		String url = baseUrl + "/id/1/activated";
		ResponseEntity<String> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				String.class
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void testDeactivateItemI() {
		String url = baseUrl + "/id/1/deactivated";
		ResponseEntity<String> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				String.class
		);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}